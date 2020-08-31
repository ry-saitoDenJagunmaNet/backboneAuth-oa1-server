package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.history.passwordHistory;

import net.jagunma.backbone.auth.authmanager.application.model.domain.history.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.application.model.domain.history.passwordHistory.PasswordHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.history.passwordHistory.PasswordHistoryRepository;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntity;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityDao;
import org.springframework.stereotype.Component;

/**
 * パスワード履歴検索 DataSource
 */
@Component
public class PasswordHistoryDataSource implements PasswordHistoryRepository {

    private final PasswordHistoryEntityDao passwordHistoryEntityDao;

    // コンストラクタ
    public PasswordHistoryDataSource(PasswordHistoryEntityDao passwordHistoryEntityDao) {
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
        PasswordHistoryEntity passwordHistoryEntity = passwordHistoryEntityDao
            .findOneBy(passwordHistoryCriteria);
        return PasswordHistory.of(passwordHistoryEntity);
    }
}
