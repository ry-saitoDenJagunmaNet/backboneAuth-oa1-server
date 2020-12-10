package net.jagunma.backbone.auth.authmanager.infra.datasource.operator;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorsRepository;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.ddd.model.values.buisiness.datetime.TargetDate;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import org.springframework.stereotype.Component;

/**
 * オペレーター群検索
 */
@Component
public class OperatorsDataSource implements OperatorsRepository {

    private final OperatorEntityDao operatorEntityDao;
    private final BranchAtMomentRepository branchAtMomentRepository;

    // コンストラクタ
    OperatorsDataSource(OperatorEntityDao operatorEntityDao,
        BranchAtMomentRepository branchAtMomentRepository) {

        this.operatorEntityDao = operatorEntityDao;
        this.branchAtMomentRepository = branchAtMomentRepository;
    }

    /**
     * オペレーター群の検索を行います
     *
     * @param operatorCriteria オペレーターの検索条件
     * @param orders           オーダー指定
     * @return オペレーター群
     */
    public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {

        // オペレーター検索
        OperatorEntityCriteria entityCriteria = new OperatorEntityCriteria();
        entityCriteria.getOperatorIdCriteria().assignFrom(operatorCriteria.getOperatorIdCriteria());
        entityCriteria.getOperatorCodeCriteria().assignFrom(operatorCriteria.getOperatorCodeCriteria());
        entityCriteria.getOperatorNameCriteria().assignFrom(operatorCriteria.getOperatorNameCriteria());
        entityCriteria.getMailAddressCriteria().assignFrom(operatorCriteria.getMailAddressCriteria());
        entityCriteria.getValidThruStartDateCriteria().assignFrom(operatorCriteria.getValidThruStartDateCriteria());
        entityCriteria.getValidThruEndDateCriteria().assignFrom(operatorCriteria.getValidThruEndDateCriteria());
        entityCriteria.getIsDeviceAuthCriteria().assignFrom(operatorCriteria.getIsDeviceAuthCriteria());
        entityCriteria.getJaIdCriteria().setEqualTo(operatorCriteria.getJaIdentifierCriteria().getEqualTo());
        entityCriteria.getJaCodeCriteria().assignFrom(operatorCriteria.getJaCodeCriteria());
        entityCriteria.getBranchIdCriteria().assignFrom(operatorCriteria.getBranchIdCriteria());
        entityCriteria.getBranchCodeCriteria().assignFrom(operatorCriteria.getBranchCodeCriteria());
        entityCriteria.getAvailableStatusCriteria().assignFrom(operatorCriteria.getAvailableStatusCriteria());
        List<OperatorEntity> operatorEntity = operatorEntityDao.findBy(entityCriteria, orders);

        // BranchesAtMoment検索
        BranchAtMomentCriteria branchAtMomentCriteria = new BranchAtMomentCriteria();
        branchAtMomentCriteria.getIdentifierCriteria().getIncludes().addAll(
            operatorEntity.stream().map(OperatorEntity::getBranchId).distinct().collect(Collectors.toList()));
        branchAtMomentCriteria.setTargetDate(TargetDate.now());
        branchAtMomentCriteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
        BranchesAtMoment branchesAtMoment = branchAtMomentRepository.selectBy(branchAtMomentCriteria, Orders.empty());

        List<Operator> list = newArrayList();
        for (OperatorEntity entity : operatorEntityDao.findBy(entityCriteria, orders)) {
            list.add(Operator.createFrom(
                entity.getOperatorId(),
                entity.getOperatorCode(),
                entity.getOperatorName(),
                entity.getMailAddress(),
                entity.getValidThruStartDate(),
                entity.getValidThruEndDate(),
                entity.getIsDeviceAuth(),
                entity.getJaId(),
                entity.getJaCode(),
                entity.getBranchId(),
                entity.getBranchCode(),
                AvailableStatus.codeOf(entity.getAvailableStatus()),
                entity.getRecordVersion(),
                branchesAtMoment.getValue().stream().filter(b->b.getIdentifier().equals(entity.getBranchId())).findFirst().orElse(null)
            ));
        }
        return Operators.createFrom(list);
    }
}
