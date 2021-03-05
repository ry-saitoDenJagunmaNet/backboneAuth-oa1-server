package net.jagunma.backbone.auth.authmanager.application.api_usecase.authenticationCommand;

/**
 * 認証サービス Request
 */
public interface AuthenticationRequest {

    /**
     * オペレータコードのＧｅｔ
     *
     * @return オペレータコード
     */
    String getOperatorCode();
    /**
     * パスワードのＧｅｔ
     *
     * @return パスワード
     */
    String getPassword();
}
