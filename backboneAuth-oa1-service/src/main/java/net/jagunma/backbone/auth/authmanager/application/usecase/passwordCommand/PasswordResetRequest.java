package net.jagunma.backbone.auth.authmanager.application.usecase.passwordCommand;

/**
 * パスワードリセットサービス Request
 * パスワード更新サービス Request  ToDo:★どっちにするか？
 */
public interface PasswordResetRequest {
    /**
     * オペレーターIDのＧｅｔ
     *
     * @return オペレーターID
     */
    Long getOperatorId();
    /**
     * パスワードのＧｅｔ
     *
     * @return パスワード
     */
    String getPassword();
    /**
     * パスワードの確認入力のＧｅｔ
     *
     * @return パスワードの確認入力
     */
    String getConfirmPassword();
}
