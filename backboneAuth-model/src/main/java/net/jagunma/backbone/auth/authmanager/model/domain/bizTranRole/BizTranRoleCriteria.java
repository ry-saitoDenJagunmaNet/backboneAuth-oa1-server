package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole;

import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * 取引ロールの検索条件
 */
public class BizTranRoleCriteria {

    private LongCriteria bizTranRoleIdCriteria = new LongCriteria();
    private StringCriteria subSystemCodeCriteria = new StringCriteria();

    // Getter
    public LongCriteria getBizTranRoleIdCriteria() {
        return bizTranRoleIdCriteria;
    }
    public StringCriteria getSubSystemCodeCriteria() {
        return subSystemCodeCriteria;
    }

}
