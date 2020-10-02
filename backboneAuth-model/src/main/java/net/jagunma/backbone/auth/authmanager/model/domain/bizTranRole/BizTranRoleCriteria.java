package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole;

import net.jagunma.common.ddd.model.criterias.LongCriteria;

/**
 * 取引ロールの検索条件
 */
public class BizTranRoleCriteria {

    private LongCriteria bizTranRoleIdCriteria = new LongCriteria();

    // Getter
    public LongCriteria getBizTranRoleIdCriteria() {
        return bizTranRoleIdCriteria;
    }
}
