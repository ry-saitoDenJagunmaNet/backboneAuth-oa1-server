package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp;

import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * 取引グループの検索条件
 */
public class BizTranGrpCriteria {

    private StringCriteria subSystemCodeCriteria = new StringCriteria();

    // Getter
    public StringCriteria getSubSystemCodeCriteria() {
        return subSystemCodeCriteria;
    }
}