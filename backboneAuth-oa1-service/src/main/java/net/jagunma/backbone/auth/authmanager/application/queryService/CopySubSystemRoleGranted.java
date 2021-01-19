package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAssignRoleDto;
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

        // アサインロールDtoリストにサインインオペレーターが付与可能なものだけコピー追加します
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
        return operator_SubSystemRoleRepository.selectBy(criteria, Orders.empty().addOrder("Operator_SubSystemRoleId"));
    }

    /**
     * アサインロールDtoリストにサインインオペレーターが付与可能なものだけコピー追加します
     *
     * @param currentAssignRoleList 現在のアサインロールリスト
     * @param signInOperator_SubSystemRoles サインインオペレーターのオペレーター_サブシステムロール割当群
     * @param selectedOperator_SubSystemRoles サインインオペレーターのオペレーター_サブシステムロール割当群
     * @return コピー追加後のアサインロールDtoリスト
     */
    List<SubSystemRoleGrantedAssignRoleDto> copyAddAssignRoleDtoList(List<SubSystemRoleGrantedCopyRequestAssignRole> currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_SubSystemRoles selectedOperator_SubSystemRoles) {

        // 現在のアサインロールリストのコードをList化
        List<String> currentAssignRoleCodeList = newArrayList();
        for (SubSystemRoleGrantedCopyRequestAssignRole currentAssignRole : currentAssignRoleList) {
            currentAssignRoleCodeList.add(currentAssignRole.getSubSystemRole().getCode());
        }
        // サインインオペレーターのオペレーター_サブシステムロール割当群をコードをキーにしてMap化
        Map<String, Operator_SubSystemRole> signInOperator_SubSystemRoleMap = new HashMap<>();
        for (Operator_SubSystemRole operator_SubSystemRole : signInOperator_SubSystemRoles.getValues()) {
            signInOperator_SubSystemRoleMap.put(operator_SubSystemRole.getSubSystemRoleCode(), operator_SubSystemRole);
        }

        // 追加するサブシステムロールリストを作成）選択をサインインで篩（ふるい）に掛ける（サインインにないものを除外する）
        List<Operator_SubSystemRole> additionalOperator_SubSystemRoleList = newArrayList();
        for(Operator_SubSystemRole selectedOperator_SubSystemRole : selectedOperator_SubSystemRoles.getValues()) {
            if (signInOperator_SubSystemRoleMap.containsKey(selectedOperator_SubSystemRole.getSubSystemRoleCode())) {
                additionalOperator_SubSystemRoleList.add(selectedOperator_SubSystemRole);
            }
        }

        // 現在のアサインサブシステムロールリストをDtoリスト化
        List<SubSystemRoleGrantedAssignRoleDto> copyAddedAssignRoleDtoList = newArrayList();
        for (SubSystemRoleGrantedCopyRequestAssignRole currentAssignRole : currentAssignRoleList) {
            SubSystemRoleGrantedAssignRoleDto copyAddedAssignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            copyAddedAssignRoleDto.setOperator_SubSystemRole(Operator_SubSystemRole.createFrom(
                null,
                null,
                currentAssignRole.getSubSystemRole().getCode(),
                currentAssignRole.getValidThruStartDate(),
                currentAssignRole.getValidThruEndDate(),
                null,
                null,
                currentAssignRole.getSubSystemRole()));
            copyAddedAssignRoleDto.setIsModifiable(currentAssignRole.getIsModifiable());
            copyAddedAssignRoleDtoList.add(copyAddedAssignRoleDto);
        }

        // アサインサブシステムロールリストに追加（既に持っているロールは除外）
        for(Operator_SubSystemRole additionalOperator_SubSystemRole : additionalOperator_SubSystemRoleList) {
            if (!currentAssignRoleCodeList.contains(additionalOperator_SubSystemRole.getSubSystemRoleCode())) {
                SubSystemRoleGrantedAssignRoleDto copyAddedAssignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
                copyAddedAssignRoleDto.setOperator_SubSystemRole(Operator_SubSystemRole.createFrom(
                    additionalOperator_SubSystemRole.getOperator_SubSystemRoleId(),
                    additionalOperator_SubSystemRole.getOperatorId(),
                    additionalOperator_SubSystemRole.getSubSystemRoleCode(),
                    additionalOperator_SubSystemRole.getValidThruStartDate(),
                    additionalOperator_SubSystemRole.getValidThruEndDate(),
                    additionalOperator_SubSystemRole.getRecordVersion(),
                    additionalOperator_SubSystemRole.getOperator(),
                    additionalOperator_SubSystemRole.getSubSystemRole()));
                copyAddedAssignRoleDtoList.add(copyAddedAssignRoleDto);
            }
        }

        return copyAddedAssignRoleDtoList;
//         現在の割当対象サブシステムロールリストと追加するサブシステムロールリストで

//        // ターゲットオペレーターのオペレーター_サブシステムロール割当群をコードをキーにしてMap化
//        Map<String, Operator_SubSystemRole> targetOperator_SubSystemRoleMap = new HashMap<>();
//        for (Operator_SubSystemRole operator_SubSystemRole : targetOperator_SubSystemRoles.getValues()) {
//            targetOperator_SubSystemRoleMap.put(operator_SubSystemRole.getSubSystemRoleCode(), operator_SubSystemRole);
//        }

//        // サインインオペレーターのオペレーター_サブシステムロール割当群をコードをキーにしてMap化
//        Map<String, Operator_SubSystemRole> signInOperator_SubSystemRoleMap = new HashMap<>();
//        for (Operator_SubSystemRole operator_SubSystemRole : signInOperator_SubSystemRoles.getValues()) {
//            signInOperator_SubSystemRoleMap.put(operator_SubSystemRole.getSubSystemRoleCode(), operator_SubSystemRole);
//        }

//        // 選択オペレーターのオペレーター_サブシステムロール割当群をコードをキーにしてMap化
//        Map<String, Operator_SubSystemRole> selectedOperator_SubSystemRoleMap = new HashMap<>();
//        for (Operator_SubSystemRole operator_SubSystemRole : selectedOperator_SubSystemRoles.getValues()) {
//            selectedOperator_SubSystemRoleMap.put(operator_SubSystemRole.getSubSystemRoleCode(), operator_SubSystemRole);
//        }

//        // 追加するサブシステムロール）選択をサインインで篩（ふるい）に掛ける（サインインにないものを除外する）
//        Map<String, Operator_SubSystemRole> additionalOperator_SubSystemRoleMap = new HashMap<>();
//        for(Map.Entry<String, Operator_SubSystemRole> selectedOperator_SubSystemRoleMapEntry : selectedOperator_SubSystemRoleMap.entrySet()) {
//            if (signInOperator_SubSystemRoleMap.containsKey(selectedOperator_SubSystemRoleMapEntry.getKey())) {
//                additionalOperator_SubSystemRoleMap.put(selectedOperator_SubSystemRoleMapEntry.getKey(), selectedOperator_SubSystemRoleMapEntry.getValue());
//            }
//        }

//        // 追加するサブシステムロールをターゲットに追加（ターゲットがすでにもっているものは除外）
//        for(Map.Entry<String, Operator_SubSystemRole> additionalOperator_SubSystemRoleMapEntry : additionalOperator_SubSystemRoleMap.entrySet()) {
//            if (!targetOperator_SubSystemRoleMap.containsKey(additionalOperator_SubSystemRoleMapEntry.getKey())) {
//                targetOperator_SubSystemRoleMap.put(additionalOperator_SubSystemRoleMapEntry.getKey(), additionalOperator_SubSystemRoleMapEntry.getValue());
//            }
//        }

//        // Listに変換して返却
//        return Operator_SubSystemRoles.createFrom(new ArrayList<>(targetOperator_SubSystemRoleMap.values()));
    }
}
