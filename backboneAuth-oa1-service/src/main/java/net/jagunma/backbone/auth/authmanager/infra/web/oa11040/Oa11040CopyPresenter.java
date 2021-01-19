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

        List<Oa11040AssignRoleTableVo> oa11040AssignRoleTableVoList = newArrayList();
        for (SubSystemRoleGrantedAssignRoleDto assignRoleDto : assignRoleDtoList) {
            Oa11040AssignRoleTableVo oa11040AssignRoleTableVo = new Oa11040AssignRoleTableVo();
            oa11040AssignRoleTableVo.setRoleCode(assignRoleDto.getOperator_SubSystemRole().getSubSystemRoleCode());
            oa11040AssignRoleTableVo.setRoleName(SubSystemRole.codeOf(assignRoleDto.getOperator_SubSystemRole().getSubSystemRoleCode()).getDisplayName());
            oa11040AssignRoleTableVo.setValidThruStartDate(assignRoleDto.getOperator_SubSystemRole().getValidThruStartDate());
            oa11040AssignRoleTableVo.setValidThruEndDate(assignRoleDto.getOperator_SubSystemRole().getValidThruEndDate());
            oa11040AssignRoleTableVo.setIsModifiable(assignRoleDto.getIsModifiable());
            oa11040AssignRoleTableVoList.add(oa11040AssignRoleTableVo);
        }
        vo.setOa11040AssignRoleTableVoList(oa11040AssignRoleTableVoList);
    }
}
