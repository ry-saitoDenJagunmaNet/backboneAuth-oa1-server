package net.jagunma.backbone.auth.authmanager.infra.api.oa31010;

/**
 * Oa31010 サインイン Arg
 */
public class Oa31010SignInArg {

    private static final long serialVersionUID = 1L;

    /**
     * オペレーターコード
     */
    private String operatorCode;
    /**
     * パスワード
     */
    private String password;
    /**
     * クライアントIPアドレス
     */
    private String clientIpaddress;

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
    public String getClientIpaddress() {
        return clientIpaddress;
    }
    public void setClientIpaddress(String clientIpaddress) {
        this.clientIpaddress = clientIpaddress;
    }
}
