package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantCommand.SubSystemRoleGrantRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantCommand.SubSystemRoleGrantRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * サブシステムロール付与サービス
 */
@Service
@Transactional
public class GrantSubSystemRole {

    private final Operator_SubSystemRoleRepositoryForStore operator_SubSystemRoleRepositoryForStore;

    public GrantSubSystemRole(Operator_SubSystemRoleRepositoryForStore operator_SubSystemRoleRepositoryForStore) {
        this.operator_SubSystemRoleRepositoryForStore = operator_SubSystemRoleRepositoryForStore;
    }

    /**
     * サブシステムロールの付与を行います
     *
     * @param request サブシステムロール付与サービス Request
     */
    public void execute(SubSystemRoleGrantRequest request) {

        // パラメーターの検証
        GrantSubSystemRoleValidator.with(request).validate();

        // オペレーター_サブシステムロール割当群の生成を行います
        Operator_SubSystemRoles operator_SubSystemRoles = createOperator_SubSystemRoles(request);

        // オペレーター_サブシステムロール割当群の登録を行います
        operator_SubSystemRoleRepositoryForStore.store(operator_SubSystemRoles, request.getChangeCause());

    }

    /**
     * オペレーター_サブシステムロール割当群の生成を行います
     *
     * @param request サブシステムロール付与サービス Request
     * @return オペレーター_サブシステムロール割当群
     */
    Operator_SubSystemRoles createOperator_SubSystemRoles(SubSystemRoleGrantRequest request) {
        List<SubSystemRoleGrantRequestAssignRole> assignRoleList = request.getAssignRoleList();

        assignRoleList = assignRoleList.stream().sorted(Orders.empty().addOrder("subSystemRole.displaySortOrder").toComparator()).collect(Collectors.toList());

        List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList();
        for (SubSystemRoleGrantRequestAssignRole assignRole : assignRoleList) {

            Operator_SubSystemRole operator_SubSystemRole = Operator_SubSystemRole.createFrom(
                null,
                request.getOperatorId(),
                assignRole.getSubSystemRole().getCode(),
                assignRole.getValidThruStartDate(),
                assignRole.getValidThruEndDate(),
                null,
                null,
                assignRole.getSubSystemRole());

            operator_SubSystemRoleList.add(operator_SubSystemRole);
        }

        return Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);
    }
}
