package net.jagunma.backbone.auth.authmanager.infra.web.oa12020.vo;

import java.time.LocalDate;

/**
 * OA12020 一時取引抑止検索結果一覧行
 */
public class Oa12020SearchResultVo {

    /**
     * 一時取引抑止ID
     */
    private Long suspendBizTranId;
    /**
     * JAコード
     */
    private String jaCode;
    /**
     * JA名
     */
    private String jaName;
    /**
     * 店舗コード
     */
    private String branchCode;
    /**
     * 店舗名
     */
    private String branchName;
    /**
     * サブシステム名
     */
    private String subSystemName;
    /**
     * 取引グループコード
     */
    private String bizTranGrpCode;
    /**
     * 取引グループ名
     */
    private String bizTranGrpName;
    /**
     * 取引コード
     */
    private String bizTranCode;
    /**
     取引名
     */
    private String bizTranName;
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
    public String getJaName() {
        return jaName;
    }
    public void setJaName(String jaName) {
        this.jaName = jaName;
    }
    public String getBranchCode() {
        return branchCode;
    }
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
    public String getBranchName() {
        return branchName;
    }
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    public String getSubSystemName() {
        return subSystemName;
    }
    public void setSubSystemName(String subSystemName) {
        this.subSystemName = subSystemName;
    }
    public String getBizTranGrpCode() {
        return bizTranGrpCode;
    }
    public void setBizTranGrpCode(String bizTranGrpCode) {
        this.bizTranGrpCode = bizTranGrpCode;
    }
    public String getBizTranGrpName() {
        return bizTranGrpName;
    }
    public void setBizTranGrpName(String bizTranGrpName) {
        this.bizTranGrpName = bizTranGrpName;
    }
    public String getBizTranCode() {
        return bizTranCode;
    }
    public void setBizTranCode(String bizTranCode) {
        this.bizTranCode = bizTranCode;
    }
    public String getBizTranName() {
        return bizTranName;
    }
    public void setBizTranName(String bizTranName) {
        this.bizTranName = bizTranName;
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
}
