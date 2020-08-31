package net.jagunma.backbone.auth.authmanager.model.domain.accountLock;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * アカウントロック検索
 */
public interface AccountLocksRepository {
	/**
	 * アカウントロックの条件検索を行います。
	 *
	 * @param accountLockCriteria アカウントロックの検索条件
	 * @param orders オーダー指定
	 * @return アカウントロック群
	 */
	AccountLocks selectBy(AccountLockCriteria accountLockCriteria, Orders orders);
	/**
	 * アカウントロックの条件検索を行います。
	 *
	 * @param accountLockCriteria アカウントロックの検索条件
	 * @return アカウントロック群
	 */
	AccountLocks selectBy(AccountLockCriteria accountLockCriteria);

	/**
	 * アカウントロックの全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return アカウントロック群
	 */
	AccountLocks selectAll(Orders orders);
	/**
	 * アカウントロックの全件検索を行います。
	 *
	 * @return アカウントロック群
	 */
	AccountLocks selectAll();
}
