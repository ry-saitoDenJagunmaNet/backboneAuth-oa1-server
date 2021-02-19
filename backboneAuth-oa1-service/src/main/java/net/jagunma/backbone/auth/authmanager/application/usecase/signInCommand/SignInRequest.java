package net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand;

/**
 * サインインサービス Request
 */
public interface SignInRequest {

    /**
     * オペレーターコードのＧｅｔ
     *
     * @return オペレーターコード
     */
    String getOperatorCode();
    /**
     * パスワードのＧｅｔ
     *
     * @return パスワード
     */
    String getPassword();
    /**
     * クライアントIPアドレスのＧｅｔ
     *
     * @return クライアントIPアドレス
     */
    String getClientIpaddress();
    /**
     * モードのＧｅｔ
     *
     * @return モード
     */
    Integer getMode();
}
