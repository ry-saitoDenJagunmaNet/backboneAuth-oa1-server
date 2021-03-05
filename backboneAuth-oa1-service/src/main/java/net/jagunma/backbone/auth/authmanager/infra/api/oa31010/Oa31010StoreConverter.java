package net.jagunma.backbone.auth.authmanager.infra.api.oa31010;

import net.jagunma.backbone.auth.authmanager.application.api_usecase.signInTraceCommand.SignInTraceStoreRequest;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;

/**
 * Oa31010 登録 Converter
 */
public class Oa31010StoreConverter implements SignInTraceStoreRequest {

    private final String tryIpAddress;
    private final String operatorCode;
    private final SignInCause signInCause;
    private final SignInResult signInResult;

    // コンストラクタ
    Oa31010StoreConverter(
        String tryIpAddress,
        String operatorCode,
        SignInCause signInCause,
        SignInResult signInResult) {

        this.tryIpAddress = tryIpAddress;
        this.operatorCode = operatorCode;
        this.signInCause = signInCause;
        this.signInResult = signInResult;
    }

    // ファクトリーメソッド
    public static Oa31010StoreConverter with(
        String tryIpAddress,
        String operatorCode,
        SignInCause signInCause,
        SignInResult signInResult) {

        return new Oa31010StoreConverter(
            tryIpAddress,
            operatorCode,
            signInCause,
            signInResult);
    }

    /**
     * 試行IPアドレスのＧｅｔ
     *
     * @return 試行IPアドレス
     */
    public String getTryIpAddress() {
        return tryIpAddress;
    }
    /**
     * オペレーターコードのＧｅｔ
     *
     * @return オペレーターコード
     */
    public String getOperatorCode() {
        return operatorCode;
    };
    /**
     * サインイン起因のＧｅｔ
     *
     * @return サインイン起因
     */
    public SignInCause getSignInCause() {
        return signInCause;
    };
    /**
     * サインイン結果のＧｅｔ
     *
     * @return サインイン結果
     */
    public SignInResult getSignInResult() {
        return signInResult;
    };
}
