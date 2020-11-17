package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran;

import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * 取引グループ_取引割当の検索条件
 */
public class BizTranGrp_BizTranCriteria {

    private LongCriteria bizTranGrpIdCriteria = new LongCriteria();
    private StringCriteria subSystemCodeCriteria = new StringCriteria();

    // Getter
    public LongCriteria getBizTranGrpIdCriteria() {
        return bizTranGrpIdCriteria;
    }
    public StringCriteria getSubSystemCodeCriteria() {
        return subSystemCodeCriteria;
    }
}
