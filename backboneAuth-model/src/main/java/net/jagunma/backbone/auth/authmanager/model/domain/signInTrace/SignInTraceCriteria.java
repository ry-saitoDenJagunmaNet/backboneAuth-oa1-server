package net.jagunma.backbone.auth.authmanager.model.domain.signInTrace;

import net.jagunma.backbone.auth.authmanager.model.base.AbstractCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * サインイン証跡の検索条件
 */
public class SignInTraceCriteria extends AbstractCriteria {

    private final LongCriteria signInTraceIdCriteria = new LongCriteria();
    private final StringCriteria operatorCodeCriteria = new StringCriteria();

    // Getter
    public LongCriteria getSignInTraceIdCriteria() {
        return signInTraceIdCriteria;
    }
    public StringCriteria getOperatorCodeCriteria() {
        return operatorCodeCriteria;
    }
}
