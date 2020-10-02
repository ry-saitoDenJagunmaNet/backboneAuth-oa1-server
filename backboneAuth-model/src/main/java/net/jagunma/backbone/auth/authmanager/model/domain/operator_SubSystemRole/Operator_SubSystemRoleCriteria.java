package net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole;

import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * オペレーター_サブシステムロール割当の検索条件
 */
public class Operator_SubSystemRoleCriteria {

    private LongCriteria operatorIdCriteria = new LongCriteria();
    private StringCriteria subSystemRoleCodeCriteria = new StringCriteria();

    // Getter
    public LongCriteria getOperatorIdCriteria() {
        return operatorIdCriteria;
    }
    public StringCriteria getSubSystemRoleCodeCriteria() {
        return subSystemRoleCodeCriteria;
    }
}
