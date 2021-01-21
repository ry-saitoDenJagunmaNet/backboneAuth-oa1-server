package net.jagunma.backbone.auth.authmanager.application.queryService.dto;

import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;

/**
 * サブシステムロール付与検索サービス Response
 * 全ロールDto
 */
public class SubSystemRoleGrantedAllRoleDto {

    private SubSystemRole subSystemRole;
    private Boolean isModifiable;

    // コンストラクタ
    public SubSystemRoleGrantedAllRoleDto() {
    }

    /**
     * サブシステムロールのＳｅｔ
     *
     * @param subSystemRole サブシステムロール
     */
    public void setSubSystemRole(SubSystemRole subSystemRole) {
        this.subSystemRole = subSystemRole;
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
     * サブシステムロールのＧｅｔ
     *
     * @return サブシステムロール
     */
    public SubSystemRole getSubSystemRole() {
        return subSystemRole;
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
