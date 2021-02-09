package net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand;

/**
 * サインインサービス Request
 */
public interface SignInRequest {

    /**
     * オペレーターCodeのＧｅｔ
     *
     * @return オペレーターCode
     */
    String getOperatorCode();
    /**
     * パスワードのＧｅｔ
     *
     * @return パスワード
     */
    String getPassword();
    /**
     * モードのＧｅｔ
     *
     * @return モード
     */
    Integer getMode();
    /**
     * アクセストークンのＧｅｔ
     *
     * @return アクセストークン
     */
    String getAccessToken();
}
