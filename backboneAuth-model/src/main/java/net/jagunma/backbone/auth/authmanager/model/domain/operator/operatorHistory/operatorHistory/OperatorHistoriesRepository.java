package net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operatorHistory;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * オペレーター履歴検索
 */
public interface OperatorHistoriesRepository {
	/**
	 * オペレーター履歴の条件検索を行います。
	 *
	 * @param operatorHistoryCriteria オペレーター履歴の検索条件
	 * @param orders オーダー指定
	 * @return オペレーター履歴群
	 */
	OperatorHistories selectBy(OperatorHistoryCriteria operatorHistoryCriteria, Orders orders);
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
	 * @param orders オーダー指定
	 * @return オペレーター履歴群
	 */
	OperatorHistories selectAll(Orders orders);
	/**
	 * オペレーター履歴の全件検索を行います。
	 *
	 * @return オペレーター履歴群
	 */
	OperatorHistories selectAll();
}
