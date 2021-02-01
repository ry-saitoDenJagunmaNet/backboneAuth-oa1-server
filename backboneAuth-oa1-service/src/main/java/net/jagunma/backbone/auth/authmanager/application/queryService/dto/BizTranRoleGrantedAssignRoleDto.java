package net.jagunma.backbone.auth.authmanager.application.queryService.dto;

import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;

/**
 * 取引ロール付与検索サービス Response
 * 取引ロール付与コピーサービス Response
 * アサインロールDto
 */
public class BizTranRoleGrantedAssignRoleDto {

    private Operator_BizTranRole operator_BizTranRole;
    private Boolean isModifiable;

    // コンストラクタ
    public BizTranRoleGrantedAssignRoleDto() {
    }

    /**
     * オペレーター_取引ロール割当のＳｅｔ
     *
     * @param operator_BizTranRole オペレーター_取引ロール割当
     */
    public void setOperator_BizTranRole(Operator_BizTranRole operator_BizTranRole) {
        this.operator_BizTranRole = operator_BizTranRole;
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
     * オペレーター_取引ロール割当のＧｅｔ
     *
     * @return オペレーター_取引ロール割当
     */
    public Operator_BizTranRole getOperator_BizTranRole() {
        return operator_BizTranRole;
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
