package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleAllocateReference.SubSystemRoleAllocateCopyResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AllocateRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;

/**
 * OA11040 コピー Presenter
 */
class Oa11040CopyPresenter implements SubSystemRoleAllocateCopyResponse {

    protected Operator_SubSystemRoles operator_SubSystemRoles;

    // コンストラクタ
    public Oa11040CopyPresenter() {
    }

    /**
     * オペレーター_サブシステムロール割当群のＳｅｔ
     *
     * @param operator_SubSystemRoles オペレーター_サブシステムロール割当群
     */
    public void setOperator_SubSystemRoles(Operator_SubSystemRoles operator_SubSystemRoles) {
        this.operator_SubSystemRoles = operator_SubSystemRoles;
    }

    /**
     * voに変換します
     *
     * @param vo
     */
    public void bindTo(Oa11040Vo vo) {

        List<Oa11040AllocateRoleTableVo> oa11040AllocateRoleTableVoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : operator_SubSystemRoles.getValues()) {
            Oa11040AllocateRoleTableVo oa11040AllocateRoleTableVo = new Oa11040AllocateRoleTableVo();
            oa11040AllocateRoleTableVo.setRoleCode(operator_SubSystemRole.getSubSystemRoleCode());
            oa11040AllocateRoleTableVo.setRoleName(operator_SubSystemRole.getSubSystemRole().getDisplayName());
            oa11040AllocateRoleTableVo.setValidThruStartDate(operator_SubSystemRole.getValidThruStartDate());
            oa11040AllocateRoleTableVo.setValidThruEndDate(operator_SubSystemRole.getValidThruEndDate());
            oa11040AllocateRoleTableVoList.add(oa11040AllocateRoleTableVo);
        }
        vo.setOa11040AllocateRoleTableVoList(oa11040AllocateRoleTableVoList);
    }
}
