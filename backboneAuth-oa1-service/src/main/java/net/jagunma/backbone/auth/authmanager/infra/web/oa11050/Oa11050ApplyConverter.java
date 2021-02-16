package net.jagunma.backbone.auth.authmanager.infra.web.oa11050;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantCommand.BizTranRoleGrantRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantCommand.BizTranRoleGrantRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050Vo;

/**
 * OA11050 適用 Converter
 */
class Oa11050ApplyConverter implements BizTranRoleGrantRequest {

    /**
     * OA11050 ViewObject
     */
    private final Oa11050Vo vo;

    // コンストラクタ
    Oa11050ApplyConverter(Oa11050Vo vo) {
        this.vo = vo;
    }

    // ファクトリーメソッド
    public static Oa11050ApplyConverter with(Oa11050Vo vo) {
        return new Oa11050ApplyConverter(vo);
    }

    /**
     * オペレーターIDのＧｅｔ
     *
     * @return オペレーターID
     */
    public Long getOperatorId() {
        return vo.getOperatorId();
    }
    /**
     * アサインロールリストのＧｅｔ
     *
     * @return アサインロールリスト
     */
    public List<BizTranRoleGrantRequestAssignRole> getAssignRoleList() {
        if (vo.getAssignRoleTableVoList() == null) {
            return newArrayList();
        }
        List<Oa11050AssignRoleTableVo> assignRoleTableVoList = vo.getAssignRoleTableVoList();
        List<BizTranRoleGrantRequestAssignRole> assignRoleList = newArrayList();
        for (Oa11050AssignRoleTableVo assignRoleTableVo : assignRoleTableVoList) {
            Oa11050ApplyAssignRoleConverter oa11050ApplyAssignRoleConverter = Oa11050ApplyAssignRoleConverter.with(assignRoleTableVo);
            assignRoleList.add(oa11050ApplyAssignRoleConverter);
        }
        return assignRoleList;
    }
    /**
     * 変更事由のＧｅｔ
     *
     * @return 変更事由
     */
    public String getChangeCause() {
        return vo.getChangeCause();
    }
}
