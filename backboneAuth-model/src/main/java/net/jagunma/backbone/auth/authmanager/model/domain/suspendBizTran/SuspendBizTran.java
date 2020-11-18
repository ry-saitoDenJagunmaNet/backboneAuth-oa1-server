package net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.values.model.branch.BranchAtMoment;

/**
 * 一時取引抑止
 */
public class SuspendBizTran {

    private final Long suspendBizTranId;
    private final Long jaId;
    private final Long branchId;
    private final String subSystemCode;
    private final Long bizTranGrpId;
    private final Long bizTranId;
    private final LocalDate suspendStartDate;
    private final LocalDate suspendEndDate;
    private final String suspendReason;
    private final Integer recordVersion;
    private final BranchAtMoment branchAtMoment;
    private final SubSystem subSystem;
    private final BizTranGrp bizTranGrp;
    private final BizTran bizTran;

    // コンストラクタ
    SuspendBizTran(
        Long suspendBizTranId,
        Long jaId,
        Long branchId,
        String subSystemCode,
        Long bizTranGrpId,
        Long bizTranId,
        LocalDate suspendStartDate,
        LocalDate suspendEndDate,
        String suspendReason,
        Integer recordVersion,
        BranchAtMoment branchAtMoment,
        SubSystem subSystem,
        BizTranGrp bizTranGrp,
        BizTran bizTran) {

        this.suspendBizTranId = suspendBizTranId;
        this.jaId = jaId;
        this.branchId = branchId;
        this.subSystemCode = subSystemCode;
        this.bizTranGrpId = bizTranGrpId;
        this.bizTranId = bizTranId;
        this.suspendStartDate = suspendStartDate;
        this.suspendEndDate = suspendEndDate;
        this.suspendReason = suspendReason;
        this.recordVersion = recordVersion;
        this.branchAtMoment = branchAtMoment;
        this.subSystem = subSystem;
        this.bizTranGrp = bizTranGrp;
        this.bizTran = bizTran;
    }

    // ファクトリーメソッド
    public static SuspendBizTran createFrom(
        Long suspendBizTranId,
        Long jaId,
        Long branchId,
        String subSystemCode,
        Long bizTranGrpId,
        Long bizTranId,
        LocalDate suspendStartDate,
        LocalDate suspendEndDate,
        String suspendReason,
        Integer recordVersion,
        BranchAtMoment branchAtMoment,
        SubSystem subSystem,
        BizTranGrp bizTranGrp,
        BizTran bizTran) {

        return new SuspendBizTran(
            suspendBizTranId,
            jaId,
            branchId,
            subSystemCode,
            bizTranGrpId,
            bizTranId,
            suspendStartDate,
            suspendEndDate,
            suspendReason,
            recordVersion,
            branchAtMoment,
            subSystem,
            bizTranGrp,
            bizTran);
    }

    // Getter
    public Long getSuspendBizTranId() {
        return suspendBizTranId;
    }
    public Long getJaId() {
        return jaId;
    }
    public Long getBranchId() {
        return branchId;
    }
    public String getSubSystemCode() {
        return subSystemCode;
    }
    public Long getBizTranGrpId() {
        return bizTranGrpId;
    }
    public Long getBizTranId() {
        return bizTranId;
    }
    public LocalDate getSuspendStartDate() {
        return suspendStartDate;
    }
    public LocalDate getSuspendEndDate() {
        return suspendEndDate;
    }
    public String getSuspendReason() {
        return suspendReason;
    }
    public Integer getRecordVersion() {
        return recordVersion;
    }
    public BranchAtMoment getBranchAtMoment() {
        return branchAtMoment;
    }
    public SubSystem getSubSystem() {
        return subSystem;
    }
    public BizTranGrp getBizTranGrp() {
        return bizTranGrp;
    }
    public BizTran getBizTran() {
        return bizTran;
    }
}
