package net.jagunma.backbone.auth.authmanager.application.model.domain.signTrace.signOutTrace;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * サインアウト証跡検索
 */
public interface SignOutTracesRepository {
	/**
	 * サインアウト証跡の条件検索を行います。
	 *
	 * @param signOutTraceCriteria サインアウト証跡の検索条件
	 * @param orders オーダー指定
	 * @return サインアウト証跡群
	 */
	SignOutTraces selectBy(SignOutTraceCriteria signOutTraceCriteria, Orders orders);
	/**
	 * サインアウト証跡の条件検索を行います。
	 *
	 * @param signOutTraceCriteria サインアウト証跡の検索条件
	 * @return サインアウト証跡群
	 */
	SignOutTraces selectBy(SignOutTraceCriteria signOutTraceCriteria);

	/**
	 * サインアウト証跡の全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return サインアウト証跡群
	 */
	SignOutTraces selectAll(Orders orders);
	/**
	 * サインアウト証跡の全件検索を行います。
	 *
	 * @return サインアウト証跡群
	 */
	SignOutTraces selectAll();
}
