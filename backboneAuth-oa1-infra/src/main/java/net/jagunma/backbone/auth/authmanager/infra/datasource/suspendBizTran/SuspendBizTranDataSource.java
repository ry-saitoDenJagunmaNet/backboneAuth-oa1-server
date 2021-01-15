package net.jagunma.backbone.auth.authmanager.infra.datasource.suspendBizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.Objects;
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
import net.jagunma.common.values.model.ja.JasAtMoment;
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
     * @param suspendBizTranId 一時取引抑止ID
     * @return 一時取引抑止
     */
    public SuspendBizTran findOneById(Long suspendBizTranId) {

        // 一時取引抑止の検索
        SuspendBizTranEntityCriteria suspendBizTranEntityCriteria = new SuspendBizTranEntityCriteria();
        suspendBizTranEntityCriteria.getSuspendBizTranIdCriteria().setEqualTo(suspendBizTranId);
        SuspendBizTranEntity suspendBizTranEntity = suspendBizTranEntityDao.findOneBy(suspendBizTranEntityCriteria);

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
            bizTranGrp = bizTranGrpRepository.findOneByCode(suspendBizTranEntity.getBizTranGrpCode());
        }

        // 取引の検索
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
     * 一時取引抑止群の検索を行います
     *
     * @param suspendBizTranCriteria 一時取引抑止の検索条件
     * @param orders                 オーダー指定
     * @return 一時取引抑止群
     */
    public SuspendBizTrans selectBy(SuspendBizTranCriteria suspendBizTranCriteria, Orders orders) {

        List<SuspendBizTran> list = newArrayList();

        // 一時取引抑止群の検索
        List<SuspendBizTranEntity> suspendBizTranEntityList = suspendBizTranEntityDao.findBy(createSuspendBizTranEntityCriteria(suspendBizTranCriteria), Orders.empty());
        if (suspendBizTranEntityList.size() == 0) { return SuspendBizTrans.createFrom(list); }

        // jaAtMomentの検索
        JaAtMomentCriteria jaAtMomentCriteria = new JaAtMomentCriteria();
        if (Strings2.isNotEmpty(suspendBizTranCriteria.getJaCodeCriteria().getEqualTo())) {
            jaAtMomentCriteria.getJaAttributeCriteria().getJaCodeCriteria().setEqualTo(
                JaCode.of(suspendBizTranCriteria.getJaCodeCriteria().getEqualTo()));
        }
        jaAtMomentCriteria.setTargetDate(TargetDate.now());
        jaAtMomentCriteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
        JasAtMoment jasAtMoment = jaAtMomentRepository.selectBy(jaAtMomentCriteria, Orders.empty());

        // branchAtMomentの検索
        BranchAtMomentCriteria branchAtMomentCriteria = new BranchAtMomentCriteria();
        if (Strings2.isNotEmpty(suspendBizTranCriteria.getJaCodeCriteria().getEqualTo())) {
            branchAtMomentCriteria.getNarrowedJaCodeCriteria().setEqualTo(JaCode.of(suspendBizTranCriteria.getJaCodeCriteria().getEqualTo()));
        }
        if (Strings2.isNotEmpty(suspendBizTranCriteria.getBranchCodeCriteria().getEqualTo())) {
            branchAtMomentCriteria.getBranchAttributeCriteria().getBranchCodeCriteria().setEqualTo(
                BranchCode.of(suspendBizTranCriteria.getBranchCodeCriteria().getEqualTo()));
        }
        branchAtMomentCriteria.setTargetDate(TargetDate.now());
        branchAtMomentCriteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
        BranchesAtMoment branchesAtMoment = branchAtMomentRepository.selectBy(branchAtMomentCriteria, Orders.empty());

        // 取引グループの検索
        BizTranGrpCriteria bizTranGrpCriteria = new BizTranGrpCriteria();
        bizTranGrpCriteria .getBizTranGrpCodeCriteria().getIncludes().addAll(
            suspendBizTranEntityList.stream().map(SuspendBizTranEntity::getBizTranGrpCode).filter(
                Objects::nonNull).distinct().collect(Collectors.toList()));
        BizTranGrps bizTranGrps = bizTranGrpRepository.selectBy(bizTranGrpCriteria, Orders.empty());

        // 取引の検索
        BizTranCriteria bizTranCriteria = new BizTranCriteria();
        bizTranCriteria.getBizTranCodeCriteria().getIncludes().addAll(
            suspendBizTranEntityList.stream().map(SuspendBizTranEntity::getBizTranCode).filter(
                Objects::nonNull).distinct().collect(Collectors.toList()));
        BizTrans bizTrans = bizTranRepository.selectBy(bizTranCriteria, Orders.empty());

        for (SuspendBizTranEntity entity : suspendBizTranEntityList) {
            // stream().sorted()でnull項目があると例外になるためBlankに置き換える
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
     * 一時取引抑止EntityCriteriaへ変換します
     *
     * @param suspendBizTranCriteria 一時取引抑止の検索条件
     * @return 一時取引抑止EntityCriteria
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
