package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.BizTranRole;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.bizTranRole.BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.application.model.domain.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.application.model.domain.bizTranRole.BizTranRolesRepository;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * 取引ロール検索 DataSource
 */
@Component
public class BizTranRolesRepositoryDataSource implements BizTranRolesRepository {

	private final BizTranRoleEntityDao bizTranRoleEntityDao;

	BizTranRolesRepositoryDataSource(BizTranRoleEntityDao bizTranRoleEntityDao) {
		this.bizTranRoleEntityDao = bizTranRoleEntityDao;
	}

	/**
	 * 取引ロールの条件検索を行います。
	 * @param bizTranRoleCriteria 取引ロールの検索条件
	 * @return 取引ロール群
	 */
	@Override
	public BizTranRoles findBy(BizTranRoleCriteria bizTranRoleCriteria) {
		Orders orders = Orders.empty().addOrder("BizTranRoleCode");
		List<BizTranRoleEntity> list = bizTranRoleEntityDao.findBy(bizTranRoleCriteria, orders);
		return BizTranRoles.createFrom(list);
	}

	/**
	 * 取引ロールの全件検索を行います。
	 * @return 取引ロール群
	 */
	@Override
	public BizTranRoles findAll() {
		Orders orders = Orders.empty().addOrder("BizTranRoleCode");
		return BizTranRoles.createFrom(bizTranRoleEntityDao.findAll(orders));
	}

}
