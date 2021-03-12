package net.jagunma.backbone.auth.authmanager.infra.web.ed01010;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfOperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01010.vo.Ed01010Vo;

/**
 * ED01010 初期表示 Presenter
 */
class Ed01010InitPresenter extends BaseOfOperatorSearchResponse implements OperatorSearchResponse {

    // コンストラクタ
    Ed01010InitPresenter() {
    }

    /**
     * voに変換します
     *
     * @param vo ViewObject
     */
    public void bindTo(Ed01010Vo vo) {

        vo.setJa(operator.getJaCode() + " " + operator.getBranchAtMoment().getJaAtMoment().getJaAttribute().getName());
        vo.setOperatorId(operator.getOperatorId());
        vo.setOperator(operator.getOperatorCode() + " " + operator.getOperatorName());
        vo.setOldPassword(null);
        vo.setNewPassword(null);
        vo.setConfirmPassword(null);
    }
}
