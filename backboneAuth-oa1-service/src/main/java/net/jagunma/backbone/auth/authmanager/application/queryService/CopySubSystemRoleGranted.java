package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.util.SubSystemRoleGrantedQueryUtil;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedCopyRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedCopyRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedCopyResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;

/**
 * サブシステムロール付与コピーサービス
 */
@Service
public class CopySubSystemRoleGranted {

    private final Operator_SubSystemRoleRepository operator_SubSystemRoleRepository;
    private final SubSystemRoleGrantedQueryUtil subSystemRoleGrantedQueryUtil = new SubSystemRoleGrantedQueryUtil();

    // コンストラクタ
    public CopySubSystemRoleGranted(Operator_SubSystemRoleRepository operator_SubSystemRoleRepository) {
        this.operator_SubSystemRoleRepository = operator_SubSystemRoleRepository;
    }

    /**
     * サブシステムロール付与コピーします
     *
     * @param request  サブシステムロール付与コピーサービス Request
     * @param response サブシステムロール付与コピーサービス Response
     */
    public void execute(SubSystemRoleGrantedCopyRequest request, SubSystemRoleGrantedCopyResponse response) {

        // パラメーターの検証
        CopySubSystemRoleGrantedValidator.with(request).validate();

        // オペレーター_サブシステムロール割当群を検索します（サインインオペレーター）
        Operator_SubSystemRoles signInOperator_SubSystemRoles = searchOperator_SubSystemRoles(request.getSignInOperatorId());

        // オペレーター_サブシステムロール割当群を検索します（選択オペレーター）
        Operator_SubSystemRoles selectedOperator_SubSystemRoles = searchOperator_SubSystemRoles(request.getSelectedOperatorId());

        // アサインロールDtoリストにサインインオペレーターが操作可能な選択オペレーターのオペレーター_サブシステムロールをコピー追加します
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_SubSystemRoles);

        // Responseへセット
        response.setAssignRoleDtoList(assignRoleDtoList);
    }

    /**
     * オペレーター_サブシステムロール割当群を検索します
     *
     * @param operatorId オペレーターID
     * @return オペレーター_サブシステムロール割当群
     */
    Operator_SubSystemRoles searchOperator_SubSystemRoles(Long operatorId) {
        Operator_SubSystemRoleCriteria criteria = new Operator_SubSystemRoleCriteria();
        criteria.getOperatorIdCriteria().setEqualTo(operatorId);

        List<Operator_SubSystemRole> operator_SubSystemRoleList = operator_SubSystemRoleRepository.selectBy(criteria, Orders.empty()).getValues();
        operator_SubSystemRoleList = operator_SubSystemRoleList.stream().sorted(Orders.empty().addOrder("subSystemRole.displaySortOrder").toComparator()).collect(Collectors.toList());
        return Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);
    }

    /**
     * アサインロールDtoリストにサインインオペレーターが操作可能な選択オペレーターのオペレーター_サブシステムロールをコピー追加します
     *
     * @param currentAssignRoleList 現在のアサインロールリスト
     * @param signInOperator_SubSystemRoles サインインオペレーターのオペレーター_サブシステムロール割当群
     * @param selectedOperator_SubSystemRoles 選択オペレーターのオペレーター_サブシステムロール割当群
     * @return コピー追加後のアサインロールDtoリスト
     */
    List<SubSystemRoleGrantedAssignRoleDto> copyAddAssignRoleDtoList(List<SubSystemRoleGrantedCopyRequestAssignRole> currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_SubSystemRoles selectedOperator_SubSystemRoles) {

        // 現在のアサインロールリストをアサインロールDtoリスト化（返却の型にコンバート）
        List<SubSystemRoleGrantedAssignRoleDto> copyAddedAssignRoleDtoList = newArrayList();
        for (SubSystemRoleGrantedCopyRequestAssignRole currentAssignRole : currentAssignRoleList) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(Operator_SubSystemRole.createFrom(
                null,
                null,
                currentAssignRole.getSubSystemRole().getCode(),
                currentAssignRole.getValidThruStartDate(),
                currentAssignRole.getValidThruEndDate(),
                null,
                null,
                currentAssignRole.getSubSystemRole()));
            assignRoleDto.setIsModifiable(currentAssignRole.getIsModifiable());
            copyAddedAssignRoleDtoList.add(assignRoleDto);
        }

        // 現在のアサインロールリストのコードをList化（追加除外判定に使用する為）
        List<String> currentAssignRoleCodeList = newArrayList();
        for (SubSystemRoleGrantedCopyRequestAssignRole currentAssignRole : currentAssignRoleList) {
            currentAssignRoleCodeList.add(currentAssignRole.getSubSystemRole().getCode());
        }

        // アサインロールDtoリストに追加
        for(Operator_SubSystemRole operator_SubSystemRole : selectedOperator_SubSystemRoles.getValues()) {
            // サインインオペレーターによる変更可否を判定
            if (!subSystemRoleGrantedQueryUtil.judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles)) {
                continue;
            }
            // 現在のアサインロールリストに存在するロールは除外
            if (currentAssignRoleCodeList.contains(operator_SubSystemRole.getSubSystemRoleCode())) {
                continue;
            }

            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(Operator_SubSystemRole.createFrom(
                operator_SubSystemRole.getOperator_SubSystemRoleId(),
                operator_SubSystemRole.getOperatorId(),
                operator_SubSystemRole.getSubSystemRoleCode(),
                operator_SubSystemRole.getValidThruStartDate(),
                operator_SubSystemRole.getValidThruEndDate(),
                operator_SubSystemRole.getRecordVersion(),
                operator_SubSystemRole.getOperator(),
                operator_SubSystemRole.getSubSystemRole()));
            assignRoleDto.setIsModifiable(true);

            copyAddedAssignRoleDtoList.add(assignRoleDto);
        }

        return copyAddedAssignRoleDtoList;
    }
}
