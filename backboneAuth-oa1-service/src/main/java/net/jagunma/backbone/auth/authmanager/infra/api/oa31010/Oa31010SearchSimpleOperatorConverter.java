package net.jagunma.backbone.auth.authmanager.infra.api.oa31010;

import net.jagunma.backbone.auth.authmanager.application.api_usecase.operatorReference.SimpleOperatorSearchRequest;

/**
 * Oa31010 オペレーター（簡略版）検索 Converter
 */
public class Oa31010SearchSimpleOperatorConverter implements SimpleOperatorSearchRequest {

    private final String operatorCode;

    // コンストラクタ
    Oa31010SearchSimpleOperatorConverter(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    // ファクトリーメソッド
    public static Oa31010SearchSimpleOperatorConverter with(String operatorCode) {
        return new Oa31010SearchSimpleOperatorConverter(operatorCode);
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
