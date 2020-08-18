package net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistory.operatorHistoryPackHeader;

/**
 * オペレーター履歴パックヘッダー検索
 */
public interface OperatorHistoryPackHeadersRepository {
	/**
	 * オペレーター履歴パックヘッダーの条件検索を行います。
	 *
	 * @param operatorHistoryPackHeaderCriteria オペレーター履歴パックヘッダーの検索条件
	 * @return オペレーター履歴パックヘッダー群
	 */
	OperatorHistoryPackHeaders selectBy(OperatorHistoryPackHeaderCriteria operatorHistoryPackHeaderCriteria);
	/**
	 * オペレーター履歴パックヘッダーの全件検索を行います。
	 *
	 * @return オペレーター履歴パックヘッダー群
	 */
	OperatorHistoryPackHeaders selectAll();
}
