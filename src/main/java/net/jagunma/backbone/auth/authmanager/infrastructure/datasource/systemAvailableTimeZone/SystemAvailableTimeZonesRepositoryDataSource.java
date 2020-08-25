package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.systemAvailableTimeZone;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.systemAvailableTimeZone.SystemAvailableTimeZoneCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.systemAvailableTimeZone.SystemAvailableTimeZones;
import net.jagunma.backbone.auth.authmanager.application.model.domain.systemAvailableTimeZone.SystemAvailableTimeZonesRepository;
import net.jagunma.backbone.auth.model.dao.systemAvailableTimeZone.SystemAvailableTimeZoneEntity;
import net.jagunma.backbone.auth.model.dao.systemAvailableTimeZone.SystemAvailableTimeZoneEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * アカウントロック検索 DataSource
 */
@Component
public class SystemAvailableTimeZonesRepositoryDataSource implements SystemAvailableTimeZonesRepository {

	private final SystemAvailableTimeZoneEntityDao systemAvailableTimeZoneEntityDao;
	private final Orders defaultOrders = Orders.empty().addOrder("subSystemCode").addOrder("dayOfWeek");

	// コンストラクタ
	SystemAvailableTimeZonesRepositoryDataSource(SystemAvailableTimeZoneEntityDao systemAvailableTimeZoneEntityDao) {
		this.systemAvailableTimeZoneEntityDao = systemAvailableTimeZoneEntityDao;
	}

	/**
	 * アカウントロックの条件検索を行います。
	 *
	 * @param systemAvailableTimeZoneCriteria アカウントロックの検索条件
	 * @param orders オーダー指定
	 * @return アカウントロック群
	 */
	@Override
	public SystemAvailableTimeZones selectBy(SystemAvailableTimeZoneCriteria systemAvailableTimeZoneCriteria, Orders orders) {
		List<SystemAvailableTimeZoneEntity> list = systemAvailableTimeZoneEntityDao.findBy(systemAvailableTimeZoneCriteria, orders);
		return SystemAvailableTimeZones.createFrom(list);
	}
	/**
	 * アカウントロックの条件検索を行います。
	 *
	 * @param systemAvailableTimeZoneCriteria アカウントロックの検索条件
	 * @return アカウントロック群
	 */
	@Override
	public SystemAvailableTimeZones selectBy(SystemAvailableTimeZoneCriteria systemAvailableTimeZoneCriteria) {
		return selectBy(systemAvailableTimeZoneCriteria, defaultOrders);
	}

	/**
	 * アカウントロックの全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return アカウントロック群
	 */
	@Override
	public SystemAvailableTimeZones selectAll(Orders orders) {
		return SystemAvailableTimeZones.createFrom(systemAvailableTimeZoneEntityDao.findAll(orders));
	}
	/**
	 * アカウントロックの全件検索を行います。
	 *
	 * @return アカウントロック群
	 */
	@Override
	public SystemAvailableTimeZones selectAll() {
		return selectAll(defaultOrders);
	}
}
