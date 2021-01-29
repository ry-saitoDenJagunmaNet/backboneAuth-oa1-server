package net.jagunma.backbone.auth.authmanager.application.usecase.signInTraceCommand;

import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;

/**
 * サインイン証跡登録サービス Request
 */
public interface SignInTraceEntryRequest {

    /**
     * 試行IPアドレスのＧｅｔ
     *
     * @return 試行IPアドレス
     */
    String getTryIpAddress();
    /**
     * オペレーターコードのＧｅｔ
     *
     * @return オペレーターコード
     */
    String getOperatorCode();
    /**
     * サインイン起因のＧｅｔ
     *
     * @return サインイン起因
     */
    SignInCause getSignInCause();
    /**
     * サインイン結果のＧｅｔ
     *
     * @return サインイン結果
     */
    SignInResult getSignInResult();
}
