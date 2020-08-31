package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.accountLock;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.accountLock.AccountLockCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.application.model.domain.accountLock.AccountLocksRepository;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntity;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * アカウントロック検索 DataSource
 */
@Component
public class AccountLocksRepositoryDataSource implements AccountLocksRepository {

	private final AccountLockEntityDao accountLockEntityDao;
	private final Orders defaultOrders = Orders.empty().addOrder("occurredDateTime");

	// コンストラクタ
	AccountLocksRepositoryDataSource(AccountLockEntityDao accountLockEntityDao) {
		this.accountLockEntityDao = accountLockEntityDao;
	}

	/**
	 * アカウントロックの条件検索を行います。
	 *
	 * @param accountLockCriteria アカウントロックの検索条件
	 * @param orders オーダー指定
	 * @return アカウントロック群
	 */
	@Override
	public AccountLocks selectBy(AccountLockCriteria accountLockCriteria, Orders orders) {
		List<AccountLockEntity> list = accountLockEntityDao.findBy(accountLockCriteria, orders);
		return AccountLocks.createFrom(list);
	}
	/**
	 * アカウントロックの条件検索を行います。
	 *
	 * @param accountLockCriteria アカウントロックの検索条件
	 * @return アカウントロック群
	 */
	@Override
	public AccountLocks selectBy(AccountLockCriteria accountLockCriteria) {
		return selectBy(accountLockCriteria, defaultOrders);
	}

	/**
	 * アカウントロックの全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return アカウントロック群
	 */
	@Override
	public AccountLocks selectAll(Orders orders) {
		return AccountLocks.createFrom(accountLockEntityDao.findAll(orders));
	}
	/**
	 * アカウントロックの全件検索を行います。
	 *
	 * @return アカウントロック群
	 */
	@Override
	public AccountLocks selectAll() {
		return selectAll(defaultOrders);
	}
}
