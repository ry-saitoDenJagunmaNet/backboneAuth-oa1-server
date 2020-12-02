package net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * OA11010サブシステムロール一覧行
 */
public class Oa11010SubSystemRoleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * サブシステムロール選択
     */
    private Boolean subSystemRoleSelected;
    /**
     * サブシステムロールコード
     */
    private String subSystemRoleCode;
    /**
     * サブシステムロール名
     */
    private String subSystemRoleName;
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
    public Boolean getSubSystemRoleSelected() {
        return subSystemRoleSelected;
    }
    public void setSubSystemRoleSelected(Boolean subSystemRoleSelected) {
        this.subSystemRoleSelected = subSystemRoleSelected;
    }
    public String getSubSystemRoleCode() {
        return subSystemRoleCode;
    }
    public void setSubSystemRoleCode(String subSystemRoleCode) {
        this.subSystemRoleCode = subSystemRoleCode;
    }
    public String getSubSystemRoleName() {
        return subSystemRoleName;
    }
    public void setSubSystemRoleName(String subSystemRoleName) {
        this.subSystemRoleName = subSystemRoleName;
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
