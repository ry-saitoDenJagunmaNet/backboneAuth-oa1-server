package net.jagunma.backbone.auth.authmanager.model.domain.accountLock;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * アカウントロック群検索
 */
public interface AccountLockRepository {

    /**
     * アカウントロック群の条件検索を行います
     *
     * @param accountLockrCriteria アカウントロックの検索条件
     * @param orders               オーダー指定
     * @return アカウントロック群
     */
    AccountLocks selectBy(AccountLockCriteria accountLockrCriteria, Orders orders);
}
