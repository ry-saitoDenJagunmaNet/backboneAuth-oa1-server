package net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;

/**
 * オペレーター_サブシステムロール割当
 */
public class Operator_SubSystemRole {

    private final Long operator_SubSystemRoleId;
    private final Long operatorId;
    private final String subSystemRoleCode;
    private final LocalDate expirationStartDate;
    private final LocalDate expirationEndDate;
    private final Integer recordVersion;
    private final Operator operator;
    private final SubSystemRole subSystemRole;

    // コンストラクタ
    Operator_SubSystemRole(
        Long operator_SubSystemRoleId,
        Long operatorId,
        String subSystemRoleCode,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Integer recordVersion,
        Operator operator,
        SubSystemRole subSystemRole) {

        this.operator_SubSystemRoleId = operator_SubSystemRoleId;
        this.operatorId = operatorId;
        this.subSystemRoleCode = subSystemRoleCode;
        this.expirationStartDate = expirationStartDate;
        this.expirationEndDate = expirationEndDate;
        this.recordVersion = recordVersion;
        this.operator = operator;
        this.subSystemRole = subSystemRole;
    }

    // ファクトリーメソッド
    public static Operator_SubSystemRole createFrom(
        Long operator_SubSystemRoleId,
        Long operatorId,
        String subSystemRoleCode,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Integer recordVersion,
        Operator operator,
        SubSystemRole subSystemRole) {

        return new Operator_SubSystemRole(
            operator_SubSystemRoleId,
            operatorId,
            subSystemRoleCode,
            expirationStartDate,
            expirationEndDate,
            recordVersion,
            operator,
            subSystemRole);
    }

    // Getter
    public Long getOperator_SubSystemRoleId() { return operator_SubSystemRoleId; }
    public Long getOperatorId() { return operatorId; }
    public String getSubSystemRoleCode() { return subSystemRoleCode; }
    public LocalDate getExpirationStartDate() { return expirationStartDate; }
    public LocalDate getExpirationEndDate() { return expirationEndDate; }
    public Integer getRecordVersion() { return recordVersion; }
    public Operator getOperator() { return operator; }
    public SubSystemRole getSubSystemRole() { return subSystemRole; }

}
