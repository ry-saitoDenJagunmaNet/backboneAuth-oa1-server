package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.history.passwordHistory;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.history.passwordHistory.PasswordHistories;
import net.jagunma.backbone.auth.authmanager.application.model.domain.history.passwordHistory.PasswordHistoriesRepository;
import net.jagunma.backbone.auth.authmanager.application.model.domain.history.passwordHistory.PasswordHistoryCriteria;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntity;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * パスワード履歴検索 DataSource
 */
@Component
public class PasswordHistoriesRepositoryDataSource implements PasswordHistoriesRepository {

	private final PasswordHistoryEntityDao passwordHistoryEntityDao;
	private final Orders defaultOrders = Orders.empty().addOrder("changeDateTime");

	// コンストラクタ
	PasswordHistoriesRepositoryDataSource(PasswordHistoryEntityDao passwordHistoryEntityDao) {
		this.passwordHistoryEntityDao = passwordHistoryEntityDao;
	}

	/**
	 * パスワード履歴の条件検索を行います。
	 *
	 * @param passwordHistoryCriteria パスワード履歴の検索条件
	 * @param orders オーダー指定
	 * @return パスワード履歴群
	 */
	@Override
	public PasswordHistories selectBy(PasswordHistoryCriteria passwordHistoryCriteria, Orders orders) {
		List<PasswordHistoryEntity> list = passwordHistoryEntityDao.findBy(passwordHistoryCriteria, orders);
		return PasswordHistories.createFrom(list);
	}
	/**
	 * パスワード履歴の条件検索を行います。
	 *
	 * @param passwordHistoryCriteria パスワード履歴の検索条件
	 * @return パスワード履歴群
	 */
	@Override
	public PasswordHistories selectBy(PasswordHistoryCriteria passwordHistoryCriteria) {
		return selectBy(passwordHistoryCriteria, defaultOrders);
	}

	/**
	 * パスワード履歴の全件検索を行います。
	 *
	 * @param orders  パスワード履歴のオーダー指定
	 * @return パスワード履歴群
	 */
	@Override
	public PasswordHistories selectAll(Orders orders) {
		return PasswordHistories.createFrom(passwordHistoryEntityDao.findAll(orders));
	}
	/**
	 * パスワード履歴の全件検索を行います。
	 *
	 * @return パスワード履歴群
	 */
	@Override
	public PasswordHistories selectAll() {
		return selectAll(defaultOrders);
	}
}
