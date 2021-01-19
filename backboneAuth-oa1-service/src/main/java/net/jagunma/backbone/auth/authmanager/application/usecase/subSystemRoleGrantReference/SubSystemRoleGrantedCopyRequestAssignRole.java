package net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;

/**
 * サブシステムロール付与コピーサービス Request
 * アサインロール
 */
public interface SubSystemRoleGrantedCopyRequestAssignRole {
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
    /**
     * 変更可否のＧｅｔ
     *
     * @return 変更可否
     */
    Boolean getIsModifiable();
}
