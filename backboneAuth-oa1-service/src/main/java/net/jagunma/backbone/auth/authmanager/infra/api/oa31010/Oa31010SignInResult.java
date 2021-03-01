package net.jagunma.backbone.auth.authmanager.infra.api.oa31010;

import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;

/**
 * Oa31010 サインイン Result
 */
public class Oa31010SignInResult {

    private Short signInResultCode = null;
    private String signInResultMessage = "";

    // コンストラクタ
    Oa31010SignInResult(Short signInResultCode,
        String signInResultMessage) {

        this.signInResultCode = signInResultCode;
        this.signInResultMessage = signInResultMessage;
    }

    // ファクトリーメソッド
    public static Oa31010SignInResult createFrom(SignInResult signInResult) {
        return new Oa31010SignInResult(signInResult.getCode(), signInResult.getDisplayName());
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
     * サインイン結果メッセージのＧｅｔ
     *
     * @return サインイン結果メッセージ
     */
    public String getSignInResultMessage() {
        return signInResultMessage;
    }
}