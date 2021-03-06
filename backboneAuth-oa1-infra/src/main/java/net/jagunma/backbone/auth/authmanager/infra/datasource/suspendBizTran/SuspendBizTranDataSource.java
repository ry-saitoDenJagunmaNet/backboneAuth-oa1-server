package net.jagunma.backbone.auth.authmanager.infra.datasource.suspendBizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTrans;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntity;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntityCriteria;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntityDao;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.backbone.shared.application.ja.JaAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.ddd.model.values.buisiness.datetime.TargetDate;
import net.jagunma.common.util.strings2.Strings2;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAtMomentCriteria;
import net.jagunma.common.values.model.ja.JaCode;
import net.jagunma.common.values.model.ja.JaCodes;
import net.jagunma.common.values.model.ja.JasAtMoment;
import org.springframework.stereotype.Component;

/**
 * ????????????????????????
 */
@Component
public class SuspendBizTranDataSource implements SuspendBizTranRepository {

    private final SuspendBizTranEntityDao suspendBizTranEntityDao;
    private final JaAtMomentRepository jaAtMomentRepository;
    private final BranchAtMomentRepository branchAtMomentRepository;
    private final BizTranGrpRepository bizTranGrpRepository;
    private final BizTranRepository bizTranRepository;

    // ?????????????????????
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
     * ??????????????????????????????????????????
     *
     * @param suspendBizTranId ??????????????????ID
     * @return ??????????????????
     */
    public SuspendBizTran findOneById(Long suspendBizTranId) {

        // ???????????????????????????
        SuspendBizTranEntityCriteria suspendBizTranEntityCriteria = new SuspendBizTranEntityCriteria();
        suspendBizTranEntityCriteria.getSuspendBizTranIdCriteria().setEqualTo(suspendBizTranId);
        SuspendBizTranEntity suspendBizTranEntity = suspendBizTranEntityDao.findOneBy(suspendBizTranEntityCriteria);

        // jaAtMoment?????????
        JaAtMoment jaAtMoment = null;
        if (Strings2.isNotEmpty(suspendBizTranEntity.getJaCode())) {
            JaAtMomentCriteria jaAtMomentCriteria = new JaAtMomentCriteria();
            jaAtMomentCriteria.getJaAttributeCriteria().getJaCodeCriteria().setEqualTo(JaCode.of(suspendBizTranEntity.getJaCode()));
            jaAtMomentCriteria.setTargetDate(TargetDate.now());
            jaAtMomentCriteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
            jaAtMoment = jaAtMomentRepository.findOneBy(jaAtMomentCriteria);
        }

        // branchAtMoment?????????
        BranchAtMoment branchAtMoment = null;
        if (Strings2.isNotEmpty(suspendBizTranEntity.getBranchCode())) {
            BranchAtMomentCriteria branchAtMomentCriteria = new BranchAtMomentCriteria();
            branchAtMomentCriteria.getJaAtMomentCriteria().getJaAttributeCriteria().getJaCodeCriteria().setEqualTo(JaCode.of(suspendBizTranEntity.getJaCode()));
            branchAtMomentCriteria.getBranchAttributeCriteria().getBranchCodeCriteria().setEqualTo(BranchCode.of(suspendBizTranEntity.getBranchCode()));
            branchAtMomentCriteria.setTargetDate(TargetDate.now());
            branchAtMomentCriteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
            branchAtMoment = branchAtMomentRepository.findOneBy(branchAtMomentCriteria);
        }

        // ???????????????????????????
        BizTranGrp bizTranGrp = null;
        if (Strings2.isNotEmpty(suspendBizTranEntity.getBizTranGrpCode())) {
            bizTranGrp = bizTranGrpRepository.findOneByCode(suspendBizTranEntity.getBizTranGrpCode());
        }

        // ???????????????
        BizTran bizTran = null;
        if (Strings2.isNotEmpty(suspendBizTranEntity.getBizTranCode())) {
            BizTranCriteria bizTranCriteria = new BizTranCriteria();
            bizTran = bizTranRepository.findOneByCode(suspendBizTranEntity.getBizTranCode());
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

    /**
     * ?????????????????????????????????????????????
     *
     * @param suspendBizTranCriteria ?????????????????????????????????
     * @param orders                 ??????????????????
     * @return ?????????????????????
     */
    public SuspendBizTrans selectBy(SuspendBizTranCriteria suspendBizTranCriteria, Orders orders) {

        List<SuspendBizTran> list = newArrayList();

        // ??????????????????????????????
        List<SuspendBizTranEntity> suspendBizTranEntityList = suspendBizTranEntityDao.findBy(createSuspendBizTranEntityCriteria(suspendBizTranCriteria), Orders.empty());
        if (suspendBizTranEntityList.size() == 0) { return SuspendBizTrans.createFrom(list); }

        // jaAtMoment?????????
        JaAtMomentCriteria jaAtMomentCriteria = new JaAtMomentCriteria();
        List<String> jaCodeList = suspendBizTranEntityList.stream().filter(s -> Strings2.isNotEmpty(s.getJaCode())).map(SuspendBizTranEntity::getJaCode).collect(Collectors.toList());
        jaAtMomentCriteria.getJaAttributeCriteria().getJaCodeCriteria().getIncludes().addAll(JaCodes.createFrom(jaCodeList).getValue());
        jaAtMomentCriteria.setTargetDate(TargetDate.now());
        jaAtMomentCriteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
        JasAtMoment jasAtMoment = jaAtMomentRepository.selectBy(jaAtMomentCriteria, Orders.empty());

        // branchAtMoment?????????
        BranchAtMomentCriteria branchAtMomentCriteria = new BranchAtMomentCriteria();
        List<BranchAtMoment> branchAtMomentList = newArrayList();
        for (JaAtMoment jaAtMoment : jasAtMoment.getValue()) {
            branchAtMomentCriteria.getJaAtMomentCriteria().getIdentifierCriteria().setEqualTo(jaAtMoment.getIdentifier());
            branchAtMomentCriteria.setTargetDate(TargetDate.now());
            branchAtMomentCriteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
            BranchesAtMoment branchesAtMoment = branchAtMomentRepository.selectBy(branchAtMomentCriteria, Orders.empty());
            branchAtMomentList.addAll(branchesAtMoment.getValue());
        }
        BranchesAtMoment branchesAtMoment = BranchesAtMoment.of(branchAtMomentList);

        // ???????????????????????????
        BizTranGrpCriteria bizTranGrpCriteria = new BizTranGrpCriteria();
        bizTranGrpCriteria .getBizTranGrpCodeCriteria().getIncludes().addAll(
            suspendBizTranEntityList.stream().filter(s -> Strings2.isNotEmpty(s.getBizTranGrpCode())).map(SuspendBizTranEntity::getBizTranGrpCode).distinct().collect(Collectors.toList()));
        BizTranGrps bizTranGrps = bizTranGrpRepository.selectBy(bizTranGrpCriteria, Orders.empty());

        // ???????????????
        BizTranCriteria bizTranCriteria = new BizTranCriteria();
        bizTranCriteria.getBizTranCodeCriteria().getIncludes().addAll(
            suspendBizTranEntityList.stream().filter(s -> Strings2.isNotEmpty(s.getBizTranCode())).map(SuspendBizTranEntity::getBizTranCode).distinct().collect(Collectors.toList()));
        BizTrans bizTrans = bizTranRepository.selectBy(bizTranCriteria, Orders.empty());

        for (SuspendBizTranEntity entity : suspendBizTranEntityList) {
            // stream().sorted()???null???????????????????????????????????????Blank??????????????????
            list.add(SuspendBizTran.createFrom(
                entity.getSuspendBizTranId(),
                (Strings2.isNull(entity.getJaCode()))? "" : entity.getJaCode(),
                (Strings2.isNull(entity.getBranchCode()))? "" : entity.getBranchCode(),
                (Strings2.isNull(entity.getSubSystemCode()))? "" : entity.getSubSystemCode(),
                (Strings2.isNull(entity.getBizTranGrpCode()))? "" : entity.getBizTranGrpCode(),
                (Strings2.isNull(entity.getBizTranCode()))? "" : entity.getBizTranCode(),
                entity.getSuspendStartDate(),
                entity.getSuspendEndDate(),
                entity.getSuspendReason(),
                entity.getRecordVersion(),
                jasAtMoment.getValue().stream().filter(b->b.getJaAttribute().getJaCode().getValue().equals(entity.getJaCode())).findFirst().orElse(null),
                branchesAtMoment.getValue().stream().filter(b->b.getBranchAttribute().getBranchCode().getValue().equals(entity.getBranchCode())).findFirst().orElse(null),
                SubSystem.codeOf(entity.getSubSystemCode()),
                bizTranGrps.getValues().stream().filter(b->b.getBizTranGrpCode().equals(entity.getBizTranGrpCode())).findFirst().orElse(null),
                bizTrans.getValues().stream().filter(b->b.getBizTranCode().equals(entity.getBizTranCode())).findFirst().orElse(null)));
        }

        return SuspendBizTrans.createFrom(list.stream().sorted(orders.toComparator()).collect(Collectors.toList()));
    }

    /**
     * ??????????????????EntityCriteria??????????????????
     *
     * @param suspendBizTranCriteria ?????????????????????????????????
     * @return ??????????????????EntityCriteria
     */
    private SuspendBizTranEntityCriteria createSuspendBizTranEntityCriteria(SuspendBizTranCriteria suspendBizTranCriteria) {
        SuspendBizTranEntityCriteria suspendBizTranEntityCriteria = new SuspendBizTranEntityCriteria();
        suspendBizTranEntityCriteria.getSuspendBizTranIdCriteria().assignFrom(suspendBizTranCriteria.getSuspendBizTranIdCriteria());
        suspendBizTranEntityCriteria.getJaCodeCriteria().assignFrom(suspendBizTranCriteria.getJaCodeCriteria());
        suspendBizTranEntityCriteria.getBranchCodeCriteria().assignFrom(suspendBizTranCriteria.getBranchCodeCriteria());
        suspendBizTranEntityCriteria.getSubSystemCodeCriteria().assignFrom(suspendBizTranCriteria.getSubSystemCodeCriteria());
        suspendBizTranEntityCriteria.getBizTranGrpCodeCriteria().assignFrom(suspendBizTranCriteria.getBizTranGrpCodeCriteria());
        suspendBizTranEntityCriteria.getBizTranCodeCriteria().assignFrom(suspendBizTranCriteria.getBizTranCodeCriteria());
        suspendBizTranEntityCriteria.getSuspendStartDateCriteria().assignFrom(suspendBizTranCriteria.getSuspendStartDateCriteria());
        suspendBizTranEntityCriteria.getSuspendEndDateCriteria().assignFrom(suspendBizTranCriteria.getSuspendEndDateCriteria());
        suspendBizTranEntityCriteria.getSuspendReasonCriteria().assignFrom(suspendBizTranCriteria.getSuspendReasonCriteria());
        return suspendBizTranEntityCriteria;
    }
}
