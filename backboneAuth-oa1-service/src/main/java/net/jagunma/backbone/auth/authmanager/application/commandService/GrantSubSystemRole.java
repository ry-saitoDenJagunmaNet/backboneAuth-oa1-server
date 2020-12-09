package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleCommand.AllocateSubSystemRole;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleCommand.SubSystemRoleGrantRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * サブシステムロール付与サービス
 */
@Service
@Transactional
public class GrantSubSystemRole {

    private final Operator_SubSystemRoleRepositoryForStore operator_SubSystemRoleRepositoryForStore;

    public GrantSubSystemRole( Operator_SubSystemRoleRepositoryForStore operator_SubSystemRoleRepositoryForStore) {
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
        operator_SubSystemRoleRepositoryForStore.store(operator_SubSystemRoles);

    }

    /**
     * オペレーター_サブシステムロール割当群の生成を行います
     *
     * @param request サブシステムロール付与サービス Request
     * @return オペレーター_サブシステムロール割当群
     */
    Operator_SubSystemRoles createOperator_SubSystemRoles(SubSystemRoleGrantRequest request) {
        List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList();

        List<AllocateSubSystemRole> allocateSubSystemRoleList = request.getAllocateSubSystemRoleList();

        for (AllocateSubSystemRole allocateSubSystemRole : allocateSubSystemRoleList) {

            Operator_SubSystemRole operator_SubSystemRole = Operator_SubSystemRole.createFrom(
                null,
                request.getOperatorId(),
                allocateSubSystemRole.getSubSystemRole().getCode(),
                allocateSubSystemRole.getValidThruStartDate(),
                allocateSubSystemRole.getValidThruEndDate(),
                null,
                null,
                allocateSubSystemRole.getSubSystemRole()
                );

            operator_SubSystemRoleList.add(operator_SubSystemRole);
        }

        return Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);
    }
}
