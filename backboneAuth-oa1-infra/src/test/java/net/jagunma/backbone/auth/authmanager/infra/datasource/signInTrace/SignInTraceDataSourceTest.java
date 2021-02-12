package net.jagunma.backbone.auth.authmanager.infra.datasource.signInTrace;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraces;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntity;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntityCriteria;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntityDao;
import net.jagunma.common.ddd.model.orders.Order;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SignInTraceDataSourceTest {

    // 実行既定値
    private final String operatorCode = "yu001009";
    private List<SignInTraceEntity> signInTraceEntityList = createSignInTraceEntityList();
    private final List<Operator> operatorList = createOperatorsList();

    // 検証値
    private SignInTraceEntityCriteria actualSignInTraceEntityDao_criteria;
    private Orders actualSignInTraceEntityDao_orders;
    private OperatorCriteria actualOperatorRepository_criteria;
    private String actualOperatorRepository_operatorCode;

    // サインイン証跡Entityリストデータ作成
    private List<SignInTraceEntity> createSignInTraceEntityList() {
        List<SignInTraceEntity> list = newArrayList();
        list.add(createSignInTraceEntity(1L,LocalDateTime.of(2020,10,28,8,30,12),"001.001.001.001","yu001010",SignInCause.サインイン.getCode(),SignInResult.成功.getCode(),null,null,null,null,null,null,1));
        list.add(createSignInTraceEntity(2L,LocalDateTime.of(2020,10,28,9,31,3),"001.001.001.002","yu001010",SignInCause.継続サインイン.getCode(),SignInResult.失敗_パスワード誤り.getCode(),null,null,null,null,null,null,1));
        list.add(createSignInTraceEntity(3L,LocalDateTime.of(2020,10,28,10,1,45),"001.001.001.003","yu001010",SignInCause.サインイン.getCode(),SignInResult.成功.getCode(),null,null,null,null,null,null,1));
        list.add(createSignInTraceEntity(4L,LocalDateTime.of(2020,11,1,8,30,11),"001.001.001.004","yu001009",SignInCause.サインイン.getCode(),SignInResult.成功.getCode(),null,null,null,null,null,null,1));
        list.add(createSignInTraceEntity(5L,LocalDateTime.of(2020,11,2,7,12,34),"001.001.001.005","yu001009",SignInCause.サインイン.getCode(),SignInResult.成功.getCode(),null,null,null,null,null,null,1));
        return list;
    }
    // サインイン証跡Entityデータ作成
    private SignInTraceEntity createSignInTraceEntity(
        Long signInTraceId,
        LocalDateTime tryDateTime,
        String tryIpAddress,
        String operatorCode,
        Short signInCause,
        Short signInResult,
        Long createdBy,
        LocalDateTime createdAt,
        String createdIpAddress,
        Long updatedBy,
        LocalDateTime updatedAt,
        String updatedIpAddress,
        Integer recordVersion) {

        SignInTraceEntity entity = new SignInTraceEntity();
        entity.setSignInTraceId(signInTraceId);
        entity.setTryDateTime(tryDateTime);
        entity.setTryIpAddress(tryIpAddress);
        entity.setOperatorCode(operatorCode);
        entity.setSignInCause(signInCause);
        entity.setSignInResult(signInResult);
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
    private  List<Operator> createOperatorsList() {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L,"yu001009","ｙｕ００１００９","yu001009@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        list.add(Operator.createFrom(19L,"yu001010","ｙｕ００１０１０","yu001010@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),true,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        list.add(Operator.createFrom(20L,"yu001011","ｙｕ００１０１１","yu001011@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.利用不可,1,null));
        return list;
    }

    // サインイン証跡Daoのスタブ
    private SignInTraceEntityDao createSignInTraceEntityDao() {
        return new SignInTraceEntityDao() {
            @Override
            public List<SignInTraceEntity> findBy(SignInTraceEntityCriteria criteria, Orders orders) {
                actualSignInTraceEntityDao_criteria = criteria;
                actualSignInTraceEntityDao_orders = orders;
                return signInTraceEntityList;
            }
            @Override
            public SignInTraceEntity findOneBy(SignInTraceEntityCriteria criteria) {
                actualSignInTraceEntityDao_criteria = criteria;
                return signInTraceEntityList.stream().filter(s -> s.getSignInTraceId().equals(criteria.getSignInTraceIdCriteria().getEqualTo())).findFirst().orElse(null);
            }
            @Override
            public List<SignInTraceEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public int countBy(SignInTraceEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(SignInTraceEntity entity) {
                return 0;
            }
            @Override
            public int update(SignInTraceEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(SignInTraceEntity entity) {
                return 0;
            }
            @Override
            public int delete(SignInTraceEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(SignInTraceEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<SignInTraceEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<SignInTraceEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<SignInTraceEntity> entities) {
                return new int[0];
            }
        };
    }
    // オペレーターリポジトリのスタブ
    private OperatorRepository createOperatorRepository() {
        return new OperatorRepository() {
            @Override
            public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
                actualOperatorRepository_criteria = operatorCriteria;
                return Operators.createFrom(operatorList);
            }
            @Override
            public Operator findOneByCode(String operatorCode) {
                actualOperatorRepository_operatorCode = operatorCode;
                return operatorList.stream().filter(o -> o.getOperatorCode().equals(operatorCode)).findFirst().orElse(null);
            }
            @Override
            public Operator findOneById(Long operatorId) {
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
     * {@link SignInTraceDataSource#findOneById(Long)}テスト
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
    void findOneById_test0() {

        // 実行値
        Long signInTraceId = 1L;

        // テスト対象クラス生成
        SignInTraceDataSource signInTraceDataSource = new SignInTraceDataSource(
            createSignInTraceEntityDao(),
            createOperatorRepository());

        // 期待値
        SignInTraceEntity signInTraceEntity = signInTraceEntityList.stream().filter(s -> s.getSignInTraceId().equals(signInTraceId)).findFirst().orElse(null);
        Operator operator = operatorList.stream().filter(o -> o.getOperatorCode().equals(signInTraceEntity.getOperatorCode())).findFirst().orElse(null);
        SignInTrace expectedSignInTrace = SignInTrace.createFrom(
            signInTraceEntity.getSignInTraceId(),
            signInTraceEntity.getTryDateTime(),
            signInTraceEntity.getTryIpAddress(),
            signInTraceEntity.getOperatorCode(),
            SignInCause.codeOf(signInTraceEntity.getSignInCause()),
            SignInResult.codeOf(signInTraceEntity.getSignInResult()),
            signInTraceEntity.getRecordVersion(),
            operator);
        SignInTraceEntityCriteria expectedSignInTraceEntityDao_criteria = new SignInTraceEntityCriteria();
        expectedSignInTraceEntityDao_criteria.getSignInTraceIdCriteria().setEqualTo(signInTraceId);
        String expectedOperatorCode = signInTraceEntity.getOperatorCode();

        // 実行
        SignInTrace actualSignInTrace = signInTraceDataSource.findOneById(signInTraceId);

        // 結果検証
        assertThat(actualSignInTrace).usingRecursiveComparison().isEqualTo(expectedSignInTrace);
        assertThat(actualSignInTraceEntityDao_criteria.toString()).isEqualTo(expectedSignInTraceEntityDao_criteria.toString());
        assertThat(actualOperatorRepository_operatorCode).isEqualTo(expectedOperatorCode);
    }

    /**
     * {@link SignInTraceDataSource#selectBy(SignInTraceCriteria,Orders)}テスト
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
        SignInTraceCriteria criteria = new SignInTraceCriteria();
        criteria.getOperatorCodeCriteria().setEqualTo(operatorCode);
        Orders orders = Orders.empty().addOrder("tryDateTime", Order.DESC);

        // テスト対象クラス生成
        SignInTraceDataSource signInTraceDataSource = new SignInTraceDataSource(
            createSignInTraceEntityDao(),
            createOperatorRepository());

        // 期待値
        List<SignInTrace> expectedSignInTraceList = newArrayList();
        for(SignInTraceEntity entity : signInTraceEntityList) {
            expectedSignInTraceList.add(SignInTrace.createFrom(
                entity.getSignInTraceId(),
                entity.getTryDateTime(),
                entity.getTryIpAddress(),
                entity.getOperatorCode(),
                SignInCause.codeOf(entity.getSignInCause()),
                SignInResult.codeOf(entity.getSignInResult()),
                entity.getRecordVersion(),
                operatorList.stream().filter(o->o.getOperatorCode().equals(entity.getOperatorCode())).findFirst().orElse(null)));
        }
        SignInTraceEntityCriteria expectedSignInTraceEntityDao_criteria = new SignInTraceEntityCriteria();
        expectedSignInTraceEntityDao_criteria.getOperatorCodeCriteria().assignFrom(criteria.getOperatorCodeCriteria());
        Orders expectedOrders = orders;
        OperatorCriteria expectedOperatorRepository_criteria = new OperatorCriteria();
        expectedOperatorRepository_criteria.getOperatorCodeCriteria().getIncludes().addAll(
            signInTraceEntityList.stream().map(SignInTraceEntity::getOperatorCode).distinct().collect(Collectors.toList()));

        // 実行
        SignInTraces actualSignInTraces = signInTraceDataSource.selectBy(criteria, orders);

        // 結果検証
        assertThat(actualSignInTraces.getValues().size()).isEqualTo(expectedSignInTraceList.size());
        for(int i = 0; i < actualSignInTraces.getValues().size(); i++) {
            assertThat(actualSignInTraces.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedSignInTraceList.get(i));
        }
        assertThat(actualSignInTraceEntityDao_criteria.toString()).isEqualTo(expectedSignInTraceEntityDao_criteria.toString());
        assertThat(actualSignInTraceEntityDao_orders).usingRecursiveComparison().isEqualTo(expectedOrders);
        assertThat(actualOperatorRepository_criteria.toString()).isEqualTo(expectedOperatorRepository_criteria.toString());
    }

    /**
     * {@link SignInTraceDataSource#selectBy(SignInTraceCriteria,Orders)}テスト
     *  ●パターン
     *    正常（検索結果0件）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・検索結果
     */
    @Test
    @Tag(TestSize.SMALL)
    void selectBy_test1() {

        // 実行値
        SignInTraceCriteria criteria = new SignInTraceCriteria();
        criteria.getOperatorCodeCriteria().setEqualTo(operatorCode);
        Orders orders = Orders.empty().addOrder("tryDateTime", Order.DESC);
        signInTraceEntityList = newArrayList();

        // テスト対象クラス生成
        SignInTraceDataSource signInTraceDataSource = new SignInTraceDataSource(
            createSignInTraceEntityDao(),
            createOperatorRepository());

        // 期待値
        int expectedSignInTraceListSize = 0;

        // 実行
        SignInTraces actualSignInTraces = signInTraceDataSource.selectBy(criteria, orders);

        // 結果検証
        assertThat(actualSignInTraces.getValues().size()).isEqualTo(expectedSignInTraceListSize);
    }

    /**
     * {@link SignInTraceDataSource#latestBy(SignInTraceCriteria,Orders)}テスト
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
    void latestBy_test0() {

        // 実行値
        SignInTraceCriteria criteria = new SignInTraceCriteria();
        Orders orders = Orders.empty().addOrder("operatorCode");

        // テスト対象クラス生成
        SignInTraceDataSource signInTraceDataSource = new SignInTraceDataSource(
            createSignInTraceEntityDao(),
            createOperatorRepository());

        // 期待値
        List<SignInTrace> expectedSignInTraceList = newArrayList();
        List<SignInTraceEntity> entityList = signInTraceEntityList.stream().filter(s ->
            s.getSignInTraceId().equals(3L) || s.getSignInTraceId().equals(5L)).collect(Collectors.toList());
        for(SignInTraceEntity entity : entityList.stream().sorted(orders.toComparator()).collect(Collectors.toList())) {
            expectedSignInTraceList.add(SignInTrace.createFrom(
                entity.getSignInTraceId(),
                entity.getTryDateTime(),
                entity.getTryIpAddress(),
                entity.getOperatorCode(),
                SignInCause.codeOf(entity.getSignInCause()),
                SignInResult.codeOf(entity.getSignInResult()),
                entity.getRecordVersion(),
                operatorList.stream().filter(o->o.getOperatorCode().equals(entity.getOperatorCode())).findFirst().orElse(null)));
        }
        SignInTraceEntityCriteria expectedSignInTraceEntityDao_criteria = new SignInTraceEntityCriteria();
        expectedSignInTraceEntityDao_criteria.getOperatorCodeCriteria().assignFrom(criteria.getOperatorCodeCriteria());
        Orders expectedOrders = Orders.empty();
        OperatorCriteria expectedOperatorRepository_criteria = new OperatorCriteria();
        expectedOperatorRepository_criteria.getOperatorCodeCriteria().getIncludes().addAll(
            entityList.stream().map(SignInTraceEntity::getOperatorCode).distinct().collect(Collectors.toList()));

        // 実行
        SignInTraces actualSignInTraces = signInTraceDataSource.latestBy(criteria, orders);

        // 結果検証
        assertThat(actualSignInTraces.getValues().size()).isEqualTo(expectedSignInTraceList.size());
        for(int i = 0; i < actualSignInTraces.getValues().size(); i++) {
            assertThat(actualSignInTraces.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedSignInTraceList.get(i));
        }
        assertThat(actualSignInTraceEntityDao_criteria.toString()).isEqualTo(expectedSignInTraceEntityDao_criteria.toString());
        assertThat(actualSignInTraceEntityDao_orders).usingRecursiveComparison().isEqualTo(expectedOrders);
        assertThat(actualOperatorRepository_criteria.toString()).isEqualTo(expectedOperatorRepository_criteria.toString());
    }

    /**
     * {@link SignInTraceDataSource#latestBy(SignInTraceCriteria,Orders)}テスト
     *    正常（検索結果0件）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・検索結果
     */
    @Test
    @Tag(TestSize.SMALL)
    void latestBy_test1() {

        // 実行値
        SignInTraceCriteria criteria = new SignInTraceCriteria();
        Orders orders = Orders.empty().addOrder("operatorCode");
        signInTraceEntityList = newArrayList();

        // テスト対象クラス生成
        SignInTraceDataSource signInTraceDataSource = new SignInTraceDataSource(
            createSignInTraceEntityDao(),
            createOperatorRepository());

        // 期待値
        int expectedSignInTraceListSize = 0;

        // 実行
        SignInTraces actualSignInTraces = signInTraceDataSource.latestBy(criteria, orders);

        // 結果検証
        assertThat(actualSignInTraces.getValues().size()).isEqualTo(expectedSignInTraceListSize);
    }
}