package net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;

/**
 * 一時取引抑止
 */
public class SuspendBizTran {

    private final Long suspendBizTranId;
    private final String jaCode;
    private final String branchCode;
    private final String subSystemCode;
    private final String bizTranGrpCode;
    private final String bizTranCode;
    private final LocalDate suspendStartDate;
    private final LocalDate suspendEndDate;
    private final String suspendReason;
    private final Integer recordVersion;
    private final JaAtMoment jaAtMoment;
    private final BranchAtMoment branchAtMoment;
    private final SubSystem subSystem;
    private final Integer subSystemDisplaySortOrder;
    private final BizTranGrp bizTranGrp;
    private final BizTran bizTran;

    // コンストラクタ
    SuspendBizTran(
        Long suspendBizTranId,
        String jaCode,
        String branchCode,
        String subSystemCode,
        String bizTranGrpCode,
        String bizTranCode,
        LocalDate suspendStartDate,
        LocalDate suspendEndDate,
        String suspendReason,
        Integer recordVersion,
        JaAtMoment jaAtMoment,
        BranchAtMoment branchAtMoment,
        SubSystem subSystem,
        BizTranGrp bizTranGrp,
        BizTran bizTran) {

        this.suspendBizTranId = suspendBizTranId;
        this.jaCode = jaCode;
        this.branchCode = branchCode;
        this.subSystemCode = subSystemCode;
        this.bizTranGrpCode = bizTranGrpCode;
        this.bizTranCode = bizTranCode;
        this.suspendStartDate = suspendStartDate;
        this.suspendEndDate = suspendEndDate;
        this.suspendReason = suspendReason;
        this.recordVersion = recordVersion;
        this.jaAtMoment = jaAtMoment;
        this.branchAtMoment = branchAtMoment;
        this.subSystem = subSystem;
        this.subSystemDisplaySortOrder = (subSystem == null)? 0 : subSystem.getDisplaySortOrder();
        this.bizTranGrp = bizTranGrp;
        this.bizTran = bizTran;
    }

    // ファクトリーメソッド
    public static SuspendBizTran createFrom(
        Long suspendBizTranId,
        String jaCode,
        String branchCode,
        String subSystemCode,
        String bizTranGrpCode,
        String bizTranCode,
        LocalDate suspendStartDate,
        LocalDate suspendEndDate,
        String suspendReason,
        Integer recordVersion,
        JaAtMoment jaAtMoment,
        BranchAtMoment branchAtMoment,
        SubSystem subSystem,
        BizTranGrp bizTranGrp,
        BizTran bizTran) {

        return new SuspendBizTran(
            suspendBizTranId,
            jaCode,
            branchCode,
            subSystemCode,
            bizTranGrpCode,
            bizTranCode,
            suspendStartDate,
            suspendEndDate,
            suspendReason,
            recordVersion,
            jaAtMoment,
            branchAtMoment,
            subSystem,
            bizTranGrp,
            bizTran);
    }

    // Getter
    public Long getSuspendBizTranId() {
        return suspendBizTranId;
    }
    public String getJaCode() {
        return jaCode;
    }
    public String getBranchCode() {
        return branchCode;
    }
    public String getSubSystemCode() {
        return subSystemCode;
    }
    public String getBizTranGrpCode() {
        return bizTranGrpCode;
    }
    public String getBizTranCode() {
        return bizTranCode;
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
    public JaAtMoment getJaAtMoment() {
        return jaAtMoment;
    }
    public BranchAtMoment getBranchAtMoment() {
        return branchAtMoment;
    }
    public SubSystem getSubSystem() {
        return subSystem;
    }
    public Integer getSubSystemDisplaySortOrder() {
        return subSystemDisplaySortOrder;
    }
    public BizTranGrp getBizTranGrp() {
        return bizTranGrp;
    }
    public BizTran getBizTran() {
        return bizTran;
    }
}