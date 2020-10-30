package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp;

import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * 取引グループの検索条件
 */
public class BizTranGrpCriteria {

    private StringCriteria subSystemCode = new StringCriteria();

    // Getter
    public StringCriteria getSubSystemCode() {
        return subSystemCode;
    }
}
