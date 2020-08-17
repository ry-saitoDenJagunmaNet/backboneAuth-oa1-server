package net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole;

/**
 * 取引ロール検索
 */
public interface BizTranRoleRepository {
	/**
	 * 取引ロールの条件検索を行います。
	 * @param bizTranRoleCriteria 取引ロールの検索条件
	 * @return 取引ロール
	 */
	BizTranRole findOneBy(BizTranRoleCriteria bizTranRoleCriteria);
}
