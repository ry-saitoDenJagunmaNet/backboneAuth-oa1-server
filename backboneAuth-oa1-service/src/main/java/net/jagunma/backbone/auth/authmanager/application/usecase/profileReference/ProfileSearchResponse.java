package net.jagunma.backbone.auth.authmanager.application.usecase.profileReference;

import net.jagunma.common.values.model.operator.SimpleOperator;

public interface ProfileSearchResponse {

    /**
     * オペレーター（簡略版）のＳｅｔ
     *
     * @param simpleOperator オペレーター（簡略版）
     */
    void setSimpleOperator(SimpleOperator simpleOperator);
}
