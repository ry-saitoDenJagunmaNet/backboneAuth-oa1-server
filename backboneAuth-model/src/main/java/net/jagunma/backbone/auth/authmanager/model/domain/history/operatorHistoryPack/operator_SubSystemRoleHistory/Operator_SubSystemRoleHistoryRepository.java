package net.jagunma.backbone.auth.authmanager.model.domain.history.operatorHistoryPack.operator_SubSystemRoleHistory;

/**
 * オペレーター_サブシステムロール割当履歴検索
 */
public interface Operator_SubSystemRoleHistoryRepository {
	/**
	 * オペレーター_サブシステムロール割当履歴の条件検索を行います。
	 *
	 * @param operator_SubSystemRoleHistoryCriteria オペレーター_サブシステムロール割当履歴の検索条件
	 * @return オペレーター_サブシステムロール割当履歴
	 */
	Operator_SubSystemRoleHistory findOneBy(Operator_SubSystemRoleHistoryCriteria operator_SubSystemRoleHistoryCriteria);
}
