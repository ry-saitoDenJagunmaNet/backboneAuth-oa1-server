package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.ja;

import net.jagunma.backbone.auth.authmanager.application.model.domain.ja.JaIpAddressRange;
import net.jagunma.backbone.auth.authmanager.application.model.domain.ja.JaIpAddressRangeCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.ja.JaIpAddressRangeRepository;
import net.jagunma.backbone.auth.model.dao.jaIpAddressRange.JaIpAddressRangeEntity;
import net.jagunma.backbone.auth.model.dao.jaIpAddressRange.JaIpAddressRangeEntityDao;
import org.springframework.stereotype.Component;

/**
 * JA割当IPアドレス範囲検索 DataSource
 */
@Component
public class JaIpAddressRangeRepositoryDataSource implements JaIpAddressRangeRepository {

	private final JaIpAddressRangeEntityDao jaIpAddressRangeEntityDao;

	// コンストラクタ
	public JaIpAddressRangeRepositoryDataSource(JaIpAddressRangeEntityDao jaIpAddressRangeEntityDao) {
		this.jaIpAddressRangeEntityDao = jaIpAddressRangeEntityDao;
	}

	/**
	 * JA割当IPアドレス範囲の条件検索を行います。
	 *
	 * @param jaIpAddressRangeCriteria JA割当IPアドレス範囲の検索条件
	 * @return JA割当IPアドレス範囲
	 */
	@Override
	public JaIpAddressRange findOneBy(JaIpAddressRangeCriteria jaIpAddressRangeCriteria) {
		JaIpAddressRangeEntity jaIpAddressRangeEntity = jaIpAddressRangeEntityDao.findOneBy(jaIpAddressRangeCriteria);
		return JaIpAddressRange.of(jaIpAddressRangeEntity);
	}
}
