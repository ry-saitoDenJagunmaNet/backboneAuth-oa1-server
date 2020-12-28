package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp;

import net.jagunma.backbone.auth.authmanager.model.base.AbstractCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * 取引ロール_取引グループ割当の検索条件
 */
public class BizTranRole_BizTranGrpCriteria extends AbstractCriteria {

    private final StringCriteria subSystemCodeCriteria = new StringCriteria();

    // Getter
    public StringCriteria getSubSystemCodeCriteria() {
        return subSystemCodeCriteria;
    }
}
