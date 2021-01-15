package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleAllocateReference.SubSystemRoleAllocateCopyRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleAllocateReference.SubSystemRoleAllocateCopyResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;

/**
 * オペレーター_サブシステムロール割当コピーサービス
 */
@Service
public class CopySubSystemRoleAllocate {

    private final Operator_SubSystemRoleRepository operator_SubSystemRoleRepository;

    // コンストラクタ
    public CopySubSystemRoleAllocate(Operator_SubSystemRoleRepository operator_SubSystemRoleRepository) {
        this.operator_SubSystemRoleRepository = operator_SubSystemRoleRepository;
    }

    /**
     * オペレーター_サブシステムロール割当コピーします
     *
     * @param request  オペレーター_サブシステムロール割当コピーサービス Request
     * @param response オペレーター_サブシステムロール割当コピーサービス Response
     */
    public void execute(SubSystemRoleAllocateCopyRequest request, SubSystemRoleAllocateCopyResponse response) {

        // オペレーターID に null が含まれていたら処理しない
        if (request.getOperatorIdCriteria().getIncludes().contains(null)) {
            response.setOperator_SubSystemRoles(Operator_SubSystemRoles.createFrom(newArrayList()));
            return;
        }

        // オペレーター_サブシステムロール割当群を検索します
        Operator_SubSystemRoles operator_SubSystemRoles = searchOperator_SubSystemRoles(request);

        // オペレーター_サブシステムロール割当群をサインインオペレーターが付与可能なものだけコピー追加します
        Operator_SubSystemRoles CopyAddedOperator_SubSystemRoles = copyAdditionalOperator_SubSystemRoles(request, operator_SubSystemRoles);

        // Responseへセット
        response.setOperator_SubSystemRoles(CopyAddedOperator_SubSystemRoles);
    }

    /**
     * オペレーター_サブシステムロール割当群を検索します
     *
     * @param request  オペレーター_サブシステムロール割当コピーサービス Request
     * @return オペレーター_サブシステムロール割当群
     */
    Operator_SubSystemRoles searchOperator_SubSystemRoles(SubSystemRoleAllocateCopyRequest request) {
        Operator_SubSystemRoleCriteria criteria = new Operator_SubSystemRoleCriteria();
        criteria.getOperatorIdCriteria().assignFrom(request.getOperatorIdCriteria());
        return operator_SubSystemRoleRepository.selectBy(criteria, Orders.empty().addOrder("OperatorId"));
    }

    /**
     * オペレーター_サブシステムロール割当群をサインインオペレーターが付与可能なものだけコピー追加します
     *
     * @param request  オペレーター_サブシステムロール割当コピーサービス Request
     * @param operator_SubSystemRoles オペレーター_サブシステムロール割当群
     * @return オペレーター_サブシステムロール割当群
     */
    Operator_SubSystemRoles copyAdditionalOperator_SubSystemRoles(SubSystemRoleAllocateCopyRequest request, Operator_SubSystemRoles operator_SubSystemRoles) {
        Map<String, Operator_SubSystemRole> targetOperator_SubSystemRoleMap = new HashMap<>();
        Map<String, Operator_SubSystemRole> selectedOperator_SubSystemRoleMap = new HashMap<>();
        Map<String, Operator_SubSystemRole> signInOperator_SubSystemRoleMap = new HashMap<>();
        Map<String, Operator_SubSystemRole> additionalOperator_SubSystemRoleMap = new HashMap<>();

        // 振り分け（ターゲット，選択，サインイン）
        for (Operator_SubSystemRole operator_SubSystemRole : operator_SubSystemRoles.getValues()) {
            if (operator_SubSystemRole.getOperatorId().equals(request.getTargetOperatorId())) {
                targetOperator_SubSystemRoleMap.put(operator_SubSystemRole.getSubSystemRoleCode(), operator_SubSystemRole);
            }
            if (operator_SubSystemRole.getOperatorId().equals(request.getSelectedOperatorId())) {
                selectedOperator_SubSystemRoleMap.put(operator_SubSystemRole.getSubSystemRoleCode(), operator_SubSystemRole);
            }
            if (operator_SubSystemRole.getOperatorId().equals(request.getSignInOperatorId())) {
                signInOperator_SubSystemRoleMap.put(operator_SubSystemRole.getSubSystemRoleCode(), operator_SubSystemRole);
            }
        }

        // 選択をサインインで篩（ふるい）に掛ける（サインインにないものは除外）
        for(Map.Entry<String, Operator_SubSystemRole> selectedOperator_SubSystemRoleMapEntry : selectedOperator_SubSystemRoleMap.entrySet()) {
            if (signInOperator_SubSystemRoleMap.containsKey(selectedOperator_SubSystemRoleMapEntry.getKey())) {
                additionalOperator_SubSystemRoleMap.put(selectedOperator_SubSystemRoleMapEntry.getKey(), selectedOperator_SubSystemRoleMapEntry.getValue());
            }
        }

        // ターゲットに篩（ふるい）を掛けた（サインインにないものを除外）ものを追加（ターゲットがすでにもっているものは除外）
        for(Map.Entry<String, Operator_SubSystemRole> additionalOperator_SubSystemRoleMapEntry : additionalOperator_SubSystemRoleMap.entrySet()) {
            if (!targetOperator_SubSystemRoleMap.containsKey(additionalOperator_SubSystemRoleMapEntry.getKey())) {
                targetOperator_SubSystemRoleMap.put(additionalOperator_SubSystemRoleMapEntry.getKey(), additionalOperator_SubSystemRoleMapEntry.getValue());
            }
        }

        // Listに変換して返却
        List<Operator_SubSystemRole> newTargetOperator_SubSystemRoleList = new ArrayList<>(targetOperator_SubSystemRoleMap.values());
        return Operator_SubSystemRoles.createFrom(newTargetOperator_SubSystemRoleList);
    }
}
