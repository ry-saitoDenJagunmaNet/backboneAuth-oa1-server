package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * オペレーター検索
 */
public interface OperatorsRepository {
	/**
	 * オペレーターの条件検索を行います。
	 *
	 * @param operatorCriteria オペレーターの検索条件
	 * @param orders オーダー指定
	 * @return オペレーター群
	 */
	Operators selectBy(OperatorCriteria operatorCriteria, Orders orders);
	/**
	 * オペレーターの条件検索を行います。
	 *
	 * @param operatorCriteria オペレーターの検索条件
	 * @return オペレーター群
	 */
	Operators selectBy(OperatorCriteria operatorCriteria);

	/**
	 * オペレーターの全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return オペレーター群
	 */
	Operators selectAll(Orders orders);
	/**
	 * オペレーターの全件検索を行います。
	 *
	 * @return オペレーター群
	 */
	Operators selectAll();
}
