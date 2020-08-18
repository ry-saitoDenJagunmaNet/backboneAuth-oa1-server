package net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistory.operatorHistoryPackHeader;

import net.jagunma.backbone.auth.model.dao.operatorHistoryPackHeader.OperatorHistoryPackHeaderEntityCriteria;

/**
 * オペレーター履歴パックヘッダーの検索条件
 */
public class OperatorHistoryPackHeaderCriteria extends OperatorHistoryPackHeaderEntityCriteria {
	public boolean test(OperatorHistoryPackHeader aValue) {
		return super.test(aValue.getOperatorHistoryPackHeaderEntityForRepository());
	}
}
