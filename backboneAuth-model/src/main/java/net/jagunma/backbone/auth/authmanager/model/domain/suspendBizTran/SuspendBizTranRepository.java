package net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * 一時取引抑止検索
 */
public interface SuspendBizTranRepository {

    /**
     * 一時取引抑止の条件検索を行います
     *
     * @param suspendBizTranCriteria  一時取引抑止の検索条件
     * @return  一時取引抑止
     */
    SuspendBizTran findOneBy(SuspendBizTranCriteria suspendBizTranCriteria);
    /**
     * 一時取引抑止群の条件検索を行います
     *
     * @param suspendBizTranCriteria  一時取引抑止の検索条件
     * @param orders                  オーダー指定
     * @return  一時取引抑止群
     */
    SuspendBizTrans selectBy(SuspendBizTranCriteria suspendBizTranCriteria, Orders orders);
}
