package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * 取引グループ検索
 */
public interface BizTranGrpRepository {

    /**
     * 取引グループの検索を行います
     *
     * @param bizTranGrpCode 取引グループコード
     * @return 取引グループ群
     */
    BizTranGrp findOneByCode(String bizTranGrpCode);
    /**
     * 取引グループ群の条件検索を行います
     *
     * @param bizTranGrpCriteria 取引グループの検索条件
     * @param orders             オーダー指定
     * @return 取引グループ群
     */
    BizTranGrps selectBy(BizTranGrpCriteria bizTranGrpCriteria, Orders orders);
    /**
     * 取引グループ群の全件検索を行います
     *
     * @param orders オーダー指定
     * @return 取引グループ群
     */
    BizTranGrps selectAll(Orders orders);
}
