package net.jagunma.backbone.auth.authmanager.infra.web.oa11050;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantCommand.BizTranRoleGrantRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;

/**
 * OA11050 適用 Converter
 * アサインロール
 */
class Oa11050ApplyAssignRoleConverter implements BizTranRoleGrantRequestAssignRole {

    /**
     * OA11050 AssignRoleTable ViewObject
     */
    private final Oa11050AssignRoleTableVo vo;

    // コンストラクタ
    Oa11050ApplyAssignRoleConverter(Oa11050AssignRoleTableVo vo) {
        this.vo = vo;
    }

    // ファクトリーメソッド
    public static Oa11050ApplyAssignRoleConverter with(Oa11050AssignRoleTableVo vo) {
        return new Oa11050ApplyAssignRoleConverter(vo);
    }

    /**
     * 取引ロールのＧｅｔ
     *
     * @return 取引ロール
     */
    public BizTranRole getBizTranRole() {
        return BizTranRole.createFrom(vo.getRoleId(), vo.getRoleCode(), vo.getRoleName(), null, null, null);
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
}
