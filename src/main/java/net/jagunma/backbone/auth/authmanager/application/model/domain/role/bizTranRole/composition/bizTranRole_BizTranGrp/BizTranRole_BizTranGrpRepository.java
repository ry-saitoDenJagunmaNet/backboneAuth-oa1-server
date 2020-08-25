package net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTranRole_BizTranGrp;

/**
 * 取引ロール_取引グループ割当検索
 */
public interface BizTranRole_BizTranGrpRepository {
	/**
	 * 取引ロール_取引グループ割当の条件検索を行います。
	 *
	 * @param bizTranRole_BizTranGrpCriteria 取引ロール_取引グループ割当の検索条件
	 * @return 取引ロール_取引グループ割当
	 */
	BizTranRole_BizTranGrp findOneBy(BizTranRole_BizTranGrpCriteria bizTranRole_BizTranGrpCriteria);
}
