package net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace;

import net.jagunma.backbone.auth.authmanager.model.base.AbstractCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;

/**
 * サインアウト証跡の検索条件
 */
public class SignOutTraceCriteria extends AbstractCriteria {

    private final LongCriteria signOutTraceIdCriteria = new LongCriteria();
    private final LongCriteria operatorIdCriteria = new LongCriteria();

    // Getter
    public LongCriteria getSignOutTraceIdCriteria() {
        return signOutTraceIdCriteria;
    }
    public LongCriteria getOperatorIdCriteria() {
        return operatorIdCriteria;
    }
}
