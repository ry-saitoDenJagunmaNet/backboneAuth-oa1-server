package net.jagunma.backbone.auth.authmanager.application.queryService.dto;

import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;

/**
 * 取引ロール付与検索サービス Response
 * 全ロールDto
 */
public class BizTranRoleGrantedAllRoleDto {

    private BizTranRole bizTranRole;
    private Boolean isModifiable;

    // コンストラクタ
    public BizTranRoleGrantedAllRoleDto() {
    }

    /**
     * 取引ロールのＳｅｔ
     *
     * @param bizTranRole 取引ロール
     */
    public void setBizTranRole(BizTranRole bizTranRole) {
        this.bizTranRole = bizTranRole;
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
     * 取引ロールのＧｅｔ
     *
     * @return 取引ロール
     */
    public BizTranRole getBizTranRole() {
        return bizTranRole;
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
