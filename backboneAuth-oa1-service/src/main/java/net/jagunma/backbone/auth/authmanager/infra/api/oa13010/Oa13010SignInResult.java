package net.jagunma.backbone.auth.authmanager.infra.api.oa13010;

import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;

/**
 * Oa13010 サインイン Result
 */
public class Oa13010SignInResult {

    private Short signInResultCode = null;
    private String signInResultMessage = "";

    // コンストラクタ
    Oa13010SignInResult(Short signInResultCode,
        String signInResultMessage) {

        this.signInResultCode = signInResultCode;
        this.signInResultMessage = signInResultMessage;
    }

    // ファクトリメソッド
    public static Oa13010SignInResult createFrom(SignInResult signInResult) {
        return new Oa13010SignInResult(signInResult.getCode(), signInResult.getDisplayName());
    }

    /**
     * サインイン結果コードのＧｅｔ
     *
     * @return サインイン結果コード
     */
    public Short getSignInResultCode() {
        return signInResultCode;
    }
    /**
     * サインイン結果コードのＳｅｔ
     *
     * @param signInResultCode サインイン結果コード
     */
    public void setSignInResultCode(Short signInResultCode) {
        this.signInResultCode = signInResultCode;
    }
    /**
     * サインイン結果メッセージのＧｅｔ
     *
     * @return サインイン結果メッセージ
     */
    public String getSignInResultMessage() {
        return signInResultMessage;
    }
    /**
     * サインイン結果メッセージのＳｅｔ
     *
     * @param signInResultMessage サインイン結果メッセージ
     */
    public void setSignInResultMessage(String signInResultMessage) {
        this.signInResultMessage = signInResultMessage;
    }
}