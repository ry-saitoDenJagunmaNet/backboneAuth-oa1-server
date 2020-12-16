package net.jagunma.backbone.auth.authmanager.model.domain.operator;

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
}