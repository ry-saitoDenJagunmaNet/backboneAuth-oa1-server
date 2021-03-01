package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.BizTranRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.util.BizTranRoleGrantedQueryUtil;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedCopyRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedCopyRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedCopyResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;

/**
 * 取引ロール付与コピーサービス
 */
@Service
public class CopyBizTranRoleGranted {

    private final Operator_BizTranRoleRepository operator_BizTranRoleRepository;
    private final Operator_SubSystemRoleRepository operator_SubSystemRoleRepository;
    private final BizTranRoleGrantedQueryUtil bizTranRoleGrantedQueryUtil = new BizTranRoleGrantedQueryUtil();

    // コンストラクタ
    public CopyBizTranRoleGranted(Operator_BizTranRoleRepository operator_BizTranRoleRepository, Operator_SubSystemRoleRepository operator_SubSystemRoleRepository) {
        this.operator_BizTranRoleRepository = operator_BizTranRoleRepository;
        this.operator_SubSystemRoleRepository = operator_SubSystemRoleRepository;
    }

    /**
     * 取引ロール付与コピーします
     *
     * @param request  取引ロール付与コピーサービス Request
     * @param response 取引ロール付与コピーサービス Response
     */
    public void execute(BizTranRoleGrantedCopyRequest request, BizTranRoleGrantedCopyResponse response) {

        // パラメーターの検証
        CopyBizTranRoleGrantedValidator.with(request).validate();

        // オペレーター_サブシステムロール割当群を検索します（サインインオペレーター）
        Operator_SubSystemRoles signInOperator_SubSystemRoles = searchOperator_SubSystemRoles(request.getSignInOperatorId());

        // オペレーター_取引ロール割当群を検索します（選択オペレーター）
        Operator_BizTranRoles selectedOperator_BizTranRoles = searchOperator_BizTranRoles(request.getSelectedOperatorId());

        // アサインロールDtoリストにサインインオペレーターが操作可能な選択オペレーターのオペレーター_取引ロールをコピー追加します
        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_BizTranRoles);

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
     * オペレーター_取引ロール割当群を検索します
     *
     * @param operatorId オペレーターID
     * @return オペレーター_取引ロール割当群
     */
    Operator_BizTranRoles searchOperator_BizTranRoles(Long operatorId) {
        Operator_BizTranRoleCriteria criteria = new Operator_BizTranRoleCriteria();
        criteria.getOperatorIdCriteria().setEqualTo(operatorId);

        List<Operator_BizTranRole> operator_BizTranRoleList = operator_BizTranRoleRepository.selectBy(criteria, Orders.empty()).getValues();
        operator_BizTranRoleList = operator_BizTranRoleList.stream().sorted(Orders.empty()
            .addOrder("bizTranRole.subSystem.displaySortOrder")
            .addOrder("bizTranRole.bizTranRoleCode")
            .toComparator()).collect(Collectors.toList());
        return Operator_BizTranRoles.createFrom(operator_BizTranRoleList);
    }

    /**
     * アサインロールDtoリストにサインインオペレーターが操作可能な選択オペレーターのオペレーター_取引ロールをコピー追加します
     *
     * @param currentAssignRoleList 現在のアサインロールリスト
     * @param signInOperator_SubSystemRoles サインインオペレーターのオペレーター_サブシステムロール割当群
     * @param selectedOperator_BizTranRoles 選択オペレーターのオペレーター_取引ロール割当群
     * @return コピー追加後のアサインロールDtoリスト
     */
    List<BizTranRoleGrantedAssignRoleDto> copyAddAssignRoleDtoList(List<BizTranRoleGrantedCopyRequestAssignRole> currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_BizTranRoles selectedOperator_BizTranRoles) {

        // 現在のアサインロールリストをアサインロールDtoリスト化（返却の型にコンバート）
        List<BizTranRoleGrantedAssignRoleDto> copyAddedAssignRoleDtoList = newArrayList();
        for (BizTranRoleGrantedCopyRequestAssignRole currentAssignRole : currentAssignRoleList) {
            BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_BizTranRole(Operator_BizTranRole.createFrom(
                null,
                null,
                currentAssignRole.getBizTranRole().getBizTranRoleId(),
                currentAssignRole.getValidThruStartDate(),
                currentAssignRole.getValidThruEndDate(),
                null,
                null,
                currentAssignRole.getBizTranRole()));
            assignRoleDto.setIsModifiable(currentAssignRole.getIsModifiable());
            copyAddedAssignRoleDtoList.add(assignRoleDto);
        }

        // 現在のアサインロールリストのコードをList化（追加除外判定に使用する為）
        List<String> currentAssignRoleCodeList = newArrayList();
        for (BizTranRoleGrantedCopyRequestAssignRole currentAssignRole : currentAssignRoleList) {
            currentAssignRoleCodeList.add(currentAssignRole.getBizTranRole().getBizTranRoleCode());
        }

        // アサインロールDtoリストに追加
        for(Operator_BizTranRole operator_BizTranRole : selectedOperator_BizTranRoles.getValues()) {
            // サインインオペレーターによる変更可否を判定
            if (!bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles)) {
                continue;
            }
            // 現在のアサインロールリストに存在するロールは除外
            if (currentAssignRoleCodeList.contains(operator_BizTranRole.getBizTranRole().getBizTranRoleCode())) {
                continue;
            }

            BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_BizTranRole(Operator_BizTranRole.createFrom(
                operator_BizTranRole.getOperator_BizTranRoleId(),
                operator_BizTranRole.getOperatorId(),
                operator_BizTranRole.getBizTranRole().getBizTranRoleId(),
                operator_BizTranRole.getValidThruStartDate(),
                operator_BizTranRole.getValidThruEndDate(),
                operator_BizTranRole.getRecordVersion(),
                operator_BizTranRole.getOperator(),
                operator_BizTranRole.getBizTranRole()));
            assignRoleDto.setIsModifiable(true);

            copyAddedAssignRoleDtoList.add(assignRoleDto);
        }

        return copyAddedAssignRoleDtoList;
    }
}
