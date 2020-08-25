package net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTranGrp_BizTran;

import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntityCriteria;

/**
 * 取引グループ_取引割当の検索条件
 */
public class BizTranGrp_BizTranCriteria extends BizTranGrp_BizTranEntityCriteria {
	public boolean test(BizTranGrp_BizTran aValue) {
		return super.test(aValue.getBizTranGrp_BizTranEntityForRepository());
	}
}
