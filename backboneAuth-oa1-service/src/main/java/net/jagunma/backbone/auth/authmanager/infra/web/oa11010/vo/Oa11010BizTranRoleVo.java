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
    private Boolean bizTranRoleSelected;
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
    private Integer validThruSelect;
    /**
     * 状態指定日
     */
    private LocalDate validThruStatusDate;
    /**
     * 条件指定日開始（開始日）
     */
    private LocalDate validThruStartDateFrom;
    /**
     * 条件指定日開始（終了日）
     */
    private LocalDate validThruStartDateTo;
    /**
     * 条件指定日終了（開始日）
     */
    private LocalDate validThruEndDateFrom;
    /**
     * 条件指定日終了（終了日）
     */
    private LocalDate validThruEndDateTo;

    // Getter／Setter
    public Boolean getBizTranRoleSelected() {
        return bizTranRoleSelected;
    }
    public void setBizTranRoleSelected(Boolean bizTranRoleSelected) {
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
    public Integer getValidThruSelect() {
        return validThruSelect;
    }
    public void setValidThruSelect(Integer validThruSelect) {
        this.validThruSelect = validThruSelect;
    }
    public LocalDate getValidThruStatusDate() {
        return validThruStatusDate;
    }
    public void setValidThruStatusDate(LocalDate validThruStatusDate) {
        this.validThruStatusDate = validThruStatusDate;
    }
    public LocalDate getValidThruStartDateFrom() {
        return validThruStartDateFrom;
    }
    public void setValidThruStartDateFrom(LocalDate validThruStartDateFrom) {
        this.validThruStartDateFrom = validThruStartDateFrom;
    }
    public LocalDate getValidThruStartDateTo() {
        return validThruStartDateTo;
    }
    public void setValidThruStartDateTo(LocalDate validThruStartDateTo) {
        this.validThruStartDateTo = validThruStartDateTo;
    }
    public LocalDate getValidThruEndDateFrom() {
        return validThruEndDateFrom;
    }
    public void setValidThruEndDateFrom(LocalDate validThruEndDateFrom) {
        this.validThruEndDateFrom = validThruEndDateFrom;
    }
    public LocalDate getValidThruEndDateTo() {
        return validThruEndDateTo;
    }
    public void setValidThruEndDateTo(LocalDate validThruEndDateTo) {
        this.validThruEndDateTo = validThruEndDateTo;
    }
}
