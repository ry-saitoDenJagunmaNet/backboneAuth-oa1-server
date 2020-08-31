package net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTranGrp;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * 取引グループ検索
 */
public interface BizTranGrpsRepository {
	/**
	 * 取引グループの条件検索を行います。
	 *
	 * @param bizTranGrpCriteria 取引グループの検索条件
	 * @param orders オーダー指定
	 * @return 取引グループ群
	 */
	BizTranGrps selectBy(BizTranGrpCriteria bizTranGrpCriteria, Orders orders);
	/**
	 * 取引グループの条件検索を行います。
	 *
	 * @param bizTranGrpCriteria 取引グループの検索条件
	 * @return 取引グループ群
	 */
	BizTranGrps selectBy(BizTranGrpCriteria bizTranGrpCriteria);

	/**
	 * 取引グループの全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return 取引グループ群
	 */
	BizTranGrps selectAll(Orders orders);
	/**
	 * 取引グループの全件検索を行います。
	 *
	 * @return 取引グループ群
	 */
	BizTranGrps selectAll();
}
