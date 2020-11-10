package net.jagunma.backbone.auth.authmanager.infra.web.oa11030;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.format.DateTimeFormatter;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfOperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo.Oa11030BizTranRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo.Oa11030SubsystemRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo.Oa11030Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchesAtMoment;

/**
 * OA11030 初期表示 Presenter
 */
class Oa11030InitPresenter extends BaseOfOperatorSearchResponse implements OperatorSearchResponse {

    private BranchesAtMoment branchesAtMomentForBranchItemsSource;

    // コンストラクタ
    Oa11030InitPresenter() {
    }

    /**
     * 店舗コンボボックスItemsSource の為の 店舗群AtMoment
     *
     * @param branchesAtMomentForBranchItemsSource
     */
    public void setBranchesAtMomentForBranchItemsSource(BranchesAtMoment branchesAtMomentForBranchItemsSource) {
        this.branchesAtMomentForBranchItemsSource = branchesAtMomentForBranchItemsSource;
    }

    /**
     * voに変換します
     *
     * @param vo
     */
    public void bindTo(Oa11030Vo vo) {

        if (operators.getValues().size() == 0) {
            return;
        }

        Operator operator = operators.getValues().get(0);
        BranchAtMoment branchAtMoment = operators.getValues().get(0).getBranchAtMoment();
        OperatorHistoryHeader operatorHistoryHeader = operatorHistoryHeaders.getValues().get(0);
        AccountLock accountLock = accountLocks.getValues().get(0);

        vo.setOperatorId(operator.getOperatorId());
        vo.setRecordVersion(operator.getRecordVersion());
        vo.setJa(operator.getJaCode() + " " + branchAtMoment.getJaAtMoment().getJaAttribute().getName());
        vo.setBranchId(operator.getBranchId());
        vo.setOperatorCode(operator.getOperatorCode());
        vo.setOperatorName(operator.getOperatorName());
        vo.setMailAddress(operator.getMailAddress());
        vo.setExpirationStartDate(operator.getExpirationStartDate());
        vo.setExpirationEndDate(operator.getExpirationEndDate());
        vo.setIsDeviceAuth(operator.getIsDeviceAuth());
        vo.setAvailableStatus(operator.getAvailableStatus().getCode());

        vo.setChangeCausePlaceholder(operatorHistoryHeader.getChangeCause());

        vo.setAccountLockStatus(accountLock.getLockStatus());

        List<Oa11030SubsystemRoleTableVo> oa11030SubsystemRoleTableVoList = newArrayList();
        for (Operator_SubSystemRole operator_subSystemRole : operator_SubSystemRoles.getValues()) {
            Oa11030SubsystemRoleTableVo oa11030SubsystemRoleTableVo = new Oa11030SubsystemRoleTableVo();
            oa11030SubsystemRoleTableVo.setRoleName(operator_subSystemRole.getSubSystemRole().getName());
            oa11030SubsystemRoleTableVo.setExpirationDate(
                operator_subSystemRole.getExpirationStartDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " ～ " +
                operator_subSystemRole.getExpirationEndDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            oa11030SubsystemRoleTableVoList.add(oa11030SubsystemRoleTableVo);
        }
        vo.setOa11030SubsystemRoleTableVoList(oa11030SubsystemRoleTableVoList);

        List<Oa11030BizTranRoleTableVo> oa11030BizTranRoleTableVoList = newArrayList();
        for (Operator_BizTranRole operator_BizTranRole : operator_BizTranRoles.getValues()) {
            Oa11030BizTranRoleTableVo oa11030BizTranRoleTableVo = new Oa11030BizTranRoleTableVo();
            oa11030BizTranRoleTableVo.setRoleCode(operator_BizTranRole.getBizTranRole().getBizTranRoleCode());
            oa11030BizTranRoleTableVo.setRoleName(operator_BizTranRole.getBizTranRole().getBizTranRoleName());
            oa11030BizTranRoleTableVo.setExpirationDate(
                operator_BizTranRole.getExpirationStartDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " ～ " +
                operator_BizTranRole.getExpirationEndDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            oa11030BizTranRoleTableVoList.add(oa11030BizTranRoleTableVo);
        }
        vo.setOa11030BizTranRoleTableVoList(oa11030BizTranRoleTableVoList);

        vo.setBranchItemsSource(SelectOptionItemsSource.createFrom(branchesAtMomentForBranchItemsSource).getValue());
    }
}
