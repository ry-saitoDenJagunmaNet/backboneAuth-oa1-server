package net.jagunma.backbone.auth.authmanager.model.domain.signInTrace;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;

/**
 * サインイン証跡
 */
public class SignInTrace {

    private final Long SignInTraceId;
    private final LocalDateTime TryDateTime;
    private final String TryIpAddress;
    private final String OperatorCode;
    private final Short SignInCause;
    private final Short SignInResult;
    private final Operator operator;

    // コンストラクタ
    SignInTrace(
        Long SignInTraceId,
        LocalDateTime TryDateTime,
        String TryIpAddress,
        String OperatorCode,
        Short SignInCause,
        Short SignInResult,
        Operator operator) {

        this.SignInTraceId = SignInTraceId;
        this.TryDateTime = TryDateTime;
        this.TryIpAddress = TryIpAddress;
        this.OperatorCode = OperatorCode;
        this.SignInCause = SignInCause;
        this.SignInResult = SignInResult;
        this.operator = operator;
    }

    // ファクトリーメソッド
    public static SignInTrace createFrom(
        Long SignInTraceId,
        LocalDateTime TryDateTime,
        String TryIpAddress,
        String OperatorCode,
        Short SignInCause,
        Short SignInResult,
        Operator operator) {

        return new SignInTrace(
            SignInTraceId,
            TryDateTime,
            TryIpAddress,
            OperatorCode,
            SignInCause,
            SignInResult,
            operator);
    }

    // Getter
    public Long getSignInTraceId() {
        return SignInTraceId;
    }
    public LocalDateTime getTryDateTime() {
        return TryDateTime;
    }
    public String getTryIpAddress() {
        return TryIpAddress;
    }
    public String getOperatorCode() {
        return OperatorCode;
    }
    public Short getSignInCause() {
        return SignInCause;
    }
    public Short getSignInResult() {
        return SignInResult;
    }
    public Operator getOperator() {
        return operator;
    }
}
