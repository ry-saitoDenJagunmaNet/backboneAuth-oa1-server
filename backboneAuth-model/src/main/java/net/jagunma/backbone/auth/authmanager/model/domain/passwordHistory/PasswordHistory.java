package net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;

/**
 * パスワード履歴
 */
public class PasswordHistory {

    private final Long passwordHistoryId;
    private final Long operatorId;
    private final LocalDateTime changeDateTime;
    private final String password;
    private final Short changeType;
    private final Integer recordVersion;
    private final Operator operator;

    // コンストラクタ
    PasswordHistory(
        Long passwordHistoryId,
        Long operatorId,
        LocalDateTime changeDateTime,
        String password,
        Short changeType,
        Integer recordVersion,
        Operator operator) {

        this.passwordHistoryId = passwordHistoryId;
        this.operatorId = operatorId;
        this.changeDateTime = changeDateTime;
        this.password = password;
        this.changeType = changeType;
        this.recordVersion = recordVersion;
        this.operator = operator;
    }

    // ファクトリーメソッド
    public static PasswordHistory createFrom(
        Long passwordHistoryId,
        Long operatorId,
        LocalDateTime changeDateTime,
        String password,
        Short changeType,
        Integer recordVersion,
        Operator operator) {

        return new PasswordHistory(
            passwordHistoryId,
            operatorId,
            changeDateTime,
            password,
            changeType,
            recordVersion,
            operator);
    }

    // Getter
    public Long getPasswordHistoryId() {
        return passwordHistoryId;
    }
    public Long getOperatorId() {
        return operatorId;
    }
    public LocalDateTime getChangeDateTime() {
        return changeDateTime;
    }
    public String getPassword() {
        return password;
    }
    public Short getChangeType() {
        return changeType;
    }
    public Integer getRecordVersion() {
        return recordVersion;
    }
    public Operator getOperator() {
        return operator;
    }
}
