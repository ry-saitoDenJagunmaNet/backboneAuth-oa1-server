package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.operatorSubSystemRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.operatorSubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.application.model.domain.operatorSubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.application.model.domain.operatorSubSystemRole.Operator_SubSystemRolesRepository;
import net.jagunma.backbone.auth.authmanager.application.model.domain.subSystemRole.SubSystemRole;
import net.jagunma.backbone.auth.authmanager.application.model.domain.subSystemRole.SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.application.model.domain.subSystemRole.SubSystemRolesRepository;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntity;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * オペレーター_サブシステムロール割当検索 DataSource
 */
@Component
public class Operator_SubSystemRolesRepositoryDataSource implements Operator_SubSystemRolesRepository {

	private final Operator_SubSystemRoleEntityDao operator_SubSystemRoleEntityDao;
	private final SubSystemRolesRepository subSystemRolesRepository;

	/**
	 * コンストラクタ
	 */
	public Operator_SubSystemRolesRepositoryDataSource(Operator_SubSystemRoleEntityDao operator_SubSystemRoleEntityDao,
		SubSystemRolesRepository subSystemRolesRepository) {

		this.operator_SubSystemRoleEntityDao = operator_SubSystemRoleEntityDao;
		this.subSystemRolesRepository = subSystemRolesRepository;
	}

	/**
	 * オペレーター_ブシステムロール割当群を全件検索します。
	 *
	 * @return オペレーター_ブシステムロール割当群
	 */
	@Override
	public Operator_SubSystemRoles selectAll() {

		// オペレーター_サブシステムロール割当検索
		Orders orders = Orders.empty()
			.addOrder("OperatorId")
			.addOrder("SubSystemRoleCode")
			.addOrder("ExpirationStartDate");
		List<Operator_SubSystemRoleEntity> entities = operator_SubSystemRoleEntityDao.findAll(orders);

		// サブシステムロール検索
		SubSystemRoles subSystemRoles = subSystemRolesRepository.selectAll();

		List<Operator_SubSystemRole> list = newArrayList();
		entities.forEach(ossr -> {
			SubSystemRole subSystemRole = subSystemRoles.getValues().stream().filter(sbr->
				sbr.getSubSystemRoleCode().equals(ossr.getSubSystemRoleCode())).findAny().get();
			Operator_SubSystemRole item = Operator_SubSystemRole.createOf(ossr, subSystemRole);
			list.add(item);
		});

		return Operator_SubSystemRoles.createFrom(list);
	}
}
