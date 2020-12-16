package net.jagunma.backbone.auth.authmanager.infra.datasource.operator;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
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

    // オペレーターDaoの作成
    private OperatorEntityDao createOperatorEntityDao() {
        return new OperatorEntityDao() {
            @Override
            public OperatorEntity findOneBy(OperatorEntityCriteria criteria) {
                return createOperatorEntity();
            }
            @Override
            public boolean existsBy(OperatorEntityCriteria criteria) {
                return true;
            }
            @Override
            public List<OperatorEntity> findBy(OperatorEntityCriteria criteria, Orders orders) {
                return null;
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
    // BranchAtMomentRepositoryの作成
    private BranchAtMomentRepository createBranchAtMomentRepository() {
        return new BranchAtMomentRepository() {
            @Override
            public BranchAtMoment findOneBy(BranchAtMomentCriteria criteria) {
                return createBranchAtMoment();
            }
            @Override
            public BranchesAtMoment selectBy(BranchAtMomentCriteria criteria, Orders orders) {
                return null;
            }
        };
    }
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

    /**
     * {@link OperatorDataSource#findOneBy(OperatorCriteria)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void findOneBy_test0() {

        // 実行値
        OperatorCriteria criteria = new OperatorCriteria();

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

        // 実行
        Operator actualOperator = operatorDataSource.findOneBy(criteria);

        // 結果検証
        assertThat(actualOperator).usingRecursiveComparison().isEqualTo(expectedOperator);
    }

    /**
     * {@link OperatorDataSource#existsBy(OperatorCriteria)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void existsBy_test0() {

        // 実行値
        OperatorCriteria criteria = new OperatorCriteria();

        // テスト対象クラス生成
        OperatorDataSource operatorDataSource = new OperatorDataSource(createOperatorEntityDao(),
            createBranchAtMomentRepository());

        // 実行
        boolean actualResult = operatorDataSource.existsBy(criteria);

        // 結果検証
        assertThat(actualResult).isEqualTo(true);
    }
}