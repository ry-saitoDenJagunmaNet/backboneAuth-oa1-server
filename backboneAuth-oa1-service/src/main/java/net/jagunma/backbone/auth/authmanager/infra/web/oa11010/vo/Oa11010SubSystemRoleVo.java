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
