package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference;

/**
 * 取引ロール付与検索サービス Request
 */
public interface BizTranRoleGrantedSearchRequest {

    /**
     * サインインオペレーターIDのＧｅｔ
     *
     * @return サインインオペレーターID
     */
    Long getSignInOperatorId();
    /**
     * ターゲットオペレーターIDのＧｅｔ
     *
     * @return ターゲットオペレーターID
     */
    Long getTargetOperatorId();
}
