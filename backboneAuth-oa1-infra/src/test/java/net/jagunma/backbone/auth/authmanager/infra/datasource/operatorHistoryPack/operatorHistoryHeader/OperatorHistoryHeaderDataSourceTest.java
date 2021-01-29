package net.jagunma.backbone.auth.authmanager.infra.datasource.operatorHistoryPack.operatorHistoryHeader;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaderCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaders;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntityDao;
import net.jagunma.common.ddd.model.orders.Order;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OperatorHistoryHeaderDataSourceTest {

    // 実行既定値
    private List<OperatorHistoryHeaderEntity> operatorHistoryHeaderList = createOperatorHistoryHeaderEntityList();
    private List<Operator> operatorList = createOperatorList();

    // 検証値
    private OperatorHistoryHeaderEntityCriteria actualOperatorHistoryHeaderEntityDao_criteria;
    private Orders actualOperatorHistoryHeaderEntityDao_orders;
    private OperatorCriteria actualOperatorRepository_criteria;
    private Long actualOperatorRepository_operatorId;


    // オペレーター履歴ヘッダーEntityリストデータ作成
    private List<OperatorHistoryHeaderEntity> createOperatorHistoryHeaderEntityList() {
        List<OperatorHistoryHeaderEntity> list = newArrayList();
        list.add(createOperatorHistoryHeaderEntity(100001L,200001L,LocalDateTime.of(2021,1,6,8,30,12),"変更事由１行目",1));
        list.add(createOperatorHistoryHeaderEntity(100002L,200002L,LocalDateTime.of(2021,1,6,8,30,12),"変更事由２行目",1));
        list.add(createOperatorHistoryHeaderEntity(100003L,200003L,LocalDateTime.of(2021,1,6,8,30,12),"変更事由３行目",1));
        list.add(createOperatorHistoryHeaderEntity(100004L,200002L,LocalDateTime.of(2021,1,6,8,31,1),"変更事由４行目",1));
        list.add(createOperatorHistoryHeaderEntity(100005L,200002L,LocalDateTime.of(2021,1,6,8,30,13),"変更事由５行目",1));
        return list;
    }
    // オペレーター履歴ヘッダーEntityデータ作成
    private OperatorHistoryHeaderEntity createOperatorHistoryHeaderEntity(
        Long operatorHistoryId,
        Long operatorId,
        LocalDateTime changeDateTime,
        String changeCause,
        Integer recordVersion) {

        OperatorHistoryHeaderEntity entity = new OperatorHistoryHeaderEntity();
        entity.setOperatorHistoryId(operatorHistoryId);
        entity.setOperatorId(operatorId);
        entity.setChangeDateTime(changeDateTime);
        entity.setRecordVersion(recordVersion);
        return entity;
    }
    // オペレ－タリストデータ作成
    private List<Operator> createOperatorList() {
        List<Operator> list = newArrayList();
        list.add(createOperator(200001L,"yu001009","ｙｕ００１００９","yu001009@aaaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.利用可能,1));
        list.add(createOperator(200002L,"yu001010","ｙｕ００１０１０","yu001010@aaaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.利用可能,1));
        list.add(createOperator(100003L,"yu001011","ｙｕ００１０１１","yu001011@aaaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.利用可能,1));
        return list;
    }
    // オペレ－タデータ作成
    private Operator createOperator(
        Long operatorId,
        String operatorCode,
        String operatorName,
        String mailAddress,
        LocalDate validThruStartDate,
        LocalDate validThruEndDate,
        Boolean isDeviceAuth,
        Long jaId,
        String jaCode,
        Long branchId,
        String branchCode,
        AvailableStatus availableStatus,
        Integer recordVersion) {

        return Operator.createFrom(operatorId,
            operatorCode,
            operatorName,
            mailAddress,
            validThruStartDate,
            validThruEndDate,
            isDeviceAuth,
            jaId,
            jaCode,
            branchId,
            branchCode,
            availableStatus,
            recordVersion,
            null);
    }
    // オペレーター履歴ヘッダーリストデータ作成
    private List<OperatorHistoryHeader> createOperatorHistoryHeaderList(Long opratorId, Orders orders) {
        List<OperatorHistoryHeader> list = newArrayList();

        for (OperatorHistoryHeaderEntity entity : operatorHistoryHeaderList.stream()
            .filter(o->o.getOperatorId().equals(opratorId))
            .sorted(orders.toComparator())
            .collect(Collectors.toList())) {

            list.add(OperatorHistoryHeader.createFrom(
                entity.getOperatorHistoryId(),
                entity.getOperatorId(),
                entity.getChangeDateTime(),
                entity.getChangeCause(),
                entity.getRecordVersion(),
                operatorList.stream()
                    .filter(o->o.getOperatorId().equals(opratorId)).findFirst().orElse(null)));
        }
        return list;
    }

    // オペレーター履歴ヘッダーDaoのスタブ
    private OperatorHistoryHeaderEntityDao createOperatorHistoryHeaderEntityDao() {
        return new OperatorHistoryHeaderEntityDao() {
            @Override
            public List<OperatorHistoryHeaderEntity> findBy(OperatorHistoryHeaderEntityCriteria criteria, Orders orders) {
                actualOperatorHistoryHeaderEntityDao_criteria = criteria;
                actualOperatorHistoryHeaderEntityDao_orders = orders;
                return operatorHistoryHeaderList.stream()
                    .filter(o->o.getOperatorId().equals(criteria.getOperatorIdCriteria().getEqualTo()))
                    .sorted(orders.toComparator())
                    .collect(Collectors.toList());
            }
            @Override
            public List<OperatorHistoryHeaderEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public OperatorHistoryHeaderEntity findOneBy(OperatorHistoryHeaderEntityCriteria criteria) {
                return null;
            }
            @Override
            public int countBy(OperatorHistoryHeaderEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(OperatorHistoryHeaderEntity entity) {
                return 0;
            }
            @Override
            public int update(OperatorHistoryHeaderEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(OperatorHistoryHeaderEntity entity) {
                return 0;
            }
            @Override
            public int delete(OperatorHistoryHeaderEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(OperatorHistoryHeaderEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<OperatorHistoryHeaderEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<OperatorHistoryHeaderEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<OperatorHistoryHeaderEntity> entities) {
                return new int[0];
            }
        };
    }
    // オペレーターRepositoryのスタブ
    private OperatorRepository createOperatorRepository() {
        return new OperatorRepository() {
            @Override
            public Operator findOneById(Long operatorId) {
                actualOperatorRepository_operatorId = operatorId;
                return operatorList.stream().filter(o->o.getOperatorId().equals(operatorId)).findFirst().orElse(null);
            }
            @Override
            public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
                actualOperatorRepository_criteria = operatorCriteria;
                return Operators.createFrom(operatorList.stream()
                    .filter(o->o.getOperatorId().equals(operatorCriteria.getOperatorIdCriteria().getEqualTo()))
                    .sorted(orders.toComparator())
                    .collect(Collectors.toList()));
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
     * {@link OperatorHistoryHeaderDataSource#latestOneByOperatorId(Long)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void latestOneByOperatorId_test0() {

        // 実行値
        Long operatorId = 200002L;

        // テスト対象クラス生成
        OperatorHistoryHeaderDataSource operatorHistoryHeaderDataSource = new OperatorHistoryHeaderDataSource(
            createOperatorHistoryHeaderEntityDao(),
            createOperatorRepository());

        // 期待値
        Operator operator = operatorList.stream().filter(o->o.getOperatorId().equals(operatorId)).findFirst().orElse(null);
        OperatorHistoryHeader expectedOperatorHistoryHeader = createOperatorHistoryHeaderList(operatorId, Orders.empty().addOrder("changeDateTime", Order.DESC))
            .stream().findFirst().orElse(null);
        OperatorHistoryHeaderEntityCriteria expectedOperatorHistoryHeaderEntityDao_criteria = new OperatorHistoryHeaderEntityCriteria();
        expectedOperatorHistoryHeaderEntityDao_criteria.getOperatorIdCriteria().setEqualTo(operatorId);
        Orders expectedOrders = Orders.empty().addOrder("changeDateTime", Order.DESC);
        Long expectedOperatorId = operatorId;

        // 実行
        OperatorHistoryHeader actualOperatorHistoryHeader = operatorHistoryHeaderDataSource.latestOneByOperatorId(operatorId);

        // 結果検証
        assertThat(actualOperatorHistoryHeader).usingRecursiveComparison().isEqualTo(expectedOperatorHistoryHeader);
        assertThat(actualOperatorHistoryHeaderEntityDao_criteria.toString()).isEqualTo(expectedOperatorHistoryHeaderEntityDao_criteria.toString());
        assertThat(actualOperatorHistoryHeaderEntityDao_orders).usingRecursiveComparison().isEqualTo(expectedOrders);
        assertThat(actualOperatorRepository_operatorId).isEqualTo(expectedOperatorId);
    }

    /**
     * {@link OperatorHistoryHeaderDataSource#latestOneByOperatorId(Long)}テスト
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
        Long operatorId = 200002L;
        operatorHistoryHeaderList = newArrayList();

        // テスト対象クラス生成
        OperatorHistoryHeaderDataSource operatorHistoryHeaderDataSource = new OperatorHistoryHeaderDataSource(
            createOperatorHistoryHeaderEntityDao(),
            createOperatorRepository());

        // 期待値
        String expectedMessageCode = "EOA11002";
        String[] expectedArgs = {"オペレーター履歴ヘッダー", "OperatorId="+operatorId.toString()};

        assertThatThrownBy(() ->
            // 実行
            operatorHistoryHeaderDataSource.latestOneByOperatorId(operatorId))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo(expectedMessageCode);
                assertThat(e.getArgs()).containsSequence(expectedArgs);
            });
    }

    /**
     * {@link OperatorHistoryHeaderDataSource#selectBy(OperatorHistoryHeaderCriteria, Orders)}テスト
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
        Long operatorId = 200002L;
        OperatorHistoryHeaderCriteria criteria = new OperatorHistoryHeaderCriteria();
        criteria.getOperatorIdCriteria().setEqualTo(operatorId);
        Orders orders = Orders.empty().addOrder("operatorId").addOrder("changeDateTime", Order.DESC);

        // テスト対象クラス生成
        OperatorHistoryHeaderDataSource operatorHistoryHeaderDataSource = new OperatorHistoryHeaderDataSource(
            createOperatorHistoryHeaderEntityDao(),
            createOperatorRepository());
        OperatorHistoryHeaderEntityCriteria expectedOperatorHistoryHeaderEntityDao_criteria = new OperatorHistoryHeaderEntityCriteria();
        expectedOperatorHistoryHeaderEntityDao_criteria.getOperatorHistoryIdCriteria().assignFrom(criteria.getOperatorHistoryIdCriteria());
        expectedOperatorHistoryHeaderEntityDao_criteria.getOperatorIdCriteria().assignFrom(criteria.getOperatorIdCriteria());
        Orders expectedOrders = orders;
        OperatorCriteria expectedOperatorRepository_criteria = new OperatorCriteria();
        expectedOperatorRepository_criteria.getOperatorIdCriteria().assignFrom(criteria.getOperatorIdCriteria());

        // 期待値
        List<OperatorHistoryHeader> expectedOperatorHistoryHeaderList = createOperatorHistoryHeaderList(operatorId, orders);

        // 実行
        OperatorHistoryHeaders actualOperatorHistoryHeaders = operatorHistoryHeaderDataSource.selectBy(criteria, orders);

        // 結果検証
        assertThat(actualOperatorHistoryHeaders.getValues().size()).isEqualTo(expectedOperatorHistoryHeaderList.size());
        for(int i = 0; i < actualOperatorHistoryHeaders.getValues().size(); i++) {
            assertThat(actualOperatorHistoryHeaders.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedOperatorHistoryHeaderList.get(i));
        }
        assertThat(actualOperatorHistoryHeaderEntityDao_criteria.toString()).isEqualTo(expectedOperatorHistoryHeaderEntityDao_criteria.toString());
        assertThat(actualOperatorHistoryHeaderEntityDao_orders).usingRecursiveComparison().isEqualTo(expectedOrders);
        assertThat(actualOperatorRepository_criteria.toString()).isEqualTo(expectedOperatorRepository_criteria.toString());
    }
}