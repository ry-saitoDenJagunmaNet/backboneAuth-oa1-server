package net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo;

/**
 * OA11030 サブシステムロールテーブル
 */
public class Oa11030SubSystemRoleTableVo {

    private static final long serialVersionUID = 1L;

    /**
     * 付与ロール名
     */
    private String roleName;
    /**
     * 有効期限
     */
    private String validThruDate;

    // Getter
    public String getRoleName() {
        return roleName;
    }
    public String getValidThruDate() {
        return validThruDate;
    }

    // Setter
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public void setValidThruDate(String validThruDate) {
        this.validThruDate = validThruDate;
    }
}
