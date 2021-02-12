package net.jagunma.backbone.auth.authmanager.model.domain.accountLock;

/**
 * アカウントロック格納
 */
public interface AccountLockRepositoryForStore {

    /**
     * アカウントロックの追加を行います
     *
     * @param accountLock アカウントロック
     * @return アカウントロック
     */
    AccountLock insert(AccountLock accountLock);
}
