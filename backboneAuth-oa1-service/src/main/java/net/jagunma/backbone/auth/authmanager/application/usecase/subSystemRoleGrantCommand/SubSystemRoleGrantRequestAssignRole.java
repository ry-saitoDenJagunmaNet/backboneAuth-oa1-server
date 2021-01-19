package net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantCommand;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;

/**
 * サブシステムロール付与サービス Request
 * アサインロール
 */
public interface SubSystemRoleGrantRequestAssignRole {
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
