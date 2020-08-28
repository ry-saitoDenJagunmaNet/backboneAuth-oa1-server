package net.jagunma.backbone.auth.authmanager.application.model.domain.operator.operatorHistory.operator_SubSystemRoleHistory;

import net.jagunma.backbone.auth.model.dao.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistoryEntityCriteria;

/**
 * オペレーター_サブシステムロール割当履歴の検索条件
 */
public class Operator_SubSystemRoleHistoryCriteria extends Operator_SubSystemRoleHistoryEntityCriteria {
	public boolean test(Operator_SubSystemRoleHistory aValue) {
		return super.test(aValue.getOperator_SubSystemRoleHistoryEntityForRepository());
	}
}
