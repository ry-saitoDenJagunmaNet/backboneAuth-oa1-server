package net.jagunma.backbone.auth.authmanager.infra.datasource.accountLock;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.types.AccountLockStatus;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntity;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntityCriteria;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AccountLockDataSourceTest {

    // 実行既定値
    // アカウントロックDaoの作成
    private AccountLockEntityDao createAccountLockEntityDao() {
        return new AccountLockEntityDao() {
            @Override
            public List<AccountLockEntity> findBy(AccountLockEntityCriteria criteria, Orders orders) {
                return createAccountLockEntityList();
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
            public int countBy(AccountLockEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(AccountLockEntity entity) {
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
    }
    // アカウントロックリストデータ作成
    private List<AccountLockEntity> createAccountLockEntityList() {
        List<AccountLockEntity> list = newArrayList();
        list.add(createAccountLockEntity(1L,18L,LocalDateTime.of(2020,10,27,8,30,12),AccountLockStatus.アンロック.getCode(),null,null,null,null,null,null,1));
        list.add(createAccountLockEntity(1L,19L,LocalDateTime.of(2020,10,27,8,31,3),AccountLockStatus.ロック.getCode(),null,null,null,null,null,null,1));
        list.add(createAccountLockEntity(1L,20L,LocalDateTime.of(2020,10,27,8,32,45),AccountLockStatus.アンロック.getCode(),null,null,null,null,null,null,1));
        return list;
    }
    // アカウントロックデータ作成
    private AccountLockEntity createAccountLockEntity(
        Long accountLockId,
        Long operatorId,
        LocalDateTime occurredDateTime,
        Short lockStatus,
        Long createdBy,
        LocalDateTime createdAt,
        String createdIpAddress,
        Long updatedBy,
        LocalDateTime updatedAt,
        String updatedIpAddress,
        Integer recordVersion) {

        AccountLockEntity entity = new AccountLockEntity();
        entity.setAccountLockId(accountLockId);
        entity.setOperatorId(operatorId);
        entity.setOccurredDateTime(occurredDateTime);
        entity.setLockStatus(lockStatus);
        entity.setCreatedBy(createdBy);
        entity.setCreatedAt(createdAt);
        entity.setCreatedIpAddress(createdIpAddress);
        entity.setUpdatedBy(updatedBy);
        entity.setUpdatedAt(updatedAt);
        entity.setUpdatedIpAddress(updatedIpAddress);
        entity.setRecordVersion(recordVersion);
        return entity;
    }
    // オペレータRepositoryの作成
    private OperatorRepository createOperatorRepository() {
        return new OperatorRepository() {
            @Override
            public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
                return createOperators();
            }
            @Override
            public Operator findOneBy(OperatorCriteria operatorCriteria) {
                return null;
            }
            @Override
            public boolean existsBy(OperatorCriteria operatorCriteria) {
                return false;
            }
        };
    }
    // オペレータ群データ作成
    private Operators createOperators() {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L,"yu001009","ｙｕ００１００９","yu001009@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        list.add(Operator.createFrom(19L,"yu001010","ｙｕ００１０１０","yu001010@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),true,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        list.add(Operator.createFrom(20L,"yu001011","ｙｕ００１０１１","yu001011@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.利用不可,1,null));
        return Operators.createFrom(list);
    }


    /**
     * {@link AccountLockDataSource#selectBy(AccountLockCriteria,Orders)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void selectBy_test0() {

        // 実行値
        AccountLockCriteria criteria = new AccountLockCriteria();
        Orders orders = Orders.empty();

        // テスト対象クラス生成
        AccountLockDataSource accountLockDataSource = new AccountLockDataSource(createAccountLockEntityDao(),
            createOperatorRepository());

        // 期待値
        Operators operators = createOperators();
        List<AccountLock> expectedAccountLockList = newArrayList();
        for(AccountLockEntity entity : createAccountLockEntityList()) {
            expectedAccountLockList.add(AccountLock.createFrom(
                entity.getAccountLockId(),
                entity.getOperatorId(),
                entity.getOccurredDateTime(),
                entity.getLockStatus(),
                entity.getRecordVersion(),
                operators.getValues().stream().filter(o->o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null)));
        }

        // 実行
        AccountLocks actualAccountLocks = accountLockDataSource.selectBy(criteria, orders);

        // 結果検証
        for(int i = 0; i < actualAccountLocks.getValues().size(); i++) {
            assertThat(actualAccountLocks.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedAccountLockList.get(i));
        }
    }
}