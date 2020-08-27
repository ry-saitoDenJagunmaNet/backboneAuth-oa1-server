package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.operator.passwordHistory;

import net.jagunma.backbone.auth.authmanager.application.model.domain.operator.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.application.model.domain.operator.passwordHistory.PasswordHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.operator.passwordHistory.PasswordHistoryRepository;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntity;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityDao;
import org.springframework.stereotype.Component;

/**
 * パスワード履歴検索 DataSource
 */
@Component
public class PasswordHistoryRepositoryDataSource implements PasswordHistoryRepository {

	private final PasswordHistoryEntityDao passwordHistoryEntityDao;

	// コンストラクタ
	public PasswordHistoryRepositoryDataSource(PasswordHistoryEntityDao passwordHistoryEntityDao) {
		this.passwordHistoryEntityDao = passwordHistoryEntityDao;
	}

	/**
	 * パスワード履歴の条件検索を行います。
	 *
	 * @param passwordHistoryCriteria パスワード履歴の検索条件
	 * @return パスワード履歴
	 */
	@Override
	public PasswordHistory findOneBy(PasswordHistoryCriteria passwordHistoryCriteria) {
		PasswordHistoryEntity passwordHistoryEntity = passwordHistoryEntityDao.findOneBy(passwordHistoryCriteria);
		return PasswordHistory.of(passwordHistoryEntity);
	}
}
