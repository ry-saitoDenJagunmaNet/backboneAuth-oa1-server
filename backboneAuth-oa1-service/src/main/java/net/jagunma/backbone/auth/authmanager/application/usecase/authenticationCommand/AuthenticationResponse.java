package net.jagunma.backbone.auth.authmanager.application.usecase.authenticationCommand;

import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;

/**
 * 認証サービス Response
 */
public interface AuthenticationResponse {

    /**
     * サインイン結果のＳｅｔ
     *
     * @param signInResult サインイン結果
     */
    void setSignInResult(SignInResult signInResult);
}
