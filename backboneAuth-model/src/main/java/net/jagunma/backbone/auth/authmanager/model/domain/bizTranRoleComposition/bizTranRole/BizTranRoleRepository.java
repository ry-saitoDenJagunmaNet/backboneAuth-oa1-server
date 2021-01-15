package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * 取引ロール検索
 */
public interface BizTranRoleRepository {

    /**
     * 取引ロール群の条件検索を行います
     *
     * @param bizTranRoleCriteria 取引ロールの検索条件
     * @param orders              オーダー指定
     * @return 取引ロール群
     */
    BizTranRoles selectBy(BizTranRoleCriteria bizTranRoleCriteria, Orders orders);
    /**
     * 取引ロール群の全件検索を行います
     *
     * @param orders オーダー指定
     * @return 取引ロール群
     */
    BizTranRoles selectAll(Orders orders);
}
