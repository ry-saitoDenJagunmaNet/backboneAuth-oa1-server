package net.jagunma.backbone.auth.authmanager.infra.web.oa10000;

import net.jagunma.backbone.auth.authmanager.application.usecase.profileReference.ProfileSearchRequest;

public class Oa10000SearchProfileConverter implements ProfileSearchRequest {

    private final String operatorCode;

    // コンストラクタ
    Oa10000SearchProfileConverter(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    // ファクトリーメソッド
    public static Oa10000SearchProfileConverter with(String operatorCode) {
        return new Oa10000SearchProfileConverter(operatorCode);
    }

    /**
     * オペレーターコードのＧｅｔ
     *
     * @return オペレーターコード
     */
    public String getOperatorCode() {
        return operatorCode;
    }
}
