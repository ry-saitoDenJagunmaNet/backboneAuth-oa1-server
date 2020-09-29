package net.jagunma.backbone.auth.authmanager.model.domain.signInTrace;

import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * サインイン証跡の検索条件
 */
public class SignInTraceCriteria {

    private LongCriteria signInTraceIdCriteria = new LongCriteria();
    private StringCriteria operatorCodeCriteria = new StringCriteria();

    // Getter
    public LongCriteria getSignInTraceIdCriteria() {
        return signInTraceIdCriteria;
    }
    public StringCriteria getOperatorCodeCriteria() {
        return operatorCodeCriteria;
    }
}
