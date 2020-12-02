package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp;

import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * 取引グループの検索条件
 */
public class BizTranGrpCriteria {

    private LongCriteria bizTranGrpIdCriteria = new LongCriteria();
    private StringCriteria bizTranGrpCodeCriteria = new StringCriteria();
    private StringCriteria subSystemCodeCriteria = new StringCriteria();

    // Getter
    public LongCriteria getBizTranGrpIdCriteria() {
        return bizTranGrpIdCriteria;
    }
    public StringCriteria getBizTranGrpCodeCriteria() {
        return bizTranGrpCodeCriteria;
    }
    public StringCriteria getSubSystemCodeCriteria() {
        return subSystemCodeCriteria;
    }
}
