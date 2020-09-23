package net.jagunma.backbone.auth.authmanager.infra.datasource.operator;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorsRepository;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * オペレーター群検索
 */
@Component
public class OperatorsDataSource implements OperatorsRepository {

    private final OperatorEntityDao oeratorEntityDao;

    // コンストラクタ
    OperatorsDataSource(OperatorEntityDao oeratorEntityDao) {
        this.oeratorEntityDao = oeratorEntityDao;
    }

    /**
     * オペレーター群の検索を行います。
     *
     * @param operatorCriteria オペレーターの検索条件
     * @param orders           オーダー指定
     * @return オペレーター群
     */
    public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {

        OperatorEntityCriteria entityCriteria = new OperatorEntityCriteria();
        entityCriteria.getOperatorIdCriteria().getIncludes().addAll(operatorCriteria.getOperatorIdCriteria().getIncludes());
        entityCriteria.getOperatorCodeCriteria().setForwardMatch(operatorCriteria.getOperatorCodeCriteria().getForwardMatch());
        entityCriteria.getOperatorNameCriteria().setForwardMatch(operatorCriteria.getOperatorNameCriteria().getForwardMatch());
        entityCriteria.getMailAddressCriteria().setForwardMatch(operatorCriteria.getMailAddressCriteria().getForwardMatch());
        entityCriteria.getExpirationStartDateCriteria().setLessOrEqual(operatorCriteria.getExpirationStartDateCriteria().getLessOrEqual());
        entityCriteria.getExpirationStartDateCriteria().setMoreOrEqual(operatorCriteria.getExpirationStartDateCriteria().getMoreOrEqual());
        entityCriteria.getExpirationEndDateCriteria().setLessOrEqual(operatorCriteria.getExpirationEndDateCriteria().getLessOrEqual());
        entityCriteria.getExpirationEndDateCriteria().setMoreOrEqual(operatorCriteria.getExpirationEndDateCriteria().getMoreOrEqual());
        entityCriteria.getIsDeviceAuthCriteria().setEqualTo(operatorCriteria.getIsDeviceAuthCriteria().getEqualTo());
        entityCriteria.getJaIdCriteria().setEqualTo(operatorCriteria.getJaIdCriteria().getEqualTo());
		entityCriteria.getTempoIdCriteria().setEqualTo(operatorCriteria.getTempoIdCriteria().getEqualTo());
        entityCriteria.getAvailableStatusCriteria().getIncludes().addAll(operatorCriteria.getAvailableStatusCriteria().getIncludes());

        // オペレーター検索
        List<Operator> list = newArrayList();
        for (OperatorEntity entity : oeratorEntityDao.findBy(entityCriteria, orders)) {
            list.add(Operator.createFrom(
                entity.getOperatorId(),
                entity.getOperatorCode(),
                entity.getOperatorName(),
                entity.getMailAddress(),
                entity.getExpirationStartDate(),
                entity.getExpirationEndDate(),
                entity.getIsDeviceAuth(),
                entity.getJaId(),
                entity.getJaCode(),
                entity.getTempoId(),
                entity.getTempoCode(),
                entity.getAvailableStatus(),
                entity.getRecordVersion()));
        }

        return Operators.createFrom(list);
    }
}
