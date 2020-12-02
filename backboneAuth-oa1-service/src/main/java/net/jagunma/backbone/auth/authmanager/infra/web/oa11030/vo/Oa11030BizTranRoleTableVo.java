package net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo;

/**
 * OA11030 取引ロールテーブル
 */
public class Oa11030BizTranRoleTableVo {

    private static final long serialVersionUID = 1L;

    /**
     * 付与ロールコード
     */
    private String roleCode;
    /**
     * 付与ロール名
     */
    private String roleName;
    /**
     * 有効期限
     */
    private String validThruDate;

    // Getter
    public String getRoleCode() {
        return roleCode;
    }
    public String getRoleName() {
        return roleName;
    }
    public String getValidThruDate() {
        return validThruDate;
    }

    // Setter
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public void setValidThruDate(String validThruDate) {
        this.validThruDate = validThruDate;
    }
}
