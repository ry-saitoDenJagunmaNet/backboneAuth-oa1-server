package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole;

import net.jagunma.backbone.auth.authmanager.model.base.AbstractCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * 取引ロールの検索条件
 */
public class BizTranRoleCriteria extends AbstractCriteria {

    private final LongCriteria bizTranRoleIdCriteria = new LongCriteria();
    private final StringCriteria subSystemCodeCriteria = new StringCriteria();

    // Getter
    public LongCriteria getBizTranRoleIdCriteria() {
        return bizTranRoleIdCriteria;
    }
    public StringCriteria getSubSystemCodeCriteria() {
        return subSystemCodeCriteria;
    }

}
