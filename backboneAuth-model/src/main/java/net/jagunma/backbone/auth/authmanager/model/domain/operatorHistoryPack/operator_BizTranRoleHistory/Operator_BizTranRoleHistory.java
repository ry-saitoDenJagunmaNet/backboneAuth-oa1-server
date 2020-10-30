package net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operator_BizTranRoleHistory;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRole;

/**
 * オペレーター_サブシステムロール割当履歴
 */
public class Operator_BizTranRoleHistory {

    private final Long operatorHistoryId;
    private final Long operator_BizTranRoleId;
    private final Long operatorId;
    private final Long bizTranRoleId;
    private final LocalDate expirationStartDate;
    private final LocalDate expirationEndDate;
    private final Integer recordVersion;
    private final BizTranRole bizTranRole;

    // コンストラクタ
    Operator_BizTranRoleHistory(
        Long operatorHistoryId,
        Long operator_BizTranRoleId,
        Long operatorId,
        Long bizTranRoleId,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Integer recordVersion,
        BizTranRole bizTranRole) {

        this.operatorHistoryId = operatorHistoryId;
        this.operator_BizTranRoleId = operator_BizTranRoleId;
        this.operatorId = operatorId;
        this.bizTranRoleId = bizTranRoleId;
        this.expirationStartDate = expirationStartDate;
        this.expirationEndDate = expirationEndDate;
        this.recordVersion = recordVersion;
        this.bizTranRole = bizTranRole;
    }

    // ファクトリーメソッド
    public static Operator_BizTranRoleHistory createFrom(
        Long operatorHistoryId,
        Long operator_BizTranRoleId,
        Long operatorId,
        Long bizTranRoleId,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Integer recordVersion,
        BizTranRole bizTranRole) {

        return new Operator_BizTranRoleHistory(
            operatorHistoryId,
            operator_BizTranRoleId,
            operatorId,
            bizTranRoleId,
            expirationStartDate,
            expirationEndDate,
            recordVersion,
            bizTranRole);
    }

    // Getter
    public Long getOperatorHistoryId() {
        return operatorHistoryId;
    }
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
    public Integer getRecordVersion() {
        return recordVersion;
    }
    public BizTranRole getBizTranRole() {
        return bizTranRole;
    }
}