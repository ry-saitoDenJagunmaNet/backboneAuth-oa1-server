package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran;

import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * 取引グループ_取引割当の検索条件
 */
public class BizTranGrp_BizTranCriteria {

    private StringCriteria subSystemCodeCriteria = new StringCriteria();

    // Getter
    public StringCriteria getSubSystemCodeCriteria() {
        return subSystemCodeCriteria;
    }
}