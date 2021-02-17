package net.jagunma.backbone.auth.authmanager.infra.web.oa11050;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.BizTranRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedCopyResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050Vo;

/**
 * OA11050 コピー Presenter
 */
class Oa11050CopyPresenter implements BizTranRoleGrantedCopyResponse {

    private List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList;

    // コンストラクタ
    public Oa11050CopyPresenter() {
    }

    /**
     * アサインロールDtoリストのＳｅｔ
     *
     * @param assignRoleDtoList アサインロールDtoリスト
     */
    public void setAssignRoleDtoList(List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList) {
        this.assignRoleDtoList = assignRoleDtoList;
    }

    /**
     * voに変換します
     *
     * @param vo
     */
    public void bindTo(Oa11050Vo vo) {

        List<Oa11050AssignRoleTableVo> assignRoleTableVoList = newArrayList();
        for (BizTranRoleGrantedAssignRoleDto assignRoleDto : assignRoleDtoList) {
            Oa11050AssignRoleTableVo assignRoleTableVo = new Oa11050AssignRoleTableVo();
            assignRoleTableVo.setRoleCode(assignRoleDto.getOperator_BizTranRole().getBizTranRole().getBizTranRoleCode());
            assignRoleTableVo.setRoleName(assignRoleDto.getOperator_BizTranRole().getBizTranRole().getBizTranRoleName());
            assignRoleTableVo.setValidThruStartDate(assignRoleDto.getOperator_BizTranRole().getValidThruStartDate());
            assignRoleTableVo.setValidThruEndDate(assignRoleDto.getOperator_BizTranRole().getValidThruEndDate());
            assignRoleTableVo.setIsModifiable(assignRoleDto.getIsModifiable());
            assignRoleTableVoList.add(assignRoleTableVo);
        }
        vo.setAssignRoleTableVoList(assignRoleTableVoList);
    }
}
