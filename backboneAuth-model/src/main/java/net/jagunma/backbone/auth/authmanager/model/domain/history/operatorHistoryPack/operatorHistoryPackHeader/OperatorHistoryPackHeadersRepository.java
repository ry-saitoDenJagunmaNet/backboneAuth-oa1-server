package net.jagunma.backbone.auth.authmanager.model.domain.history.operatorHistoryPack.operatorHistoryPackHeader;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * オペレーター履歴パックヘッダー検索
 */
public interface OperatorHistoryPackHeadersRepository {
	/**
	 * オペレーター履歴パックヘッダーの条件検索を行います。
	 *
	 * @param operatorHistoryPackHeaderCriteria オペレーター履歴パックヘッダーの検索条件
	 * @param orders オーダー指定
	 * @return オペレーター履歴パックヘッダー群
	 */
	OperatorHistoryPackHeaders selectBy(OperatorHistoryPackHeaderCriteria operatorHistoryPackHeaderCriteria, Orders orders);
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
	 * @param orders オーダー指定
	 * @return オペレーター履歴パックヘッダー群
	 */
	OperatorHistoryPackHeaders selectAll(Orders orders);
	/**
	 * オペレーター履歴パックヘッダーの全件検索を行います。
	 *
	 * @return オペレーター履歴パックヘッダー群
	 */
	OperatorHistoryPackHeaders selectAll();
}
