package net.jagunma.backbone.auth.authmanager.infra.datasource.operator;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.ddd.model.values.buisiness.datetime.TargetDate;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import org.springframework.stereotype.Component;

/**
 * オペレーター検索
 */
@Component
public class OperatorDataSource implements OperatorRepository {

    private final OperatorEntityDao operatorEntityDao;
    private final BranchAtMomentRepository branchAtMomentRepository;

    // コンストラクタ
    OperatorDataSource(OperatorEntityDao operatorEntityDao,
        BranchAtMomentRepository branchAtMomentRepository) {

        this.operatorEntityDao = operatorEntityDao;
        this.branchAtMomentRepository = branchAtMomentRepository;
    }

    /**
     * オペレーターの検索を行います
     *
     * @param operatorId オペレーターID
     * @return オペレーター
     */
    public Operator findOneById(Long operatorId) {

        // オペレーター検索
        OperatorCriteria criteria = new OperatorCriteria();
        criteria.getOperatorIdCriteria().setEqualTo(operatorId);
        return findOneBy(criteria);
    }

    /**
     * オペレーターの検索を行います
     *
     * @param operatorCode オペレーターコ－ド
     * @return オペレーター
     */
    public Operator findOneByCode(String operatorCode) {

        // オペレーター検索
        OperatorCriteria criteria = new OperatorCriteria();
        criteria.getOperatorCodeCriteria().setEqualTo(operatorCode);
        return findOneBy(criteria);
    }

    /**
     * オペレーターの検索を行います
     *
     * @param operatorCriteria オペレーターの検索条件
     * @return オペレーター
     */
    private Operator findOneBy(OperatorCriteria operatorCriteria) {

        // オペレーター検索
        OperatorEntity entity = operatorEntityDao.findOneBy(convertCriteria(operatorCriteria));

        // Branch検索
        BranchAtMomentCriteria branchAtMomentCriteria = new BranchAtMomentCriteria();
        branchAtMomentCriteria.getIdentifierCriteria().setEqualTo(entity.getBranchId());
        branchAtMomentCriteria.setTargetDate(TargetDate.now());
        branchAtMomentCriteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
        BranchAtMoment branchAtMoment = branchAtMomentRepository.findOneBy(branchAtMomentCriteria);

        return operatorCreateFrom(entity, branchAtMoment);
    }

    /**
     * オペレーターの存在チェックを行います
     *
     * @param operatorId オペレーターID
     * @return オペレーターの有無
     */
    public boolean existsById(Long operatorId) {

        // オペレーター検索
        OperatorCriteria criteria = new OperatorCriteria();
        criteria.getOperatorIdCriteria().setEqualTo(operatorId);
        return existsBy(criteria);
    }

    /**
     * オペレーターの存在チェックを行います
     *
     * @param operatorCode オペレーターコ－ド
     * @return オペレーターの有無
     */
    public boolean existsByCode(String operatorCode) {

        // オペレーター検索
        OperatorCriteria criteria = new OperatorCriteria();
        criteria.getOperatorCodeCriteria().setEqualTo(operatorCode);
        return existsBy(criteria);
    }

    /**
     * オペレーターの存在チェックを行います
     *
     * @param operatorCriteria オペレーターの検索条件
     * @return オペレーターの有無
     */
    public boolean existsBy(OperatorCriteria operatorCriteria) {

        // オペレーター検索
        return operatorEntityDao.existsBy(convertCriteria(operatorCriteria));
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
        List<OperatorEntity> operatorEntityList = operatorEntityDao.findBy(convertCriteria(operatorCriteria), orders);

        // BranchesAtMoment検索
        BranchAtMomentCriteria branchAtMomentCriteria = new BranchAtMomentCriteria();
        branchAtMomentCriteria.getIdentifierCriteria().getIncludes().addAll(
            operatorEntityList.stream().map(OperatorEntity::getBranchId).distinct().collect(Collectors.toList()));
        branchAtMomentCriteria.setTargetDate(TargetDate.now());
        branchAtMomentCriteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
        BranchesAtMoment branchesAtMoment = branchAtMomentRepository.selectBy(branchAtMomentCriteria, Orders.empty());

        List<Operator> list = newArrayList();
        for (OperatorEntity entity : operatorEntityList) {
            list.add(operatorCreateFrom(entity,
                branchesAtMoment.getValue().stream().filter(b -> b.getIdentifier().equals(entity.getBranchId())).findFirst().orElse(null)));
        }
        return Operators.createFrom(list);
    }

    /**
     * オペレーターEntityCriteriaへ変換します
     *
     * @param operatorCriteria オペレーターの検索条件
     * @return オペレーターEntityCriteria
     */
    private OperatorEntityCriteria convertCriteria(OperatorCriteria operatorCriteria) {
        OperatorEntityCriteria operatorEntityCriteria = new OperatorEntityCriteria();
        operatorEntityCriteria.getOperatorIdCriteria().assignFrom(operatorCriteria.getOperatorIdCriteria());
        operatorEntityCriteria.getOperatorCodeCriteria().assignFrom(operatorCriteria.getOperatorCodeCriteria());
        operatorEntityCriteria.getOperatorNameCriteria().assignFrom(operatorCriteria.getOperatorNameCriteria());
        operatorEntityCriteria.getMailAddressCriteria().assignFrom(operatorCriteria.getMailAddressCriteria());
        operatorEntityCriteria.getValidThruStartDateCriteria().assignFrom(operatorCriteria.getValidThruStartDateCriteria());
        operatorEntityCriteria.getValidThruEndDateCriteria().assignFrom(operatorCriteria.getValidThruEndDateCriteria());
        operatorEntityCriteria.getIsDeviceAuthCriteria().assignFrom(operatorCriteria.getIsDeviceAuthCriteria());
        operatorEntityCriteria.getJaIdCriteria().setEqualTo(operatorCriteria.getJaIdentifierCriteria().getEqualTo());
        operatorEntityCriteria.getJaCodeCriteria().assignFrom(operatorCriteria.getJaCodeCriteria());
        operatorEntityCriteria.getBranchIdCriteria().assignFrom(operatorCriteria.getBranchIdCriteria());
        operatorEntityCriteria.getBranchCodeCriteria().assignFrom(operatorCriteria.getBranchCodeCriteria());
        operatorEntityCriteria.getAvailableStatusCriteria().assignFrom(operatorCriteria.getAvailableStatusCriteria());
        return operatorEntityCriteria;
    }

    /**
     * オペレーターを生成します
     *
     * @param operatorEntity オペレーターEntity
     * @param branchAtMoment BranchAtMoment
     * @return オペレーター
     */
    private Operator operatorCreateFrom(OperatorEntity operatorEntity, BranchAtMoment branchAtMoment) {

        return Operator.createFrom(
            operatorEntity.getOperatorId(),
            operatorEntity.getOperatorCode(),
            operatorEntity.getOperatorName(),
            operatorEntity.getMailAddress(),
            operatorEntity.getValidThruStartDate(),
            operatorEntity.getValidThruEndDate(),
            operatorEntity.getIsDeviceAuth(),
            operatorEntity.getJaId(),
            operatorEntity.getJaCode(),
            operatorEntity.getBranchId(),
            operatorEntity.getBranchCode(),
            AvailableStatus.codeOf(operatorEntity.getAvailableStatus()),
            operatorEntity.getRecordVersion(),
            branchAtMoment);
    }
}