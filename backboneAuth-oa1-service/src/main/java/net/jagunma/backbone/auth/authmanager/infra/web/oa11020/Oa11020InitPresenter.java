package net.jagunma.backbone.auth.authmanager.infra.web.oa11020;

import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11020.vo.Oa11020Vo;
import net.jagunma.common.values.model.branch.BranchesAtMoment;

/**
 * OA11020 初期表示 Presenter
 */
class Oa11020InitPresenter {

    private String jaCode;
    private String jaName;
    private String operatorCodePrefix;
    private BranchesAtMoment branchesAtMomentForBranchItemsSource;

    // コンストラクタ
    Oa11020InitPresenter() {
    }

    /**
     * ＪＡコードのＳｅｔ
     *
     * @param jaCode
     */
    public void setJaCode(String jaCode) {
        this.jaCode = jaCode;
    }
    /**
     * ＪＡ名のＳｅｔ
     *
     * @param jaName
     */
    public void setJaName(String jaName) {
        this.jaName = jaName;
    }
    /**
     * 識別（オペレーターコードプレフィックス）のＳｅｔ
     *
     * @param operatorCodePrefix
     */
    public void setOperatorCodePrefix(String operatorCodePrefix) {
        this.operatorCodePrefix = operatorCodePrefix;
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
     * voに変換します。
     *
     * @param vo
     */
    public void bindTo(Oa11020Vo vo) {
        vo.setJa(jaCode + " " + jaName);
        vo.setBranchId(null);
        vo.setOperatorCodePrefix(operatorCodePrefix);
        vo.setOperatorCode6(null);
        vo.setOperatorName(null);
        vo.setMailAddress(null);
        vo.setExpirationStartDate(null);
        vo.setExpirationEndDate(null);
        vo.setChangeCause(null);
        vo.setBranchItemsSource(SelectOptionItemsSource.createFrom(branchesAtMomentForBranchItemsSource).getValue());
        vo.setPassword(null);
        vo.setConfirmPassword(null);
    }
}
