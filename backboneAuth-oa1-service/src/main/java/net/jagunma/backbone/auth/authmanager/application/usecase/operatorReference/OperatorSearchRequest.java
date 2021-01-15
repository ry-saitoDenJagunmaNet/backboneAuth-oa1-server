package net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference;

/**
 * オペレータ検索サービス Request
 */
public interface OperatorSearchRequest {

    /**
     * オペレーターIDのＧｅｔ
     *
     * @return オペレーターID
     */
    Long getOperatorId();
}
