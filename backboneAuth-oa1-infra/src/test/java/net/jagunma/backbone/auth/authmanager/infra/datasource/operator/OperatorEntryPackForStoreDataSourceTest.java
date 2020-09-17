package net.jagunma.backbone.auth.authmanager.infra.datasource.operator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPack;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntityDao;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntityDao;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntity;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityCriteria;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.beans.Beans;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OperatorEntryPackForStoreDataSourceTest {

    // 実行既定値
    private String operatorCode = "yu123456";
    private String operatorName = "オペレーター名";
    private String mailAddress = "test@den.jagunma.net";
    private LocalDate expirationStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate expirationEndDate = LocalDate.of(2020, 9, 30);
    private Long jaId = 6L;
    private String jaCode = "006";
    private Long tempoId = 1L;
    private String tempoCode = "001";
    private String changeCause = "新職員の入組による登録";
    private String password = "PaSsWoRd";
    private String confirmPassword = "PaSsWoRd";
    private OperatorEntryPack createOperatorEntryPack() {
        return OperatorEntryPack.createFrom(
        operatorCode,
        operatorName,
        mailAddress,
        expirationStartDate,
        expirationEndDate,
        jaId,
        jaCode,
        tempoId,
        tempoCode,
        changeCause,
        password,
        confirmPassword);
    }
    Long operatorId = 123456L;
    LocalDateTime createdAt = LocalDateTime.of(2020, 10, 31,1,2,3);
    Long operatorHistoryId = 234567L;
    Long passwordHistoryId = 345678L;

    // テスト対象クラス生成
    private OperatorEntryPackForStoreDataSource createOperatorEntryPackForStoreDataSource() {
        OperatorEntityDao operatorEntityDao = new OperatorEntityDao() {
            @Override
            public int countBy(OperatorEntityCriteria criteria) {
                if (!criteria.getOperatorCodeCriteria().getEqualTo().equals(operatorCode)) {
                    return 1;
                }
                return 0;
            }
            @Override
            public int insert(OperatorEntity entity) {
                entity.setOperatorId(operatorId);
                entity.setCreatedAt(createdAt);
                return 0;
            }
            @Override
            public List<OperatorEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public OperatorEntity findOneBy(OperatorEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<OperatorEntity> findBy(OperatorEntityCriteria criteria, Orders orders) {
                return null;
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

        OperatorHistoryHeaderEntityDao operatorHistoryHeaderEntityDao = new OperatorHistoryHeaderEntityDao() {
            @Override
            public int insert(OperatorHistoryHeaderEntity entity) {
                entity.setOperatorHistoryId(operatorHistoryId);
                return 0;
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
            public List<OperatorHistoryHeaderEntity> findBy(OperatorHistoryHeaderEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public int countBy(OperatorHistoryHeaderEntityCriteria criteria) {
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

        OperatorHistoryEntityDao operatorHistoryEntityDao = new OperatorHistoryEntityDao() {
            @Override
            public int insert(OperatorHistoryEntity entity) {
                return 0;
            }
            @Override
            public List<OperatorHistoryEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public OperatorHistoryEntity findOneBy(OperatorHistoryEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<OperatorHistoryEntity> findBy(OperatorHistoryEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public int countBy(OperatorHistoryEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int update(OperatorHistoryEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(OperatorHistoryEntity entity) {
                return 0;
            }
            @Override
            public int delete(OperatorHistoryEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(OperatorHistoryEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<OperatorHistoryEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<OperatorHistoryEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<OperatorHistoryEntity> entities) {
                return new int[0];
            }
        };

        PasswordHistoryEntityDao passwordHistoryEntityDao = new PasswordHistoryEntityDao() {
            @Override
            public int insert(PasswordHistoryEntity entity) {
                entity.setPasswordHistoryId(passwordHistoryId);
                return 0;
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
            public List<PasswordHistoryEntity> findBy(PasswordHistoryEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public int countBy(PasswordHistoryEntityCriteria criteria) {
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

        return new OperatorEntryPackForStoreDataSource(operatorEntityDao, operatorHistoryHeaderEntityDao, operatorHistoryEntityDao, passwordHistoryEntityDao);
    }

    /**
     * {@link OperatorEntryPackForStoreDataSource#insert(OperatorEntryPack)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void insert_test() {
        // テスト対象クラス生成
        OperatorEntryPackForStoreDataSource operatorEntryPackForStoreDataSource = createOperatorEntryPackForStoreDataSource();

        // 実行値
        OperatorEntryPack operatorEntryPack = createOperatorEntryPack();

        assertThatCode(() ->
            // 実行
            operatorEntryPackForStoreDataSource.insert(operatorEntryPack))
            .doesNotThrowAnyException();
    }

    /**
     * {@link OperatorEntryPackForStoreDataSource#checkAlreadyExists(String)}テスト
     *  ●パターン
     *    同(オペレーターコード)既存データあり(オペレーターコード:yu999999)
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkAlreadyExists_test() {
        // テスト対象クラス生成
        OperatorEntryPackForStoreDataSource operatorEntryPackForStoreDataSource = createOperatorEntryPackForStoreDataSource();

        // 実行値
        String alreadyExistsOperatorCode = "yu999999";

        assertThatThrownBy(() ->
            // 実行
            operatorEntryPackForStoreDataSource.checkAlreadyExists(alreadyExistsOperatorCode))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA11001");
                assertThat(e.getArgs()).containsSequence("オペレーターコード");
                assertThat(e.getArgs()).containsSequence(alreadyExistsOperatorCode);
            });
    }

    /**
     * {@link OperatorEntryPackForStoreDataSource#insertOperator(OperatorEntryPack)}テスト
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
        OperatorEntryPackForStoreDataSource operatorEntryPackForStoreDataSource = createOperatorEntryPackForStoreDataSource();

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
        expectedEntity.setIsDeviceAuth(false);
        expectedEntity.setJaId(jaId);
        expectedEntity.setJaCode(jaCode);
        expectedEntity.setTempoId(tempoId);
        expectedEntity.setTempoCode(tempoCode);
        expectedEntity.setAvailableStatus(AvailableStatus.利用可能.getCode());
        expectedEntity.setCreatedAt(createdAt);

        // 実行
        OperatorEntity operatorEntity = operatorEntryPackForStoreDataSource.insertOperator(operatorEntryPack);

        // 結果検証
        assertThat(operatorEntity).isEqualToComparingFieldByField(expectedEntity);
    }

    /**
     * {@link OperatorEntryPackForStoreDataSource#insertOperatorHistoryHeader(OperatorEntryPack, OperatorEntity)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Entityへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void insertOperatorHistoryHeader_test() {
        // テスト対象クラス生成
        OperatorEntryPackForStoreDataSource operatorEntryPackForStoreDataSource = createOperatorEntryPackForStoreDataSource();

        // 実行値
        OperatorEntryPack operatorEntryPack = createOperatorEntryPack();
        OperatorEntity operatorEntity = operatorEntryPackForStoreDataSource.insertOperator(operatorEntryPack);

        // 期待値
        OperatorHistoryHeaderEntity expectedEntity = new OperatorHistoryHeaderEntity();
        expectedEntity.setOperatorHistoryId(operatorHistoryId);
        expectedEntity.setOperatorId(operatorId);
        expectedEntity.setChangeDateTime(createdAt);
        expectedEntity.setChangeCause(changeCause);

        // 実行
        OperatorHistoryHeaderEntity operatorHistoryHeaderEntity = operatorEntryPackForStoreDataSource.insertOperatorHistoryHeader(operatorEntryPack, operatorEntity);

        // 結果検証
        assertThat(operatorHistoryHeaderEntity).isEqualToComparingFieldByField(expectedEntity);
    }

    /**
     * {@link OperatorEntryPackForStoreDataSource#insertOperatorHistory(OperatorHistoryHeaderEntity, OperatorEntity)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Entityへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void insertOperatorHistory_test() {
        // テスト対象クラス生成
        OperatorEntryPackForStoreDataSource operatorEntryPackForStoreDataSource = createOperatorEntryPackForStoreDataSource();

        // 実行値
        OperatorEntryPack operatorEntryPack = createOperatorEntryPack();
        OperatorEntity operatorEntity = operatorEntryPackForStoreDataSource.insertOperator(operatorEntryPack);
        OperatorHistoryHeaderEntity operatorHistoryHeaderEntity = operatorEntryPackForStoreDataSource.insertOperatorHistoryHeader(operatorEntryPack, operatorEntity);

        // 期待値
        OperatorHistoryEntity expectedEntity = Beans.createAndCopy(OperatorHistoryEntity.class, operatorEntity).execute();
        expectedEntity.setOperatorHistoryId(operatorHistoryId);

        // 実行
        OperatorHistoryEntity operatorHistoryEntity = operatorEntryPackForStoreDataSource.insertOperatorHistory(operatorHistoryHeaderEntity, operatorEntity);

        // 結果検証
        assertThat(operatorHistoryEntity).isEqualToComparingFieldByField(expectedEntity);
    }

    /**
     * {@link OperatorEntryPackForStoreDataSource#insertPasswordHistory(OperatorEntryPack, OperatorEntity)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Entityへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void insertPasswordHistory_test() {
        // テスト対象クラス生成
        OperatorEntryPackForStoreDataSource operatorEntryPackForStoreDataSource = createOperatorEntryPackForStoreDataSource();

        // 実行値
        OperatorEntryPack operatorEntryPack = createOperatorEntryPack();
        OperatorEntity operatorEntity = operatorEntryPackForStoreDataSource.insertOperator(operatorEntryPack);

        // 期待値
        PasswordHistoryEntity expectedEntity = new PasswordHistoryEntity();
        expectedEntity.setPasswordHistoryId(passwordHistoryId);
        expectedEntity.setOperatorId(operatorId);
        expectedEntity.setChangeDateTime(createdAt);
        expectedEntity.setPassword(password);
        expectedEntity.setChangeType(PasswordChangeType.初期.getCode());

        // 実行
        PasswordHistoryEntity passwordHistoryEntity = operatorEntryPackForStoreDataSource.insertPasswordHistory(operatorEntryPack, operatorEntity);

        // 結果検証
        assertThat(passwordHistoryEntity).isEqualToComparingFieldByField(expectedEntity);
    }
}