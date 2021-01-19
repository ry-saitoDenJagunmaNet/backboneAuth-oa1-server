package net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference;

import java.util.List;

/**
 * サブシステムロール付与コピーサービス Request
 */
public interface SubSystemRoleGrantedCopyRequest {

    /**
     * サインインオペレーターIDのＧｅｔ
     *
     * @return サインインオペレーターID
     */
    Long getSignInOperatorId();
    /**
     * 選択オペレーターIDのＧｅｔ
     *
     * @return 選択オペレーターID
     */
    Long getSelectedOperatorId();
    /**
     * アサインロールリストのＧｅｔ
     *
     * @return アサインロールリスト
     */
    List<SubSystemRoleGrantedCopyRequestAssignRole> getAssignRoleList();
}
