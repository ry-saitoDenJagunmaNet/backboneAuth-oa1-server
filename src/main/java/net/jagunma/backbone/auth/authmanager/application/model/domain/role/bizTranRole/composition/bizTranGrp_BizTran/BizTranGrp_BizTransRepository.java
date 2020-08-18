package net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTranGrp_BizTran;

/**
 * 取引グループ_取引割当検索
 */
public interface BizTranGrp_BizTransRepository {
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
	 * @return 取引グループ_取引割当群
	 */
	BizTranGrp_BizTrans selectAll();
}
