package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.history.operatorHistoryPack.operator_SubSystemRoleHistory;

import net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistoryPack.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistory;
import net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistoryPack.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistoryPack.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistoryRepository;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistoryEntity;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistoryEntityDao;
import org.springframework.stereotype.Component;

/**
 * オペレーター_サブシステムロール割当履歴検索 DataSource
 */
@Component
public class Operator_SubSystemRoleHistoryRepositoryDataSource implements	Operator_SubSystemRoleHistoryRepository {

	private final Operator_SubSystemRoleHistoryEntityDao operator_SubSystemRoleHistoryEntityDao;

	// コンストラクタ
	public Operator_SubSystemRoleHistoryRepositoryDataSource(Operator_SubSystemRoleHistoryEntityDao operator_SubSystemRoleHistoryEntityDao) {
		this.operator_SubSystemRoleHistoryEntityDao = operator_SubSystemRoleHistoryEntityDao;
	}

	/**
	 * オペレーター_サブシステムロール割当履歴の条件検索を行います。
	 *
	 * @param operator_SubSystemRoleHistoryCriteria オペレーター_サブシステムロール割当履歴の検索条件
	 * @return オペレーター_サブシステムロール割当履歴
	 */
	@Override
	public Operator_SubSystemRoleHistory findOneBy(Operator_SubSystemRoleHistoryCriteria operator_SubSystemRoleHistoryCriteria) {
		Operator_SubSystemRoleHistoryEntity operator_SubSystemRoleHistoryEntity = operator_SubSystemRoleHistoryEntityDao.findOneBy(operator_SubSystemRoleHistoryCriteria);
		return Operator_SubSystemRoleHistory.of(operator_SubSystemRoleHistoryEntity);
	}
}
