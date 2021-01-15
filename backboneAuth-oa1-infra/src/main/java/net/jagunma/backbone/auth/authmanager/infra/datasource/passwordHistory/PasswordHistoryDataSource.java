package net.jagunma.backbone.auth.authmanager.infra.datasource.passwordHistory;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistories;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryRepository;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntity;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityCriteria;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * パスワード履歴群検索
 */
@Component
public class PasswordHistoryDataSource implements PasswordHistoryRepository {

    private final PasswordHistoryEntityDao passwordHistoryEntityDao;
    private final OperatorRepository operatorRepository;

    // コンストラクタ
    PasswordHistoryDataSource(PasswordHistoryEntityDao passwordHistoryEntityDao,
        OperatorRepository operatorRepository) {

        this.passwordHistoryEntityDao = passwordHistoryEntityDao;
        this.operatorRepository = operatorRepository;
    }

    /**
     * パスワード履歴群の検索を行います
     *
     * @param passwordHistoryCriteria パスワード履歴の検索条件
     * @param orders                  オーダー指定
     * @return パスワード履歴群
     */
    public PasswordHistories selectBy(PasswordHistoryCriteria passwordHistoryCriteria, Orders orders) {

        // オペレーター群の検索
        OperatorCriteria operatorCriteria = new OperatorCriteria();
        operatorCriteria.getOperatorIdCriteria().assignFrom(passwordHistoryCriteria.getOperatorIdCriteria());
        Operators operators = operatorRepository.selectBy(operatorCriteria, Orders.empty());

        // パスワード履歴群検索
        PasswordHistoryEntityCriteria entityCriteria = new PasswordHistoryEntityCriteria();
        entityCriteria.getPasswordHistoryIdCriteria().assignFrom(passwordHistoryCriteria.getPasswordHistoryIdCriteria());
        entityCriteria.getOperatorIdCriteria().assignFrom(passwordHistoryCriteria.getOperatorIdCriteria());
        entityCriteria.getChangeTypeCriteria().assignFrom(passwordHistoryCriteria.getChangeTypeCriteria());

        List<PasswordHistory> list = newArrayList();
        for (PasswordHistoryEntity entity : passwordHistoryEntityDao.findBy(entityCriteria, orders)) {
            list.add(PasswordHistory.createFrom(
                entity.getPasswordHistoryId(),
                entity.getOperatorId(),
                entity.getChangeDateTime(),
                entity.getPassword(),
                PasswordChangeType.codeOf(entity.getChangeType()),
                entity.getRecordVersion(),
                operators.getValues().stream().filter(o->
                    o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null)
            ));
        }

        return PasswordHistories.createFrom(list);
    }
}
