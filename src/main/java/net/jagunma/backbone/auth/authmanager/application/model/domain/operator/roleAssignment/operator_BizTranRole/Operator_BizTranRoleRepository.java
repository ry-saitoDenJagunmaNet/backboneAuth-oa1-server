package net.jagunma.backbone.auth.authmanager.application.model.domain.operator.roleAssignment.operator_BizTranRole;

/**
 * オペレーター_取引ロール割当検索
 */
public interface Operator_BizTranRoleRepository {
	/**
	 * オペレーター_取引ロール割当の条件検索を行います。
	 * @param operatorBizTranRoleCriteria オペレーター_取引ロール割当の検索条件
	 * @return 取引ロール
	 */
	Operator_BizTranRole findOneBy(Operator_BizTranRoleCriteria operatorBizTranRoleCriteria);
}
