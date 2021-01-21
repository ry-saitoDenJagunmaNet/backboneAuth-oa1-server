package net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference;

/**
 * サブシステムロール付与検索サービス Request
 */
public interface SubSystemRoleGrantedSearchRequest {

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
