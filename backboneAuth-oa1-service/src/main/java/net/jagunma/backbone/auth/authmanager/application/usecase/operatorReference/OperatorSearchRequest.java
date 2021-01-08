package net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference;

/**
 * オペレーターリスト参照サービス Request
 */
public interface OperatorSearchRequest {

    /**
     * オペレーターIDのＧｅｔ
     *
     * @return オペレーターID
     */
    Long getOperatorId();
}
