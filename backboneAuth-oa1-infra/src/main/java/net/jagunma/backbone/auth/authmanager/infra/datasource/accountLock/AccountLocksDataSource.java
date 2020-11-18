package net.jagunma.backbone.auth.authmanager.infra.datasource.accountLock;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocksRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorsRepository;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntity;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntityCriteria;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * アカウントロック群検索
 */
@Component
public class AccountLocksDataSource implements AccountLocksRepository {

    private final AccountLockEntityDao accountLockEntityDao;
    private final OperatorsRepository operatorsRepository;

    // コンストラクタ
    AccountLocksDataSource(AccountLockEntityDao accountLockEntityDao,
        OperatorsRepository operatorsRepository) {

        this.accountLockEntityDao = accountLockEntityDao;
        this.operatorsRepository = operatorsRepository;
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
        Operators operators = operatorsRepository.selectBy(operatorCriteria, Orders.empty());

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
                entity.getLockStatus(),
                entity.getRecordVersion(),
                operators.getValues().stream().filter(o->
                    o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null)
            ));
        }

        return AccountLocks.createFrom(list);
    }
}
