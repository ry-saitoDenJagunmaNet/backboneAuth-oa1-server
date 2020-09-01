package net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operatorHistoryHeader;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * オペレーター履歴ヘッダー検索
 */
public interface OperatorHistoryHeadersRepository {
	/**
	 * オペレーター履歴ヘッダーの条件検索を行います。
	 *
	 * @param operatorHistoryHeaderCriteria オペレーター履歴ヘッダーの検索条件
	 * @param orders オーダー指定
	 * @return オペレーター履歴ヘッダー群
	 */
	OperatorHistoryHeaders selectBy(OperatorHistoryHeaderCriteria operatorHistoryHeaderCriteria, Orders orders);
	/**
	 * オペレーター履歴ヘッダーの条件検索を行います。
	 *
	 * @param operatorHistoryHeaderCriteria オペレーター履歴ヘッダーの検索条件
	 * @return オペレーター履歴ヘッダー群
	 */
	OperatorHistoryHeaders selectBy(OperatorHistoryHeaderCriteria operatorHistoryHeaderCriteria);

	/**
	 * オペレーター履歴ヘッダーの全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return オペレーター履歴ヘッダー群
	 */
	OperatorHistoryHeaders selectAll(Orders orders);
	/**
	 * オペレーター履歴ヘッダーの全件検索を行います。
	 *
	 * @return オペレーター履歴ヘッダー群
	 */
	OperatorHistoryHeaders selectAll();
}
