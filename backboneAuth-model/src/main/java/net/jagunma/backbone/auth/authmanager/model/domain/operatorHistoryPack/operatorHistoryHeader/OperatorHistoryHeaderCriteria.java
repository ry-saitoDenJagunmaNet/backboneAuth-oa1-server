package net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader;

import net.jagunma.common.ddd.model.criterias.LongCriteria;

/**
 * オペレーター履歴ヘッダーの検索条件
 */
public class OperatorHistoryHeaderCriteria {

    private LongCriteria operatorHistoryIdCriteria = new LongCriteria();
    private LongCriteria operatorIdCriteria = new LongCriteria();

    // Getter
    public LongCriteria getOperatorHistoryIdCriteria() {
        return operatorHistoryIdCriteria;
    }
    public LongCriteria getOperatorIdCriteria() {
        return operatorIdCriteria;
    }
}
