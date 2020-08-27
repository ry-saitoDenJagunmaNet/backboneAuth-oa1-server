package net.jagunma.backbone.auth.authmanager.application.model.domain.operator.operatorHistoryPack.operatorHistory;

/**
 * オペレーター履歴検索
 */
public interface OperatorHistoryRepository {
	/**
	 * オペレーター履歴の条件検索を行います。
	 *
	 * @param operatorHistoryCriteria オペレーター履歴の検索条件
	 * @return オペレーター履歴
	 */
	OperatorHistory findOneBy(OperatorHistoryCriteria operatorHistoryCriteria);
}
