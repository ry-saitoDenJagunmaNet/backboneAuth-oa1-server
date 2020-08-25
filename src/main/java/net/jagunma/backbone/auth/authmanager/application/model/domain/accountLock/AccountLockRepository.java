package net.jagunma.backbone.auth.authmanager.application.model.domain.accountLock;

/**
 * アカウントロック検索
 */
public interface AccountLockRepository {
	/**
	 * アカウントロックの条件検索を行います。
	 *
	 * @param accountLockCriteria アカウントロックの検索条件
	 * @return アカウントロック
	 */
	AccountLock findOneBy(AccountLockCriteria accountLockCriteria);
}
