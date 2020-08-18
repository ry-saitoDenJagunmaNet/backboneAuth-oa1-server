package net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistory.operator_BizTranRoleHistory;

/**
 * オペレーター_取引ロール割当履歴検索
 */
public interface Operator_BizTranRoleHistoriesRepository {
	/**
	 * オペレーター_取引ロール割当履歴の条件検索を行います。
	 *
	 * @param operator_BizTranRoleHistoryCriteria オペレーター_取引ロール割当履歴の検索条件
	 * @return オペレーター_取引ロール割当履歴群
	 */
	Operator_BizTranRoleHistories selectBy(Operator_BizTranRoleHistoryCriteria operator_BizTranRoleHistoryCriteria);
	/**
	 * オペレーター_取引ロール割当履歴の全件検索を行います。
	 *
	 * @return オペレーター_取引ロール割当履歴群
	 */
	Operator_BizTranRoleHistories selectAll();
}
