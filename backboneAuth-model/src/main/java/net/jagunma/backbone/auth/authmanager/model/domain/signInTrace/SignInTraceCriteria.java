package net.jagunma.backbone.auth.authmanager.model.domain.signInTrace;

import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * サインイン証跡の検索条件
 */
public class SignInTraceCriteria {

    private StringCriteria operatorCodeCriteria = new StringCriteria();

    // Getter
    public StringCriteria getOperatorCodeCriteria() {
        return operatorCodeCriteria;
    }
}
