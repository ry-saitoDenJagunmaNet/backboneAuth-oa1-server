package net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleCommand;

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
     * 割当対象サブシステムロールリスト
     *
     * @return 割当対象サブシステムロールリスト
     */
    List<AllocateSubSystemRole> getAllocateSubSystemRoleList();
    /**
     * 変更事由のＧｅｔ
     *
     * @return 変更事由
     */
    String getChangeCause();
}
