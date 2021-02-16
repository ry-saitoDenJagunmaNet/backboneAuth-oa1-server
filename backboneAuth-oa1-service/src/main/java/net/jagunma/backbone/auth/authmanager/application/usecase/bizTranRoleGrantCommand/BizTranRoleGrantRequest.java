package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantCommand;

import java.util.List;

/**
 * 取引ロール付与サービス Request
 */
public interface BizTranRoleGrantRequest {
    /**
     * オペレーターIDのＧｅｔ
     *
     * @return オペレーターID
     */
    Long getOperatorId();
    /**
     * アサインロールリストのＧｅｔ
     *
     * @return アサインロールリスト
     */
    List<BizTranRoleGrantRequestAssignRole> getAssignRoleList();
    /**
     * 変更事由のＧｅｔ
     *
     * @return 変更事由
     */
    String getChangeCause();
}
