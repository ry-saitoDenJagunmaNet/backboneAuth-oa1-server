package net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo;

/**
 * OA11040 未付与ロールテーブル
 */
public class Oa11040UnallocateRoleTableVo {

    private static final long serialVersionUID = 1L;

    /**
     * ロールコード
     */
    private String roleCode;
    /**
     * ロール名
     */
    private String roleName;

    // Getter
    public String getRoleCode() {
        return roleCode;
    }
    public String getRoleName() {
        return roleName;
    }

    // Setter
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
