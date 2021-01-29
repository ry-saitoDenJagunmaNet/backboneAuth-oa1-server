package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * オペレーター検索
 */
public interface OperatorRepository {

    /**
     * オペレーターの検索を行います
     *
     * @param operatorId オペレーターID
     * @return オペレーター
     */
    Operator findOneById(Long operatorId);
    /**
     * オペレーターの検索を行います
     *
     * @param operatorCode オペレーターコ－ド
     * @return オペレーター
     */
    Operator findOneByCode(String operatorCode);
    /**
     * オペレーターの存在チェックを行います
     *
     * @param operatorId オペレーターID
     * @return オペレーターの有無
     */
    boolean existsById(Long operatorId);
    /**
     * オペレーターの存在チェックを行います
     *
     * @param operatorCode オペレーターコ－ド
     * @return オペレーターの有無
     */
    boolean existsByCode(String operatorCode);
    /**
     * オペレーターの存在チェックを行います
     *
     * @param operatorCriteria オペレーターの検索条件
     * @return オペレーターの有無
     */
    boolean existsBy(OperatorCriteria operatorCriteria);
    /**
     * オペレーター群の検索を行います
     *
     * @param operatorCriteria オペレーターの検索条件
     * @param orders           オーダー指定
     * @return オペレーター群
     */
    Operators selectBy(OperatorCriteria operatorCriteria, Orders orders);
}