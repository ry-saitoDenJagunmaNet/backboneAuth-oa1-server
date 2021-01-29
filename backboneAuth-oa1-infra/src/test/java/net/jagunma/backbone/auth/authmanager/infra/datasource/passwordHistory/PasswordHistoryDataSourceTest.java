package net.jagunma.backbone.auth.authmanager.infra.datasource.passwordHistory;

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
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistories;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntity;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityCriteria;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityDao;
import net.jagunma.common.ddd.model.orders.Order;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PasswordHistoryDataSourceTest {

    // 実行既定値
    private List<PasswordHistoryEntity> passwordHistoryEntityList = createPasswordHistoryEntityList();
    private final List<Operator> operatorList = createOperatorList();

    // 検証値
    private PasswordHistoryEntityCriteria actualPasswordHistoryEntityDao_criteria;
    private Orders actualPasswordHistoryEntityDao_orders;
    private OperatorCriteria actualOperatorRepository_criteria;
    private Long actualOperatorRepository_operatorId;

    // パスワード履歴Entityリストデータ作成
    private List<PasswordHistoryEntity> createPasswordHistoryEntityList() {
        List<PasswordHistoryEntity> list = newArrayList();
        list.add(createPasswordHistoryEntity(1L,18L,LocalDateTime.of(2020,10,28,8,30,12),"abcdefgh",PasswordChangeType.初期.getCode(),null,null,null,null,null,null,1));
        list.add(createPasswordHistoryEntity(2L,19L,LocalDateTime.of(2020,10,28,9,31,3),"12345678",PasswordChangeType.初期.getCode(),null,null,null,null,null,null,1));
        list.add(createPasswordHistoryEntity(3L,20L,LocalDateTime.of(2020,10,28,10,1,45),"abcd1234",PasswordChangeType.初期.getCode(),null,null,null,null,null,null,1));
        list.add(createPasswordHistoryEntity(4L,18L,LocalDateTime.of(2020,10,28,8,30,13),"abcdefghnew",PasswordChangeType.初期.getCode(),null,null,null,null,null,null,1));
        return list;
    }
    // パスワード履歴Entityデータ作成
    private PasswordHistoryEntity createPasswordHistoryEntity(
        Long passwordHistoryId,
        Long operatorId,
        LocalDateTime changeDateTime,
        String password,
        Short changeType,
        Long createdBy,
        LocalDateTime createdAt,
        String createdIpAddress,
        Long updatedBy,
        LocalDateTime updatedAt,
        String updatedIpAddress,
        Integer recordVersion) {

        PasswordHistoryEntity entity = new PasswordHistoryEntity();
        entity.setPasswordHistoryId(passwordHistoryId);
        entity.setOperatorId(operatorId);
        entity.setChangeDateTime(changeDateTime);
        entity.setPassword(password);
        entity.setChangeType(changeType);
        entity.setCreatedBy(createdBy);
        entity.setCreatedAt(createdAt);
        entity.setCreatedIpAddress(createdIpAddress);
        entity.setUpdatedBy(updatedBy);
        entity.setUpdatedAt(updatedAt);
        entity.setUpdatedIpAddress(updatedIpAddress);
        entity.setRecordVersion(recordVersion);
        return entity;
    }
    // オペレータリストデータ作成
    private List<Operator> createOperatorList() {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L,"yu001009","ｙｕ００１００９","yu001009@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        list.add(Operator.createFrom(19L,"yu001010","ｙｕ００１０１０","yu001010@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),true,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        list.add(Operator.createFrom(20L,"yu001011","ｙｕ００１０１１","yu001011@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.利用不可,1,null));
        return list;
    }

    // パスワード履歴Daoのスタブ
    private PasswordHistoryEntityDao createPasswordHistoryEntityDao() {
        return new PasswordHistoryEntityDao() {
            @Override
            public List<PasswordHistoryEntity> findBy(PasswordHistoryEntityCriteria criteria, Orders orders) {
                actualPasswordHistoryEntityDao_criteria = criteria;
                actualPasswordHistoryEntityDao_orders = orders;
                if (criteria.getOperatorIdCriteria().getEqualTo() == null) {
                    return passwordHistoryEntityList;
                } else {
                    return passwordHistoryEntityList.stream().
                        filter(a -> a.getOperatorId().equals(criteria.getOperatorIdCriteria().getEqualTo()))
                        .sorted(orders.toComparator()).collect(Collectors.toList());
                }
            }
            @Override
            public List<PasswordHistoryEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public PasswordHistoryEntity findOneBy(PasswordHistoryEntityCriteria criteria) {
                return null;
            }
            @Override
            public int countBy(PasswordHistoryEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(PasswordHistoryEntity entity) {
                return 0;
            }
            @Override
            public int update(PasswordHistoryEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(PasswordHistoryEntity entity) {
                return 0;
            }
            @Override
            public int delete(PasswordHistoryEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(PasswordHistoryEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<PasswordHistoryEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<PasswordHistoryEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<PasswordHistoryEntity> entities) {
                return new int[0];
            }
        };
    }
    // オペレータRepositoryのスタブ
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
     * {@link PasswordHistoryDataSource#latestOneByOperatorId(Long)}テスト
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
        PasswordHistoryDataSource passwordHistoryDataSource = new PasswordHistoryDataSource(
            createPasswordHistoryEntityDao(),
            createOperatorRepository());

        // 期待値
        Comparator<PasswordHistoryEntity> comparator = Comparator.comparing(PasswordHistoryEntity::getChangeDateTime, nullsLast(reverseOrder()));
        PasswordHistoryEntity entity = passwordHistoryEntityList.stream().filter(a -> a.getOperatorId().equals(operatorId)).sorted(comparator).findFirst().orElse(null);
        PasswordHistory expectedPasswordHistory =  PasswordHistory.createFrom(
            entity.getPasswordHistoryId(),
            entity.getOperatorId(),
            entity.getChangeDateTime(),
            entity.getPassword(),
            PasswordChangeType.codeOf(entity.getChangeType()),
            entity.getRecordVersion(),
            operatorList.stream().filter(o->o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null));
        PasswordHistoryEntityCriteria expectedPasswordHistoryEntityDao_criteria = new PasswordHistoryEntityCriteria();
        expectedPasswordHistoryEntityDao_criteria.getOperatorIdCriteria().setEqualTo(operatorId);
        Orders expectedOrders = Orders.empty().addOrder("changeDateTime", Order.DESC);
        Long expectedOperatorId = operatorId;

        // 実行
        PasswordHistory actualPasswordHistory = passwordHistoryDataSource.latestOneByOperatorId(operatorId);

        // 結果検証
        assertThat(actualPasswordHistory).usingRecursiveComparison().isEqualTo(expectedPasswordHistory);
        assertThat(actualPasswordHistoryEntityDao_criteria.toString()).isEqualTo(expectedPasswordHistoryEntityDao_criteria.toString());
        assertThat(actualPasswordHistoryEntityDao_orders).usingRecursiveComparison().isEqualTo(expectedOrders);
        assertThat(actualOperatorRepository_operatorId).isEqualTo(expectedOperatorId);
    }

    /**
     * {@link PasswordHistoryDataSource#latestOneByOperatorId(Long)}テスト
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
        passwordHistoryEntityList = newArrayList();

        // テスト対象クラス生成
        PasswordHistoryDataSource passwordHistoryDataSource = new PasswordHistoryDataSource(
            createPasswordHistoryEntityDao(),
            createOperatorRepository());

        // 期待値
        String expectedMessageCode = "EOA11002";
        String[] expectedArgs = {"パスワード履歴", "OperatorId="+operatorId.toString()};

        assertThatThrownBy(() ->
            // 実行
            passwordHistoryDataSource.latestOneByOperatorId(operatorId))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo(expectedMessageCode);
                assertThat(e.getArgs()).containsSequence(expectedArgs);
            });
    }

    /**
     * {@link PasswordHistoryDataSource#selectBy(PasswordHistoryCriteria,Orders)}テスト
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
        PasswordHistoryCriteria criteria = new PasswordHistoryCriteria();
        Orders orders = Orders.empty();

        // テスト対象クラス生成
        PasswordHistoryDataSource passwordHistoryDataSource = new PasswordHistoryDataSource(
            createPasswordHistoryEntityDao(),
            createOperatorRepository());

        // 期待値
        List<PasswordHistory> expectedPasswordHistoryList = newArrayList();
        for(PasswordHistoryEntity entity : passwordHistoryEntityList) {
            expectedPasswordHistoryList.add(PasswordHistory.createFrom(
                entity.getPasswordHistoryId(),
                entity.getOperatorId(),
                entity.getChangeDateTime(),
                entity.getPassword(),
                PasswordChangeType.codeOf(entity.getChangeType()),
                entity.getRecordVersion(),
                operatorList.stream().filter(o->o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null)));
        }
        PasswordHistoryEntityCriteria expectedPasswordHistoryEntityDao_Criteria = new PasswordHistoryEntityCriteria();
        expectedPasswordHistoryEntityDao_Criteria.getPasswordHistoryIdCriteria().assignFrom(criteria.getPasswordHistoryIdCriteria());
        expectedPasswordHistoryEntityDao_Criteria.getOperatorIdCriteria().assignFrom(criteria.getOperatorIdCriteria());
        expectedPasswordHistoryEntityDao_Criteria.getChangeTypeCriteria().assignFrom(criteria.getChangeTypeCriteria());
        Orders expectedOrders = orders;
        OperatorCriteria expectedOperatorRepository_criteria = new OperatorCriteria();
        expectedOperatorRepository_criteria.getOperatorIdCriteria().assignFrom(criteria.getOperatorIdCriteria());

        // 実行
        PasswordHistories actualPasswordHistories = passwordHistoryDataSource.selectBy(criteria, orders);

        // 結果検証
        assertThat(actualPasswordHistories.getValues().size()).isEqualTo(expectedPasswordHistoryList.size());
        for(int i = 0; i < actualPasswordHistories.getValues().size(); i++) {
            assertThat(actualPasswordHistories.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedPasswordHistoryList.get(i));
        }
        assertThat(actualPasswordHistoryEntityDao_criteria.toString()).isEqualTo(expectedPasswordHistoryEntityDao_Criteria.toString());
        assertThat(actualPasswordHistoryEntityDao_orders).usingRecursiveComparison().isEqualTo(expectedOrders);
        assertThat(actualOperatorRepository_criteria.toString()).isEqualTo(expectedOperatorRepository_criteria.toString());
    }
}