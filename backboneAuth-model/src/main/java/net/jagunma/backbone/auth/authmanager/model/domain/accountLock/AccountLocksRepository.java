package net.jagunma.backbone.auth.authmanager.model.domain.accountLock;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * カウントロック群検索
 */
public interface AccountLocksRepository {

    /**
     * オペレーター群の条件検索を行います。
     *
     * @param accountLockrCriteria カウントロックの検索条件
     * @param orders               オーダー指定
     * @return カウントロック群
     */
    AccountLocks selectBy(AccountLockCriteria accountLockrCriteria, Orders orders);
}
