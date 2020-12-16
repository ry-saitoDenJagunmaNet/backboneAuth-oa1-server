package net.jagunma.backbone.auth.authmanager.infra.datasource.operator;

import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.common.ddd.model.values.buisiness.datetime.TargetDate;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import org.springframework.stereotype.Component;

/**
 * オペレーター検索
 */
@Component
public class OperatorDataSource implements OperatorRepository {

    private final OperatorEntityDao oeratorEntityDao;
    private final BranchAtMomentRepository branchAtMomentRepository;

    // コンストラクタ
    OperatorDataSource(OperatorEntityDao oeratorEntityDao,
        BranchAtMomentRepository branchAtMomentRepository) {

        this.oeratorEntityDao = oeratorEntityDao;
        this.branchAtMomentRepository = branchAtMomentRepository;
    }

    /**
     * オペレーターの検索を行います
     *
     * @param operatorCriteria オペレーターの検索条件
     * @return オペレーター
     */
    public Operator findOneBy(OperatorCriteria operatorCriteria) {

        // オペレーター検索
        OperatorEntity entity = oeratorEntityDao.findOneBy(convertCriteria(operatorCriteria));

        // Branch検索
        BranchAtMomentCriteria branchAtMomentCriteria = new BranchAtMomentCriteria();
        branchAtMomentCriteria.getJaIdentifierCriteria().setEqualTo(entity.getJaId());
        branchAtMomentCriteria.setTargetDate(TargetDate.now());
        branchAtMomentCriteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
        BranchAtMoment branchAtMoment = branchAtMomentRepository.findOneBy(branchAtMomentCriteria);

        return Operator.createFrom(
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
            branchAtMoment);
    }

    /**
     * オペレーターの存在チェックを行います
     *
     * @param operatorCriteria オペレーターの検索条件
     * @return オペレーターの有無
     */
    public boolean existsBy(OperatorCriteria operatorCriteria) {

        // オペレーター検索
        return oeratorEntityDao.existsBy(convertCriteria(operatorCriteria));
    }

    /**
     * オペレーターEntityCriteriaへ変換します
     *
     * @param operatorCriteria オペレーターの検索条件
     * @return オペレーターEntityCriteria
     */
    private OperatorEntityCriteria convertCriteria(OperatorCriteria operatorCriteria) {
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
        return entityCriteria;
    }

}
