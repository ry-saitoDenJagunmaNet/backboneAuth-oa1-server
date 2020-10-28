package net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole;

import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * オペレーター_取引ロール割当の検索条件
 */
public class Operator_BizTranRoleCriteria {

    private LongCriteria operatorIdCriteria = new LongCriteria();
    private LongCriteria bizTranRoleIdCriteria = new LongCriteria();
    private StringCriteria bizTranRoleCodeCriteria = new StringCriteria();

    // Getter
    public LongCriteria getOperatorIdCriteria() {
        return operatorIdCriteria;
    }
    public LongCriteria getBizTranRoleIdCriteria() {
        return bizTranRoleIdCriteria;
    }
    public StringCriteria getBizTranRoleCodeCriteria() {
        return bizTranRoleCodeCriteria;
    }
}
