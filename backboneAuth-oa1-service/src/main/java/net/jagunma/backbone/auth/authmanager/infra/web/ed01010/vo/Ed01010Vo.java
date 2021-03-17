package net.jagunma.backbone.auth.authmanager.infra.web.ed01010.vo;

import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfVo;

/**
 * ED01010 ViewObject
 */
public class Ed01010Vo extends BaseOfVo {

    private static final long serialVersionUID = 1L;

    /**
     * モード
     */
    private String mode;
    /**
     * オペレーターID
     */
    private Long operatorId;
    /**
     * ＪＡ
     */
    private String ja;
    /**
     * オペレーター
     */
    private String operator;
    /**
     * 古いパスワード
     */
    private String oldPassword;
    /**
     * 新しいパスワード・パスワード
     */
    private String newPassword;
    /**
     * パスワードの確認入力
     */
    private String confirmPassword;
    /**
     * リダイレクトURIの確認入力
     */
    private String redirectUri;
    /**
     * アクセストークンの確認入力
     */
    private String accessToken;

    // Getter
    public String getMode() {
        return mode;
    }
    public String getJa() {
        return ja;
    }
    public Long getOperatorId() {
        return operatorId;
    }
    public String getOperator() {
        return operator;
    }
    public String getOldPassword() {
        return oldPassword;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public String getRedirectUri() {
        return redirectUri;
    }
    public String getAccessToken() {
        return accessToken;
    }

    // Setter
    public void setMode(String mode) {
        this.mode = mode;
    }
    public void setJa(String ja) {
        this.ja = ja;
    }
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
