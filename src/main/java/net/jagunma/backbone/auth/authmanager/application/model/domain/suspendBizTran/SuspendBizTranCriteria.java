package net.jagunma.backbone.auth.authmanager.application.model.domain.suspendBizTran;

import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntityCriteria;

/**
 * 一時取引抑止の検索条件
 */
public class SuspendBizTranCriteria extends SuspendBizTranEntityCriteria {
	public boolean test(SuspendBizTran aValue) {
		return super.test(aValue.getSuspendBizTranEntityForRepository());
	}
}
