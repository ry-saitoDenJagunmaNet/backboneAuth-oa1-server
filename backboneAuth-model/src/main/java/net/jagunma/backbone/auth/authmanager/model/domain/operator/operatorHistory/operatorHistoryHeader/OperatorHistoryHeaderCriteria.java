package net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operatorHistoryHeader;

import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntityCriteria;

/**
 * オペレーター履歴ヘッダーの検索条件
 */
public class OperatorHistoryHeaderCriteria extends OperatorHistoryHeaderEntityCriteria {
	public boolean test(OperatorHistoryHeader aValue) {
		return super.test(aValue.getOperatorHistoryHeaderEntityForRepository());
	}
}
