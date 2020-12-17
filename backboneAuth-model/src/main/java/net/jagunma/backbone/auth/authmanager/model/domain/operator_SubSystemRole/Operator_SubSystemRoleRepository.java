package net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * オペレーター_サブシステムロール割当検索
 */
public interface Operator_SubSystemRoleRepository {

    /**
     * オペレーター_サブシステムロール割当群の条件検索を行います
     *
     * @param operator_SubSystemRoleCriteria オペレーター_サブシステムロール割当の検索条件
     * @param orders                         オーダー指定
     * @return オペレーター_サブシステムロール割当群
     */
    Operator_SubSystemRoles selectBy(Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria, Orders orders);
}
