package net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;

/**
 * オペレーター_サブシステムロール割当
 */
public class Operator_BizTranRole {

    private final Long operator_BizTranRoleId;
    private final Long operatorId;
    private final Long bizTranRoleId;
    private final LocalDate expirationStartDate;
    private final LocalDate expirationEndDate;
    private final Operator operator;
    private final BizTranRole bizTranRole;

    // コンストラクタ
    Operator_BizTranRole(
        Long operator_BizTranRoleId,
        Long operatorId,
        Long bizTranRoleId,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Operator operator,
        BizTranRole bizTranRole) {

        this.operator_BizTranRoleId = operator_BizTranRoleId;
        this.operatorId = operatorId;
        this.bizTranRoleId = bizTranRoleId;
        this.expirationStartDate = expirationStartDate;
        this.expirationEndDate = expirationEndDate;
        this.operator = operator;
        this.bizTranRole = bizTranRole;
    }

    // ファクトリーメソッド
    public static Operator_BizTranRole createFrom(
        Long operator_BizTranRoleId,
        Long operatorId,
        Long bizTranRoleId,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Operator operator,
        BizTranRole bizTranRole) {

        return new Operator_BizTranRole(
            operator_BizTranRoleId,
            operatorId,
            bizTranRoleId,
            expirationStartDate,
            expirationEndDate,
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
    public LocalDate getExpirationStartDate() {
        return expirationStartDate;
    }
    public LocalDate getExpirationEndDate() {
        return expirationEndDate;
    }
    public Operator getOperator() {
        return operator;
    }
    public BizTranRole getBizTranRole() {
        return bizTranRole;
    }
}
