package net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistoryPack.operatorHistory;

import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntityCriteria;

/**
 * オペレーター履歴の検索条件
 */
public class OperatorHistoryCriteria extends OperatorHistoryEntityCriteria {
	public boolean test(OperatorHistory aValue) {
		return super.test(aValue.getOperatorHistoryEntityForRepository());
	}
}
