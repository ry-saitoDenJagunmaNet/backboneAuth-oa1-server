package net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleReference;

import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;

/**
 * オペレーター_サブシステムロール割当コピーサービス Response
 */
public interface SubSystemCopyResponse {
    /**
     * オペレーター_サブシステムロール割当群のＳｅｔ
     *
     * @param operator_SubSystemRoles オペレーター_サブシステムロール割当群
     */
    void setOperator_SubSystemRoles(Operator_SubSystemRoles operator_SubSystemRoles);
}
