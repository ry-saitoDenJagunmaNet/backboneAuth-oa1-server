package net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory;

import net.jagunma.common.ddd.model.criterias.LongCriteria;

/**
 * パスワード履歴の検索条件
 */
public class PasswordHistoryCriteria {

    private LongCriteria operatorIdCriteria = new LongCriteria();

    // Getter
    public LongCriteria getOperatorIdCriteria() {
        return operatorIdCriteria;
    }
}