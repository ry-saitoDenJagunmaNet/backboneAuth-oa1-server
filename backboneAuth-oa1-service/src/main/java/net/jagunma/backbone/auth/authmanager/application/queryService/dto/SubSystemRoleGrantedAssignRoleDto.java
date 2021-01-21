package net.jagunma.backbone.auth.authmanager.application.queryService.dto;

import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;

/**
 * サブシステムロール付与検索サービス Response
 * サブシステムロール付与コピーサービス Response
 * アサインロールDto
 */
public class SubSystemRoleGrantedAssignRoleDto {

    private Operator_SubSystemRole operator_SubSystemRole;
    private Boolean isModifiable;

    // コンストラクタ
    public SubSystemRoleGrantedAssignRoleDto() {
    }

    /**
     * オペレーター_サブシステムロール割当のＳｅｔ
     *
     * @param operator_SubSystemRole オペレーター_サブシステムロール割当
     */
    public void setOperator_SubSystemRole(Operator_SubSystemRole operator_SubSystemRole) {
        this.operator_SubSystemRole = operator_SubSystemRole;
    }
    /**
     * 変更可否のＳｅｔ
     *
     * @param isModifiable 変更可否
     */
    public void setIsModifiable(Boolean isModifiable) {
        this.isModifiable = isModifiable;
    }

    /**
     * オペレーター_サブシステムロール割当のＧｅｔ
     *
     * @return オペレーター_サブシステムロール割当
     */
    public Operator_SubSystemRole getOperator_SubSystemRole() {
        return operator_SubSystemRole;
    }
    /**
     * 変更可否のＧｅｔ
     *
     * @return 変更可否
     */
    public Boolean getIsModifiable() {
        return isModifiable;
    }
}
