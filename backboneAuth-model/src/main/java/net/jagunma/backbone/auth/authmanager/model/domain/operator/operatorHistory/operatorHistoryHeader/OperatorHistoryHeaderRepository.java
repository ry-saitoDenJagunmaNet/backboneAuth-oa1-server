package net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operatorHistoryHeader;

/**
 * オペレーター履歴ヘッダー検索
 */
public interface OperatorHistoryHeaderRepository {
	/**
	 * オペレーター履歴ヘッダーの条件検索を行います。
	 *
	 * @param operatorHistoryHeaderCriteria オペレーター履歴ヘッダーの検索条件
	 * @return オペレーター履歴ヘッダー
	 */
	OperatorHistoryHeader findOneBy(OperatorHistoryHeaderCriteria operatorHistoryHeaderCriteria);
}
