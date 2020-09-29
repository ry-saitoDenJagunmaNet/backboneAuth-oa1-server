package net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole;

import net.jagunma.common.ddd.model.criterias.LongCriteria;

/**
 * オペレーター_取引ロール割当の検索条件
 */
public class Operator_BizTranRoleCriteria {

    private LongCriteria operatorIdCriteria = new LongCriteria();
    private LongCriteria bizTranRoleIdCriteria = new LongCriteria();

    // Getter
    public LongCriteria getOperatorIdCriteria() {
        return operatorIdCriteria;
    }
    public LongCriteria getBizTranRoleIdCriteria() {
        return bizTranRoleIdCriteria;
    }
}
