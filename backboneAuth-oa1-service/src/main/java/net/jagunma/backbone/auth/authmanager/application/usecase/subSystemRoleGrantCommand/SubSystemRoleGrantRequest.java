package net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantCommand;

import java.util.List;

/**
 * サブシステムロール付与サービス Request
 */
public interface SubSystemRoleGrantRequest {
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
    List<SubSystemRoleGrantRequestAssignRole> getAssignRoleList();
    /**
     * 変更事由のＧｅｔ
     *
     * @return 変更事由
     */
    String getChangeCause();
}
