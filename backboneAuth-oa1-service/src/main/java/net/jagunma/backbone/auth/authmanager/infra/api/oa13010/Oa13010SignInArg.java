package net.jagunma.backbone.auth.authmanager.infra.api.oa13010;

/**
 * Oa13010 サインイン Arg
 */
public class Oa13010SignInArg {

    private static final long serialVersionUID = 1L;

    /**
     * オペレーターCode
     */
    private String operatorCode;
    /**
     * パスワード
     */
    private String password;

    // Getter／Setter
    public String getOperatorCode() {
        return operatorCode;
    }
    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
