package net.jagunma.backbone.auth.authmanager.application.model.domain.operator;

/**
 * オペレーター検索
 */
public interface OperatorsRepository {
	/**
	 * オペレーターの条件検索を行います。
	 *
	 * @param operatorCriteria オペレーターの検索条件
	 * @return オペレーター群
	 */
	Operators selectBy(OperatorCriteria operatorCriteria);
	/**
	 * オペレーターの全件検索を行います。
	 *
	 * @return オペレーター群
	 */
	Operators selectAll();
}
