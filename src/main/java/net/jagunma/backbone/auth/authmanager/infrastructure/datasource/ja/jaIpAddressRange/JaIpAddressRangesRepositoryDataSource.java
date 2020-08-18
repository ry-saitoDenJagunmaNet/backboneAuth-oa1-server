package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.ja.jaIpAddressRange;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.ja.jaIpAddressRange.JaIpAddressRangeCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.ja.jaIpAddressRange.JaIpAddressRanges;
import net.jagunma.backbone.auth.authmanager.application.model.domain.ja.jaIpAddressRange.JaIpAddressRangesRepository;
import net.jagunma.backbone.auth.model.dao.jaIpAddressRange.JaIpAddressRangeEntity;
import net.jagunma.backbone.auth.model.dao.jaIpAddressRange.JaIpAddressRangeEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * JA割当IPアドレス範囲検索 DataSource
 */
@Component
public class JaIpAddressRangesRepositoryDataSource implements JaIpAddressRangesRepository {

	private final JaIpAddressRangeEntityDao jaIpAddressRangeEntityDao;

	// コンストラクタ
	JaIpAddressRangesRepositoryDataSource(JaIpAddressRangeEntityDao jaIpAddressRangeEntityDao) {
		this.jaIpAddressRangeEntityDao = jaIpAddressRangeEntityDao;
	}

	/**
	 * JA割当IPアドレス範囲の条件検索を行います。
	 *
	 * @param jaIpAddressRangeCriteria JA割当IPアドレス範囲の検索条件
	 * @return JA割当IPアドレス範囲群
	 */
	@Override
	public JaIpAddressRanges selectBy(JaIpAddressRangeCriteria jaIpAddressRangeCriteria) {
		Orders orders = Orders.empty()
			.addOrder("jaCode")
			.addOrder("ipAddressRange")
			.addOrder("expirationStartDate");
		List<JaIpAddressRangeEntity> list = jaIpAddressRangeEntityDao.findBy(jaIpAddressRangeCriteria, orders);
		return JaIpAddressRanges.createFrom(list);
	}

	/**
	 * JA割当IPアドレス範囲の全件検索を行います。
	 *
	 * @return JA割当IPアドレス範囲群
	 */
	@Override
	public JaIpAddressRanges selectAll() {
		Orders orders = Orders.empty()
			.addOrder("jaCode")
			.addOrder("ipAddressRange")
			.addOrder("expirationStartDate");
		return JaIpAddressRanges.createFrom(jaIpAddressRangeEntityDao.findAll(orders));
	}

}
