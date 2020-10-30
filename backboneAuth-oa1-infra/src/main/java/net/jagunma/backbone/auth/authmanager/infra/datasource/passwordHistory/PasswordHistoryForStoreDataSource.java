package net.jagunma.backbone.auth.authmanager.infra.datasource.passwordHistory;

import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryRepositoryForStore;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntity;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityDao;
import org.springframework.stereotype.Component;

/**
 * パスワード格納
 */
@Component
public class PasswordHistoryForStoreDataSource implements PasswordHistoryRepositoryForStore {

    private final PasswordHistoryEntityDao passwordHistoryEntityDao;

    // コンストラクタ
    public PasswordHistoryForStoreDataSource(PasswordHistoryEntityDao passwordHistoryEntityDao) {

        this.passwordHistoryEntityDao = passwordHistoryEntityDao;
    }

    /**
     * パスワードの格納を行います
     *
     * @param passwordHistory パスワード履歴
     */
    public void store(PasswordHistory passwordHistory) {

        // パスワード履歴のインサートを行います
        insertPasswordHistory(passwordHistory);
    }

    /**
     * パスワード履歴のインサートを行います
     *
     * @param passwordHistory パスワード履歴
     * @return パスワード履歴エンティティ
     */
    PasswordHistoryEntity insertPasswordHistory(PasswordHistory passwordHistory) {
        PasswordHistoryEntity passwordHistoryEntity = new PasswordHistoryEntity();

        passwordHistoryEntity.setOperatorId(passwordHistory.getOperatorId());
        passwordHistoryEntity.setChangeDateTime(passwordHistory.getChangeDateTime());
        passwordHistoryEntity.setPassword(passwordHistory.getPassword());
        passwordHistoryEntity.setChangeType(passwordHistory.getPasswordChangeType().getCode());

        passwordHistoryEntityDao.insert(passwordHistoryEntity);

        return passwordHistoryEntity;
    }
}
