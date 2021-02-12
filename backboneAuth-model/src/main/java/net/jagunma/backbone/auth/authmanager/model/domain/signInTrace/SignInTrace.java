package net.jagunma.backbone.auth.authmanager.model.domain.signInTrace;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;

/**
 * サインイン証跡
 */
public class SignInTrace {

    private final Long signInTraceId;
    private final LocalDateTime tryDateTime;
    private final String tryIpAddress;
    private final String operatorCode;
    private final SignInCause signInCause;
    private final SignInResult signInResult;
    private final Integer recordVersion;
    private final Operator operator;

    // コンストラクタ
    SignInTrace(
        Long signInTraceId,
        LocalDateTime tryDateTime,
        String tryIpAddress,
        String operatorCode,
        SignInCause signInCause,
        SignInResult signInResult,
        Integer recordVersion,
        Operator operator) {

        this.signInTraceId = signInTraceId;
        this.tryDateTime = tryDateTime;
        this.tryIpAddress = tryIpAddress;
        this.operatorCode = operatorCode;
        this.signInCause = signInCause;
        this.signInResult = signInResult;
        this.recordVersion = recordVersion;
        this.operator = operator;
    }

    // ファクトリーメソッド
    public static SignInTrace createFrom(
        Long signInTraceId,
        LocalDateTime tryDateTime,
        String tryIpAddress,
        String operatorCode,
        SignInCause signInCause,
        SignInResult signInResult,
        Integer recordVersion,
        Operator operator) {

        return new SignInTrace(
            signInTraceId,
            tryDateTime,
            tryIpAddress,
            operatorCode,
            signInCause,
            signInResult,
            recordVersion,
            operator);
    }

    // Getter
    public Long getSignInTraceId() {
        return signInTraceId;
    }
    public LocalDateTime getTryDateTime() {
        return tryDateTime;
    }
    public String getTryIpAddress() {
        return tryIpAddress;
    }
    public String getOperatorCode() {
        return operatorCode;
    }
    public SignInCause getSignInCause() {
        return signInCause;
    }
    public SignInResult getSignInResult() {
        return signInResult;
    }
    public Integer getRecordVersion() {
        return recordVersion;
    }
    public Operator getOperator() {
        return operator;
    }
}
