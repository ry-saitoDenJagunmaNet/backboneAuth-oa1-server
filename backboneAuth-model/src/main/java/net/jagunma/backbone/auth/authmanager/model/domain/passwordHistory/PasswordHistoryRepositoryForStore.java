package net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory;

/**
 * パスワード格納
 */
public interface PasswordHistoryRepositoryForStore {

    /**
     * パスワードの格納を行います
     *
     * @param passwordHistory パスワード履歴
     */
    void store(PasswordHistory passwordHistory);

}