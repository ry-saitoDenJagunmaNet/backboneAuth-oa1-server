package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.signTrace.signOutTrace;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.signTrace.signOutTrace.SignOutTraceCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.signTrace.signOutTrace.SignOutTraces;
import net.jagunma.backbone.auth.authmanager.application.model.domain.signTrace.signOutTrace.SignOutTracesRepository;
import net.jagunma.backbone.auth.model.dao.signOutTrace.SignOutTraceEntity;
import net.jagunma.backbone.auth.model.dao.signOutTrace.SignOutTraceEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * サインアウト証跡検索 DataSource
 */
@Component
public class SignOutTracesRepositoryDataSource implements SignOutTracesRepository {

	private final SignOutTraceEntityDao signOutTraceEntityDao;
	private final Orders defaultOrders = Orders.empty().addOrder("signOutDateTime").addOrder("signOutIpAddress").addOrder("operatorId");

	// コンストラクタ
	SignOutTracesRepositoryDataSource(SignOutTraceEntityDao signOutTraceEntityDao) {
		this.signOutTraceEntityDao = signOutTraceEntityDao;
	}

	/**
	 * サインアウト証跡の条件検索を行います。
	 *
	 * @param signOutTraceCriteria サインアウト証跡の検索条件
	 * @param orders オーダー指定
	 * @return サインアウト証跡群
	 */
	@Override
	public SignOutTraces selectBy(SignOutTraceCriteria signOutTraceCriteria, Orders orders) {
		List<SignOutTraceEntity> list = signOutTraceEntityDao.findBy(signOutTraceCriteria, orders);
		return SignOutTraces.createFrom(list);
	}
	/**
	 * サインアウト証跡の条件検索を行います。
	 *
	 * @param signOutTraceCriteria サインアウト証跡の検索条件
	 * @return サインアウト証跡群
	 */
	@Override
	public SignOutTraces selectBy(SignOutTraceCriteria signOutTraceCriteria) {
		return selectBy(signOutTraceCriteria, defaultOrders);
	}

	/**
	 * サインアウト証跡の全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return サインアウト証跡群
	 */
	@Override
	public SignOutTraces selectAll(Orders orders) {
		return SignOutTraces.createFrom(signOutTraceEntityDao.findAll(orders));
	}
	/**
	 * サインアウト証跡の全件検索を行います。
	 *
	 * @return サインアウト証跡群
	 */
	@Override
	public SignOutTraces selectAll() {
		return selectAll(defaultOrders);
	}
}
