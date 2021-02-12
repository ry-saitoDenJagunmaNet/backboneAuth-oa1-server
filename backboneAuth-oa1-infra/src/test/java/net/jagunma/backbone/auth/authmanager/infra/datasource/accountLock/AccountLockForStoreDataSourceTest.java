package net.jagunma.backbone.auth.authmanager.infra.datasource.accountLock;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.types.AccountLockStatus;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntity;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntityCriteria;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AccountLockForStoreDataSourceTest {

    // 実行既定値
    private Long accountLockId = 1L;
    private Long operatorId = 18L;
    private LocalDateTime occurredDateTime = LocalDateTime.of(2020,10,27,8,30,12);
    private AccountLockStatus lockStatus = AccountLockStatus.アンロック;
    private Integer recordVersion = 1;
    private Operator operator = null;

    // 検証値
    private AccountLockEntity actualAccountLockEntity;
    private Long actualAccountLockId;

    // アカウントロック格納クラス作成（テスト対象クラス）
    private AccountLockForStoreDataSource createAccountLockForStoreDataSource() {
        // アカウントロックEntityのスタブ
        AccountLockEntityDao accountLockEntityDao = new AccountLockEntityDao(){
            @Override
            public int insert(AccountLockEntity entity) {
                actualAccountLockEntity = new AccountLockEntity();
                actualAccountLockEntity.setOperatorId(operatorId);
                actualAccountLockEntity.setOccurredDateTime(occurredDateTime);
                actualAccountLockEntity.setLockStatus(lockStatus.getCode());
                entity.setAccountLockId(accountLockId);
                entity.setRecordVersion(recordVersion);
                return 0;
            }
            @Override
            public List<AccountLockEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public AccountLockEntity findOneBy(AccountLockEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<AccountLockEntity> findBy(AccountLockEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public int countBy(AccountLockEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int update(AccountLockEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(AccountLockEntity entity) {
                return 0;
            }
            @Override
            public int delete(AccountLockEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(AccountLockEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<AccountLockEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<AccountLockEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<AccountLockEntity> entities) {
                return new int[0];
            }
        };
        // アカウントロックリポジトリのスタブ
        AccountLockRepository accountLockRepository = new AccountLockRepository() {
            @Override
            public AccountLock findOneById(Long argAccountLockId) {
                actualAccountLockId = accountLockId;
                return AccountLock.createFrom(
                    argAccountLockId,
                    operatorId,
                    occurredDateTime,
                    lockStatus,
                    recordVersion,
                    operator);
            }
            @Override
            public boolean existsByOperatorId(Long operatorId) {
                return false;
            }
            @Override
            public AccountLock latestOneByOperatorId(Long operatorId) {
                return null;
            }
            @Override
            public AccountLocks selectBy(AccountLockCriteria accountLockrCriteria, Orders orders) {
                return null;
            }
        };
        return new AccountLockForStoreDataSource(accountLockEntityDao, accountLockRepository);
    }


    /**
     * {@link AccountLockForStoreDataSource#insert( AccountLock)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *  ・Dao格納値、リポジトリ検索条件
     */
    @Test
    @Tag(TestSize.SMALL)
    void insert_test0() {

        // テスト対象クラス生成
        AccountLockForStoreDataSource accountLockForStoreDataSource = createAccountLockForStoreDataSource();

        // 実行値
        AccountLock accountLock = AccountLock.createFrom(
            null,
            operatorId,
            occurredDateTime,
            lockStatus,
            null,
            null);

        // 期待値
        AccountLock expectedAccountLock = AccountLock.createFrom(
            accountLockId,
            operatorId,
            occurredDateTime,
            lockStatus,
            recordVersion,
            operator);
        AccountLockEntity expectedAccountLockEntity = new AccountLockEntity();
        expectedAccountLockEntity.setOperatorId(operatorId);
        expectedAccountLockEntity.setOccurredDateTime(occurredDateTime);
        expectedAccountLockEntity.setLockStatus(lockStatus.getCode());
        Long expectedAccountLockId = accountLockId;

        // 実行
        AccountLock actualAccountLock = accountLockForStoreDataSource.insert(accountLock);

        // 結果検証
        assertThat(actualAccountLock).usingRecursiveComparison().isEqualTo(expectedAccountLock);
        assertThat(actualAccountLockEntity).usingRecursiveComparison().isEqualTo(expectedAccountLockEntity);
        assertThat(actualAccountLockId).isEqualTo(expectedAccountLockId);
    }
}