package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.operator.roleAssignment.operator_BizTranRole;

import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.application.model.domain.operator.roleAssignment.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.application.model.domain.operator.roleAssignment.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.operator.roleAssignment.operator_BizTranRole.Operator_BizTranRoleRepository;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntityDao;
import org.springframework.stereotype.Component;

/**
 * オペレーター_取引ロール割当検索 DataSource
 */
@Component
public class Operator_BizTranRoleRepositoryDataSource implements Operator_BizTranRoleRepository {

	private final Operator_BizTranRoleEntityDao operator_BizTranRoleEntityDao;
	private final BizTranRoleRepository bizTranRoleRepository;

	/**
	 * コンストラクタ
	 */
	public Operator_BizTranRoleRepositoryDataSource(Operator_BizTranRoleEntityDao operator_BizTranRoleEntityDao,
		BizTranRoleRepository bizTranRoleRepository) {

		this.operator_BizTranRoleEntityDao = operator_BizTranRoleEntityDao;
		this.bizTranRoleRepository = bizTranRoleRepository;
	}

	/**
	 * オペレーター_取引ロール割当の条件検索を行います。
	 * @param operator_BizTranRoleCriteria オペレーター_取引ロール割当の検索条件
	 * @return オペレーター_取引ロール割当
	 */
	@Override
	public Operator_BizTranRole findOneBy(Operator_BizTranRoleCriteria operator_BizTranRoleCriteria) {

		// オペレーター_取引ロール割当
		Operator_BizTranRoleEntity operator_BizTranRoleEntity = operator_BizTranRoleEntityDao.findOneBy(operator_BizTranRoleCriteria);

		// 取引ロール
		BizTranRoleCriteria bizTranRoleCriteria = new BizTranRoleCriteria();
		bizTranRoleCriteria.getBizTranRoleIdCriteria().setEqualTo(operator_BizTranRoleEntity.getBizTranRoleId());

		return Operator_BizTranRole.createOf(operator_BizTranRoleEntity,
			bizTranRoleRepository.findOneBy(bizTranRoleCriteria));
	}

}
