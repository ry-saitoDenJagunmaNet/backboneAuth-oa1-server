package net.jagunma.backbone.auth.authmanager.infra.datasource.suspendBizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTransRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpsRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTransRepository;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntity;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntityCriteria;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntityDao;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.ddd.model.values.buisiness.datetime.TargetDate;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import org.springframework.stereotype.Component;

/**
 * 一時取引抑止群検索
 */
@Component
public class SuspendBizTransDataSource implements SuspendBizTransRepository {

    private final SuspendBizTranEntityDao suspendBizTranEntityDao;
    private final BranchAtMomentRepository branchAtMomentRepository;
    private final BizTranGrpsRepository bizTranGrpsRepository;
    private final BizTransRepository bizTransRepository;

    // コンストラクタ
    SuspendBizTransDataSource(SuspendBizTranEntityDao suspendBizTranEntityDao,
        BranchAtMomentRepository branchAtMomentRepository,
        BizTranGrpsRepository bizTranGrpsRepository,
        BizTransRepository bizTransRepository) {

        this.suspendBizTranEntityDao = suspendBizTranEntityDao;
        this.branchAtMomentRepository = branchAtMomentRepository;
        this.bizTranGrpsRepository = bizTranGrpsRepository;
        this.bizTransRepository = bizTransRepository;
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
        SuspendBizTranEntityCriteria entityCriteria = new SuspendBizTranEntityCriteria();
        entityCriteria.getJaIdCriteria().assignFrom(suspendBizTranCriteria.getJaIdCriteria());
        entityCriteria.getBranchIdCriteria().assignFrom(suspendBizTranCriteria.getBranchIdCriteria());
        entityCriteria.getSubSystemCodeCriteria().assignFrom(suspendBizTranCriteria.getSubSystemCodeCriteria());
        entityCriteria.getBizTranGrpIdCriteria().assignFrom(suspendBizTranCriteria.getBizTranGrpIdCriteria());
        entityCriteria.getBizTranIdCriteria().assignFrom(suspendBizTranCriteria.getBizTranIdCriteria());
        entityCriteria.getSuspendStartDateCriteria().assignFrom(suspendBizTranCriteria.getSuspendStartDateCriteria());
        entityCriteria.getSuspendEndDateCriteria().assignFrom(suspendBizTranCriteria.getSuspendEndDateCriteria());
        entityCriteria.getSuspendReasonCriteria().assignFrom(suspendBizTranCriteria.getSuspendReasonCriteria());
        List<SuspendBizTranEntity> suspendBizTranEntityList = suspendBizTranEntityDao.findBy(entityCriteria, Orders.empty());
        if (suspendBizTranEntityList.size() == 0) { return SuspendBizTrans.createFrom(list); }

        // branchAtMomentの検索
        BranchAtMomentCriteria branchAtMomentCriteria = new BranchAtMomentCriteria();
        branchAtMomentCriteria.getJaIdentifierCriteria().setEqualTo(suspendBizTranCriteria.getJaIdCriteria().getEqualTo());
        branchAtMomentCriteria.getIdentifierCriteria().setEqualTo(suspendBizTranCriteria.getBranchIdCriteria().getEqualTo());
        branchAtMomentCriteria.setTargetDate(TargetDate.now());
        branchAtMomentCriteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
        BranchesAtMoment branchesAtMoment = branchAtMomentRepository.selectBy(branchAtMomentCriteria, Orders.empty());

        // 取引グループの検索
        BizTranGrpCriteria bizTranGrpCriteria = new BizTranGrpCriteria();
        bizTranGrpCriteria.getBizTranGrpIdCriteria().getIncludes().addAll(
            suspendBizTranEntityList.stream().map(SuspendBizTranEntity::getBizTranGrpId).distinct().collect(Collectors.toList()));
        BizTranGrps bizTranGrps = bizTranGrpsRepository.selectBy(bizTranGrpCriteria, Orders.empty());

        // 取引の検索
        BizTranCriteria bizTranCriteria = new BizTranCriteria();
        bizTranCriteria.getBizTranIdCriteria().getIncludes().addAll(
            suspendBizTranEntityList.stream().map(SuspendBizTranEntity::getBizTranId).distinct().collect(Collectors.toList()));
        BizTrans bizTrans = bizTransRepository.selectBy(bizTranCriteria, Orders.empty());

        for (SuspendBizTranEntity entity : suspendBizTranEntityList) {
            list.add(SuspendBizTran.createFrom(
                entity.getSuspendBizTranId(),
                entity.getJaId(),
                entity.getBranchId(),
                entity.getSubSystemCode(),
                entity.getBizTranGrpId(),
                entity.getBizTranId(),
                entity.getSuspendStartDate(),
                entity.getSuspendEndDate(),
                entity.getSuspendReason(),
                entity.getRecordVersion(),
                branchesAtMoment.getValue().stream().filter(b->b.getIdentifier().equals(entity.getBranchId())).findFirst().orElse(null),
                SubSystem.codeOf(entity.getSubSystemCode()),
                bizTranGrps.getValues().stream().filter(b->b.getBizTranGrpId().equals(entity.getBizTranGrpId())).findFirst().orElse(null),
                bizTrans.getValues().stream().filter(b->b.getBizTranId().equals(entity.getBizTranId())).findFirst().orElse(null)));
        }

        return SuspendBizTrans.createFrom(list.stream().sorted(orders.toComparator()).collect(Collectors.toList()));
    }
}
