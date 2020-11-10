package net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operator_SubSystemRoleHistory;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;

/**
 * オペレーター_サブシステムロール割当履歴
 */
public class Operator_SubSystemRoleHistory {

    private final Long operatorHistoryId;
    private final Long operator_SubSystemRoleId;
    private final Long operatorId;
    private final String subSystemRoleCode;
    private final LocalDate expirationStartDate;
    private final LocalDate expirationEndDate;
    private final Integer recordVersion;
    private final SubSystemRole subSystemRole;

    // コンストラクタ
    Operator_SubSystemRoleHistory(
        Long operatorHistoryId,
        Long operator_SubSystemRoleId,
        Long operatorId,
        String subSystemRoleCode,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Integer recordVersion,
        SubSystemRole subSystemRole) {

        this.operatorHistoryId = operatorHistoryId;
        this.operator_SubSystemRoleId = operator_SubSystemRoleId;
        this.operatorId = operatorId;
        this.subSystemRoleCode = subSystemRoleCode;
        this.expirationStartDate = expirationStartDate;
        this.expirationEndDate = expirationEndDate;
        this.recordVersion = recordVersion;
        this.subSystemRole = subSystemRole;
    }

    // ファクトリーメソッド
    public static Operator_SubSystemRoleHistory createFrom(
        Long operatorHistoryId,
        Long operator_SubSystemRoleId,
        Long operatorId,
        String subSystemRoleCode,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Integer recordVersion,
        SubSystemRole subSystemRole) {

        return new Operator_SubSystemRoleHistory(
            operatorHistoryId,
            operator_SubSystemRoleId,
            operatorId,
            subSystemRoleCode,
            expirationStartDate,
            expirationEndDate,
            recordVersion,
            subSystemRole);
    }

    // Getter
    public Long getOperatorHistoryId() {
        return operatorHistoryId;
    }
    public Long getOperator_SubSystemRoleId() { return operator_SubSystemRoleId; }
    public Long getOperatorId() { return operatorId; }
    public String getSubSystemRoleCode() { return subSystemRoleCode; }
    public LocalDate getExpirationStartDate() { return expirationStartDate; }
    public LocalDate getExpirationEndDate() { return expirationEndDate; }
    public Integer getRecordVersion() { return recordVersion; }
    public SubSystemRole getSubSystemRole() { return subSystemRole; }

}
