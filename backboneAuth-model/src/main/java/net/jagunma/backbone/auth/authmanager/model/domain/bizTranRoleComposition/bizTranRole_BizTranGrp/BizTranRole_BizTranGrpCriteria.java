package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp;

import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * 取引ロール_取引グループ割当の検索条件
 */
public class BizTranRole_BizTranGrpCriteria {

    private StringCriteria subSystemCode = new StringCriteria();

    // Getter
    public StringCriteria getSubSystemCode() {
        return subSystemCode;
    }
}
