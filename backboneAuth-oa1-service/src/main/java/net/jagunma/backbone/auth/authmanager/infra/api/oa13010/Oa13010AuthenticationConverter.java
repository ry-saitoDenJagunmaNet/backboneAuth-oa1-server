package net.jagunma.backbone.auth.authmanager.infra.api.oa13010;

import net.jagunma.backbone.auth.authmanager.application.usecase.authenticationCommand.AuthenticationRequest;

/**
 * Oa13010 認証 Converter
 */
public class Oa13010AuthenticationConverter implements AuthenticationRequest {

    private final String operatorCode;
    private final String password;

    // コンストラクタ
    Oa13010AuthenticationConverter(
        String operatorCode,
        String password) {

        this.operatorCode = operatorCode;
        this.password = password;
    }

    // ファクトリーメソッド
    public static Oa13010AuthenticationConverter with(
        String operatorCode,
        String password) {

        return new Oa13010AuthenticationConverter(operatorCode, password);
    }

    /**
     * オペレータコードのＧｅｔ
     *
     * @return オペレータコード
     */
    public String getOperatorCode() {
        return operatorCode;
    }
    /**
     * パスワードのＧｅｔ
     *
     * @return パスワード
     */
    public String getPassword() {
        return password;
    }}
