package net.jagunma.backbone.auth.authmanager.application.model.domain.operator.roleAssignment.operator_SubSystemRole;

import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntityCriteria;

/**
 * オペレーター_サブシステムロール割当検索条件
 */
public class Operator_SubSystemRoleCriteria extends Operator_SubSystemRoleEntityCriteria {
	public boolean test(Operator_SubSystemRole aValue) {
		return super.test(aValue.getOperator_SubSystemRoleEntityForRepository());
	}
}
