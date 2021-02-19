package net.jagunma.backbone.auth.authmanager.infra.web.ed01000;

import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01000.vo.Ed01000Vo;

/**
 * Ed01000 サインイン Converter
 */
public class Ed01000SignInConverter implements SignInRequest {

    /**
     * ED01010 View Object
     */
    private Ed01000Vo vo;
    /**
     * クライアントIPアドレス
     */
    private String clientIpaddress;

    // コンストラクタ
    Ed01000SignInConverter(
        Ed01000Vo vo,
        String clientIpaddress) {

        this.vo = vo;
        this.clientIpaddress = clientIpaddress;
    }

    // ファクトリーメソッド
    public static Ed01000SignInConverter with(
        Ed01000Vo vo,
        String clientIpaddress) {

        return new Ed01000SignInConverter(vo, clientIpaddress);
    }

    // Getter
    public String getOperatorCode() {
        return vo.getOperatorCode();
    }
    public String getPassword() {
        return vo.getPassword();
    }
    public Integer getMode() {
        return vo.getMode();
    }
    public String getClientIpaddress() {
        return clientIpaddress;
    }
}
