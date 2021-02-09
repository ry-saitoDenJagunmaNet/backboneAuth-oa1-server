package net.jagunma.backbone.auth.authmanager.infra.web.ed01000.vo;

import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfVo;
import net.jagunma.common.util.strings2.Strings2;

/**
 * ED01010 ViewObject
 */
public class Ed01000Vo extends BaseOfVo {

    private static final long serialVersionUID = 1L;

    /**
     * オペレーターCode
     */
    private String operatorCode;
    /**
     * パスワード
     */
    private String password;
    /**
     * client id
     */
    private String clientId;
    /**
     * redirect uri
     */
    private String redirectUri;
    /**
     * state
     */
    private String state;
    /**
     * code
     */
    private String code;
    /**
     * アクセストークン
     */
    private String accessToken;

    // コンストラクタ
    public Ed01000Vo() {};
    public Ed01000Vo(
        String clientId,
        String redirectUri,
        String state,
        String code,
        String accessToken) {

        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.state = state;
        this.code = code;
        this.accessToken = accessToken;
    }

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
    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getRedirectUri() {
        return redirectUri;
    }
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public Integer getMode() {
        return Strings2.isEmpty(accessToken)? 0 : 1;
    }
}
