package net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference;

/**
 * サブシステムロール付与検索サービス Request
 */
public interface SubSystemRoleGrantedSearchRequest {

    /**
     * ターゲットオペレーターIDのＧｅｔ
     *
     * @return ターゲットオペレーターID
     */
    Long getTargetOperatorId();
    /**
     * サインインオペレーターIDのＧｅｔ
     *
     * @return サインインオペレーターID
     */
    Long getSignInOperatorId();
}
