package net.jagunma.backbone.auth.authmanager.application.api_usecase.operatorReference;

/**
 * オペレーター（簡略版）検索サービス Request
 */
public interface SimpleOperatorSearchRequest {

    /**
     * オペレーターコードのＧｅｔ
     *
     * @return オペレーターコード
     */
    String getOperatorCode();
}
