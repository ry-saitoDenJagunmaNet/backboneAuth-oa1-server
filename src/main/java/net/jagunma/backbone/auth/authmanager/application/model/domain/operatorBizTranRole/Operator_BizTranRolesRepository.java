package net.jagunma.backbone.auth.authmanager.application.model.domain.operatorBizTranRole;

/**
 * オペレーター取引ロール検索
 */
public interface Operator_BizTranRolesRepository {
	/**
	 * オペレーター_取引ロール割当群を全件検索します。
	 * @return オペレーター_取引ロール割当群
	 */
	Operator_BizTranRoles selectAll();
}
