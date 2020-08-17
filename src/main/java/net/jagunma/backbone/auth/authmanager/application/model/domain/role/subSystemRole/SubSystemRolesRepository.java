package net.jagunma.backbone.auth.authmanager.application.model.domain.role.subSystemRole;

/**
 * サブシステムロール検索
 */
public interface SubSystemRolesRepository {
	/**
	 * サブシステムロール割当群を全件検索します。
	 * @return サブシステムロール割当群
	 */
	SubSystemRoles selectAll();
}
