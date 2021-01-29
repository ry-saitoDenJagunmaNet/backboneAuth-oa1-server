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

    private Long signInOperatorId;
    private Long targetOperatorId;
    private List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList;
    private List<SubSystemRoleGrantedAllRoleDto> allRoleDtoList;
    private OperatorHistoryHeader operatorHistoryHeader;

    // コンストラクタ
    Oa11040InitPresenter() {
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
     * ターゲットオペレーターIDのＳｅｔ
     *
     * @param targetOperatorId ターゲットオペレーターID
     */
    public void setTargetOperatorId(Long targetOperatorId) {
        this.targetOperatorId = targetOperatorId;
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

        List<Oa11040UnAssignRoleTableVo> unAssignRoleTableVoList = newArrayList();
        for (SubSystemRoleGrantedAllRoleDto allRoleDto : allRoleDtoList) {
            Oa11040UnAssignRoleTableVo unAssignRoleTableVo = new Oa11040UnAssignRoleTableVo();
            unAssignRoleTableVo.setRoleCode(allRoleDto.getSubSystemRole().getCode());
            unAssignRoleTableVo.setRoleName(allRoleDto.getSubSystemRole().getDisplayName());
            unAssignRoleTableVo.setIsModifiable(allRoleDto.getIsModifiable());
            unAssignRoleTableVoList.add(unAssignRoleTableVo);
        }
        vo.setUnAssignRoleTableVoList(unAssignRoleTableVoList);

        vo.setChangeCausePlaceholder(operatorHistoryHeader.getChangeCause());
    }
}
