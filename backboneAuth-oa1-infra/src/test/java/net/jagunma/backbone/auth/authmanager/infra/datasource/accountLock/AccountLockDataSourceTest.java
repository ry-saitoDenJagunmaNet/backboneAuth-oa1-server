package net.jagunma.backbone.auth.authmanager.infra.datasource.accountLock;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.nullsLast;
import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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
import net.jagunma.common.ddd.model.orders.Order;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AccountLockDataSourceTest {

    // 実行既定値
    private List<AccountLockEntity> accountLockEntityList = createAccountLockEntityList();
    private final List<Operator> operatorList = createOperatorList();
    private boolean accountLockEntityDaoExistsBy = true;

    // 検証値
    private AccountLockEntityCriteria actualAccountLockEntityDao_criteria;
    private Orders actualAccountLockEntityDao_orders;
    private OperatorCriteria actualOperatorRepository_criteria;
    private Long actualOperatorRepository_operatorId;

    // アカウントロックEntityリストデータ作成
    private List<AccountLockEntity> createAccountLockEntityList() {
        List<AccountLockEntity> list = newArrayList();
        list.add(createAccountLockEntity(1L,18L,LocalDateTime.of(2020,10,27,8,30,12),AccountLockStatus.アンロック.getCode(),null,null,null,null,null,null,1));
        list.add(createAccountLockEntity(2L,18L,LocalDateTime.of(2020,10,27,8,30,13),AccountLockStatus.アンロック.getCode(),null,null,null,null,null,null,1));
        list.add(createAccountLockEntity(3L,19L,LocalDateTime.of(2020,10,27,8,31,3),AccountLockStatus.ロック.getCode(),null,null,null,null,null,null,1));
        list.add(createAccountLockEntity(4L,20L,LocalDateTime.of(2020,10,27,8,32,45),AccountLockStatus.アンロック.getCode(),null,null,null,null,null,null,1));
        return list;
    }
    // アカウントロックEntityデータ作成
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
    // オペレーターリストデータ作成
    private List<Operator> createOperatorList() {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L,"yu001009","ｙｕ００１００９","yu001009@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        list.add(Operator.createFrom(19L,"yu001010","ｙｕ００１０１０","yu001010@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),true,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        list.add(Operator.createFrom(20L,"yu001011","ｙｕ００１０１１","yu001011@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.利用不可,1,null));
        return list;
    }

    // アカウントロックDaoのスタブ
    private AccountLockEntityDao createAccountLockEntityDao() {
        return new AccountLockEntityDao() {
            @Override
            public List<AccountLockEntity> findBy(AccountLockEntityCriteria criteria, Orders orders) {
                actualAccountLockEntityDao_criteria = criteria;
                actualAccountLockEntityDao_orders = orders;
                if (criteria.getOperatorIdCriteria().getEqualTo() == null) {
                    return accountLockEntityList;
                } else {
                    return accountLockEntityList.stream().
                        filter(a -> a.getOperatorId().equals(criteria.getOperatorIdCriteria().getEqualTo()))
                        .sorted(orders.toComparator()).collect(Collectors.toList());
                }
            }
            @Override
            public boolean existsBy(AccountLockEntityCriteria criteria) {
                actualAccountLockEntityDao_criteria = criteria;
                return accountLockEntityDaoExistsBy;
            }
            @Override
            public AccountLockEntity findOneBy(AccountLockEntityCriteria criteria) {
                actualAccountLockEntityDao_criteria = criteria;
                return accountLockEntityList.stream().
                    filter(a -> a.getAccountLockId().equals(criteria.getAccountLockIdCriteria().getEqualTo())).findFirst().orElse(null);
            }
            @Override
            public List<AccountLockEntity> findAll(Orders orders) {
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
    // オペレータリポジトリのスタブ
    private OperatorRepository createOperatorRepository() {
        return new OperatorRepository() {
            @Override
            public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
                actualOperatorRepository_criteria = operatorCriteria;
                return Operators.createFrom(operatorList);
            }
            @Override
            public Operator findOneById(Long operatorId) {
                actualOperatorRepository_operatorId = operatorId;
                Operator operator = operatorList.stream().filter(o -> o.getOperatorId().equals(operatorId)).findFirst().orElse(null);
                return Operator.createFrom(
                    operator.getOperatorId(),
                    operator.getOperatorCode(),
                    operator.getOperatorName(),
                    operator.getMailAddress(),
                    operator.getValidThruStartDate(),
                    operator.getValidThruEndDate(),
                    operator.getIsDeviceAuth(),
                    operator.getJaId(),
                    operator.getJaCode(),
                    operator.getBranchId(),
                    operator.getBranchCode(),
                    operator.getAvailableStatus(),
                    operator.getRecordVersion(),
                    operator.getBranchAtMoment());
            }
            @Override
            public Operator findOneByCode(String operatorCode) {
                return null;
            }
            @Override
            public boolean existsById(Long operatorId) {
                return false;
            }
            @Override
            public boolean existsByCode(String operatorCode) {
                return false;
            }
            @Override
            public boolean existsBy(OperatorCriteria operatorCriteria) {
                return false;
            }
        };
    }

    /**
     * {@link AccountLockDataSource#findOneById(Long)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *  ・検索結果
     *  ・Dao検索条件
     */
    @Test
    @Tag(TestSize.SMALL)
    void findOneById_test0() {

        // 実行値
        Long accountLockId = 1L;

        // テスト対象クラス生成
        AccountLockDataSource accountLockDataSource = new AccountLockDataSource(
            createAccountLockEntityDao(),
            createOperatorRepository());

        // 期待値
        AccountLockEntity entity = accountLockEntityList.stream().filter(a -> a.getAccountLockId().equals(accountLockId)).findFirst().orElse(null);
        AccountLock expectedAccountLock = AccountLock.createFrom(
            entity.getAccountLockId(),
            entity.getOperatorId(),
            entity.getOccurredDateTime(),
            AccountLockStatus.codeOf(entity.getLockStatus()),
            entity.getRecordVersion(),
            operatorList.stream().filter(o->o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null));
        AccountLockEntityCriteria expectedAccountLockEntityDao_criteria = new AccountLockEntityCriteria();
        expectedAccountLockEntityDao_criteria.getAccountLockIdCriteria().setEqualTo(accountLockId);

        // 実行
        AccountLock actualAccountLock = accountLockDataSource.findOneById(accountLockId);

        // 結果検証
        assertThat(actualAccountLock).usingRecursiveComparison().isEqualTo(expectedAccountLock);
        assertThat(actualAccountLockEntityDao_criteria.toString()).isEqualTo(expectedAccountLockEntityDao_criteria.toString());
    }

    /**
     * {@link AccountLockDataSource#existsByOperatorId(Long)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *  ・検索結果
     *  ・Dao検索条件
     */
    @Test
    @Tag(TestSize.SMALL)
    void existsByOperatorId_test0() {

        // 実行値
        Long operatorId = 18L;

        // テスト対象クラス生成
        AccountLockDataSource accountLockDataSource = new AccountLockDataSource(
            createAccountLockEntityDao(),
            createOperatorRepository());

        // 期待値
        boolean expected = accountLockEntityDaoExistsBy;
        AccountLockEntityCriteria expectedAccountLockEntityDao_criteria = new AccountLockEntityCriteria();
        expectedAccountLockEntityDao_criteria.getOperatorIdCriteria().setEqualTo(operatorId);

        // 実行
        boolean actualResult = accountLockDataSource.existsByOperatorId(operatorId);

        // 結果検証
        assertThat(actualResult).isEqualTo(expected);
        assertThat(actualAccountLockEntityDao_criteria.toString()).isEqualTo(expectedAccountLockEntityDao_criteria.toString());
    }

    /**
     * {@link AccountLockDataSource#latestOneByOperatorId(Long)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *  ・検索結果
     *  ・Dao検索条件、リポジトリ検索条件
     */
    @Test
    @Tag(TestSize.SMALL)
    void latestOneByOperatorId_test0() {

        // 実行値
        Long operatorId = 18L;

        // テスト対象クラス生成
        AccountLockDataSource accountLockDataSource = new AccountLockDataSource(
            createAccountLockEntityDao(),
            createOperatorRepository());

        // 期待値
        Comparator<AccountLockEntity> comparator = Comparator.comparing(AccountLockEntity::getOccurredDateTime, nullsLast(reverseOrder()));
        AccountLockEntity entity = accountLockEntityList.stream().filter(a -> a.getOperatorId().equals(operatorId)).sorted(comparator).findFirst().orElse(null);
        AccountLock expectedAccountLock = AccountLock.createFrom(
            entity.getAccountLockId(),
            entity.getOperatorId(),
            entity.getOccurredDateTime(),
            AccountLockStatus.codeOf(entity.getLockStatus()),
            entity.getRecordVersion(),
            operatorList.stream().filter(o->o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null));
        AccountLockEntityCriteria expectedAccountLockEntityDao_criteria = new AccountLockEntityCriteria();
        expectedAccountLockEntityDao_criteria.getOperatorIdCriteria().setEqualTo(operatorId);
        Orders expectedOrders = Orders.empty().addOrder("occurredDateTime", Order.DESC);
        Long expectedOperatorId = operatorId;

        // 実行
        AccountLock actualAccountLock = accountLockDataSource.latestOneByOperatorId(operatorId);

        // 結果検証
        assertThat(actualAccountLock).usingRecursiveComparison().isEqualTo(expectedAccountLock);
        assertThat(actualAccountLockEntityDao_criteria.toString()).isEqualTo(expectedAccountLockEntityDao_criteria.toString());
        assertThat(actualAccountLockEntityDao_orders).usingRecursiveComparison().isEqualTo(expectedOrders);
        assertThat(actualOperatorRepository_operatorId).isEqualTo(expectedOperatorId);
    }

    /**
     * {@link AccountLockDataSource#latestOneByOperatorId(Long)}テスト
     *  ●パターン
     *    検索結果該当無し
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void latestOneByOperatorId_test1() {

        // 実行値
        Long operatorId = 18L;
        accountLockEntityList = newArrayList();

        // テスト対象クラス生成
        AccountLockDataSource accountLockDataSource = new AccountLockDataSource(
            createAccountLockEntityDao(),
            createOperatorRepository());

        // 期待値
        String expectedMessageCode = "EOA11002";
        String[] expectedArgs = {"アカウントロック", "OperatorId="+operatorId.toString()};

        assertThatThrownBy(() ->
            // 実行
            accountLockDataSource.latestOneByOperatorId(operatorId))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo(expectedMessageCode);
                assertThat(e.getArgs()).containsSequence(expectedArgs);
            });
    }

    /**
     * {@link AccountLockDataSource#selectBy(AccountLockCriteria,Orders)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *  ・検索結果
     *  ・Dao検索条件、リポジトリ検索条件
     */
    @Test
    @Tag(TestSize.SMALL)
    void selectBy_test0() {

        // 実行値
        AccountLockCriteria criteria = new AccountLockCriteria();
        Orders orders = Orders.empty();

        // テスト対象クラス生成
        AccountLockDataSource accountLockDataSource = new AccountLockDataSource(
            createAccountLockEntityDao(),
            createOperatorRepository());

        // 期待値
        List<AccountLock> expectedAccountLockList = newArrayList();
        for(AccountLockEntity entity : accountLockEntityList) {
            expectedAccountLockList.add(AccountLock.createFrom(
                entity.getAccountLockId(),
                entity.getOperatorId(),
                entity.getOccurredDateTime(),
                AccountLockStatus.codeOf(entity.getLockStatus()),
                entity.getRecordVersion(),
                operatorList.stream().filter(o->o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null)));
        }
        AccountLockEntityCriteria expectedAccountLockEntityDao_criteria = new AccountLockEntityCriteria();
        expectedAccountLockEntityDao_criteria.getAccountLockIdCriteria().assignFrom(criteria.getAccountLockIdCriteria());
        expectedAccountLockEntityDao_criteria.getOperatorIdCriteria().assignFrom(criteria.getOperatorIdCriteria());
        Orders expectedOrders = orders;
        OperatorCriteria expectedOperatorRepository_criteria = new OperatorCriteria();
        expectedOperatorRepository_criteria.getOperatorIdCriteria().assignFrom(criteria.getOperatorIdCriteria());

        // 実行
        AccountLocks actualAccountLocks = accountLockDataSource.selectBy(criteria, orders);

        // 結果検証
        assertThat(actualAccountLocks.getValues().size()).isEqualTo(expectedAccountLockList.size());
        for(int i = 0; i < actualAccountLocks.getValues().size(); i++) {
            assertThat(actualAccountLocks.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedAccountLockList.get(i));
        }
        assertThat(actualAccountLockEntityDao_criteria.toString()).isEqualTo(expectedAccountLockEntityDao_criteria.toString());
        assertThat(actualAccountLockEntityDao_orders).usingRecursiveComparison().isEqualTo(expectedOrders);
        assertThat(actualOperatorRepository_criteria.toString()).isEqualTo(expectedOperatorRepository_criteria.toString());
    }
}