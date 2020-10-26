package net.jagunma.backbone.auth.authmanager.model.domain.accountLock;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Collection;
import java.util.List;

/**
 * アカウントロック群
 */
public class AccountLocks {

    private final List<AccountLock> list = newArrayList();

    // コンストラクタ
    AccountLocks(Collection<AccountLock> collection) {
        this.list.addAll(collection);
    }

    /**
     * アカウントロックリストから作成します
     *
     * @param accountLockList アカウントロックリスト
     * @return アカウントロック群
     */
    public static AccountLocks createFrom(Collection<AccountLock> accountLockList) {
        return new AccountLocks(accountLockList);
    }

    /**
     * アカウントロックリストを取得します
     *
     * @return アカウントロックリスト
     */
    public List<AccountLock> getValues() {
        return list;
    }
}
