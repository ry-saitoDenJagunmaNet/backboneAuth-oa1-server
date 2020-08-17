package net.jagunma.backbone.auth.authmanager.application.model.domain.operator.roleAssignment.operator_SubSystemRole;

/**
 * オオペレーター_サブシステムロール割当検索
 */
public interface Operator_SubSystemRoleRepository {
	/**
	 * オペレーター_サブシステムロール割当の条件検索を行います。
	 * @param operatorSubSystemRoleCriteria オペレーター_サブシステムロール割当の検索条件
	 * @return 取引ロール
	 */
	Operator_SubSystemRole findOneBy(Operator_SubSystemRoleCriteria operatorSubSystemRoleCriteria);
}
