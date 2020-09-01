package net.jagunma.backbone.auth.authmanager.infra.datasource.operator.operatorHistory.operatorHistoryHeader;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operatorHistoryHeader.OperatorHistoryHeaderCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operatorHistoryHeader.OperatorHistoryHeaders;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operatorHistoryHeader.OperatorHistoryHeadersRepository;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * オペレーター履歴パックヘッダー検索 DataSource
 */
@Component
public class OperatorHistoryHeadersDataSource implements OperatorHistoryHeadersRepository {

    private final OperatorHistoryHeaderEntityDao operatorHistoryHeaderEntityDao;
    private final Orders defaultOrders = Orders.empty().addOrder("operatorHistoryId");

    // コンストラクタ
    OperatorHistoryHeadersDataSource(
        OperatorHistoryHeaderEntityDao operatorHistoryHeaderEntityDao) {
        this.operatorHistoryHeaderEntityDao = operatorHistoryHeaderEntityDao;
    }

    /**
     * オペレーター履歴パックヘッダーの条件検索を行います。
     *
     * @param operatorHistoryHeaderCriteria オペレーター履歴パックヘッダーの検索条件
     * @param orders                            オーダー指定
     * @return オペレーター履歴パックヘッダー群
     */
    @Override
    public OperatorHistoryHeaders selectBy(
        OperatorHistoryHeaderCriteria operatorHistoryHeaderCriteria, Orders orders) {
        List<OperatorHistoryHeaderEntity> list = operatorHistoryHeaderEntityDao
            .findBy(operatorHistoryHeaderCriteria, orders);
        return OperatorHistoryHeaders.createFrom(list);
    }

    /**
     * オペレーター履歴パックヘッダーの条件検索を行います。
     *
     * @param operatorHistoryHeaderCriteria オペレーター履歴パックヘッダーの検索条件
     * @return オペレーター履歴パックヘッダー群
     */
    @Override
    public OperatorHistoryHeaders selectBy(
        OperatorHistoryHeaderCriteria operatorHistoryHeaderCriteria) {
        return selectBy(operatorHistoryHeaderCriteria, defaultOrders);
    }

    /**
     * オペレーター履歴パックヘッダーの全件検索を行います。
     *
     * @param orders オーダー指定
     * @return オペレーター履歴パックヘッダー群
     */
    @Override
    public OperatorHistoryHeaders selectAll(Orders orders) {
        return OperatorHistoryHeaders
            .createFrom(operatorHistoryHeaderEntityDao.findAll(orders));
    }

    /**
     * オペレーター履歴パックヘッダーの全件検索を行います。
     *
     * @return オペレーター履歴パックヘッダー群
     */
    @Override
    public OperatorHistoryHeaders selectAll() {
        return selectAll(defaultOrders);
    }
}
