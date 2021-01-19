package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAllRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040UnAssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;

/**
 * OA11040 初期表示 Presenter
 */
class Oa11040InitPresenter implements SubSystemRoleGrantedSearchResponse {

    private Long targetOperatorId;
    private Long signInOperatorId;
    private List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList;
    private List<SubSystemRoleGrantedAllRoleDto> allRoleDtoList;
    private OperatorHistoryHeader operatorHistoryHeader;

    // コンストラクタ
    Oa11040InitPresenter() {
    }

    /**
     * ターゲットオペレーターIDのＳｅｔ
     *
     * @param targetOperatorId ターゲットオペレーターID
     */
    public void setTargetOperatorId(Long targetOperatorId) {
        this.targetOperatorId = targetOperatorId;
    }
    /**
     * サインインオペレーターIDのSｅｔ
     *
     * @param signInOperatorId サインインオペレーターID
     */
    public void setSignInOperatorId(Long signInOperatorId) {
        this.signInOperatorId = signInOperatorId;
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
     * 全ロールDtoリストのＳｅｔ
     *
     * @param allRoleDtoList 全ロールDtoリスト
     */
    public void setAllRoleDtoList(List<SubSystemRoleGrantedAllRoleDto> allRoleDtoList) {
        this.allRoleDtoList = allRoleDtoList;
    }
    /**
     * オペレーター履歴ヘッダーのＳｅｔ
     *
     * @param operatorHistoryHeader オペレーター履歴ヘッダー
     */
    public void setOperatorHistoryHeader(OperatorHistoryHeader operatorHistoryHeader) {
        this.operatorHistoryHeader = operatorHistoryHeader;
    }

    /**
     * voに変換します
     *
     * @param vo
     */
    public void bindTo(Oa11040Vo vo) {

        vo.setOperatorId(operatorHistoryHeader.getOperator().getOperatorId());
        vo.setJa(operatorHistoryHeader.getOperator().getJaCode() + " " + operatorHistoryHeader.getOperator().getBranchAtMoment().getJaAtMoment().getJaAttribute().getName());
        vo.setOperator(operatorHistoryHeader.getOperator().getOperatorCode() + " " + operatorHistoryHeader.getOperator().getOperatorName());

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

        List<Oa11040UnAssignRoleTableVo> oa11040UnAssignRoleTableVoList = newArrayList();
        for (SubSystemRoleGrantedAllRoleDto allRoleDto : allRoleDtoList) {
            Oa11040UnAssignRoleTableVo oa11040UnAssignRoleTableVo = new Oa11040UnAssignRoleTableVo();
            oa11040UnAssignRoleTableVo.setRoleCode(allRoleDto.getSubSystemRole().getCode());
            oa11040UnAssignRoleTableVo.setRoleName(allRoleDto.getSubSystemRole().getDisplayName());
            oa11040UnAssignRoleTableVo.setIsModifiable(allRoleDto.getIsModifiable());
            oa11040UnAssignRoleTableVoList.add(oa11040UnAssignRoleTableVo);
        }
        vo.setOa11040UnAssignRoleTableVoList(oa11040UnAssignRoleTableVoList);

        vo.setChangeCausePlaceholder(operatorHistoryHeader.getChangeCause());
    }
}
