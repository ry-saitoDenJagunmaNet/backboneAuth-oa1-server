package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantCommand.BizTranRoleGrantRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantCommand.BizTranRoleGrantRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 取引ロール付与サービス
 */
@Service
@Transactional
public class GrantBizTranRole {

    private final Operator_BizTranRoleRepositoryForStore operator_BizTranRoleRepositoryForStore;

    public GrantBizTranRole(Operator_BizTranRoleRepositoryForStore operator_BizTranRoleRepositoryForStore) {
        this.operator_BizTranRoleRepositoryForStore = operator_BizTranRoleRepositoryForStore;
    }

    /**
     * 取引ロールの付与を行います
     *
     * @param request 取引ロール付与サービス Request
     */
    public void execute(BizTranRoleGrantRequest request) {

        // パラメーターの検証
        GrantBizTranRoleValidator.with(request).validate();

        // オペレーター_取引ロール割当群の生成を行います
        Operator_BizTranRoles operator_BizTranRoles = createOperator_BizTranRoles(request);

        // オペレーター_取引ロール割当群の登録を行います
        operator_BizTranRoleRepositoryForStore.store(operator_BizTranRoles, request.getChangeCause());

    }

    /**
     * オペレーター_取引ロール割当群の生成を行います
     *
     * @param request 取引ロール付与サービス Request
     * @return オペレーター_取引ロール割当群
     */
    Operator_BizTranRoles createOperator_BizTranRoles(BizTranRoleGrantRequest request) {
        List<BizTranRoleGrantRequestAssignRole> assignRoleList = request.getAssignRoleList();

        assignRoleList = assignRoleList.stream().sorted(Orders.empty()
            .addOrder("bizTranRole.subSystem.displaySortOrder")
            .addOrder("bizTranRole.bizTranRoleCode")
            .toComparator()).collect(Collectors.toList());

        List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList();
        for (BizTranRoleGrantRequestAssignRole assignRole : assignRoleList) {

            Operator_BizTranRole operator_BizTranRole = Operator_BizTranRole.createFrom(
                null,
                request.getOperatorId(),
                assignRole.getBizTranRole().getBizTranRoleId(),
                assignRole.getValidThruStartDate(),
                assignRole.getValidThruEndDate(),
                null,
                null,
                assignRole.getBizTranRole());

            operator_BizTranRoleList.add(operator_BizTranRole);
        }

        return Operator_BizTranRoles.createFrom(operator_BizTranRoleList);
    }
}
