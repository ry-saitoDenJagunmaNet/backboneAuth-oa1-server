package net.jagunma.backbone.auth.authmanager.application.model.domain.operator.operatorHistory.operator_BizTranRoleHistory;

/**
 * オペレーター_取引ロール割当履歴検索
 */
public interface Operator_BizTranRoleHistoryRepository {
	/**
	 * オペレーター_取引ロール割当履歴の条件検索を行います。
	 *
	 * @param operator_BizTranRoleHistoryCriteria オペレーター_取引ロール割当履歴の検索条件
	 * @return オペレーター_取引ロール割当履歴
	 */
	Operator_BizTranRoleHistory findOneBy(Operator_BizTranRoleHistoryCriteria operator_BizTranRoleHistoryCriteria);
}
