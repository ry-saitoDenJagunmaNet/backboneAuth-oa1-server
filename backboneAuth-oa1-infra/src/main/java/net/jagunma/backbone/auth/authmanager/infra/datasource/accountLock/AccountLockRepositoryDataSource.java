package net.jagunma.backbone.auth.authmanager.infra.datasource.accountLock;

import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockRepository;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntity;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntityDao;
import org.springframework.stereotype.Component;

/**
 * アカウントロック検索 DataSource
 */
@Component
public class AccountLockRepositoryDataSource implements AccountLockRepository {

    private final AccountLockEntityDao accountLockEntityDao;

    // コンストラクタ
    public AccountLockRepositoryDataSource(AccountLockEntityDao accountLockEntityDao) {
        this.accountLockEntityDao = accountLockEntityDao;
    }

    /**
     * アカウントロックの条件検索を行います。
     *
     * @param accountLockCriteria アカウントロックの検索条件
     * @return アカウントロック
     */
    @Override
    public AccountLock findOneBy(AccountLockCriteria accountLockCriteria) {
        AccountLockEntity accountLockEntity = accountLockEntityDao.findOneBy(accountLockCriteria);
        return AccountLock.of(accountLockEntity);
    }
}
