package net.jagunma.backbone.auth.authmanager.infra.datasource.accountLock;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.types.AccountLockStatus;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntity;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntityCriteria;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntityDao;
import net.jagunma.common.ddd.model.orders.Order;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.springframework.stereotype.Component;

/**
 * アカウントロック検索
 */
@Component
public class AccountLockDataSource implements AccountLockRepository {

    private final AccountLockEntityDao accountLockEntityDao;
    private final OperatorRepository operatorRepository;

    // コンストラクタ
    AccountLockDataSource(AccountLockEntityDao accountLockEntityDao,
        OperatorRepository operatorRepository) {

        this.accountLockEntityDao = accountLockEntityDao;
        this.operatorRepository = operatorRepository;
    }

    /**
     * アカウントロックの検索を行います
     *
     * @param accountLockId アカウントロックID
     * @return アカウントロック
     */
    public AccountLock findOneById(Long accountLockId) {

        // アカウントロック検索
        AccountLockEntityCriteria entityCriteria = new AccountLockEntityCriteria();
        entityCriteria.getAccountLockIdCriteria().setEqualTo(accountLockId);
        AccountLockEntity entity = accountLockEntityDao.findOneBy(entityCriteria);

        // オペレーターの検索
        Operator operator = operatorRepository.findOneById(entity.getOperatorId());

        return AccountLock.createFrom(
            entity.getAccountLockId(),
            entity.getOperatorId(),
            entity.getOccurredDateTime(),
            AccountLockStatus.codeOf(entity.getLockStatus()),
            entity.getRecordVersion(),
            operator);
    }

    /**
     * アカウントロックの存在チェックを行います
     *
     * @param operatorId オペレーターID
     * @return アカウントロックの有無
     */
    public boolean existsByOperatorId(Long operatorId) {

        // オペレーター検索
        AccountLockEntityCriteria entityCriteria = new AccountLockEntityCriteria();
        entityCriteria.getOperatorIdCriteria().setEqualTo(operatorId);
        return accountLockEntityDao.existsBy(entityCriteria);
    }

    /**
     * 対象オペレーターで最新のアカウントロックの検索を行います
     *
     * @param operatorId オペレーターID
     * @return 対象オペレーターの中で最新のアカウントロック
     */
    public AccountLock latestOneByOperatorId(Long operatorId) {

        // アカウントロック群検索
        AccountLockEntityCriteria entityCriteria = new AccountLockEntityCriteria();
        entityCriteria.getOperatorIdCriteria().setEqualTo(operatorId);
        Orders orders = Orders.empty().addOrder("occurredDateTime", Order.DESC);
        List<AccountLockEntity> list = accountLockEntityDao.findBy(entityCriteria, orders);

        if (list.size() == 0) {
            throw new GunmaRuntimeException("EOA11002", "アカウントロック", "OperatorId="+operatorId.toString());
        }

        // オペレーターの検索
        Operator operator = operatorRepository.findOneById(operatorId);

        AccountLockEntity entity = list.get(0);
        return AccountLock.createFrom(
            entity.getAccountLockId(),
            entity.getOperatorId(),
            entity.getOccurredDateTime(),
            AccountLockStatus.codeOf(entity.getLockStatus()),
            entity.getRecordVersion(),
            operator);
    }

    /**
     * アカウントロック群の検索を行います
     *
     * @param accountLockCriteria アカウントロックの検索条件
     * @param orders              オーダー指定
     * @return アカウントロック群
     */
    public AccountLocks selectBy(AccountLockCriteria accountLockCriteria, Orders orders) {

        // オペレーター群の検索
        OperatorCriteria operatorCriteria = new OperatorCriteria();
        operatorCriteria.getOperatorIdCriteria().assignFrom(accountLockCriteria.getOperatorIdCriteria());
        Operators operators = operatorRepository.selectBy(operatorCriteria, Orders.empty());

        // アカウントロック群検索
        AccountLockEntityCriteria entityCriteria = new AccountLockEntityCriteria();
        entityCriteria.getAccountLockIdCriteria().assignFrom(accountLockCriteria.getAccountLockIdCriteria());
        entityCriteria.getOperatorIdCriteria().assignFrom(accountLockCriteria.getOperatorIdCriteria());

        List<AccountLock> list = newArrayList();
        for (AccountLockEntity entity : accountLockEntityDao.findBy(entityCriteria, orders)) {
            list.add(AccountLock.createFrom(
                entity.getAccountLockId(),
                entity.getOperatorId(),
                entity.getOccurredDateTime(),
                AccountLockStatus.codeOf(entity.getLockStatus()),
                entity.getRecordVersion(),
                operators.getValues().stream().filter(o->
                    o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null)
            ));
        }

        return AccountLocks.createFrom(list);
    }
}
