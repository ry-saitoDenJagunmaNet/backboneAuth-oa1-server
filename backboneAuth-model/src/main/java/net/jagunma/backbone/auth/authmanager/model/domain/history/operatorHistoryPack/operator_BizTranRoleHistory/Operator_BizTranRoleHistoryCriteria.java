package net.jagunma.backbone.auth.authmanager.model.domain.history.operatorHistoryPack.operator_BizTranRoleHistory;

import net.jagunma.backbone.auth.model.dao.operator_BizTranRoleHistory.Operator_BizTranRoleHistoryEntityCriteria;

/**
 * オペレーター_取引ロール割当履歴の検索条件
 */
public class Operator_BizTranRoleHistoryCriteria extends Operator_BizTranRoleHistoryEntityCriteria {
	public boolean test(Operator_BizTranRoleHistory aValue) {
		return super.test(aValue.getOperator_BizTranRoleHistoryEntityForRepository());
	}
}
