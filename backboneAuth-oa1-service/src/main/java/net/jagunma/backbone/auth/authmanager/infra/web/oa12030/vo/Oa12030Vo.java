package net.jagunma.backbone.auth.authmanager.infra.web.oa12030.vo;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfResponseVo;

/**
 * OA12030 View Object
 */
public class Oa12030Vo extends BaseOfResponseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 一時取引抑止ID
     */
    private Long suspendBizTranId;
    /**
     * ＪＡコード
     */
    private String jaCode;
    /**
     * 店舗コード
     */
    private String branchCode;
    /**
     * サブシステムコード
     */
    private String subSystemCode;
    /**
     * 取引グループコード
     */
    private String bizTranGrpCode;
    /**
     * 取引コード
     */
    private String bizTranCode;
    /**
     * 抑止期間開始日
     */
    private LocalDate suspendStartDate;
    /**
     * 抑止期間終了日
     */
    private LocalDate suspendEndDate;
    /**
     * 抑止理由
     */
    private String suspendReason;
    /**
     * レコードバージョン
     */
    private Integer recordVersion;

    // Getter／Setter
    public Long getSuspendBizTranId() {
        return suspendBizTranId;
    }
    public void setSuspendBizTranId(Long suspendBizTranId) {
        this.suspendBizTranId = suspendBizTranId;
    }
    public String getJaCode() {
        return jaCode;
    }
    public void setJaCode(String jaCode) {
        this.jaCode = jaCode;
    }
    public String getBranchCode() {
        return branchCode;
    }
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
    public String getSubSystemCode() {
        return subSystemCode;
    }
    public void setSubSystemCode(String subSystemCode) {
        this.subSystemCode = subSystemCode;
    }
    public String getBizTranGrpCode() {
        return bizTranGrpCode;
    }
    public void setBizTranGrpCode(String bizTranGrpCode) {
        this.bizTranGrpCode = bizTranGrpCode;
    }
    public String getBizTranCode() {
        return bizTranCode;
    }
    public void setBizTranCode(String bizTranCode) {
        this.bizTranCode = bizTranCode;
    }
    public LocalDate getSuspendStartDate() {
        return suspendStartDate;
    }
    public void setSuspendStartDate(LocalDate suspendStartDate) {
        this.suspendStartDate = suspendStartDate;
    }
    public LocalDate getSuspendEndDate() {
        return suspendEndDate;
    }
    public void setSuspendEndDate(LocalDate suspendEndDate) {
        this.suspendEndDate = suspendEndDate;
    }
    public String getSuspendReason() {
        return suspendReason;
    }
    public void setSuspendReason(String suspendReason) {
        this.suspendReason = suspendReason;
    }
    public Integer getRecordVersion() {
        return recordVersion;
    }
    public void setRecordVersion(Integer recordVersion) {
        this.recordVersion = recordVersion;
    }
}
