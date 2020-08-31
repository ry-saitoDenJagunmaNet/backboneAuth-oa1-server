package net.jagunma.backbone.auth.authmanager.model.domain.roleAssignment.operator_BizTranRole;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * オペレーター取引ロール検索
 */
public interface Operator_BizTranRolesRepository {
	/**
	 * オペレーター_取引ロール割当の条件検索を行います。
	 *
	 * @param operator_BizTranRoleCriteria オペレーター_取引ロール割当の検索条件
	 * @param orders オーダー指定
	 * @return オペレーター_取引ロール割当群
	 */
	Operator_BizTranRoles selectBy(Operator_BizTranRoleCriteria operator_BizTranRoleCriteria, Orders orders);
	/**
	 * オペレーター_取引ロール割当の条件検索を行います。
	 *
	 * @param operator_BizTranRoleCriteria オペレーター_取引ロール割当の検索条件
	 * @return オペレーター_取引ロール割当群
	 */
	Operator_BizTranRoles selectBy(Operator_BizTranRoleCriteria operator_BizTranRoleCriteria);

	/**
	 * オペレーター_取引ロール割当群の全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return オペレーター_取引ロール割当群
	 */
	Operator_BizTranRoles selectAll(Orders orders);
	/**
	 * オペレーター_取引ロール割当群の全件検索を行います。
	 *
	 * @return オペレーター_取引ロール割当群
	 */
	Operator_BizTranRoles selectAll();
}
