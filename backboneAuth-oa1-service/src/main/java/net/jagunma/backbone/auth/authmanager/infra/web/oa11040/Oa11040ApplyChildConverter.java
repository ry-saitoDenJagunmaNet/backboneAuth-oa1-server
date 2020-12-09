package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleCommand.SubSystemRoleGrantRequestAllocateSubSystemRole;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AllocateRoleTableVo;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;

/**
 * OA11040 サブシステムロール付与サービス Request Converter
 * 割当対象サブシステムロール
 */
class Oa11040ApplyChildConverter implements SubSystemRoleGrantRequestAllocateSubSystemRole {

    /**
     * OA11040 AllocateRoleTable ViewObject
     */
    private final Oa11040AllocateRoleTableVo vo;

    // コンストラクタ
    Oa11040ApplyChildConverter(Oa11040AllocateRoleTableVo vo) {
        this.vo = vo;
    }

    // ファクトリーメソッド
    public static Oa11040ApplyChildConverter with(Oa11040AllocateRoleTableVo vo) {
        return new Oa11040ApplyChildConverter(vo);
    }

    /**
     * サブシステムロールのＧｅｔ
     *
     * @return サブシステムロール
     */
    public SubSystemRole getSubSystemRole() {
        return SubSystemRole.codeOf(vo.getRoleCode());
    };
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
}
