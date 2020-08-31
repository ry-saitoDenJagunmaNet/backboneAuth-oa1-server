package net.jagunma.backbone.auth.authmanager.model.domain.accountLock;

import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntityCriteria;

/**
 * アカウントロックの検索条件
 */
public class AccountLockCriteria extends AccountLockEntityCriteria {
	public boolean test(AccountLock aValue) {
		return super.test(aValue.getAccountLockEntityForRepository());
	}
}
