package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;

/**
 * 取引ロール付与コピーサービス Request
 * アサインロール
 */
public interface BizTranRoleGrantedCopyRequestAssignRole {
    /**
     * 取引ロールのＧｅｔ
     *
     * @return 取引ロール
     */
    BizTranRole getBizTranRole();
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
