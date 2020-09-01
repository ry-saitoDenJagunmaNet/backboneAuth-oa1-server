package net.jagunma.backbone.auth.authmanager.infra.datasource.operator.operatorHistory.operator_SubSystemRoleHistory;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistories;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistoriesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistoryCriteria;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistoryEntity;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistoryEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * オペレーター_サブシステムロール割当履歴検索 DataSource
 */
@Component
public class Operator_SubSystemRoleHistoriesDataSource implements
    Operator_SubSystemRoleHistoriesRepository {

    private final Operator_SubSystemRoleHistoryEntityDao operator_SubSystemRoleHistoryEntityDao;
    private final Orders defaultOrders = Orders.empty().addOrder("operatorHistoryId")
        .addOrder("operator_SubSystemRoleId");

    // コンストラクタ
    Operator_SubSystemRoleHistoriesDataSource(
        Operator_SubSystemRoleHistoryEntityDao operator_SubSystemRoleHistoryEntityDao) {
        this.operator_SubSystemRoleHistoryEntityDao = operator_SubSystemRoleHistoryEntityDao;
    }

    /**
     * オペレーター_サブシステムロール割当履歴の条件検索を行います。
     *
     * @param operator_SubSystemRoleHistoryCriteria オペレーター_サブシステムロール割当履歴の検索条件
     * @param orders                                オーダー指定
     * @return オペレーター_サブシステムロール割当履歴群
     */
    @Override
    public Operator_SubSystemRoleHistories selectBy(
        Operator_SubSystemRoleHistoryCriteria operator_SubSystemRoleHistoryCriteria,
        Orders orders) {
        List<Operator_SubSystemRoleHistoryEntity> list = operator_SubSystemRoleHistoryEntityDao
            .findBy(operator_SubSystemRoleHistoryCriteria, orders);
        return Operator_SubSystemRoleHistories.createFrom(list);
    }

    /**
     * オペレーター_サブシステムロール割当履歴の条件検索を行います。
     *
     * @param operator_SubSystemRoleHistoryCriteria オペレーター_サブシステムロール割当履歴の検索条件
     * @return オペレーター_サブシステムロール割当履歴群
     */
    @Override
    public Operator_SubSystemRoleHistories selectBy(
        Operator_SubSystemRoleHistoryCriteria operator_SubSystemRoleHistoryCriteria) {
        return selectBy(operator_SubSystemRoleHistoryCriteria, defaultOrders);
    }

    /**
     * オペレーター_サブシステムロール割当履歴の全件検索を行います。
     *
     * @param orders オーダー指定
     * @return オペレーター_サブシステムロール割当履歴群
     */
    @Override
    public Operator_SubSystemRoleHistories selectAll(Orders orders) {
        return Operator_SubSystemRoleHistories
            .createFrom(operator_SubSystemRoleHistoryEntityDao.findAll(orders));
    }

    /**
     * オペレーター_サブシステムロール割当履歴の全件検索を行います。
     *
     * @return オペレーター_サブシステムロール割当履歴群
     */
    @Override
    public Operator_SubSystemRoleHistories selectAll() {
        return selectAll(defaultOrders);
    }
}
