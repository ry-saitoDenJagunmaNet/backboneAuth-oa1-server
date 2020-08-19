package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.roleAssignment.operator_BizTranRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.BizTranRolesRepository;
import net.jagunma.backbone.auth.authmanager.application.model.domain.roleAssignment.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.application.model.domain.roleAssignment.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.roleAssignment.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.application.model.domain.roleAssignment.operator_BizTranRole.Operator_BizTranRolesRepository;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * オペレーター_取引ロール割当検索 DataSource
 */
@Component
public class Operator_BizTranRolesRepositoryDataSource implements Operator_BizTranRolesRepository {

	private final Operator_BizTranRoleEntityDao operator_BizTranRoleEntityDao;
	private final BizTranRolesRepository bizTranRolesRepository;

	// コンストラクタ
	public Operator_BizTranRolesRepositoryDataSource(Operator_BizTranRoleEntityDao operator_BizTranRoleEntityDao,
		BizTranRolesRepository bizTranRolesRepository) {

		this.operator_BizTranRoleEntityDao = operator_BizTranRoleEntityDao;
		this.bizTranRolesRepository = bizTranRolesRepository;
	}

	/**
	 * オペレーター_取引ロール割当の条件検索を行います。
	 *
	 * @param operator_BizTranRoleCriteria オペレーター_取引ロール割当の検索条件
	 * @return オペレーター_取引ロール割当群
	 */
	@Override
	public Operator_BizTranRoles selectBy(Operator_BizTranRoleCriteria operator_BizTranRoleCriteria) {
		// TODO
		return Operator_BizTranRoles.createFrom(newArrayList());
	}

	/**
	 * オペレーター_取引ロール割当群を全件検索します。
	 *
	 * @return オペレーター_取引ロール割当群
	 */
	@Override
	public Operator_BizTranRoles selectAll() {

		// オペレーター_取引ロール割当
		Orders orders = Orders.empty()
			.addOrder("operatorId")
			.addOrder("bizTranRoleId")
			.addOrder("expirationStartDate");
		List<Operator_BizTranRoleEntity> entityList = operator_BizTranRoleEntityDao.findAll(orders);

		// 取引ロール
		BizTranRoles bizTranRoles = bizTranRolesRepository.selectAll();

		List<Operator_BizTranRole> operator_BizTranRolesList = newArrayList();
		entityList.forEach(entity -> {
			// 取引ロールの結合
			var bizTranRole = bizTranRoles.getValues()
				.stream()
				.filter(btr -> btr.getBizTranRoleId().equals(entity.getBizTranRoleId()))
				.findFirst()
				.orElse(BizTranRole.empty());
			operator_BizTranRolesList.add(Operator_BizTranRole.createOf(entity, bizTranRole));
		});

		return Operator_BizTranRoles.createFrom(operator_BizTranRolesList);
	}
}
