package net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory;

import net.jagunma.backbone.auth.authmanager.model.base.AbstractCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.ShortCriteria;

/**
 * パスワード履歴の検索条件
 */
public class PasswordHistoryCriteria extends AbstractCriteria {

    private final LongCriteria passwordHistoryIdCriteria = new LongCriteria();
    private final LongCriteria operatorIdCriteria = new LongCriteria();
    private final ShortCriteria changeTypeCriteria = new ShortCriteria();

    // Getter
    public LongCriteria getPasswordHistoryIdCriteria() {
        return passwordHistoryIdCriteria;
    }
    public LongCriteria getOperatorIdCriteria() {
        return operatorIdCriteria;
    }
    public ShortCriteria getChangeTypeCriteria() {
        return changeTypeCriteria;
    }
}
