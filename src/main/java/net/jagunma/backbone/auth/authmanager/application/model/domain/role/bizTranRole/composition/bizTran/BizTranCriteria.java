package net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTran;

import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntityCriteria;

/**
 * 取引の検索条件
 */
public class BizTranCriteria extends BizTranEntityCriteria {
	public boolean test(BizTran aValue) {
		return super.test(aValue.getBizTranEntityForRepository());
	}
}
