package net.jagunma.backbone.auth.authmanager.infra.web.oa11020;

import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11020.vo.Oa11020Vo;
import net.jagunma.backbone.auth.authmanager.model.types.OperatorCodePrefix;
import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.values.model.branch.BranchesAtMoment;

/**
 * OA11020 初期表示 Presenter
 */
class Oa11020InitPresenter {

    private BranchesAtMoment branchesAtMomentForBranchItemsSource;

    // コンストラクタ
    Oa11020InitPresenter() {
    }

    /**
     * 店舗コンボボックスItemsSourceのＳｅｔ
     *
     * @param branchesAtMomentForBranchItemsSource 店舗コンボボックスItemsSource
     */
    public void setBranchesAtMomentForBranchItemsSource(BranchesAtMoment branchesAtMomentForBranchItemsSource) {
        this.branchesAtMomentForBranchItemsSource = branchesAtMomentForBranchItemsSource;
    }

    /**
     * voに変換します
     *
     * @param vo ViewObject
     */
    public void bindTo(Oa11020Vo vo) {
        vo.setJa(AuditInfoHolder.getAuthInf().getJaCode() + " " + AuditInfoHolder.getJa().getJaAttribute().getName());
        vo.setBranchId(null);
        vo.setOperatorCodePrefix(OperatorCodePrefix.codeOf(AuditInfoHolder.getAuthInf().getJaCode()).getPrefix());
        vo.setOperatorCode6(null);
        vo.setOperatorName(null);
        vo.setMailAddress(null);
        vo.setValidThruStartDate(null);
        vo.setValidThruEndDate(null);
        vo.setChangeCause(null);
        vo.setBranchItemsSource(SelectOptionItemsSource.createFrom(branchesAtMomentForBranchItemsSource).getValue());
        vo.setPassword(null);
        vo.setConfirmPassword(null);
    }
}
