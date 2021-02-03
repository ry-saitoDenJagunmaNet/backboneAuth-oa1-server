package net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole;

import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.entity2.AbstractEntity2Criteria;

/**
 * オペレーター_取引ロール割当の検索条件
 */
public class Operator_BizTranRoleCriteria extends AbstractEntity2Criteria<Operator_BizTranRole> {

    private final LongCriteria operatorIdCriteria = new LongCriteria();
    private final LongCriteria bizTranRoleIdCriteria = new LongCriteria();
    private final LocalDateCriteria validThruStartDateCriteria = new LocalDateCriteria();
    private final LocalDateCriteria validThruEndDateCriteria = new LocalDateCriteria();

    // Getter
    public LongCriteria getOperatorIdCriteria() {
        return operatorIdCriteria;
    }
    public LongCriteria getBizTranRoleIdCriteria() {
        return bizTranRoleIdCriteria;
    }
    public LocalDateCriteria getValidThruStartDateCriteria() {
        return validThruStartDateCriteria;
    }
    public LocalDateCriteria getValidThruEndDateCriteria() {
        return validThruEndDateCriteria;
    }

    @Override
    public boolean test(Operator_BizTranRole value) {
        return super.test(value)
            && operatorIdCriteria.test(value.getOperatorId())
            && bizTranRoleIdCriteria.test(value.getBizTranRoleId())
            && validThruStartDateCriteria.test(value.getValidThruStartDate())
            && validThruEndDateCriteria.test(value.getValidThruEndDate());
    }
}
