package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.operator.operatorHistoryPack.operatorHistoryPackHeader;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.operator.operatorHistoryPack.operatorHistoryPackHeader.OperatorHistoryPackHeaderCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.operator.operatorHistoryPack.operatorHistoryPackHeader.OperatorHistoryPackHeaders;
import net.jagunma.backbone.auth.authmanager.application.model.domain.operator.operatorHistoryPack.operatorHistoryPackHeader.OperatorHistoryPackHeadersRepository;
import net.jagunma.backbone.auth.model.dao.operatorHistoryPackHeader.OperatorHistoryPackHeaderEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistoryPackHeader.OperatorHistoryPackHeaderEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * オペレーター履歴パックヘッダー検索 DataSource
 */
@Component
public class OperatorHistoryPackHeadersRepositoryDataSource implements OperatorHistoryPackHeadersRepository {

	private final OperatorHistoryPackHeaderEntityDao operatorHistoryPackHeaderEntityDao;
	private final Orders defaultOrders = Orders.empty().addOrder("operatorHistoryId");

	// コンストラクタ
	OperatorHistoryPackHeadersRepositoryDataSource(OperatorHistoryPackHeaderEntityDao operatorHistoryPackHeaderEntityDao) {
		this.operatorHistoryPackHeaderEntityDao = operatorHistoryPackHeaderEntityDao;
	}

	/**
	 * オペレーター履歴パックヘッダーの条件検索を行います。
	 *
	 * @param operatorHistoryPackHeaderCriteria オペレーター履歴パックヘッダーの検索条件
	 * @param orders オーダー指定
	 * @return オペレーター履歴パックヘッダー群
	 */
	@Override
	public OperatorHistoryPackHeaders selectBy(OperatorHistoryPackHeaderCriteria operatorHistoryPackHeaderCriteria, Orders orders) {
		List<OperatorHistoryPackHeaderEntity> list = operatorHistoryPackHeaderEntityDao.findBy(operatorHistoryPackHeaderCriteria, orders);
		return OperatorHistoryPackHeaders.createFrom(list);
	}
	/**
	 * オペレーター履歴パックヘッダーの条件検索を行います。
	 *
	 * @param operatorHistoryPackHeaderCriteria オペレーター履歴パックヘッダーの検索条件
	 * @return オペレーター履歴パックヘッダー群
	 */
	@Override
	public OperatorHistoryPackHeaders selectBy(OperatorHistoryPackHeaderCriteria operatorHistoryPackHeaderCriteria) {
		return selectBy(operatorHistoryPackHeaderCriteria, defaultOrders);
	}

	/**
	 * オペレーター履歴パックヘッダーの全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return オペレーター履歴パックヘッダー群
	 */
	@Override
	public OperatorHistoryPackHeaders selectAll(Orders orders) {
		return OperatorHistoryPackHeaders.createFrom(operatorHistoryPackHeaderEntityDao.findAll(orders));
	}
	/**
	 * オペレーター履歴パックヘッダーの全件検索を行います。
	 *
	 * @return オペレーター履歴パックヘッダー群
	 */
	@Override
	public OperatorHistoryPackHeaders selectAll() {
		return selectAll(defaultOrders);
	}
}
