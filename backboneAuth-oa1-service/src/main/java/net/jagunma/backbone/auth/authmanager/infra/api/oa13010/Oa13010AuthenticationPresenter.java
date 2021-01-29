package net.jagunma.backbone.auth.authmanager.infra.api.oa13010;

import net.jagunma.backbone.auth.authmanager.application.usecase.authenticationCommand.AuthenticationResponse;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;

/**
 * Oa13010 認証 Presenter
 */
public class Oa13010AuthenticationPresenter implements AuthenticationResponse {

    private SignInResult signInResult;

    /**
     * サインイン結果のＳｅｔ
     *
     * @param signInResult サインイン結果
     */
    public void setSignInResult(SignInResult signInResult) {
        this.signInResult = signInResult;
    }
    /**
     * サインイン結果のＧｅｔ
     *
     * @return サインイン結果
     */
    public SignInResult getSignInResult() {
        return signInResult;
    }
}
