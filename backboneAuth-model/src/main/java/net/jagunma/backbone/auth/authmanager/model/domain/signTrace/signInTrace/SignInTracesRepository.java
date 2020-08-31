package net.jagunma.backbone.auth.authmanager.model.domain.signTrace.signInTrace;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * サインイン証跡検索
 */
public interface SignInTracesRepository {
	/**
	 * サインイン証跡の条件検索を行います。
	 *
	 * @param signInTraceCriteria サインイン証跡の検索条件
	 * @param orders オーダー指定
	 * @return サインイン証跡群
	 */
	SignInTraces selectBy(SignInTraceCriteria signInTraceCriteria, Orders orders);
	/**
	 * サインイン証跡の条件検索を行います。
	 *
	 * @param signInTraceCriteria サインイン証跡の検索条件
	 * @return サインイン証跡群
	 */
	SignInTraces selectBy(SignInTraceCriteria signInTraceCriteria);

	/**
	 * サインイン証跡の全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return サインイン証跡群
	 */
	SignInTraces selectAll(Orders orders);
	/**
	 * サインイン証跡の全件検索を行います。
	 *
	 * @return サインイン証跡群
	 */
	SignInTraces selectAll();
}
