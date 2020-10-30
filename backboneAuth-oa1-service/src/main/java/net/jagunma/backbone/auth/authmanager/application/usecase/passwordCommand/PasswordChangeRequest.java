package net.jagunma.backbone.auth.authmanager.application.usecase.passwordCommand;

/**
 * パスワード変更サービス Request
 * パスワード更新サービス Request  ToDo:★どっちにするか？
 */
public interface PasswordChangeRequest {
    /**
     * オペレーターIDのＧｅｔ
     *
     * @return オペレーターID
     */
    Long getOperatorId();
    /**
     * 古いパスワードのＧｅｔ
     *
     * @return 古いパスワード
     */
    String getOldPassword();
    /**
     * 新しいパスワードのＧｅｔ
     *
     * @return 新しいパスワード
     */
    String getNewPassword();
    /**
     * パスワードの確認入力のＧｅｔ
     *
     * @return パスワードの確認入力
     */
    String getConfirmPassword();
}
