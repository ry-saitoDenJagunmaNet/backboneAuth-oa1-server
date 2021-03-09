package net.jagunma.backbone.auth.authmanager.infra.api.oa31010;

import net.jagunma.backbone.auth.authmanager.application.api_usecase.operatorReference.SimpleOperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.common.values.model.operator.OperatorCode;
import net.jagunma.common.values.model.operator.SimpleOperator;

/**
 * Oa31010 オペレーター（簡略版）検索 Presenter
 */
public class Oa31010SearchSimpleOperatorPresenter implements SimpleOperatorSearchResponse {

    private Operator operator;

    /**
     * オペレーターのＳｅｔ
     *
     * @param operator オペレーター
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * SimpleOperatorに変換します
     *
     * @return SimpleOperator
     */
    public SimpleOperator bindToSimpleOperator() {
        return SimpleOperator.builder()
            .withIdentifier(operator.getOperatorId())
            .withOperatorCode(OperatorCode.reconstruct(operator.getOperatorCode()))
            .withOperatorName(operator.getOperatorName())
            .withBranch(operator.getBranchAtMoment())
            .build();
    }

}
