package net.jagunma.backbone.auth.authmanager.model.domain.accountLock;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.types.AccountLockStatus;

/**
 * アカウントロック
 */
public class AccountLock {

    private final Long accountLockId;
    private final Long operatorId;
    private final LocalDateTime occurredDateTime;
    private final AccountLockStatus lockStatus;
    private final Integer recordVersion;
    private final Operator operator;

    // コンストラクタ
    AccountLock(
        Long accountLockId,
        Long operatorId,
        LocalDateTime occurredDateTime,
        AccountLockStatus lockStatus,
        Integer recordVersion,
        Operator operator) {

        this.accountLockId = accountLockId;
        this.operatorId = operatorId;
        this.occurredDateTime = occurredDateTime;
        this.lockStatus = lockStatus;
        this.recordVersion = recordVersion;
        this.operator = operator;
    }

    // ファクトリーメソッド
    public static AccountLock createFrom(
        Long accountLockId,
        Long operatorId,
        LocalDateTime occurredDateTime,
        AccountLockStatus lockStatus,
        Integer recordVersion,
        Operator operator) {

        return new AccountLock(
            accountLockId,
            operatorId,
            occurredDateTime,
            lockStatus,
            recordVersion,
            operator);
    }

    // Getter
    public Long getAccountLockId() {
        return accountLockId;
    }
    public Long getOperatorId() {
        return operatorId;
    }
    public LocalDateTime getOccurredDateTime() {
        return occurredDateTime;
    }
    public AccountLockStatus getLockStatus() {
        return lockStatus;
    }
    public Integer getRecordVersion() {
        return recordVersion;
    }
    public Operator getOperator() {
        return operator;
    }
}
