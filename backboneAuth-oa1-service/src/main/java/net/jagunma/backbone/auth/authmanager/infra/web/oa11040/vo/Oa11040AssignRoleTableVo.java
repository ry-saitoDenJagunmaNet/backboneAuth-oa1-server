package net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo;

import java.time.LocalDate;

/**
 * OA11040 付与ロールテーブル
 */
public class Oa11040AssignRoleTableVo {

    private static final long serialVersionUID = 1L;

    /**
     * ロールコード
     */
    private String roleCode;
    /**
     * ロール名
     */
    private String roleName;
    /**
     * 有効期限（開始日）
     */
    private LocalDate validThruStartDate;
    /**
     * 有効期限（終了日）
     */
    private LocalDate validThruEndDate;
    /**
     * 変更可否
     */
    private Boolean isModifiable;

    // Getter
    public String getRoleCode() {
        return roleCode;
    }
    public String getRoleName() {
        return roleName;
    }
    public LocalDate getValidThruStartDate() {
        return validThruStartDate;
    }
    public LocalDate getValidThruEndDate() {
        return validThruEndDate;
    }
    public Boolean getIsModifiable() {
        return isModifiable;
    }

    // Setter
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public void setValidThruStartDate(LocalDate validThruStartDate) {
        this.validThruStartDate = validThruStartDate;
    }
    public void setValidThruEndDate(LocalDate validThruEndDate) {
        this.validThruEndDate = validThruEndDate;
    }
    public void setIsModifiable(Boolean isModifiable) {
        this.isModifiable = isModifiable;
    }
}
