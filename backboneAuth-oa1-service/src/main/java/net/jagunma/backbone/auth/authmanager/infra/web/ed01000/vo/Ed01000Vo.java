package net.jagunma.backbone.auth.authmanager.infra.web.ed01000.vo;

import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfVo;
import net.jagunma.common.util.strings2.Strings2;

/**
 * ED01010 ViewObject
 */
public class Ed01000Vo extends BaseOfVo {

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
     * redirect uri
     */
    private String redirectUri;
    /**
     * 画面表示モード
     * （1:初期認証、2:継続再認証）
     */
    private Short mode;

    // コンストラクタ
    public Ed01000Vo() {};

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
    public String getRedirectUri() {
        return redirectUri;
    }
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
    public Short getMode() {
        return mode;
    }
    public void setMode(Short mode) {
        this.mode = mode;
    }
}
