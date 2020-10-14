package net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp_BizTran;

import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * 取引グループ_取引割当の検索条件
 */
public class BizTranGrp_BizTranCriteria {

    private StringCriteria subSystemCode = new StringCriteria();

    // Getter
    public StringCriteria getSubSystemCode() {
        return subSystemCode;
    }
}
