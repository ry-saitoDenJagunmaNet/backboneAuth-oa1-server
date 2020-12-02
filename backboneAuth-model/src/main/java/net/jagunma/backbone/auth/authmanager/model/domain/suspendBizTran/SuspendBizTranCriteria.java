package net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran;

import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * 一時取引抑止の検索条件
 */
public class SuspendBizTranCriteria {

    private LongCriteria suspendBizTranIdCriteria = new LongCriteria();
    private StringCriteria jaCodeCriteria = new StringCriteria();
    private StringCriteria branchCodeCriteria = new StringCriteria();
    private StringCriteria subSystemCodeCriteria = new StringCriteria();
    private StringCriteria bizTranGrpCodeCriteria = new StringCriteria();
    private StringCriteria bizTranCodeCriteria = new StringCriteria();
    private LocalDateCriteria suspendStartDateCriteria = new LocalDateCriteria();
    private LocalDateCriteria suspendEndDateCriteria = new LocalDateCriteria();
    private StringCriteria suspendReasonCriteria = new StringCriteria();

    // Getter
    public LongCriteria getSuspendBizTranIdCriteria() {
        return suspendBizTranIdCriteria;
    }
    public StringCriteria getJaCodeCriteria() {
        return jaCodeCriteria;
    }
    public StringCriteria getBranchCodeCriteria() {
        return branchCodeCriteria;
    }
    public StringCriteria getSubSystemCodeCriteria() {
        return subSystemCodeCriteria;
    }
    public StringCriteria getBizTranGrpCodeCriteria() {
        return bizTranGrpCodeCriteria;
    }
    public StringCriteria getBizTranCodeCriteria() {
        return bizTranCodeCriteria;
    }
    public LocalDateCriteria getSuspendStartDateCriteria() {
        return suspendStartDateCriteria;
    }
    public LocalDateCriteria getSuspendEndDateCriteria() {
        return suspendEndDateCriteria;
    }
    public StringCriteria getSuspendReasonCriteria() {
        return suspendReasonCriteria;
    }
}
