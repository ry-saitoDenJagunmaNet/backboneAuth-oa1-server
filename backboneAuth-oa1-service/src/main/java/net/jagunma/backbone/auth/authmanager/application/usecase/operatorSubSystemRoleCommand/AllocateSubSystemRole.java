package net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleCommand;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;

/**
 * サブシステムロール付与サービス Request
 * 割当対象サブシステムロール
 */
public interface AllocateSubSystemRole {
    /**
     * サブシステムロールのＧｅｔ
     *
     * @return サブシステムロール
     */
    SubSystemRole getSubSystemRole();
    /**
     * 有効期限開始日のＧｅｔ
     *
     * @return 有効期限開始日
     */
    LocalDate getValidThruStartDate();
    /**
     * 有効期限終了日のＧｅｔ
     *
     * @return 有効期限終了日
     */
    LocalDate getValidThruEndDate();
}
