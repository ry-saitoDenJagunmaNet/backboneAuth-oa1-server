package net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo;

/**
 * OA11030 サブシステムロールテーブル
 */
public class Oa11030SubsystemRoleTableVo {

    private static final long serialVersionUID = 1L;

    /**
     * 付与ロール名
     */
    private String roleName;
    /**
     * 有効期限
     */
    private String expirationDate;

    // Getter
    public String getRoleName() {
        return roleName;
    }
    public String getExpirationDate() {
        return expirationDate;
    }

    // Setter
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
