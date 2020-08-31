package net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTranRole_BizTranGrp;

import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntityCriteria;

/**
 * 取引ロール_取引グループ割当の検索条件
 */
public class BizTranRole_BizTranGrpCriteria extends BizTranRole_BizTranGrpEntityCriteria {
	public boolean test(BizTranRole_BizTranGrp aValue) {
		return super.test(aValue.getBizTranRole_BizTranGrpEntityForRepository());
	}
}
