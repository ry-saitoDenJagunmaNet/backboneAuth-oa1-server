package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * オペレーター検索
 */
public interface OperatorRepository {

    /**
     * オペレーターの条件検索を行います
     *
     * @param operatorCriteria オペレーターの検索条件
     * @return オペレーター
     */
    Operator findOneBy(OperatorCriteria operatorCriteria);
}