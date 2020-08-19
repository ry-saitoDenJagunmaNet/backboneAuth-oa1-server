package net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTran;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * 取引検索
 */
public interface BizTransRepository {
	/**
	 * 取引の条件検索を行います。
	 *
	 * @param bizTranCriteria 取引の検索条件
	 * @param orders オーダー指定
	 * @return 取引群
	 */
	BizTrans selectBy(BizTranCriteria bizTranCriteria, Orders orders);
	/**
	 * 取引の条件検索を行います。
	 *
	 * @param bizTranCriteria 取引の検索条件
	 * @return 取引群
	 */
	BizTrans selectBy(BizTranCriteria bizTranCriteria);

	/**
	 * 取引の全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return 取引群
	 */
	BizTrans selectAll(Orders orders);
	/**
	 * 取引の全件検索を行います。
	 *
	 * @return 取引群
	 */
	BizTrans selectAll();
}
