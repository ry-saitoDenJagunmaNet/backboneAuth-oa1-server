package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.history.operatorHistoryPack.operatorHistory;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistoryPack.operatorHistory.OperatorHistories;
import net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistoryPack.operatorHistory.OperatorHistoriesRepository;
import net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistoryPack.operatorHistory.OperatorHistoryCriteria;
import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * オペレーター履歴検索 DataSource
 */
@Component
public class OperatorHistoriesRepositoryDataSource implements OperatorHistoriesRepository {

	private final OperatorHistoryEntityDao operatorHistoryEntityDao;
	private final Orders defaultOrders = Orders.empty().addOrder("operatorHistoryId");

	// コンストラクタ
	OperatorHistoriesRepositoryDataSource(OperatorHistoryEntityDao operatorHistoryEntityDao) {
		this.operatorHistoryEntityDao = operatorHistoryEntityDao;
	}

	/**
	 * オペレーター履歴の条件検索を行います。
	 *
	 * @param operatorHistoryCriteria オペレーター履歴の検索条件
	 * @param orders オーダー指定
	 * @return オペレーター履歴群
	 */
	@Override
	public OperatorHistories selectBy(OperatorHistoryCriteria operatorHistoryCriteria, Orders orders) {
		List<OperatorHistoryEntity> list = operatorHistoryEntityDao.findBy(operatorHistoryCriteria, orders);
		return OperatorHistories.createFrom(list);
	}
	/**
	 * オペレーター履歴の条件検索を行います。
	 *
	 * @param operatorHistoryCriteria オペレーター履歴の検索条件
	 * @return オペレーター履歴群
	 */
	@Override
	public OperatorHistories selectBy(OperatorHistoryCriteria operatorHistoryCriteria) {
		return selectBy(operatorHistoryCriteria, defaultOrders);
	}

	/**
	 * オペレーター履歴の全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return オペレーター履歴群
	 */
	@Override
	public OperatorHistories selectAll(Orders orders) {
		return OperatorHistories.createFrom(operatorHistoryEntityDao.findAll(orders));
	}
	/**
	 * オペレーター履歴の全件検索を行います。
	 *
	 * @return オペレーター履歴群
	 */
	@Override
	public OperatorHistories selectAll() {
		return selectAll(defaultOrders);
	}
}
