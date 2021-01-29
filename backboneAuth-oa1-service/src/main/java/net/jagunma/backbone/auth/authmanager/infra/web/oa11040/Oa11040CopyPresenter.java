package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedCopyResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040Vo;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;

/**
 * OA11040 コピー Presenter
 */
class Oa11040CopyPresenter implements SubSystemRoleGrantedCopyResponse {

    private List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList;

    // コンストラクタ
    public Oa11040CopyPresenter() {
    }

    /**
     * アサインロールDtoリストのＳｅｔ
     *
     * @param assignRoleDtoList アサインロールDtoリスト
     */
    public void setAssignRoleDtoList(List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList) {
        this.assignRoleDtoList = assignRoleDtoList;
    }

    /**
     * voに変換します
     *
     * @param vo
     */
    public void bindTo(Oa11040Vo vo) {

        List<Oa11040AssignRoleTableVo> assignRoleTableVoList = newArrayList();
        for (SubSystemRoleGrantedAssignRoleDto assignRoleDto : assignRoleDtoList) {
            Oa11040AssignRoleTableVo assignRoleTableVo = new Oa11040AssignRoleTableVo();
            assignRoleTableVo.setRoleCode(assignRoleDto.getOperator_SubSystemRole().getSubSystemRoleCode());
            assignRoleTableVo.setRoleName(SubSystemRole.codeOf(assignRoleDto.getOperator_SubSystemRole().getSubSystemRoleCode()).getDisplayName());
            assignRoleTableVo.setValidThruStartDate(assignRoleDto.getOperator_SubSystemRole().getValidThruStartDate());
            assignRoleTableVo.setValidThruEndDate(assignRoleDto.getOperator_SubSystemRole().getValidThruEndDate());
            assignRoleTableVo.setIsModifiable(assignRoleDto.getIsModifiable());
            assignRoleTableVoList.add(assignRoleTableVo);
        }
        vo.setAssignRoleTableVoList(assignRoleTableVoList);
    }
}
