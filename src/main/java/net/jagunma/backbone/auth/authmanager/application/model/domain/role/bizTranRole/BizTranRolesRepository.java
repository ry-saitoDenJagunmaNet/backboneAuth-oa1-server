package net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole;

/**
 * 取引ロール検索
 */
public interface BizTranRolesRepository {
	/**
	 * 取引ロールの条件検索を行います。
	 * @param bizTranRoleCriteria 取引ロールの検索条件
	 * @return 取引ロール群
	 */
	BizTranRoles selectBy(BizTranRoleCriteria bizTranRoleCriteria);
	/**
	 * 取引ロールの全件検索を行います。
	 * @return 取引ロール群
	 */
	BizTranRoles selectAll();
}
