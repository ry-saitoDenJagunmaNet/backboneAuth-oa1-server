package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp;

import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;
import net.jagunma.common.ddd.model.entity2.AbstractEntity2Criteria;

/**
 * 取引ロール_取引グループ割当の検索条件
 */
public class BizTranRole_BizTranGrpCriteria extends AbstractEntity2Criteria<BizTranRole_BizTranGrp> {

    private final LongCriteria bizTranRoleIdCriteria = new LongCriteria();
    private final StringCriteria subSystemCodeCriteria = new StringCriteria();

    // Getter
    public LongCriteria getBizTranRoleIdCriteria() {
        return bizTranRoleIdCriteria;
    }
    public StringCriteria getSubSystemCodeCriteria() {
        return subSystemCodeCriteria;
    }

    @Override
    public boolean test(BizTranRole_BizTranGrp value) {
        return super.test(value)
            && bizTranRoleIdCriteria.test(value.getBizTranRoleId())
            && subSystemCodeCriteria.test(value.getSubSystemCode());
    }
}
