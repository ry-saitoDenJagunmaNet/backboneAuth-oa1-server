package net.jagunma.backbone.auth.authmanager.application.model.domain.accountLock;

/**
 * アカウントロック検索
 */
public interface AccountLocksRepository {
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
	 * @return アカウントロック群
	 */
	AccountLocks selectAll();
}
