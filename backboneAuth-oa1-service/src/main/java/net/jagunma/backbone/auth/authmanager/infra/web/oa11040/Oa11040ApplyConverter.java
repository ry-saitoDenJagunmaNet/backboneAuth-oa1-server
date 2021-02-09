package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantCommand.SubSystemRoleGrantRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantCommand.SubSystemRoleGrantRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040Vo;

/**
 * OA11040 適用 Converter
 */
class Oa11040ApplyConverter implements SubSystemRoleGrantRequest {

    /**
     * OA11040 ViewObject
     */
    private final Oa11040Vo vo;

    // コンストラクタ
    Oa11040ApplyConverter(Oa11040Vo vo) {
        this.vo = vo;
    }

    // ファクトリーメソッド
    public static Oa11040ApplyConverter with(Oa11040Vo vo) {
        return new Oa11040ApplyConverter(vo);
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
    public List<SubSystemRoleGrantRequestAssignRole> getAssignRoleList() {
        if (vo.getAssignRoleTableVoList() == null) {
            return newArrayList();
        }
        List<Oa11040AssignRoleTableVo> assignRoleTableVoList = vo.getAssignRoleTableVoList();
        List<SubSystemRoleGrantRequestAssignRole> assignRoleList = newArrayList();
        for (Oa11040AssignRoleTableVo assignRoleTableVo : assignRoleTableVoList) {
            Oa11040ApplyAssignRoleConverter oa11040ApplyAssignRoleConverter = Oa11040ApplyAssignRoleConverter.with(assignRoleTableVo);
            assignRoleList.add(oa11040ApplyAssignRoleConverter);
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
