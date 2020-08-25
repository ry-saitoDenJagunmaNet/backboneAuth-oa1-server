package net.jagunma.backbone.auth.authmanager.application.model.domain.role.subSystemRole;

/**
 * サブシステムロール検索
 */
public interface SubSystemRolesRepository {
	/**
	 * サブシステムロールの条件検索を行います。
	 *
	 * @param subSystemRoleCriteria サブシステムロールの検索条件
	 * @return サブシステムロール群
	 */
//	SubSystemRoles selectBy(SubSystemRoleCriteria subSystemRoleCriteria);
	/**
	 * サブシステムロール割当群を全件検索します。
	 *
	 * @return サブシステムロール割当群
	 */
	SubSystemRoles selectAll();
}
