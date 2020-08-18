package net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistory.operatorHistory;

/**
 * オペレーター履歴検索
 */
public interface OperatorHistoriesRepository {
	/**
	 * オペレーター履歴の条件検索を行います。
	 *
	 * @param operatorHistoryCriteria オペレーター履歴の検索条件
	 * @return オペレーター履歴群
	 */
	OperatorHistories selectBy(OperatorHistoryCriteria operatorHistoryCriteria);
	/**
	 * オペレーター履歴の全件検索を行います。
	 *
	 * @return オペレーター履歴群
	 */
	OperatorHistories selectAll();
}
