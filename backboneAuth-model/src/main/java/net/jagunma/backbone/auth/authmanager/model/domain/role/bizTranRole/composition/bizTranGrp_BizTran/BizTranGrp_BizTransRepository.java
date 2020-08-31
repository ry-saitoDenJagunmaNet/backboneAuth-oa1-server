package net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTranGrp_BizTran;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * 取引グループ_取引割当検索
 */
public interface BizTranGrp_BizTransRepository {
	/**
	 * 取引グループ_取引割当の条件検索を行います。
	 *
	 * @param bizTranGrp_BizTranCriteria 取引グループ_取引割当の検索条件
	 * @param orders オーダー指定
	 * @return 取引グループ_取引割当群
	 */
	BizTranGrp_BizTrans selectBy(BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria, Orders orders);
	/**
	 * 取引グループ_取引割当の条件検索を行います。
	 *
	 * @param bizTranGrp_BizTranCriteria 取引グループ_取引割当の検索条件
	 * @return 取引グループ_取引割当群
	 */
	BizTranGrp_BizTrans selectBy(BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria);

	/**
	 * 取引グループ_取引割当の全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return 取引グループ_取引割当群
	 */
	BizTranGrp_BizTrans selectAll(Orders orders);
	/**
	 * 取引グループ_取引割当の全件検索を行います。
	 *
	 * @return 取引グループ_取引割当群
	 */
	BizTranGrp_BizTrans selectAll();
}
