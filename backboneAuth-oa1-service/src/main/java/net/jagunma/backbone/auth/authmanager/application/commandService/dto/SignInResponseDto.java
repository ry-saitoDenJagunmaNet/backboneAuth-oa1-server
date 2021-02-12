package net.jagunma.backbone.auth.authmanager.application.commandService.dto;

import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInResponse;

public class SignInResponseDto {

    private Short signInResultCode;
    private String signInResultMessage;

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

//    /**
//     * responseに変換
//     * @param response サインインサービス Response
//     */
//    public void bindTo(SignInResponse response)  {
//        response.setSignInResultCode(signInResultCode);
//        response.setSignInResultMessage(signInResultMessage);
//    }
}
