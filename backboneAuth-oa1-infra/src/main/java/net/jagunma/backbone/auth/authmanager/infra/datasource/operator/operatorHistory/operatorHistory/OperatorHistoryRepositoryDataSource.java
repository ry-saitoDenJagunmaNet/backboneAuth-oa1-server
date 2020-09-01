package net.jagunma.backbone.auth.authmanager.infra.datasource.operator.operatorHistory.operatorHistory;

import net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operatorHistory.OperatorHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operatorHistory.OperatorHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operatorHistory.OperatorHistoryRepository;
import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntityDao;
import org.springframework.stereotype.Component;

/**
 * オペレーター履歴検索 DataSource
 */
@Component
public class OperatorHistoryRepositoryDataSource implements OperatorHistoryRepository {

	private final OperatorHistoryEntityDao operatorHistoryEntityDao;

	// コンストラクタ
	public OperatorHistoryRepositoryDataSource(OperatorHistoryEntityDao operatorHistoryEntityDao) {
		this.operatorHistoryEntityDao = operatorHistoryEntityDao;
	}

	/**
	 * オペレーター履歴の条件検索を行います。
	 *
	 * @param operatorHistoryCriteria オペレーター履歴の検索条件
	 * @return オペレーター履歴
	 */
	@Override
	public OperatorHistory findOneBy(OperatorHistoryCriteria operatorHistoryCriteria) {
		OperatorHistoryEntity operatorHistoryEntity = operatorHistoryEntityDao.findOneBy(operatorHistoryCriteria);
		return OperatorHistory.of(operatorHistoryEntity);
	}

}
