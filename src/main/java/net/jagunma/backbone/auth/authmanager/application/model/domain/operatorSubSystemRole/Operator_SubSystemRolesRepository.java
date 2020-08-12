package net.jagunma.backbone.auth.authmanager.application.model.domain.operatorSubSystemRole;

/**
 * オペレーターサブシステムロール検索
 */
public interface Operator_SubSystemRolesRepository {
	/**
	 * オペレーター_サブシステムロール割当群を全件検索します。
	 * @return オペレーター_サブシステムロール割当群
	 */
	Operator_SubSystemRoles selectAll();
}
