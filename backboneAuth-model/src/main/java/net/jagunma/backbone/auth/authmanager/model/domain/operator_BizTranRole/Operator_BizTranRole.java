package net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRole;

/**
 * オペレーター_サブシステムロール割当
 */
public class Operator_BizTranRole {

    private final Long operator_BizTranRoleId;
    private final Long operatorId;
    private final Long bizTranRoleId;
    private final LocalDate expirationStartDate;
    private final LocalDate expirationEndDate;
    private final BizTranRole bizTranRole;

    // コンストラクタ
    Operator_BizTranRole(
        Long operator_BizTranRoleId,
        Long operatorId,
        Long bizTranRoleId,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        BizTranRole bizTranRole) {

        this.operator_BizTranRoleId = operator_BizTranRoleId;
        this.operatorId = operatorId;
        this.bizTranRoleId = bizTranRoleId;
        this.expirationStartDate = expirationStartDate;
        this.expirationEndDate = expirationEndDate;
        this.bizTranRole = bizTranRole;
    }

    // ファクトリーメソッド
    public static Operator_BizTranRole createFrom(
        Long operator_BizTranRoleId,
        Long operatorId,
        Long bizTranRoleId,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        BizTranRole bizTranRole) {

        return new Operator_BizTranRole(
            operator_BizTranRoleId,
            operatorId,
            bizTranRoleId,
            expirationStartDate,
            expirationEndDate,
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
    public BizTranRole getBizTranRole() {
        return bizTranRole;
    }
}
