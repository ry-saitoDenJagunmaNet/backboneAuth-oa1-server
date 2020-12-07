package net.jagunma.backbone.auth.authmanager.infra.web.ed01010;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfOperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01010.vo.Ed01010Vo;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchesAtMoment;

/**
 * ED01010 初期表示 Presenter
 */
class Ed01010InitPresenter extends BaseOfOperatorSearchResponse implements OperatorSearchResponse {

    private BranchesAtMoment branchesAtMomentForBranchItemsSource;

    // コンストラクタ
    Ed01010InitPresenter() {
    }

    /**
     * voに変換します
     *
     * @param vo
     */
    public void bindTo(Ed01010Vo vo) {

        BranchAtMoment branchAtMoment = operator.getBranchAtMoment();

        vo.setJa(operator.getJaCode() + " " + branchAtMoment.getJaAtMoment().getJaAttribute().getName());
        vo.setOperatorId(operator.getOperatorId());
        vo.setOperator(operator.getOperatorCode() + " " + operator.getOperatorName());
        vo.setOldPassword(null);
        vo.setNewPassword(null);
        vo.setConfirmPassword(null);
    }
}
