package net.jagunma.backbone.auth.authmanager.infra.datasource.operator;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OperatorDataSourceTest {

    // 実行既定値
    private Long operatorId = 18L;
    private String operatorCode = "yu001009";
    private String operatorName = "ｙｕ００１００９";
    private String mailAddress = "yu001009@aaaaa.net";
    private LocalDate validThruStartDate = LocalDate.of(2020,1,1);
    private LocalDate validThruEndDate = LocalDate.of(9999,12,31);
    private Boolean isDeviceAuth = false;
    private Long jaId = 6L;
    private String jaCode = "006";
    private String jaName = "ＪＡ００６";
    private Long branchId = 33L;
    private String branchCode = "001";
    private String branchName = "店舗００１";
    private Short availableStatus = (short)0;
    private Integer recordVersion = 1;

    // 検証値
    private OperatorEntityCriteria actualOperatorEntityCriteria;
    private Orders actualOrders;

    // オペレーターデータ作成
    private OperatorEntity createOperatorEntity() {
        OperatorEntity entity = new OperatorEntity();
        entity.setOperatorId(operatorId);
        entity.setOperatorCode(operatorCode);
        entity.setOperatorName(operatorName);
        entity.setMailAddress(mailAddress);
        entity.setValidThruStartDate(validThruStartDate);
        entity.setValidThruEndDate(validThruEndDate);
        entity.setIsDeviceAuth(isDeviceAuth);
        entity.setJaId(jaId);
        entity.setJaCode(jaCode);
        entity.setBranchId(branchId);
        entity.setBranchCode(branchCode);
        entity.setAvailableStatus(availableStatus);
        entity.setRecordVersion(recordVersion);
        return entity;
    }
    // BranchAtMomentデータ作成
    private BranchAtMoment createBranchAtMoment() {
        JaAtMoment jaAtMoment = JaAtMoment.builder()
            .withIdentifier(jaId)
            .withJaAttribute(JaAttribute
                .builder()
                .withJaCode(JaCode.of(jaCode))
                .withName(jaName)
                .withFormalName("")
                .withAbbreviatedName("")
                .build())
            .build();
        return BranchAtMoment.builder()
            .withIdentifier(branchId)
            .withJaAtMoment(jaAtMoment)
            .withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般)
                .withBranchCode(BranchCode.of(branchCode))
                .withName(branchName)
                .build())
            .build();
    }
    // BranchAtMomentリストデータ作成
    private List<BranchAtMoment> createBranchAtMomentList() {
        List<BranchAtMoment> list = newArrayList();
        list.add(createBranchAtMoment(33L,"001","００１店舗",6L,"006","ＪＡ００６"));
        list.add(createBranchAtMoment(34L,"002","００２店舗",6L,"006","ＪＡ００６"));
        list.add(createBranchAtMoment(35L,"003","００３店舗",6L,"006","ＪＡ００６"));
        return list;
    }
    // BranchAtMomentデータ作成
    private BranchAtMoment createBranchAtMoment(
        Long branchId,
        String branchCode,
        String branchName,
        Long jaId,
        String jaCode,
        String jaName) {

        JaAtMoment jaAtMoment = JaAtMoment.builder()
            .withIdentifier(jaId)
            .withJaAttribute(JaAttribute
                .builder()
                .withJaCode(JaCode.of(jaCode))
                .withName(jaName)
                .withFormalName("")
                .withAbbreviatedName("")
                .build())
            .build();
        return BranchAtMoment.builder()
            .withIdentifier(branchId)
            .withJaAtMoment(jaAtMoment)
            .withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般)
                .withBranchCode(BranchCode.of(branchCode))
                .withName(branchName)
                .build())
            .build();
    }
    // オペレーターリストデータ作成
    private List<OperatorEntity> createOperatorEntityList() {
        List<OperatorEntity> list = newArrayList();
        list.add(createOperatorEntity(18L,"yu001009","ｙｕ００１００９","yu001009@aaaaa.net",LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",(short)0));
        list.add(createOperatorEntity(19L,"yu001010","ｙｕ００１０１０","yu001010@aaaaa.net",LocalDate.of(2020,4,1),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",(short)0));
        list.add(createOperatorEntity(20L,"yu001011","ｙｕ００１０１１","yu001011@aaaaa.net",LocalDate.of(2020,1,1),LocalDate.of(2020,12,31),true,6L,"006",33L,"001",(short)0));
        return list;
    }
    // オペレーターデータ作成
    private OperatorEntity createOperatorEntity(
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
        Short availableStatus) {

        OperatorEntity entity = new OperatorEntity();
        entity.setOperatorId(operatorId);
        entity.setOperatorCode(operatorCode);
        entity.setOperatorName(operatorName);
        entity.setMailAddress(mailAddress);
        entity.setValidThruStartDate(validThruStartDate);
        entity.setValidThruEndDate(validThruEndDate);
        entity.setIsDeviceAuth(isDeviceAuth);
        entity.setJaId(jaId);
        entity.setJaCode(jaCode);
        entity.setBranchId(branchId);
        entity.setBranchCode(branchCode);
        entity.setAvailableStatus(availableStatus);
        return entity;
    }

    // オペレーターDaoのスタブ
    private OperatorEntityDao createOperatorEntityDao() {
        return new OperatorEntityDao() {
            @Override
            public OperatorEntity findOneBy(OperatorEntityCriteria criteria) {
                actualOperatorEntityCriteria = criteria;
                return createOperatorEntity();
            }
            @Override
            public boolean existsBy(OperatorEntityCriteria criteria) {
                actualOperatorEntityCriteria = criteria;
                return true;
            }
            @Override
            public List<OperatorEntity> findBy(OperatorEntityCriteria criteria, Orders orders) {
                actualOperatorEntityCriteria = criteria;
                actualOrders = orders;
                return createOperatorEntityList();
            }
            @Override
            public List<OperatorEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public int countBy(OperatorEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(OperatorEntity entity) {
                return 0;
            }
            @Override
            public int update(OperatorEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(OperatorEntity entity) {
                return 0;
            }
            @Override
            public int delete(OperatorEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(OperatorEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<OperatorEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<OperatorEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<OperatorEntity> entities) {
                return new int[0];
            }
        };
    }
    // BranchAtMomentRepositoryのスタブ
    private BranchAtMomentRepository createBranchAtMomentRepository() {
        return new BranchAtMomentRepository() {
            @Override
            public BranchAtMoment findOneBy(BranchAtMomentCriteria criteria) {
                return createBranchAtMoment();
            }
            @Override
            public BranchesAtMoment selectBy(BranchAtMomentCriteria criteria, Orders orders) {
                return BranchesAtMoment.of(createBranchAtMomentList());
            }
        };
    }

    /**
     * {@link OperatorDataSource#findOneById(Long)}のテスト
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

        // テスト対象クラス生成
        OperatorDataSource operatorDataSource = new OperatorDataSource(createOperatorEntityDao(),
            createBranchAtMomentRepository());

        // 期待値
        Operator expectedOperator = Operator.createFrom(
            operatorId,
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
            AvailableStatus.codeOf(availableStatus),
            recordVersion,
            createBranchAtMoment());
        OperatorEntityCriteria expectedOperatorEntityCriteria = new OperatorEntityCriteria();
        expectedOperatorEntityCriteria.getOperatorIdCriteria().setEqualTo(operatorId);

        // 実行
        Operator actualOperator = operatorDataSource.findOneById(operatorId);

        // 結果検証
        assertThat(actualOperator).usingRecursiveComparison().isEqualTo(expectedOperator);
        assertThat(actualOperatorEntityCriteria.toString()).isEqualTo(expectedOperatorEntityCriteria.toString());
    }

    /**
     * {@link OperatorDataSource#findOneByCode(String)}のテスト
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
    void findOneByCode_test0() {

        // テスト対象クラス生成
        OperatorDataSource operatorDataSource = new OperatorDataSource(createOperatorEntityDao(),
            createBranchAtMomentRepository());

        // 期待値
        Operator expectedOperator = Operator.createFrom(
            operatorId,
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
            AvailableStatus.codeOf(availableStatus),
            recordVersion,
            createBranchAtMoment());
        OperatorEntityCriteria expectedOperatorEntityCriteria = new OperatorEntityCriteria();
        expectedOperatorEntityCriteria.getOperatorCodeCriteria().setEqualTo(operatorCode);

        // 実行
        Operator actualOperator = operatorDataSource.findOneByCode(operatorCode);

        // 結果検証
        assertThat(actualOperator).usingRecursiveComparison().isEqualTo(expectedOperator);
        assertThat(actualOperatorEntityCriteria.toString()).isEqualTo(expectedOperatorEntityCriteria.toString());
    }

    /**
     * {@link OperatorDataSource#existsById(Long)}のテスト
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
    void existsById_test0() {

        // テスト対象クラス生成
        OperatorDataSource operatorDataSource = new OperatorDataSource(createOperatorEntityDao(),
            createBranchAtMomentRepository());

        // 期待値
        boolean expected = true;
        OperatorEntityCriteria expectedOperatorEntityCriteria = new OperatorEntityCriteria();
        expectedOperatorEntityCriteria.getOperatorIdCriteria().setEqualTo(operatorId);

        // 実行
        boolean actualResult = operatorDataSource.existsById(operatorId);

        // 結果検証
        assertThat(actualResult).isEqualTo(expected);
        assertThat(actualOperatorEntityCriteria.toString()).isEqualTo(expectedOperatorEntityCriteria.toString());
    }

    /**
     * {@link OperatorDataSource#existsByCode(String)}のテスト
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
    void existsByCode_test0() {

        // テスト対象クラス生成
        OperatorDataSource operatorDataSource = new OperatorDataSource(createOperatorEntityDao(),
            createBranchAtMomentRepository());

        // 期待値
        boolean expected = true;
        OperatorEntityCriteria expectedOperatorEntityCriteria = new OperatorEntityCriteria();
        expectedOperatorEntityCriteria.getOperatorCodeCriteria().setEqualTo(operatorCode);

        // 実行
        boolean actualResult = operatorDataSource.existsByCode(operatorCode);

        // 結果検証
        assertThat(actualResult).isEqualTo(expected);
        assertThat(actualOperatorEntityCriteria.toString()).isEqualTo(expectedOperatorEntityCriteria.toString());
    }

    /**
     * {@link OperatorDataSource#selectBy(OperatorCriteria, Orders)}のテスト
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
        OperatorCriteria criteria = new OperatorCriteria();
        criteria.getJaIdentifierCriteria().setEqualTo(jaId);
        Orders orders = Orders.empty().addOrder("peratorCode");

        // テスト対象クラス生成
        OperatorDataSource operatorDataSource = new OperatorDataSource(createOperatorEntityDao(),
            createBranchAtMomentRepository());

        // 期待値
        List<Operator> expectedOperatorlist = newArrayList();
        for(OperatorEntity entity : createOperatorEntityList()) {
            expectedOperatorlist.add(Operator.createFrom(
                entity.getOperatorId(),
                entity.getOperatorCode(),
                entity.getOperatorName(),
                entity.getMailAddress(),
                entity.getValidThruStartDate(),
                entity.getValidThruEndDate(),
                entity.getIsDeviceAuth(),
                entity.getJaId(),
                entity.getJaCode(),
                entity.getBranchId(),
                entity.getBranchCode(),
                AvailableStatus.codeOf(entity.getAvailableStatus()),
                entity.getRecordVersion(),
                createBranchAtMomentList().stream().filter(b->b.getIdentifier().equals(entity.getBranchId())).findFirst().orElse(null)
            ));
        }
        Operators expectedOperators = Operators.createFrom(expectedOperatorlist);
        OperatorEntityCriteria expectedOperatorEntityCriteria = new OperatorEntityCriteria();
        expectedOperatorEntityCriteria.getJaIdCriteria().setEqualTo(jaId);
        Orders expectedOrders = Orders.empty().addOrder("peratorCode");

        // 実行
        Operators actualOperators = operatorDataSource.selectBy(criteria, orders);

        // 結果検証
        assertThat(actualOperators.getValues().size()).isEqualTo(expectedOperators.getValues().size());
        for(int i = 0; i < actualOperators.getValues().size(); i++) {
            assertThat(actualOperators.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedOperators.getValues().get(i));
        }
        assertThat(actualOperatorEntityCriteria.toString()).isEqualTo(expectedOperatorEntityCriteria.toString());
        assertThat(actualOrders).usingRecursiveComparison().isEqualTo(expectedOrders);
    }
}