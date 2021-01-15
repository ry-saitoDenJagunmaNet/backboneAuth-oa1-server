package net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * オペレーター_取引ロール割当検索
 */
public interface Operator_BizTranRoleRepository {

    /**
     * オペレーター_取引ロール割当群の条件検索を行います
     *
     * @param operator_BizTranRoleCriteria オペレーター_取引ロール割当の検索条件
     * @param orders                       オーダー指定
     * @return オペレーター_取引ロール割当群
     */
    Operator_BizTranRoles selectBy(Operator_BizTranRoleCriteria operator_BizTranRoleCriteria, Orders orders);
}
