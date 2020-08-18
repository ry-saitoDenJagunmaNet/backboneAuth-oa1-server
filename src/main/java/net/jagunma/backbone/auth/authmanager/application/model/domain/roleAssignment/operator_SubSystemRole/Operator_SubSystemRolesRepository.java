package net.jagunma.backbone.auth.authmanager.application.model.domain.roleAssignment.operator_SubSystemRole;

/**
 * オペレーターサブシステムロール検索
 */
public interface Operator_SubSystemRolesRepository {
	/**
	 * オペレーター_サブシステムロール割当の条件検索を行います。
	 *
	 * @param operator_SubSystemRoleCriteria オペレーター_サブシステムロール割当の検索条件
	 * @return オペレーター_サブシステムロール割当群
	 */
	Operator_SubSystemRoles selectBy(Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria);
	/**
	 * オペレーター_サブシステムロール割当群の全件検索を行います。
	 *
	 * @return オペレーター_サブシステムロール割当群
	 */
	Operator_SubSystemRoles selectAll();
}
