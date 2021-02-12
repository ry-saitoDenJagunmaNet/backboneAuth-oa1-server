package net.jagunma.backbone.auth.authmanager.infra.web.ed01000;

import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01000.vo.Ed01000Vo;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;

/**
 * Ed01000 サインイン Presenter
 */
public class Ed01000SignInPresenter implements SignInResponse {

    public Short signInResultCode;
    public String signInResultMessage;

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
     * サインインが成功したか判定します
     *
     * @return サインイン結果
     */
    public boolean isSignInResultSuccess() {
        if (signInResultCode == null) { return false; }
        return SignInResult.codeOf(signInResultCode).is成功();
    }

    /**
     * voに変換します
     *
     * @param vo サインイン View Object
     */
    public void bindTo(Ed01000Vo vo) {
    }
}
