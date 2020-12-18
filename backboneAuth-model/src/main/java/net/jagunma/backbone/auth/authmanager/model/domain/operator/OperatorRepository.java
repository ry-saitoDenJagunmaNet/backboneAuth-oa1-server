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
    /**
     * オペレーターの存在チェックを行います
     *
     * @param operatorCriteria オペレーターの検索条件
     * @return オペレーターの有無
     */
    boolean existsBy(OperatorCriteria operatorCriteria);
    /**
     * オペレーター群の条件検索を行います
     *
     * @param operatorCriteria オペレーターの検索条件
     * @param orders           オーダー指定
     * @return オペレーター群
     */
    Operators selectBy(OperatorCriteria operatorCriteria, Orders orders);
}