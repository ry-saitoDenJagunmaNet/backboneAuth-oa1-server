package net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistory.operatorHistoryPackHeader;

/**
 * オペレーター履歴パックヘッダー検索
 */
public interface OperatorHistoryPackHeaderRepository {
	/**
	 * オペレーター履歴パックヘッダーの条件検索を行います。
	 *
	 * @param operatorHistoryPackHeaderCriteria オペレーター履歴パックヘッダーの検索条件
	 * @return オペレーター履歴パックヘッダー
	 */
	OperatorHistoryPackHeader findOneBy(OperatorHistoryPackHeaderCriteria operatorHistoryPackHeaderCriteria);
}
