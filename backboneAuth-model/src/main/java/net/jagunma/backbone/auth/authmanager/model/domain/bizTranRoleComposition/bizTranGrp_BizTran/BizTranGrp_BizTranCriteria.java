package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran;

import net.jagunma.backbone.auth.authmanager.model.base.AbstractCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * 取引グループ_取引割当の検索条件
 */
public class BizTranGrp_BizTranCriteria extends AbstractCriteria {

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
}
