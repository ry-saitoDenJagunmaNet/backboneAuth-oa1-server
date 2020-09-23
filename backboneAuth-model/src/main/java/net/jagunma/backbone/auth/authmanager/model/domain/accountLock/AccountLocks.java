package net.jagunma.backbone.auth.authmanager.model.domain.accountLock;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;

/**
 * アカウントロック群
 */
public class AccountLocks {

    private final ArrayList<AccountLock> list = newArrayList();

    // コンストラクタ
    AccountLocks(Collection<AccountLock> collection) {
        this.list.addAll(collection);
    }

    /**
     * アカウントロックリストから作成します。
     *
     * @param accountLockList アカウントロックリスト
     * @return アカウントロック群
     */
    public static AccountLocks createFrom(Collection<AccountLock> accountLockList) {
        return new AccountLocks(accountLockList);
    }

    /**
     * アカウントロックリストを取得します。
     *
     * @return アカウントロックリスト
     */
    public ArrayList<AccountLock> getList() {
        return list;
    }
}
