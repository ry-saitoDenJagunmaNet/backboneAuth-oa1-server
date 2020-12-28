package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran;

import net.jagunma.backbone.auth.authmanager.model.base.AbstractCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * 取引の検索条件
 */
public class BizTranCriteria extends AbstractCriteria {

    private final LongCriteria bizTranIdCriteria = new LongCriteria();
    private final StringCriteria bizTranCodeCriteria = new StringCriteria();
    private final StringCriteria subSystemCodeCriteria = new StringCriteria();

    // Getter
    public LongCriteria getBizTranIdCriteria() {
        return bizTranIdCriteria;
    }
    public StringCriteria getBizTranCodeCriteria() {
        return bizTranCodeCriteria;
    }
    public StringCriteria getSubSystemCodeCriteria() {
        return subSystemCodeCriteria;
    }
}
