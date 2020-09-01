package net.jagunma.backbone.auth.authmanager.infra.datasource.operator.operatorHistory.operator_BizTranRoleHistory;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operator_BizTranRoleHistory.Operator_BizTranRoleHistories;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operator_BizTranRoleHistory.Operator_BizTranRoleHistoriesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operator_BizTranRoleHistory.Operator_BizTranRoleHistoryCriteria;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRoleHistory.Operator_BizTranRoleHistoryEntity;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRoleHistory.Operator_BizTranRoleHistoryEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * オペレーター_取引ロール割当履歴検索 DataSource
 */
@Component
public class Operator_BizTranRoleHistoriesDataSource implements
    Operator_BizTranRoleHistoriesRepository {

    private final Operator_BizTranRoleHistoryEntityDao operator_BizTranRoleHistoryEntityDao;
    private final Orders defaultOrders = Orders.empty().addOrder("operatorHistoryId")
        .addOrder("operator_BizTranRoleId");

    // コンストラクタ
    Operator_BizTranRoleHistoriesDataSource(
        Operator_BizTranRoleHistoryEntityDao operator_BizTranRoleHistoryEntityDao) {
        this.operator_BizTranRoleHistoryEntityDao = operator_BizTranRoleHistoryEntityDao;
    }

    /**
     * オペレーター_取引ロール割当履歴の条件検索を行います。
     *
     * @param operator_BizTranRoleHistoryCriteria オペレーター_取引ロール割当履歴の検索条件
     * @param orders                              オーダー指定
     * @return オペレーター_取引ロール割当履歴群
     */
    @Override
    public Operator_BizTranRoleHistories selectBy(
        Operator_BizTranRoleHistoryCriteria operator_BizTranRoleHistoryCriteria, Orders orders) {
        List<Operator_BizTranRoleHistoryEntity> list = operator_BizTranRoleHistoryEntityDao
            .findBy(operator_BizTranRoleHistoryCriteria, orders);
        return Operator_BizTranRoleHistories.createFrom(list);
    }

    /**
     * オペレーター_取引ロール割当履歴の条件検索を行います。
     *
     * @param operator_BizTranRoleHistoryCriteria オペレーター_取引ロール割当履歴の検索条件
     * @return オペレーター_取引ロール割当履歴群
     */
    @Override
    public Operator_BizTranRoleHistories selectBy(
        Operator_BizTranRoleHistoryCriteria operator_BizTranRoleHistoryCriteria) {
        return selectBy(operator_BizTranRoleHistoryCriteria, defaultOrders);
    }

    /**
     * オペレーター_取引ロール割当履歴の全件検索を行います。
     *
     * @param orders オーダー指定
     * @return オペレーター_取引ロール割当履歴群
     */
    @Override
    public Operator_BizTranRoleHistories selectAll(Orders orders) {
        return Operator_BizTranRoleHistories
            .createFrom(operator_BizTranRoleHistoryEntityDao.findAll(orders));
    }

    /**
     * オペレーター_取引ロール割当履歴の全件検索を行います。
     *
     * @return オペレーター_取引ロール割当履歴群
     */
    @Override
    public Operator_BizTranRoleHistories selectAll() {
        return selectAll(defaultOrders);
    }
}
