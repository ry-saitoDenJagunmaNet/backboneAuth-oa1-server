package net.jagunma.backbone.auth.authmanager.model.domain.history.passwordHistory;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * パスワード履歴検索
 */
public interface PasswordHistoriesRepository {
	/**
	 * パスワード履歴の条件検索を行います。
	 *
	 * @param passwordHistoryCriteria パスワード履歴の検索条件
	 * @param orders オーダー指定
	 * @return パスワード履歴群
	 */
	PasswordHistories selectBy(PasswordHistoryCriteria passwordHistoryCriteria, Orders orders);
	/**
	 * パスワード履歴の条件検索を行います。
	 *
	 * @param passwordHistoryCriteria パスワード履歴の検索条件
	 * @return パスワード履歴群
	 */
	PasswordHistories selectBy(PasswordHistoryCriteria passwordHistoryCriteria);

	/**
	 * パスワード履歴の全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return パスワード履歴群
	 */
	PasswordHistories selectAll(Orders orders);
	/**
	 * パスワード履歴の全件検索を行います。
	 *
	 * @return パスワード履歴群
	 */
	PasswordHistories selectAll();
}
