package net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;

/**
 * サインアウト証跡
 */
public class SignOutTrace {

    private final Long SignOutTraceId;
    private final LocalDateTime SignOutDateTime;
    private final String SignOutIpAddress;
    private final Long OperatorId;
    private final Operator operator;

    // コンストラクタ
    SignOutTrace(
        Long SignOutTraceId,
        LocalDateTime SignOutDateTime,
        String SignOutIpAddress,
        Long OperatorId,
        Operator operator) {

        this.SignOutTraceId = SignOutTraceId;
        this.SignOutDateTime = SignOutDateTime;
        this.SignOutIpAddress = SignOutIpAddress;
        this.OperatorId = OperatorId;
        this.operator = operator;
    }

    // ファクトリーメソッド
    public static SignOutTrace createFrom(
        Long SignOutTraceId,
        LocalDateTime SignOutDateTime,
        String SignOutIpAddress,
        Long OperatorId,
        Operator operator) {

        return new SignOutTrace(
            SignOutTraceId,
            SignOutDateTime,
            SignOutIpAddress,
            OperatorId,
            operator);
    }

    // Getter
    public Long getSignOutTraceId() {
        return SignOutTraceId;
    }
    public LocalDateTime getSignOutDateTime() {
        return SignOutDateTime;
    }
    public String getSignOutIpAddress() {
        return SignOutIpAddress;
    }
    public Long getOperatorId() {
        return OperatorId;
    }
    public Operator getOperator() {
        return operator;
    }
}
