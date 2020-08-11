package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.BizTranRole;

import net.jagunma.backbone.auth.authmanager.application.model.domain.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.application.model.domain.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.bizTranRole.BizTranRoleRepository;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntityDao;
import org.springframework.stereotype.Component;

/**
 * 取引ロール検索 DataSource
 */
@Component
public class BizTranRoleRepositoryDataSource implements BizTranRoleRepository {

	private final BizTranRoleEntityDao bizTranRoleEntityDao;

	public BizTranRoleRepositoryDataSource(BizTranRoleEntityDao bizTranRoleEntityDao) {
		this.bizTranRoleEntityDao = bizTranRoleEntityDao;
	}

	/**
	 * 取引ロールの条件検索を行います。
	 * @param bizTranRoleCriteria 取引ロールの検索条件
	 * @return 取引ロール
	 */
	@Override
	public BizTranRole findBy(BizTranRoleCriteria bizTranRoleCriteria) {
		BizTranRoleEntity bizTranRoleEntity = bizTranRoleEntityDao.findOneBy(bizTranRoleCriteria);
		return BizTranRole.of(bizTranRoleEntity);
	}
}
