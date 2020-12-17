package net.jagunma.backbone.auth.authmanager.infra.datasource.suspendBizTran;

import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntity;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntityCriteria;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntityDao;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.backbone.shared.application.ja.JaAtMomentRepository;
import net.jagunma.common.ddd.model.values.buisiness.datetime.TargetDate;
import net.jagunma.common.util.strings2.Strings2;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAtMomentCriteria;
import net.jagunma.common.values.model.ja.JaCode;
import org.springframework.stereotype.Component;

/**
 * 一時取引抑止検索
 */
@Component
public class SuspendBizTranDataSource implements SuspendBizTranRepository {

    private final SuspendBizTranEntityDao suspendBizTranEntityDao;
    private final JaAtMomentRepository jaAtMomentRepository;
    private final BranchAtMomentRepository branchAtMomentRepository;
    private final BizTranGrpRepository bizTranGrpRepository;
    private final BizTranRepository bizTranRepository;

    // コンストラクタ
    SuspendBizTranDataSource(SuspendBizTranEntityDao suspendBizTranEntityDao,
        JaAtMomentRepository jaAtMomentRepository,
        BranchAtMomentRepository branchAtMomentRepository,
        BizTranGrpRepository bizTranGrpRepository,
        BizTranRepository bizTranRepository) {

        this.suspendBizTranEntityDao = suspendBizTranEntityDao;
        this.jaAtMomentRepository = jaAtMomentRepository;
        this.branchAtMomentRepository = branchAtMomentRepository;
        this.bizTranGrpRepository = bizTranGrpRepository;
        this.bizTranRepository = bizTranRepository;
    }

    /**
     * 一時取引抑止の検索を行います
     *
     * @param suspendBizTranCriteria 一時取引抑止の検索条件
     * @return 一時取引抑止
     */
    public SuspendBizTran findOneBy(SuspendBizTranCriteria suspendBizTranCriteria) {

        // 一時取引抑止の検索
        SuspendBizTranEntityCriteria entityCriteria = new SuspendBizTranEntityCriteria();
        entityCriteria.getSuspendBizTranIdCriteria().assignFrom(suspendBizTranCriteria.getSuspendBizTranIdCriteria());
        entityCriteria.getJaCodeCriteria().assignFrom(suspendBizTranCriteria.getJaCodeCriteria());
        entityCriteria.getBranchCodeCriteria().assignFrom(suspendBizTranCriteria.getBranchCodeCriteria());
        entityCriteria.getSubSystemCodeCriteria().assignFrom(suspendBizTranCriteria.getSubSystemCodeCriteria());
        entityCriteria.getBizTranGrpCodeCriteria().assignFrom(suspendBizTranCriteria.getBizTranGrpCodeCriteria());
        entityCriteria.getBizTranCodeCriteria().assignFrom(suspendBizTranCriteria.getBizTranCodeCriteria());
        entityCriteria.getSuspendStartDateCriteria().assignFrom(suspendBizTranCriteria.getSuspendStartDateCriteria());
        entityCriteria.getSuspendEndDateCriteria().assignFrom(suspendBizTranCriteria.getSuspendEndDateCriteria());
        entityCriteria.getSuspendReasonCriteria().assignFrom(suspendBizTranCriteria.getSuspendReasonCriteria());
        SuspendBizTranEntity suspendBizTranEntity = suspendBizTranEntityDao.findOneBy(entityCriteria);

        // jaAtMomentの検索
        JaAtMoment jaAtMoment = null;
        if (Strings2.isNotEmpty(suspendBizTranEntity.getJaCode())) {
            JaAtMomentCriteria jaAtMomentCriteria = new JaAtMomentCriteria();
            jaAtMomentCriteria.getJaAttributeCriteria().getJaCodeCriteria().setEqualTo(JaCode.of(suspendBizTranEntity.getJaCode()));
            jaAtMomentCriteria.setTargetDate(TargetDate.now());
            jaAtMomentCriteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
            jaAtMoment = jaAtMomentRepository.findOneBy(jaAtMomentCriteria);
        }

        // branchAtMomentの検索
        BranchAtMoment branchAtMoment = null;
        if (Strings2.isNotEmpty(suspendBizTranEntity.getBranchCode())) {
            BranchAtMomentCriteria branchAtMomentCriteria = new BranchAtMomentCriteria();
            branchAtMomentCriteria.getNarrowedJaCodeCriteria().setEqualTo(JaCode.of(suspendBizTranEntity.getJaCode()));
            branchAtMomentCriteria.getBranchAttributeCriteria().getBranchCodeCriteria().setEqualTo(BranchCode.of(suspendBizTranEntity.getBranchCode()));
            branchAtMomentCriteria.setTargetDate(TargetDate.now());
            branchAtMomentCriteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
            branchAtMoment = branchAtMomentRepository.findOneBy(branchAtMomentCriteria);
        }

        // 取引グループの検索
        BizTranGrp bizTranGrp = null;
        if (Strings2.isNotEmpty(suspendBizTranEntity.getBizTranGrpCode())) {
            BizTranGrpCriteria bizTranGrpCriteria = new BizTranGrpCriteria();
            bizTranGrpCriteria .getBizTranGrpCodeCriteria().setEqualTo(suspendBizTranEntity.getBizTranGrpCode());
            bizTranGrp = bizTranGrpRepository.findOneBy(bizTranGrpCriteria);
        }

        // 取引の検索
        BizTran bizTran = null;
        if (Strings2.isNotEmpty(suspendBizTranEntity.getBizTranCode())) {
            BizTranCriteria bizTranCriteria = new BizTranCriteria();
            bizTranCriteria .getBizTranCodeCriteria().setEqualTo(suspendBizTranEntity.getBizTranCode());
            bizTran = bizTranRepository.findOneBy(bizTranCriteria);
        }

        return SuspendBizTran.createFrom(
            suspendBizTranEntity.getSuspendBizTranId(),
            suspendBizTranEntity.getJaCode(),
            suspendBizTranEntity.getBranchCode(),
            suspendBizTranEntity.getSubSystemCode(),
            suspendBizTranEntity.getBizTranGrpCode(),
            suspendBizTranEntity.getBizTranCode(),
            suspendBizTranEntity.getSuspendStartDate(),
            suspendBizTranEntity.getSuspendEndDate(),
            suspendBizTranEntity.getSuspendReason(),
            suspendBizTranEntity.getRecordVersion(),
            jaAtMoment,
            branchAtMoment,
            Strings2.isEmpty(suspendBizTranEntity.getSubSystemCode())? null : SubSystem.codeOf(suspendBizTranEntity.getSubSystemCode()),
            bizTranGrp,
            bizTran);
    }

}
