package net.jagunma.backbone.auth.authmanager.model.domain.bizTran;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * 取引群検索
 */
public interface BizTransRepository {

    /**
     * 取引群の条件検索を行います。
     *
     * @param bizTranCriteria 取引の検索条件
     * @param orders          オーダー指定
     * @return 取引群
     */
    BizTrans selectBy(BizTranCriteria bizTranCriteria, Orders orders);
    /**
     * 取引群の全件検索を行います。
     *
     * @param orders オーダー指定
     * @return 取引ロール群
     */
    BizTrans selectAll(Orders orders);
}
