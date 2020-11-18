package net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran;

import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * 一時取引抑止の検索条件
 */
public class SuspendBizTranCriteria {

    private LongCriteria suspendBizTranIdCriteria = new LongCriteria();
    private LongCriteria jaIdCriteria = new LongCriteria();
    private LongCriteria branchIdCriteria = new LongCriteria();
    private StringCriteria subSystemCodeCriteria = new StringCriteria();
    private LongCriteria bizTranGrpIdCriteria = new LongCriteria();
    private LongCriteria bizTranIdCriteria = new LongCriteria();
    private LocalDateCriteria suspendStartDateCriteria = new LocalDateCriteria();
    private LocalDateCriteria suspendEndDateCriteria = new LocalDateCriteria();
    private StringCriteria suspendReasonCriteria = new StringCriteria();

    // Getter
    public LongCriteria getSuspendBizTranIdCriteria() {
        return suspendBizTranIdCriteria;
    }
    public LongCriteria getJaIdCriteria() {
        return jaIdCriteria;
    }
    public LongCriteria getBranchIdCriteria() {
        return branchIdCriteria;
    }
    public StringCriteria getSubSystemCodeCriteria() {
        return subSystemCodeCriteria;
    }
    public LongCriteria getBizTranGrpIdCriteria() {
        return bizTranGrpIdCriteria;
    }
    public LongCriteria getBizTranIdCriteria() {
        return bizTranIdCriteria;
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
