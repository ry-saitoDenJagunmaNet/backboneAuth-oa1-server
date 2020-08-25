package net.jagunma.backbone.auth.authmanager.application.model.domain.roleAssignment.operator_BizTranRole;

import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntityCriteria;

/**
 * オペレーター_取引ロール割当検索条件
 */
public class Operator_BizTranRoleCriteria extends Operator_BizTranRoleEntityCriteria {
	public boolean test(Operator_BizTranRole aValue) {
		return super.test(aValue.getOperator_BizTranRoleEntityForRepository());
	}
}
