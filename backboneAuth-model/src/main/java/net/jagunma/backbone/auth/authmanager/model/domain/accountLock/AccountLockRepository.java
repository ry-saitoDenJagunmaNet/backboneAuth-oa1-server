package net.jagunma.backbone.auth.authmanager.model.domain.accountLock;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * アカウントロック検索
 */
public interface AccountLockRepository {

    /**
     * アカウントロックの存在チェックを行います
     *
     * @param operatorId オペレーターID
     * @return アカウントロックの有無
     */
    boolean existsByOperatorId(Long operatorId);
    /**
     * 対象オペレーターで最新のアカウントロックの検索を行います
     *
     * @param operatorId オペレーターID
     * @return 対象オペレーターの中で最新のアカウントロック
     */
    AccountLock latestOneByOperatorId(Long operatorId);
    /**
     * アカウントロック群の条件検索を行います
     *
     * @param accountLockrCriteria アカウントロックの検索条件
     * @param orders               オーダー指定
     * @return アカウントロック群
     */
    AccountLocks selectBy(AccountLockCriteria accountLockrCriteria, Orders orders);
}
