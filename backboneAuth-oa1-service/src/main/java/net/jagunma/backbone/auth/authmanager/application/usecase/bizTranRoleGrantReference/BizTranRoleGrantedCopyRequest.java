package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference;

import java.util.List;

/**
 * 取引ロール付与コピーサービス Request
 */
public interface BizTranRoleGrantedCopyRequest {

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
    List<BizTranRoleGrantedCopyRequestAssignRole> getAssignRoleList();
}
