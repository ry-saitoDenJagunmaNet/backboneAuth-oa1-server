package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedCopyRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;

/**
 * OA11040 コピー Converter
 * アサインロール
 */
class Oa11040CopyAssignRoleConverter implements SubSystemRoleGrantedCopyRequestAssignRole {

    /**
     * OA11040 AssignRoleTable ViewObject
     */
    private final Oa11040AssignRoleTableVo vo;

    // コンストラクタ
    Oa11040CopyAssignRoleConverter(Oa11040AssignRoleTableVo vo) {
        this.vo = vo;
    }

    // ファクトリーメソッド
    public static Oa11040CopyAssignRoleConverter with(Oa11040AssignRoleTableVo vo) {
        return new Oa11040CopyAssignRoleConverter(vo);
    }

    /**
     * サブシステムロールのＧｅｔ
     *
     * @return サブシステムロール
     */
    public SubSystemRole getSubSystemRole() {
        return SubSystemRole.codeOf(vo.getRoleCode());
    }
    /**
     * 有効期限開始日のＧｅｔ
     *
     * @return 有効期限開始日
     */
    public LocalDate getValidThruStartDate() {
        return vo.getValidThruStartDate();
    }
    /**
     * 有効期限終了日のＧｅｔ
     *
     * @return 有効期限終了日
     */
    public LocalDate getValidThruEndDate() {
        return vo.getValidThruEndDate();
    }
    /**
     * 変更可否のＧｅｔ
     *
     * @return 変更可否
     */
    public Boolean getIsModifiable() {
        return vo.getIsModifiable();
    }
}
