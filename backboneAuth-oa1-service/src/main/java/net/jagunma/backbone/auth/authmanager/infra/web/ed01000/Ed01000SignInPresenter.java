package net.jagunma.backbone.auth.authmanager.infra.web.ed01000;

import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInResponse;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;

/**
 * Ed01000 サインイン Presenter
 */
public class Ed01000SignInPresenter implements SignInResponse {

    public Short signInResultCode;
    public String signInResultMessage;
    public String accessToken;

    /**
     * サインイン結果コードのＳｅｔ
     *
     * @param signInResultCode サインイン結果コード
     */
    public void setSignInResultCode(Short signInResultCode) {
        this.signInResultCode = signInResultCode;
    };
    /**
     * サインイン結果メッセージのＳｅｔ
     *
     * @param signInResultMessage サインイン結果メッセージ
     */
    public void setSignInResultMessage(String signInResultMessage) {
        this.signInResultMessage = signInResultMessage;
    }
    /**
     * アクセストークンのＳｅｔ
     *
     * @param accessToken アクセストークン
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * サインインが成功したか判定します
     *
     * @return サインイン結果
     */
    public boolean isSignInResultSuccess() {
        if (signInResultCode == null) { return false; }
        return SignInResult.codeOf(signInResultCode).is成功();
    }

}
