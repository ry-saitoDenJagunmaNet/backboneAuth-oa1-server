package net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole;

import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntityCriteria;

/**
 * 取引ロールの検索条件
 */
public class BizTranRoleCriteria extends BizTranRoleEntityCriteria {
	public boolean test(BizTranRole aValue) {
		return super.test(aValue.getBizTranRoleEntityForRepository());
	}
}
