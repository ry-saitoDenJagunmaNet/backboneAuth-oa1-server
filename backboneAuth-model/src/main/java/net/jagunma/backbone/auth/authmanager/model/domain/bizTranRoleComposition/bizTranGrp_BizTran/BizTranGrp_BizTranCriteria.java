package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran;

import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;
import net.jagunma.common.ddd.model.entity2.AbstractEntity2Criteria;

/**
 * 取引グループ_取引割当の検索条件
 */
public class BizTranGrp_BizTranCriteria extends AbstractEntity2Criteria<BizTranGrp_BizTran> {

    private final LongCriteria bizTranGrp_BizTranIdCriteria = new LongCriteria();
    private final LongCriteria bizTranGrpIdCriteria = new LongCriteria();
    private final LongCriteria bizTranIdCriteria = new LongCriteria();
    private final StringCriteria subSystemCodeCriteria = new StringCriteria();

    // Getter
    public LongCriteria getBizTranGrp_BizTranIdCriteria() {
        return bizTranGrp_BizTranIdCriteria;
    }
    public LongCriteria getBizTranGrpIdCriteria() {
        return bizTranGrpIdCriteria;
    }
    public LongCriteria getBizTranIdCriteria() {
        return bizTranIdCriteria;
    }
    public StringCriteria getSubSystemCodeCriteria() {
        return subSystemCodeCriteria;
    }

    @Override
    public boolean test(BizTranGrp_BizTran value) {
        return super.test(value)
            && bizTranGrp_BizTranIdCriteria.test(value.getBizTranGrp_BizTranId())
            && bizTranGrpIdCriteria.test(value.getBizTranGrpId())
            && bizTranIdCriteria.test(value.getBizTranId())
            && subSystemCodeCriteria.test(value.getSubSystemCode());
    }

}
