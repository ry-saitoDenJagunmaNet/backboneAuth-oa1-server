package net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTranGrp;

import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntityCriteria;

/**
 * 取引グループの検索条件
 */
public class BizTranGrpCriteria extends BizTranGrpEntityCriteria {
	public boolean test(BizTranGrp aValue) {
		return super.test(aValue.getBizTranGrpEntityForRepository());
	}
}
