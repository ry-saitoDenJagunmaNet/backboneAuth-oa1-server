package net.jagunma.backbone.auth.authmanager.infra.web.ed01000;

import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInRequest;

/**
 * Ed01000 サインイン Converter
 */
public class Ed01000SignInConverter implements SignInRequest {

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
    /**
     * モード
     */
    private Integer mode;

    // コンストラクタ
    Ed01000SignInConverter(
        String operatorCode,
        String password,
        String clientIpaddress,
        Integer mode) {

        this.operatorCode = operatorCode;
        this.password = password;
        this.clientIpaddress = clientIpaddress;
        this.mode = mode;
    }

    // ファクトリーメソッド
    public static Ed01000SignInConverter with(
        String operatorCode,
        String password,
        String clientIpaddress,
        Integer mode) {

        return new Ed01000SignInConverter(operatorCode, password, clientIpaddress, mode);
    }

    // Getter
    public String getOperatorCode() {
        return operatorCode;
    }
    public String getPassword() {
        return password;
    }
    public String getClientIpaddress() {
        return clientIpaddress;
    }
    public Integer getMode() {
        return mode;
    }
}
