package net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo;

import java.time.LocalDate;

/**
 * OA11010取引ロール一覧行
 */
public class Oa11010BizTranRoleVo {

    private static final long serialVersionUID = 1L;

    /**
     * 取引ロール選択
     */
    private Short bizTranRoleSelected;
    /**
     * 取引ロールID
     */
    private long bizTranRoleId;
    /**
     * 取引ロールコード
     */
    private String bizTranRoleCode;
    /**
     * 取引ロール名
     */
    private String bizTranRoleName;
    /**
     * サブシステムコード
     */
    private String subSystemCode;
    /**
     * 有効期限選択
     */
    private Integer expirationSelect;
    /**
     * 状態指定日
     */
    private LocalDate expirationStatusDate;
    /**
     * 条件指定日開始（開始日）
     */
    private LocalDate expirationStartDateFrom;
    /**
     * 条件指定日開始（終了日）
     */
    private LocalDate expirationStartDateTo;
    /**
     * 条件指定日終了（開始日）
     */
    private LocalDate expirationEndDateFrom;
    /**
     * 条件指定日終了（終了日）
     */
    private LocalDate expirationEndDateTo;

    // Getter／Setter
    public Short getBizTranRoleSelected() {
        return bizTranRoleSelected;
    }
    public void setBizTranRoleSelected(Short bizTranRoleSelected) {
        this.bizTranRoleSelected = bizTranRoleSelected;
    }
    public long getBizTranRoleId() {
        return bizTranRoleId;
    }
    public void setBizTranRoleId(long bizTranRoleId) {
        this.bizTranRoleId = bizTranRoleId;
    }
    public String getBizTranRoleCode() {
        return bizTranRoleCode;
    }
    public void setBizTranRoleCode(String bizTranRoleCode) {
        this.bizTranRoleCode = bizTranRoleCode;
    }
    public String getBizTranRoleName() {
        return bizTranRoleName;
    }
    public void setBizTranRoleName(String bizTranRoleName) {
        this.bizTranRoleName = bizTranRoleName;
    }
    public String getSubSystemCode() {
        return subSystemCode;
    }
    public void setSubSystemCode(String subSystemCode) {
        this.subSystemCode = subSystemCode;
    }
    public Integer getExpirationSelect() {
        return expirationSelect;
    }
    public void setExpirationSelect(Integer expirationSelect) {
        this.expirationSelect = expirationSelect;
    }
    public LocalDate getExpirationStatusDate() {
        return expirationStatusDate;
    }
    public void setExpirationStatusDate(LocalDate expirationStatusDate) {
        this.expirationStatusDate = expirationStatusDate;
    }
    public LocalDate getExpirationStartDateFrom() {
        return expirationStartDateFrom;
    }
    public void setExpirationStartDateFrom(LocalDate expirationStartDateFrom) {
        this.expirationStartDateFrom = expirationStartDateFrom;
    }
    public LocalDate getExpirationStartDateTo() {
        return expirationStartDateTo;
    }
    public void setExpirationStartDateTo(LocalDate expirationStartDateTo) {
        this.expirationStartDateTo = expirationStartDateTo;
    }
    public LocalDate getExpirationEndDateFrom() {
        return expirationEndDateFrom;
    }
    public void setExpirationEndDateFrom(LocalDate expirationEndDateFrom) {
        this.expirationEndDateFrom = expirationEndDateFrom;
    }
    public LocalDate getExpirationEndDateTo() {
        return expirationEndDateTo;
    }
    public void setExpirationEndDateTo(LocalDate expirationEndDateTo) {
        this.expirationEndDateTo = expirationEndDateTo;
    }
}
