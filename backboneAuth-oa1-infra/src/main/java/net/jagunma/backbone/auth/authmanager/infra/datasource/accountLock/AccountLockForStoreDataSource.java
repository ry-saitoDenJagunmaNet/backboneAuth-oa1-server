package net.jagunma.backbone.auth.authmanager.infra.datasource.accountLock;

import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockRepositoryForStore;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntity;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntityDao;
import org.springframework.stereotype.Component;

/**
 * アカウントロック格納
 */
@Component
public class AccountLockForStoreDataSource implements AccountLockRepositoryForStore {

    private final AccountLockEntityDao accountLockEntityDao;
    private final AccountLockRepository accountLockRepository;

    // コンストラクタ
    AccountLockForStoreDataSource(AccountLockEntityDao accountLockEntityDao,
        AccountLockRepository accountLockRepository) {

        this.accountLockEntityDao = accountLockEntityDao;
        this.accountLockRepository = accountLockRepository;
    }

    /**
     * アカウントロックの追加を行います
     *
     * @param accountLock アカウントロック
     * @return アカウントロック
     */
    public AccountLock insert(AccountLock accountLock) {

        AccountLockEntity accountLockEntity = new AccountLockEntity();
        accountLockEntity.setOperatorId(accountLock.getOperatorId());
        accountLockEntity.setOccurredDateTime(accountLock.getOccurredDateTime());
        accountLockEntity.setLockStatus(accountLock.getLockStatus().getCode());
        accountLockEntityDao.insert(accountLockEntity);

        return accountLockRepository.findOneById(accountLockEntity.getAccountLockId());
    }
}
