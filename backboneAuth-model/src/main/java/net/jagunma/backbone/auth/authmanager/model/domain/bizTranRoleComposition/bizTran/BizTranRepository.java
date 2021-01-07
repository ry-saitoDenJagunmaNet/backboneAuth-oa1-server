package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * 取引検索
 */
public interface BizTranRepository {

    /**
     * 取引の検索を行います
     *
     * @param bizTranCode 取引コード
     * @return 取引
     */
    BizTran findOneByCode(String bizTranCode);
    /**
     * 取引群の検索を行います
     *
     * @param bizTranCriteria 取引の検索条件
     * @param orders          オーダー指定
     * @return 取引群
     */
    BizTrans selectBy(BizTranCriteria bizTranCriteria, Orders orders);
    /**
     * 取引群の全件検索を行います
     *
     * @param orders オーダー指定
     * @return 取引ロール群
     */
    BizTrans selectAll(Orders orders);
}
