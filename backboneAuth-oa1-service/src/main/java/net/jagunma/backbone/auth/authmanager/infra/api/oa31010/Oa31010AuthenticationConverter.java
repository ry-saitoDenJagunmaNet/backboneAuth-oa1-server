package net.jagunma.backbone.auth.authmanager.infra.api.oa31010;

import net.jagunma.backbone.auth.authmanager.application.api_usecase.authenticationCommand.AuthenticationRequest;

/**
 * Oa31010 認証 Converter
 */
public class Oa31010AuthenticationConverter implements AuthenticationRequest {

    private final String operatorCode;
    private final String password;

    // コンストラクタ
    Oa31010AuthenticationConverter(
        String operatorCode,
        String password) {

        this.operatorCode = operatorCode;
        this.password = password;
    }

    // ファクトリーメソッド
    public static Oa31010AuthenticationConverter with(
        String operatorCode,
        String password) {

        return new Oa31010AuthenticationConverter(operatorCode, password);
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
