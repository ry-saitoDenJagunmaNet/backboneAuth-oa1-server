package net.jagunma.backbone.auth.authmanager.infra.web.oa11050;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.BizTranRoleGrantedAllRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.BizTranRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050UnAssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;

/**
 * OA11050 初期表示 Presenter
 */
class Oa11050InitPresenter implements BizTranRoleGrantedSearchResponse {

    private Long signInOperatorId;
    private Long targetOperatorId;
    private List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList;
    private List<BizTranRoleGrantedAllRoleDto> allRoleDtoList;
    private OperatorHistoryHeader operatorHistoryHeader;

    // コンストラクタ
    Oa11050InitPresenter() {
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
    public void setAssignRoleDtoList(List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList) {
        this.assignRoleDtoList = assignRoleDtoList;
    }
    /**
     * 全ロールDtoリストのＳｅｔ
     *
     * @param allRoleDtoList 全ロールDtoリスト
     */
    public void setAllRoleDtoList(List<BizTranRoleGrantedAllRoleDto> allRoleDtoList) {
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
    public void bindTo(Oa11050Vo vo) {

        List<Oa11050AssignRoleTableVo> assignRoleTableVoList = newArrayList();
        for (BizTranRoleGrantedAssignRoleDto assignRoleDto : assignRoleDtoList) {
            Oa11050AssignRoleTableVo assignRoleTableVo = new Oa11050AssignRoleTableVo();
            assignRoleTableVo.setRoleId(assignRoleDto.getOperator_BizTranRole().getBizTranRoleId());
            assignRoleTableVo.setRoleCode(assignRoleDto.getOperator_BizTranRole().getBizTranRole().getBizTranRoleCode());
            assignRoleTableVo.setRoleName(assignRoleDto.getOperator_BizTranRole().getBizTranRole().getBizTranRoleName());
            assignRoleTableVo.setValidThruStartDate(assignRoleDto.getOperator_BizTranRole().getValidThruStartDate());
            assignRoleTableVo.setValidThruEndDate(assignRoleDto.getOperator_BizTranRole().getValidThruEndDate());
            assignRoleTableVo.setIsModifiable(assignRoleDto.getIsModifiable());
            assignRoleTableVoList.add(assignRoleTableVo);
        }
        List<Oa11050UnAssignRoleTableVo> unAssignRoleTableVoList = newArrayList();
        for (BizTranRoleGrantedAllRoleDto allRoleDto : allRoleDtoList) {
            Oa11050UnAssignRoleTableVo unAssignRoleTableVo = new Oa11050UnAssignRoleTableVo();
            unAssignRoleTableVo.setRoleId(allRoleDto.getBizTranRole().getBizTranRoleId());
            unAssignRoleTableVo.setRoleCode(allRoleDto.getBizTranRole().getBizTranRoleCode());
            unAssignRoleTableVo.setRoleName(allRoleDto.getBizTranRole().getBizTranRoleName());
            unAssignRoleTableVo.setSubSystemCode(allRoleDto.getBizTranRole().getSubSystemCode());
            unAssignRoleTableVo.setIsModifiable(allRoleDto.getIsModifiable());
            unAssignRoleTableVoList.add(unAssignRoleTableVo);
        }

        vo.setOperatorId(operatorHistoryHeader.getOperator().getOperatorId());
        vo.setJa(operatorHistoryHeader.getOperator().getJaCode() + " " + operatorHistoryHeader.getOperator().getBranchAtMoment().getJaAtMoment().getJaAttribute().getName());
        vo.setOperator(operatorHistoryHeader.getOperator().getOperatorCode() + " " + operatorHistoryHeader.getOperator().getOperatorName());
        vo.setAssignRoleTableVoList(assignRoleTableVoList);
        vo.setUnAssignRoleTableVoList(unAssignRoleTableVoList);
        vo.setChangeCausePlaceholder(operatorHistoryHeader.getChangeCause());
        vo.setSubSystemItemsSource(SelectOptionItemsSource.createFrom(SubSystem.getValidValues()).getValue());
    }
}
