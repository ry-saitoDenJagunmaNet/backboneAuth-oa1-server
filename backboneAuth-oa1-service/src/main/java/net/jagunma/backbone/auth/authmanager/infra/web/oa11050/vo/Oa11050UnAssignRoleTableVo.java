package net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo;

/**
 * OA11050 未付与ロールテーブル
 */
public class Oa11050UnAssignRoleTableVo {

    private static final long serialVersionUID = 1L;

    /**
     * ロールID
     */
    private Long roleId;
    /**
     * ロールコード
     */
    private String roleCode;
    /**
     * ロール名
     */
    private String roleName;
    /**
     * サブシステムコード
     */
    private String subSystemCode;
    /**
     * 変更可否
     */
    private Boolean isModifiable;

    // Getter
    public Long getRoleId() {
        return roleId;
    }
    public String getRoleCode() {
        return roleCode;
    }
    public String getRoleName() {
        return roleName;
    }
    public String getSubSystemCode() {
        return subSystemCode;
    }
    public Boolean getIsModifiable() {
        return isModifiable;
    }

    // Setter
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public void setSubSystemCode(String subSystemCode) {
        this.subSystemCode = subSystemCode;
    }
    public void setIsModifiable(Boolean isModifiable) {
        this.isModifiable = isModifiable;
    }
}
