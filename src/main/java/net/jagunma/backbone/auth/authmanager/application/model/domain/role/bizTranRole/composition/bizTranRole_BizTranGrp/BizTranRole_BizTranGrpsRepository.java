package net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTranRole_BizTranGrp;

/**
 * 取引ロール_取引グループ割当検索
 */
public interface BizTranRole_BizTranGrpsRepository {
	/**
	 * 取引ロール_取引グループ割当の条件検索を行います。
	 *
	 * @param bizTranRole_BizTranGrpCriteria 取引ロール_取引グループ割当の検索条件
	 * @return 取引ロール_取引グループ割当群
	 */
	BizTranRole_BizTranGrps selectBy(BizTranRole_BizTranGrpCriteria bizTranRole_BizTranGrpCriteria);
	/**
	 * 取引ロール_取引グループ割当の全件検索を行います。
	 *
	 * @return 取引ロール_取引グループ割当群
	 */
	BizTranRole_BizTranGrps selectAll();
}
