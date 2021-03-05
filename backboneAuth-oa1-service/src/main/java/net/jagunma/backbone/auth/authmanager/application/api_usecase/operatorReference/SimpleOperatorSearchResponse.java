package net.jagunma.backbone.auth.authmanager.application.api_usecase.operatorReference;

import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;

/**
 * オペレーター（簡略版）検索サービス Response
 */
public interface SimpleOperatorSearchResponse {

    /**
     * オペレーターのＳｅｔ
     *
     * @param operator オペレーター
     */
    void setOperator(Operator operator);
}
