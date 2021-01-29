package net.jagunma.backbone.auth.authmanager.infra.api.oa13010;

import net.jagunma.backbone.auth.authmanager.application.usecase.signInTraceCommand.SignInTraceEntryRequest;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;

/**
 * Oa13010 登録 Converter
 */
public class Oa13010EntryConverter implements SignInTraceEntryRequest {

    private final String tryIpAddress;
    private final String operatorCode;
    private final Short signInCauseCode;
    private final Short signInResultCode;

    // コンストラクタ
    Oa13010EntryConverter(
        String tryIpAddress,
        String operatorCode,
        Short signInCauseCode,
        Short signInResultCode) {

        this.tryIpAddress = tryIpAddress;
        this.operatorCode = operatorCode;
        this.signInCauseCode = signInCauseCode;
        this.signInResultCode = signInResultCode;
    }

    // ファクトリーメソッド
    public static Oa13010EntryConverter of(
        String tryIpAddress,
        String operatorCode,
        Short signInCauseCode,
        Short signInResultCode) {

        return new Oa13010EntryConverter(
            tryIpAddress,
            operatorCode,
            signInCauseCode,
            signInResultCode);
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
        return SignInCause.codeOf(signInCauseCode);
    };
    /**
     * サインイン結果のＧｅｔ
     *
     * @return サインイン結果
     */
    public SignInResult getSignInResult() {
        return SignInResult.codeOf(signInResultCode);
    };
}
