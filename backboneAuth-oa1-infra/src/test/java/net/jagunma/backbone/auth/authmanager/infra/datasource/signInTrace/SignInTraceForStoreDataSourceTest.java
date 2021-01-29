package net.jagunma.backbone.auth.authmanager.infra.datasource.signInTrace;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraces;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntity;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntityCriteria;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.beans.Beans;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SignInTraceForStoreDataSourceTest {

    // 実行既定値
    private Long signInTraceId = 123456789L;
    private LocalDateTime tryDateTime = LocalDateTime.of(2021,1,22,8,31,12);
    private String tryIpAddress = "001.001.001.001";
    private String operatorCode = "yu001009";
    private Short signInCause = SignInCause.サインイン.getCode();
    private Short signInResult = SignInResult.成功.getCode();
    private Integer recordVersion = 1;

    private Long operatorId = 18L;
    private String operatorName = "ｙｕ００１００９";
    private String mailAddress = "";
    private LocalDate validThruStartDate = LocalDate.of(2010,8,17);
    private LocalDate validThruEndDate = LocalDate.of(9999,12,31);
    private Boolean isDeviceAuth = false;
    private Long jaId = 6L;
    private String jaCode = "006";
    private String jaName = "ＪＡ００６";
    private Long branchId = 33L;
    private String branchCode = "001";
    String branchName = "店舗００１";
    private AvailableStatus availableStatus = AvailableStatus.利用可能;
    private Integer operatorRecordVersion = 1;
    private BranchAtMoment branchAtMoment = createBranchAtMoment();
    private Operator operator = createOperator();

    // 検証値
    private SignInTraceEntity actualSignInTraceEntityDao_entity;
    private Long actualSignInTraceRepository_signInTraceId;

    // オペレーターデータ作成
    private Operator createOperator() {
        return Operator.createFrom(
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
            availableStatus,
            operatorRecordVersion,
            branchAtMoment);
    }
    // BranchAtMomentデータ作成
    private BranchAtMoment createBranchAtMoment() {
        return BranchAtMoment.builder()
            .withIdentifier(branchId)
            .withJaAtMoment(createJaAtMoment())
            .withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般)
                .withBranchCode(BranchCode.of(branchCode))
                .withName(branchName)
                .build())
            .build();
    }
    // JaAtMomentデータ作成
    private JaAtMoment createJaAtMoment() {
        return JaAtMoment.builder()
            .withIdentifier(jaId)
            .withJaAttribute(JaAttribute
                .builder()
                .withJaCode(JaCode.of(jaCode))
                .withName(jaName)
                .withFormalName("")
                .withAbbreviatedName("")
                .build())
            .build();
    }

    // サインイン証跡格納クラス作成（テスト対象クラス）
    private SignInTraceForStoreDataSource createSignInTraceForStoreDataSource() {
        // サインイン証跡Daoのスタブ
        SignInTraceEntityDao signInTraceEntityDao = new SignInTraceEntityDao() {
            @Override
            public int insert(SignInTraceEntity entity) {
                actualSignInTraceEntityDao_entity = Beans.createAndCopy(SignInTraceEntity.class, entity).execute();;
                entity.setSignInTraceId(signInTraceId);
                entity.setRecordVersion(recordVersion);
                return 1;
            }
            @Override
            public List<SignInTraceEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public SignInTraceEntity findOneBy(SignInTraceEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<SignInTraceEntity> findBy(SignInTraceEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public int countBy(SignInTraceEntityCriteria criteria) {
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
        // サインイン証跡リポジトリのスタブ
        SignInTraceRepository signInTraceRepository = new SignInTraceRepository() {
            @Override
            public SignInTrace findOneById(Long argSignInTraceId) {
                actualSignInTraceRepository_signInTraceId = argSignInTraceId;
                return SignInTrace.createFrom(
                    argSignInTraceId,
                    tryDateTime,
                    tryIpAddress,
                    operatorCode,
                    signInCause,
                    signInResult,
                    recordVersion,
                    operator);
            }
            @Override
            public SignInTraces selectBy(SignInTraceCriteria signInTraceCriteria, Orders orders) {
                return null;
            }
        };

        return new SignInTraceForStoreDataSource(signInTraceEntityDao, signInTraceRepository);
    }

    /**
     * {@link SignInTraceForStoreDataSource#insert(SignInTrace)}テスト
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
        SignInTraceForStoreDataSource signInTraceForStoreDataSource = createSignInTraceForStoreDataSource();

        // 実行値
        SignInTrace signInTrace = SignInTrace.createFrom(
            null,
            tryDateTime,
            tryIpAddress,
            operatorCode,
            signInCause,
            signInResult,
            null,
            null);

        // 期待値
        SignInTrace expectedSignInTrace = SignInTrace.createFrom(
            signInTraceId,
            tryDateTime,
            tryIpAddress,
            operatorCode,
            signInCause,
            signInResult,
            recordVersion,
            operator);
        SignInTraceEntity expectedSignInTraceEntity = new SignInTraceEntity();
        expectedSignInTraceEntity.setTryDateTime(tryDateTime);
        expectedSignInTraceEntity.setTryIpAddress(tryIpAddress);
        expectedSignInTraceEntity.setOperatorCode(operatorCode);
        expectedSignInTraceEntity.setSignInCause(signInCause);
        expectedSignInTraceEntity.setSignInResult(signInResult);
        Long expectedSignInTraceId = signInTraceId;

        // 実行
        SignInTrace actualSignInTrace = signInTraceForStoreDataSource.insert(signInTrace);

        // 結果検証
        assertThat(actualSignInTrace).usingRecursiveComparison().isEqualTo(expectedSignInTrace);
        assertThat(actualSignInTraceEntityDao_entity).usingRecursiveComparison().isEqualTo(expectedSignInTraceEntity);
        assertThat(actualSignInTraceRepository_signInTraceId).isEqualTo(expectedSignInTraceId);
    }
}