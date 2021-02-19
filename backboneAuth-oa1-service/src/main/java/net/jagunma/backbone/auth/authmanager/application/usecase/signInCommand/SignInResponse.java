package net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand;

/**
 * サインインサービス Response
 */
public interface SignInResponse {

    /**
     * サインイン結果コードのＳｅｔ
     *
     * @param signInResultCode サインイン結果コード
     */
    void setSignInResultCode(Short signInResultCode);
    /**
     * サインイン結果メッセージのＳｅｔ
     *
     * @param signInResultMessage サインイン結果メッセージ
     */
    public void setSignInResultMessage(String signInResultMessage);
    /**
     * アクセストークンのＳｅｔ
     *
     * @param accessToken アクセストークン
     */
    void setAccessToken(String accessToken);
}
