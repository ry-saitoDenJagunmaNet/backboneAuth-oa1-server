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
    private final LocalDate validThruStartDate;
    private final LocalDate validThruEndDate;
    private final Integer recordVersion;
    private final SubSystemRole subSystemRole;

    // コンストラクタ
    Operator_SubSystemRoleHistory(
        Long operatorHistoryId,
        Long operator_SubSystemRoleId,
        Long operatorId,
        String subSystemRoleCode,
        LocalDate validThruStartDate,
        LocalDate validThruEndDate,
        Integer recordVersion,
        SubSystemRole subSystemRole) {

        this.operatorHistoryId = operatorHistoryId;
        this.operator_SubSystemRoleId = operator_SubSystemRoleId;
        this.operatorId = operatorId;
        this.subSystemRoleCode = subSystemRoleCode;
        this.validThruStartDate = validThruStartDate;
        this.validThruEndDate = validThruEndDate;
        this.recordVersion = recordVersion;
        this.subSystemRole = subSystemRole;
    }

    // ファクトリーメソッド
    public static Operator_SubSystemRoleHistory createFrom(
        Long operatorHistoryId,
        Long operator_SubSystemRoleId,
        Long operatorId,
        String subSystemRoleCode,
        LocalDate validThruStartDate,
        LocalDate validThruEndDate,
        Integer recordVersion,
        SubSystemRole subSystemRole) {

        return new Operator_SubSystemRoleHistory(
            operatorHistoryId,
            operator_SubSystemRoleId,
            operatorId,
            subSystemRoleCode,
            validThruStartDate,
            validThruEndDate,
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
    public LocalDate getValidThruStartDate() { return validThruStartDate; }
    public LocalDate getValidThruEndDate() { return validThruEndDate; }
    public Integer getRecordVersion() { return recordVersion; }
    public SubSystemRole getSubSystemRole() { return subSystemRole; }

}
