package net.jagunma.backbone.auth.authmanager.infra.web.ed01000;

import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01000.dto.Ed01000Dto;

/**
 * Ed01000 サインイン Presenter
 */
public class Ed01000SignInPresenter implements SignInResponse {

    private Short signInResultCode;
    private String signInResultMessage;
    private String accessToken;

    /**
     * サインイン結果コードのＳｅｔ
     *
     * @param signInResultCode サインイン結果コード
     */
    public void setSignInResultCode(Short signInResultCode) {
        this.signInResultCode = signInResultCode;
    }
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
     * dtoに変換します
     *
     * @param dto サインイン Dto
     */
    public void bindTo(Ed01000Dto dto) {
        dto.setSignInResultCode(signInResultCode);
        dto.setSignInResultMessage(signInResultMessage);
        dto.setAccessToken(accessToken);
    }
}
