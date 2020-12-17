package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleCommand.SubSystemRoleGrantRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleCommand.SubSystemRoleGrantRequestAllocateSubSystemRole;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AllocateRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040Vo;

/**
 * OA11040 サブシステムロール付与サービス Request Converter
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
     * 割当対象サブシステムロールリスト
     *
     * @return 割当対象サブシステムロールリスト
     */
    public List<SubSystemRoleGrantRequestAllocateSubSystemRole> getAllocateSubSystemRoleList() {
        if (vo.getOa11040AllocateRoleTableVoList() == null) {
            return newArrayList();
        }
        List<SubSystemRoleGrantRequestAllocateSubSystemRole> allocateSubSystemRoleList = newArrayList();
        List<Oa11040AllocateRoleTableVo> oa11040AllocateRoleTableVoList = vo.getOa11040AllocateRoleTableVoList();
        for (Oa11040AllocateRoleTableVo oa11040AllocateRoleTableVo : oa11040AllocateRoleTableVoList) {
            Oa11040ApplyChildConverter oa11040ApplyChildConverter = Oa11040ApplyChildConverter.with(oa11040AllocateRoleTableVo);
            allocateSubSystemRoleList.add(oa11040ApplyChildConverter);
        }
        return allocateSubSystemRoleList;
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
