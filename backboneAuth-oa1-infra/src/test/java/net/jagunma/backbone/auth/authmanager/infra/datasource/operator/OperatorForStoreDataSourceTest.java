package net.jagunma.backbone.auth.authmanager.infra.datasource.operator;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorUpdatePack;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.OperatorHistoryPackRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistories;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoriesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OperatorForStoreDataSourceTest {

    // 実行既定値
    // オペレーター系
    private Long operatorId = 123456L;
    private String operatorCode = "yu123456";
    private String operatorName = "オペレーター名";
    private String mailAddress = "test@den.jagunma.net";
    private LocalDate expirationStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate expirationEndDate = LocalDate.of(2020, 9, 30);
    private Boolean isDeviceAuth = true;   private Boolean isDeviceAuthBefore = false;
    private Long jaId = 6L;
    private String jaCode = "006";
    private Long branchId = 1L;
    private String branchCode = "001";
    private AvailableStatus availableStatus = AvailableStatus.利用可能;
    private Long createdBy = 100L;
    private LocalDateTime createdAt = LocalDateTime.of(2020, 10, 30,1,2,3);
    private String createdIpAddress = "100.100.100.100";
    private Long updatedBy = 200L;
    private LocalDateTime updatedAt = LocalDateTime.of(2020, 10, 31,4,5,6);
    private String updatedIpAddress = "200.200.200.200";
    private Integer recordVersion = 1;

    // オペレーター履歴ヘッダー系
    private String changeCause = "新職員の入組による登録";

    // パスワード履歴系
    private Long passwordHistoryId = 345678L;
    private String password = "PaSsWoRd";
    private String passwordLastTime = "PasswordLastTime";
    private String password2TimesBefore = "PasswordTwoTimesBefore";
    private PasswordChangeType passwordChangeType = PasswordChangeType.初期;
    private PasswordChangeType passwordChangeTypeLastTime = PasswordChangeType.ユーザーによる変更;
    private PasswordChangeType passwordChangeType2TimesBefore = PasswordChangeType.管理者によるリセット;
    private PasswordHistories passwordHistories;

    // テスト制御用
    boolean isOperatorEntityUpdated = false;

    // オペレーターエントリーパック生成
    private OperatorEntryPack createOperatorEntryPack() {
        return OperatorEntryPack.createFrom(
            operatorCode,
            operatorName,
            mailAddress,
            expirationStartDate,
            expirationEndDate,
            jaId,
            jaCode,
            branchId,
            branchCode,
            changeCause,
            password);
    }
    // オペレーターアップデートパック生成
    private OperatorUpdatePack createOperatorUpdatePack() {
        return OperatorUpdatePack.createFrom(
            operatorId,
            operatorName,
            mailAddress,
            expirationStartDate,
            expirationEndDate,
            isDeviceAuth,
            branchId,
            branchCode,
            availableStatus,
            recordVersion,
            changeCause);
    }

    // パスワード履歴生成
    private PasswordHistories createPasswordHistories() {
        List<PasswordHistory> passwordHistoryList = newArrayList(
            PasswordHistory.createFrom(202L, operatorId, LocalDateTime.of(2020,10,1,8,31,12), passwordLastTime, passwordChangeTypeLastTime, recordVersion, null),
            PasswordHistory.createFrom(201L, operatorId, LocalDateTime.of(2020,10,1,8,30,12), password2TimesBefore, passwordChangeType2TimesBefore, recordVersion, null));

        return PasswordHistories.createFrom(passwordHistoryList);
    }

    // テスト対象クラス生成
    private OperatorForStoreDataSource createOperatorForStoreDataSource() {
        OperatorEntityDao operatorEntityDao = new OperatorEntityDao() {
            @Override
            public int countBy(OperatorEntityCriteria criteria) {
                // entry系テスト時
                if (!criteria.getOperatorCodeCriteria().getEqualTo().equals(operatorCode)) {
                    return 1;
                }
                return 0;
            }
            @Override
            public int insert(OperatorEntity entity) {
                // entry系テスト時
                entity.setOperatorId(operatorId);
                entity.setCreatedBy(createdBy);
                entity.setCreatedAt(createdAt);
                entity.setCreatedIpAddress(createdIpAddress);
                entity.setRecordVersion(recordVersion);
                return 0;
            }
            @Override
            public List<OperatorEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public OperatorEntity findOneBy(OperatorEntityCriteria criteria) {
                OperatorEntity entity = new OperatorEntity();

                // update系テスト時
                entity.setOperatorId(criteria.getOperatorIdCriteria().getEqualTo());
                entity.setOperatorCode(operatorCode);
                entity.setOperatorName(operatorName);
                entity.setMailAddress(mailAddress);
                entity.setExpirationStartDate(expirationStartDate);
                entity.setExpirationEndDate(expirationEndDate);

                entity.setJaId(jaId);
                entity.setJaCode(jaCode);
                entity.setBranchId(branchId);
                entity.setBranchCode(branchCode);
                entity.setAvailableStatus(availableStatus.getCode());
                entity.setCreatedBy(createdBy);
                entity.setCreatedAt(createdAt);
                entity.setCreatedIpAddress(createdIpAddress);

                // update系テスト時（updateOperator での update 前の findOneBy）＆（isChangeDeviceAuth）
                if (!isOperatorEntityUpdated) {
                    entity.setIsDeviceAuth(isDeviceAuthBefore);

                    entity.setUpdatedBy(null);
                    entity.setUpdatedAt(null);
                    entity.setUpdatedIpAddress(null);
                    entity.setRecordVersion(recordVersion);
                } else {
                // update系テスト時（updateOperator での update 後の findOneBy）
                    entity.setIsDeviceAuth(isDeviceAuth);

                    entity.setUpdatedBy(updatedBy);
                    entity.setUpdatedAt(updatedAt);
                    entity.setUpdatedIpAddress(updatedIpAddress);
                    entity.setRecordVersion(recordVersion + 1);
                }

                return entity;
            }
            @Override
            public List<OperatorEntity> findBy(OperatorEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public int update(OperatorEntity entity) {
                isOperatorEntityUpdated = true;
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
        OperatorHistoryPackRepositoryForStore operatorHistoryPackRepositoryForStore = new OperatorHistoryPackRepositoryForStore() {
            @Override
            public void store(Long operatorId, String changeCause) {
            }
        };
        PasswordHistoriesRepository passwordHistoriesRepository = new PasswordHistoriesRepository() {
            @Override
            public PasswordHistories selectBy(PasswordHistoryCriteria passwordHistoryCriteria, Orders orders) {
                return passwordHistories;
            }
        };
        PasswordHistoryRepositoryForStore passwordHistoryRepositoryForStore = new PasswordHistoryRepositoryForStore() {
            @Override
            public void store(PasswordHistory passwordHistory) {
            }
        };

        return new OperatorForStoreDataSource(
            operatorEntityDao,
            operatorHistoryPackRepositoryForStore,
            passwordHistoriesRepository,
            passwordHistoryRepositoryForStore);
    }

    /**
     * {@link OperatorForStoreDataSource#entry(OperatorEntryPack operatorEntryPack)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void entry_test() {
        // テスト対象クラス生成
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // 実行値
        OperatorEntryPack operatorEntryPack = createOperatorEntryPack();

        assertThatCode(() ->
            // 実行
            operatorForStoreDataSource.entry(operatorEntryPack))
            .doesNotThrowAnyException();
    }
    /**
     * {@link OperatorForStoreDataSource#update(OperatorUpdatePack operatorUpdatePack)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test() {
        // テスト対象クラス生成
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // 実行値
        changeCause = "認証機器使用開始";
        OperatorUpdatePack operatorUpdatePack = createOperatorUpdatePack();
        passwordHistories = createPasswordHistories();

        assertThatCode(() ->
            // 実行
            operatorForStoreDataSource.update(operatorUpdatePack))
            .doesNotThrowAnyException();
    }

    /**
     * {@link OperatorForStoreDataSource#checkAlreadyExists(String operatorCode)}テスト
     *  ●パターン
     *    オペレーター（コード）がすでに存在しているかのチェック）同オペレーターコード既存データあり(オペレーターコード:yu999999)
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkAlreadyExists_test() {
        // テスト対象クラス生成
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // 実行値
        String alreadyExistsOperatorCode = "yu999999";

        assertThatThrownBy(() ->
            // 実行
            operatorForStoreDataSource.checkAlreadyExists(alreadyExistsOperatorCode))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA11001");
                assertThat(e.getArgs()).containsSequence("オペレーターコード");
                assertThat(e.getArgs()).containsSequence(alreadyExistsOperatorCode);
            });
    }

    /**
     * {@link OperatorForStoreDataSource#isChangeDeviceAuth(OperatorUpdatePack operatorUpdatePack)}テスト
     *  ●パターン
     *    機器認証の変更有無を判定）機器認証の変更 有
     *
     *  ●検証事項
     *  ・判定結果
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void isChangeDeviceAuth_test0() {
        // テスト対象クラス生成
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // 実行値
        OperatorUpdatePack operatorUpdatePack = createOperatorUpdatePack();

        // 期待値
        Boolean expectedValue = true;

        // 実行
        boolean actualValue = operatorForStoreDataSource.isChangeDeviceAuth(operatorUpdatePack);

        // 結果検証
        assertThat(actualValue).isEqualTo(expectedValue);
    }
    /**
     * {@link OperatorForStoreDataSource#isChangeDeviceAuth(OperatorUpdatePack operatorUpdatePack)}テスト
     *  ●パターン
     *    機器認証の変更有無を判定）機器認証の変更 無
     *
     *  ●検証事項
     *  ・判定結果
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void isChangeDeviceAuth_test1() {
        // テスト対象クラス生成
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // 実行値
        isDeviceAuth = false;
        isDeviceAuthBefore = false;
        OperatorUpdatePack operatorUpdatePack = createOperatorUpdatePack();

        // 期待値
        Boolean expectedValue = false;

        // 実行
        boolean actualValue = operatorForStoreDataSource.isChangeDeviceAuth(operatorUpdatePack);

        // 結果検証
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    /**
     * {@link OperatorForStoreDataSource#insertOperator(OperatorEntryPack operatorEntryPack)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Entityへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void insertOperator_test() {
        // テスト対象クラス生成
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // 実行値
        OperatorEntryPack operatorEntryPack = createOperatorEntryPack();

        // 期待値
        OperatorEntity expectedEntity = new OperatorEntity();
        expectedEntity.setOperatorId(operatorId);
        expectedEntity.setOperatorCode(operatorCode);
        expectedEntity.setOperatorName(operatorName);
        expectedEntity.setMailAddress(mailAddress);
        expectedEntity.setExpirationStartDate(expirationStartDate);
        expectedEntity.setExpirationEndDate(expirationEndDate);
        expectedEntity.setIsDeviceAuth(isDeviceAuthBefore);
        expectedEntity.setJaId(jaId);
        expectedEntity.setJaCode(jaCode);
        expectedEntity.setBranchId(branchId);
        expectedEntity.setBranchCode(branchCode);
        expectedEntity.setAvailableStatus(availableStatus.getCode());
        expectedEntity.setCreatedBy(createdBy);
        expectedEntity.setCreatedAt(createdAt);
        expectedEntity.setCreatedIpAddress(createdIpAddress);
        expectedEntity.setUpdatedBy(null);
        expectedEntity.setUpdatedAt(null);
        expectedEntity.setUpdatedIpAddress(null);
        expectedEntity.setRecordVersion(recordVersion);

        // 実行
        OperatorEntity operatorEntity = operatorForStoreDataSource.insertOperator(operatorEntryPack);

        // 結果検証
        assertThat(operatorEntity).usingRecursiveComparison().isEqualTo(expectedEntity);
    }

    /**
     * {@link OperatorForStoreDataSource#updateOperator(OperatorUpdatePack operatorUpdatePack)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Entityへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void updateOperator_test() {
        // テスト対象クラス生成
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // 実行値
        OperatorUpdatePack operatorUpdatePack = createOperatorUpdatePack();

        // 期待値
        OperatorEntity expectedEntity = new OperatorEntity();
        expectedEntity.setOperatorId(operatorId);
        expectedEntity.setOperatorCode(operatorCode);
        expectedEntity.setOperatorName(operatorName);
        expectedEntity.setMailAddress(mailAddress);
        expectedEntity.setExpirationStartDate(expirationStartDate);
        expectedEntity.setExpirationEndDate(expirationEndDate);
        expectedEntity.setIsDeviceAuth(isDeviceAuth);
        expectedEntity.setJaId(jaId);
        expectedEntity.setJaCode(jaCode);
        expectedEntity.setBranchId(branchId);
        expectedEntity.setBranchCode(branchCode);
        expectedEntity.setAvailableStatus(availableStatus.getCode());
        expectedEntity.setCreatedBy(createdBy);
        expectedEntity.setCreatedAt(createdAt);
        expectedEntity.setCreatedIpAddress(createdIpAddress);
        expectedEntity.setUpdatedBy(updatedBy);
        expectedEntity.setUpdatedAt(updatedAt);
        expectedEntity.setUpdatedIpAddress(updatedIpAddress);
        expectedEntity.setRecordVersion(recordVersion + 1);

        // 実行
        OperatorEntity operatorEntity = operatorForStoreDataSource.updateOperator(operatorUpdatePack);

        // 結果検証
        assertThat(operatorEntity).usingRecursiveComparison().isEqualTo(expectedEntity);
    }

    /**
     * {@link OperatorForStoreDataSource#storePasswordHistory(Long operatorId, LocalDateTime changeDateTime, String password, PasswordChangeType passwordChangeType)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void storePasswordHistory_test0() {
        // テスト対象クラス生成
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // 期待値
        PasswordHistory expectedModel = PasswordHistory.createFrom(
            null,
            operatorId,
            createdAt,
            password,
            passwordChangeType,
            null,
            null);

        // 実行
        PasswordHistory passwordHistory = operatorForStoreDataSource.storePasswordHistory(operatorId, createdAt, password, passwordChangeType);

        // 結果検証
        assertThat(passwordHistory).usingRecursiveComparison().isEqualTo(expectedModel);
    }

    /**
     * {@link OperatorForStoreDataSource#storePasswordHistory(Long operatorId, LocalDateTime changeDateTime, boolean isDeviceAuth)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void storePasswordHistory_test1() {
        // テスト対象クラス生成
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // 実行値
        passwordHistories = createPasswordHistories();

        // 期待値
        PasswordHistory expectedModel = PasswordHistory.createFrom(
            null,
            operatorId,
            updatedAt,
            passwordLastTime,
            PasswordChangeType.機器認証パスワード,
            null,
            null);

        // 実行
        PasswordHistory passwordHistory = operatorForStoreDataSource.storePasswordHistory(operatorId, updatedAt, isDeviceAuth);

        // 結果検証
        assertThat(passwordHistory).usingRecursiveComparison().isEqualTo(expectedModel);
    }

    /**
     * {@link OperatorForStoreDataSource#storePasswordHistory(Long operatorId, LocalDateTime changeDateTime, boolean isDeviceAuth)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void storePasswordHistory_test2() {
        // テスト対象クラス生成
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // 実行値
        isDeviceAuth = false;
        passwordLastTime = "pAsSwOrD";
        password2TimesBefore = "pAsSwOrD";
        passwordChangeTypeLastTime = PasswordChangeType.機器認証パスワード;
        passwordChangeType2TimesBefore = PasswordChangeType.ユーザーによる変更;
        passwordHistories = createPasswordHistories();

        // 期待値
        PasswordHistory expectedModel = PasswordHistory.createFrom(
            null,
            operatorId,
            updatedAt,
            passwordLastTime,
            passwordChangeType2TimesBefore,
            null,
            null);

        // 実行
        PasswordHistory passwordHistory = operatorForStoreDataSource.storePasswordHistory(operatorId, updatedAt, isDeviceAuth);

        // 結果検証
        assertThat(passwordHistory).usingRecursiveComparison().isEqualTo(expectedModel);
    }
}