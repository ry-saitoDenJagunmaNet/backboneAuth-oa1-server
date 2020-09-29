package net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;

/**
 * サインアウト証跡
 */
public class SignOutTrace {

    private final Long signOutTraceId;
    private final LocalDateTime signOutDateTime;
    private final String signOutIpAddress;
    private final Long operatorId;
    private final Integer recordVersion;
    private final Operator operator;

    // コンストラクタ
    SignOutTrace(
        Long signOutTraceId,
        LocalDateTime signOutDateTime,
        String signOutIpAddress,
        Long operatorId,
        Integer recordVersion,
        Operator operator) {

        this.signOutTraceId = signOutTraceId;
        this.signOutDateTime = signOutDateTime;
        this.signOutIpAddress = signOutIpAddress;
        this.operatorId = operatorId;
        this.recordVersion = recordVersion;
        this.operator = operator;
    }
    // ファクトリーメソッド
    public static SignOutTrace createFrom(
        Long signOutTraceId,
        LocalDateTime signOutDateTime,
        String signOutIpAddress,
        Long operatorId,
        Integer recordVersion,
        Operator operator) {

        return new SignOutTrace(
            signOutTraceId,
            signOutDateTime,
            signOutIpAddress,
            operatorId,
            recordVersion,
            operator);
    }

    // Getter
    public Long getSignOutTraceId() {
        return signOutTraceId;
    }
    public LocalDateTime getSignOutDateTime() {
        return signOutDateTime;
    }
    public String getSignOutIpAddress() {
        return signOutIpAddress;
    }
    public Long getOperatorId() {
        return operatorId;
    }
    public Integer getRecordVersion() {
        return recordVersion;
    }
    public Operator getOperator() {
        return operator;
    }
}
