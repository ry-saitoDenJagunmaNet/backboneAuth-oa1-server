package net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistoryPack.operator_SubSystemRoleHistory;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * オペレーター_サブシステムロール割当履歴検索
 */
public interface Operator_SubSystemRoleHistoriesRepository {
	/**
	 * オペレーター_サブシステムロール割当履歴の条件検索を行います。
	 *
	 * @param operator_SubSystemRoleHistoryCriteria オペレーター_サブシステムロール割当履歴の検索条件
	 * @param orders オーダー指定
	 * @return オペレーター_サブシステムロール割当履歴群
	 */
	Operator_SubSystemRoleHistories selectBy(Operator_SubSystemRoleHistoryCriteria operator_SubSystemRoleHistoryCriteria, Orders orders);
	/**
	 * オペレーター_サブシステムロール割当履歴の条件検索を行います。
	 *
	 * @param operator_SubSystemRoleHistoryCriteria オペレーター_サブシステムロール割当履歴の検索条件
	 * @return オペレーター_サブシステムロール割当履歴群
	 */
	Operator_SubSystemRoleHistories selectBy(Operator_SubSystemRoleHistoryCriteria operator_SubSystemRoleHistoryCriteria);

	/**
	 * オペレーター_サブシステムロール割当履歴の全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return オペレーター_サブシステムロール割当履歴群
	 */
	Operator_SubSystemRoleHistories selectAll(Orders orders);
	/**
	 * オペレーター_サブシステムロール割当履歴の全件検索を行います。
	 *
	 * @return オペレーター_サブシステムロール割当履歴群
	 */
	Operator_SubSystemRoleHistories selectAll();
}
