package net.jagunma.backbone.auth.authmanager.infra.web.ed01000.dto;

import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;

/**
 * Ed01000 Dto
 */
public class Ed01000Dto {

    /**
     * インイン結果コード
     */
    private Short signInResultCode;
    /**
     * サインイン結果メッセージ
     */
    private String signInResultMessage;
    /**
     * アクセストークン
     */
    private String accessToken;

    // コンストラクタ
    public Ed01000Dto() {}

    public Short getSignInResultCode() {
        return signInResultCode;
    }
    public void setSignInResultCode(Short signInResultCode) {
        this.signInResultCode = signInResultCode;
    }
    public String getSignInResultMessage() {
        return signInResultMessage;
    }
    public void setSignInResultMessage(String signInResultMessage) {
        this.signInResultMessage = signInResultMessage;
    }
    public String getAccessToken() {
        return accessToken;
    }
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
