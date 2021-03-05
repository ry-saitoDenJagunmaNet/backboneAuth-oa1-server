package net.jagunma.backbone.auth.authmanager.infra.api.oa31010;

import net.jagunma.backbone.auth.authmanager.application.api_usecase.operatorReference.SimpleOperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;

/**
 * Oa31010 オペレーター（簡略版）検索 Presenter
 */
public class Oa31010SearchSimpleOperatorPresenter implements SimpleOperatorSearchResponse {

    private Operator operator;

    /**
     * オペレーターのＧｅｔ
     *
     * @return オペレーター
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * オペレーターのＳｅｔ
     *
     * @param operator オペレーター
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
