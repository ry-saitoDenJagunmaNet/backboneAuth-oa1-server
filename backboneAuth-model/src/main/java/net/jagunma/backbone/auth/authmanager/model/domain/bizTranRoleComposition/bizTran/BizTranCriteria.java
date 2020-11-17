package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran;

import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * 取引の検索条件
 */
public class BizTranCriteria {

    private LongCriteria bizTranIdCriteria = new LongCriteria();
    private StringCriteria subSystemCodeCriteria = new StringCriteria();

    // Getter
    public LongCriteria getBizTranIdCriteria() {
        return bizTranIdCriteria;
    }
    public StringCriteria getSubSystemCodeCriteria() {
        return subSystemCodeCriteria;
    }
}
