package net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;

/**
 * オペレーター_サブシステムロール割当
 */
public class Operator_BizTranRole {

    private final Long operator_BizTranRoleId;
    private final Long operatorId;
    private final Long bizTranRoleId;
    private final LocalDate validThruStartDate;
    private final LocalDate validThruEndDate;
    private final Integer recordVersion;
    private final Operator operator;
    private final BizTranRole bizTranRole;

    // コンストラクタ
    Operator_BizTranRole(
        Long operator_BizTranRoleId,
        Long operatorId,
        Long bizTranRoleId,
        LocalDate validThruStartDate,
        LocalDate validThruEndDate,
        Integer recordVersion,
        Operator operator,
        BizTranRole bizTranRole) {

        this.operator_BizTranRoleId = operator_BizTranRoleId;
        this.operatorId = operatorId;
        this.bizTranRoleId = bizTranRoleId;
        this.validThruStartDate = validThruStartDate;
        this.validThruEndDate = validThruEndDate;
        this.recordVersion = recordVersion;
        this.operator = operator;
        this.bizTranRole = bizTranRole;
    }

    // ファクトリーメソッド
    public static Operator_BizTranRole createFrom(
        Long operator_BizTranRoleId,
        Long operatorId,
        Long bizTranRoleId,
        LocalDate validThruStartDate,
        LocalDate validThruEndDate,
        Integer recordVersion,
        Operator operator,
        BizTranRole bizTranRole) {

        return new Operator_BizTranRole(
            operator_BizTranRoleId,
            operatorId,
            bizTranRoleId,
            validThruStartDate,
            validThruEndDate,
            recordVersion,
            operator,
            bizTranRole);
    }

    // Getter
    public Long getOperator_BizTranRoleId() {
        return operator_BizTranRoleId;
    }
    public Long getOperatorId() {
        return operatorId;
    }
    public Long getBizTranRoleId() {
        return bizTranRoleId;
    }
    public LocalDate getValidThruStartDate() {
        return validThruStartDate;
    }
    public LocalDate getValidThruEndDate() {
        return validThruEndDate;
    }
    public Integer getRecordVersion() {
        return recordVersion;
    }
    public Operator getOperator() {
        return operator;
    }
    public BizTranRole getBizTranRole() {
        return bizTranRole;
    }
}