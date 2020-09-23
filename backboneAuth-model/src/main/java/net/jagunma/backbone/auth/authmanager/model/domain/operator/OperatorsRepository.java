package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * オペレーター群検索
 */
public interface OperatorsRepository {

    /**
     * オペレーター群の条件検索を行います。
     *
     * @param operetorCriteria オペレーターの検索条件
     * @param orders           オーダー指定
     * @return オペレーター群
     */
    Operators selectBy(OperatorCriteria operetorCriteria, Orders orders);
}
