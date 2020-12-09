package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfOperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AllocateRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040UnallocateRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.values.model.branch.BranchesAtMoment;

/**
 * OA11040 初期表示 Presenter
 */
class Oa11040InitPresenter extends BaseOfOperatorSearchResponse implements OperatorSearchResponse {

    private BranchesAtMoment branchesAtMomentForBranchItemsSource;

    // コンストラクタ
    Oa11040InitPresenter() {
    }

    /**
     * voに変換します
     *
     * @param vo
     */
    public void bindTo(Oa11040Vo vo) {

        vo.setJa(operator.getJaCode() + " " + operator.getBranchAtMoment().getJaAtMoment().getJaAttribute().getName());
        vo.setOperatorId(operator.getOperatorId());
        vo.setOperator(operator.getOperatorCode() + " " + operator.getOperatorName());

        List<Oa11040AllocateRoleTableVo> oa11040AllocateRoleTableVoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : operator_SubSystemRoles.getValues()) {
            Oa11040AllocateRoleTableVo oa11040AllocateRoleTableVo = new Oa11040AllocateRoleTableVo();
            oa11040AllocateRoleTableVo.setRoleCode(operator_SubSystemRole.getSubSystemRoleCode());
            oa11040AllocateRoleTableVo.setRoleName(operator_SubSystemRole.getSubSystemRole().getName());
            oa11040AllocateRoleTableVo.setValidThruStartDate(operator_SubSystemRole.getValidThruStartDate());
            oa11040AllocateRoleTableVo.setValidThruEndDate(operator_SubSystemRole.getValidThruEndDate());
            oa11040AllocateRoleTableVoList.add(oa11040AllocateRoleTableVo);
        }
        vo.setOa11040AllocateRoleTableVoList(oa11040AllocateRoleTableVoList);

        List<Oa11040UnallocateRoleTableVo> oa11040UnallocateRoleTableVoList = newArrayList();
        for (SubSystemRole subSystemRole : SubSystemRole.values()) {//ToDo:ソートオーダー回し実装（ユーティリティで実現）
            if (subSystemRole.getCode().length() == 0) { continue; }

            Oa11040UnallocateRoleTableVo oa11040UnallocateRoleTableVo = new Oa11040UnallocateRoleTableVo();
            oa11040UnallocateRoleTableVo.setRoleCode(subSystemRole.getCode());
            oa11040UnallocateRoleTableVo.setRoleName(subSystemRole.getName());
            oa11040UnallocateRoleTableVoList.add(oa11040UnallocateRoleTableVo);
        }
        vo.setOa11040UnallocateRoleTableVoList(oa11040UnallocateRoleTableVoList);
    }
}
