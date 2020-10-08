package net.jagunma.backbone.auth.authmanager.infra.datasource.operator;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorUpdatePack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntityDao;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntityDao;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRoleHistory.Operator_BizTranRoleHistoryEntity;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRoleHistory.Operator_BizTranRoleHistoryEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRoleHistory.Operator_BizTranRoleHistoryEntityDao;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistoryEntity;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistoryEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistoryEntityDao;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntity;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityCriteria;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.beans.Beans;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OperatorForStoreDataSourceTest {

    // 実行既定値
    private String operatorCode = "yu123456";
    private String operatorName = "オペレーター名";
    private String mailAddress = "test@den.jagunma.net";
    private LocalDate expirationStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate expirationEndDate = LocalDate.of(2020, 9, 30);
    private Long jaId = 6L;
    private String jaCode = "006";
    private Long branchId = 1L;
    private String branchCode = "001";
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
            branchId,
            branchCode,
            changeCause,
            password,
            confirmPassword);
    }
    private Long operatorHistoryId = 234567L;
    private Long passwordHistoryId = 345678L;
    private Long operatorId = 123456L;
    private Boolean isDeviceAuth = false;
    private LocalDateTime createdAt = LocalDateTime.of(2020, 10, 31,1,2,3);
    private Operator operator = Operator.createFrom(operatorId, operatorCode, operatorName, mailAddress, expirationStartDate, expirationEndDate, false, jaId, jaCode, branchId, branchCode, AvailableStatus.利用可能.getCode(), 191);
    private List<Operator_SubSystemRole> subSystemRoleList = newArrayList(
        Operator_SubSystemRole.createFrom(301L, operatorId, SubSystemRole.JA管理者.getCode(), expirationStartDate, expirationEndDate, 391, operator, SubSystemRole.JA管理者),
        Operator_SubSystemRole.createFrom(302L, operatorId, SubSystemRole.業務統括者_購買.getCode(), expirationStartDate, expirationEndDate, 392, operator, SubSystemRole.業務統括者_購買),
        Operator_SubSystemRole.createFrom(303L, operatorId, SubSystemRole.業務統括者_販売_青果.getCode(), expirationStartDate, expirationEndDate, 393, operator, SubSystemRole.業務統括者_販売_青果),
        Operator_SubSystemRole.createFrom(304L, operatorId, SubSystemRole.業務統括者_販売_米.getCode(), expirationStartDate, expirationEndDate, 394, operator, SubSystemRole.業務統括者_販売_米),
        Operator_SubSystemRole.createFrom(305L, operatorId, SubSystemRole.業務統括者_販売_畜産.getCode(), expirationStartDate, expirationEndDate, 395, operator, SubSystemRole.業務統括者_販売_畜産));
    private List<BizTranRole> bizTranRoleList = newArrayList(
        BizTranRole.createFrom(401L, "KB0000", "購買メインメニュー", SubSystem.購買.getCode(), SubSystem.購買),
        BizTranRole.createFrom(402L, "KB0001", "支所検索", SubSystem.購買.getCode(), SubSystem.購買),
        BizTranRole.createFrom(403L, "KB0002", "顧客検索", SubSystem.購買.getCode(), SubSystem.購買),
        BizTranRole.createFrom(404L, "YS0000", "野菜メインメニュー", SubSystem.販売_青果.getCode(), SubSystem.販売_青果));
    private List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList(
        Operator_BizTranRole.createFrom(501L, operatorId, bizTranRoleList.get(0).getBizTranRoleId(), expirationStartDate, expirationEndDate, operator, bizTranRoleList.get(0)),
        Operator_BizTranRole.createFrom(502L, operatorId, bizTranRoleList.get(1).getBizTranRoleId(), expirationStartDate, expirationEndDate, operator, bizTranRoleList.get(1)),
        Operator_BizTranRole.createFrom(503L, operatorId, bizTranRoleList.get(2).getBizTranRoleId(), expirationStartDate, expirationEndDate, operator, bizTranRoleList.get(2)),
        Operator_BizTranRole.createFrom(504L, operatorId, bizTranRoleList.get(3).getBizTranRoleId(), expirationStartDate, expirationEndDate, operator, bizTranRoleList.get(3)));
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
            AvailableStatus.利用可能,
            changeCause,
            subSystemRoleList,
            operator_BizTranRoleList
        );
    }

    // テスト対象クラス生成
    private OperatorForStoreDataSource createOperatorForStoreDataSource() {
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
        Operator_SubSystemRoleHistoryEntityDao operator_SubSystemRoleHistoryEntityDao = new Operator_SubSystemRoleHistoryEntityDao() {
            @Override
            public List<Operator_SubSystemRoleHistoryEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public Operator_SubSystemRoleHistoryEntity findOneBy(
                Operator_SubSystemRoleHistoryEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<Operator_SubSystemRoleHistoryEntity> findBy(
                Operator_SubSystemRoleHistoryEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public int countBy(Operator_SubSystemRoleHistoryEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(Operator_SubSystemRoleHistoryEntity entity) {
                return 0;
            }
            @Override
            public int update(Operator_SubSystemRoleHistoryEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(Operator_SubSystemRoleHistoryEntity entity) {
                return 0;
            }
            @Override
            public int delete(Operator_SubSystemRoleHistoryEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(Operator_SubSystemRoleHistoryEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<Operator_SubSystemRoleHistoryEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<Operator_SubSystemRoleHistoryEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<Operator_SubSystemRoleHistoryEntity> entities) {
                return new int[0];
            }
        };
        Operator_BizTranRoleHistoryEntityDao operator_BizTranRoleHistoryEntityDao = new Operator_BizTranRoleHistoryEntityDao() {
            @Override
            public List<Operator_BizTranRoleHistoryEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public Operator_BizTranRoleHistoryEntity findOneBy(
                Operator_BizTranRoleHistoryEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<Operator_BizTranRoleHistoryEntity> findBy(
                Operator_BizTranRoleHistoryEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public int countBy(Operator_BizTranRoleHistoryEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(Operator_BizTranRoleHistoryEntity entity) {
                return 0;
            }
            @Override
            public int update(Operator_BizTranRoleHistoryEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(Operator_BizTranRoleHistoryEntity entity) {
                return 0;
            }
            @Override
            public int delete(Operator_BizTranRoleHistoryEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(Operator_BizTranRoleHistoryEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<Operator_BizTranRoleHistoryEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<Operator_BizTranRoleHistoryEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<Operator_BizTranRoleHistoryEntity> entities) {
                return new int[0];
            }
        };
        Operator_SubSystemRolesRepository operator_SubSystemRolesRepository = new Operator_SubSystemRolesRepository() {
            @Override
            public Operator_SubSystemRoles selectBy(
                Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria, Orders orders) {
                return null;
            }
        };
        Operator_BizTranRolesRepository operator_BizTranRolesRepository = new Operator_BizTranRolesRepository() {
            @Override
            public Operator_BizTranRoles selectBy(
                Operator_BizTranRoleCriteria operator_BizTranRoleCriteria, Orders orders) {
                return null;
            }
        };

        return new OperatorForStoreDataSource(operatorEntityDao,
            operatorHistoryHeaderEntityDao,
            operatorHistoryEntityDao,
            passwordHistoryEntityDao,
            operator_SubSystemRoleHistoryEntityDao,
            operator_BizTranRoleHistoryEntityDao,
            operator_SubSystemRolesRepository,
            operator_BizTranRolesRepository);
    }

    /**
     * {@link OperatorForStoreDataSource#entry(OperatorEntryPack)}テスト
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
     * {@link OperatorForStoreDataSource#update(OperatorUpdatePack)}テスト
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
        OperatorUpdatePack operatorUpdatePack = createOperatorUpdatePack();

        assertThatCode(() ->
            // 実行
            operatorForStoreDataSource.update(operatorUpdatePack))
            .doesNotThrowAnyException();
    }

    /**
     * {@link OperatorForStoreDataSource#checkAlreadyExists(String)}テスト
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
     * {@link OperatorForStoreDataSource#insertOperator(OperatorEntryPack)}テスト
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
        expectedEntity.setIsDeviceAuth(false);
        expectedEntity.setJaId(jaId);
        expectedEntity.setJaCode(jaCode);
        expectedEntity.setBranchId(branchId);
        expectedEntity.setBranchCode(branchCode);
        expectedEntity.setAvailableStatus(AvailableStatus.利用可能.getCode());
        expectedEntity.setCreatedAt(createdAt);

        // 実行
        OperatorEntity operatorEntity = operatorForStoreDataSource.insertOperator(operatorEntryPack);

        // 結果検証
        assertThat(operatorEntity).usingRecursiveComparison().isEqualTo(expectedEntity);
    }

    /**
     * {@link OperatorForStoreDataSource#insertOperatorHistoryHeader(OperatorEntity, String)}テスト
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
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // 実行値
        OperatorEntryPack operatorEntryPack = createOperatorEntryPack();
        OperatorEntity operatorEntity = operatorForStoreDataSource.insertOperator(operatorEntryPack);

        // 期待値
        OperatorHistoryHeaderEntity expectedEntity = new OperatorHistoryHeaderEntity();
        expectedEntity.setOperatorHistoryId(operatorHistoryId);
        expectedEntity.setOperatorId(operatorId);
        expectedEntity.setChangeDateTime(createdAt);
        expectedEntity.setChangeCause(changeCause);

        // 実行
        OperatorHistoryHeaderEntity operatorHistoryHeaderEntity = operatorForStoreDataSource.insertOperatorHistoryHeader(operatorEntity, operatorEntryPack.getChangeCause());

        // 結果検証
        assertThat(operatorHistoryHeaderEntity).usingRecursiveComparison().isEqualTo(expectedEntity);
    }

    /**
     * {@link OperatorForStoreDataSource#insertOperatorHistory(OperatorHistoryHeaderEntity, OperatorEntity)}テスト
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
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // 実行値
        OperatorEntryPack operatorEntryPack = createOperatorEntryPack();
        OperatorEntity operatorEntity = operatorForStoreDataSource.insertOperator(operatorEntryPack);
        OperatorHistoryHeaderEntity operatorHistoryHeaderEntity = operatorForStoreDataSource.insertOperatorHistoryHeader(operatorEntity, operatorEntryPack.getChangeCause());

        // 期待値
        OperatorHistoryEntity expectedEntity = Beans.createAndCopy(OperatorHistoryEntity.class, operatorEntity).execute();
        expectedEntity.setOperatorHistoryId(operatorHistoryId);

        // 実行
        OperatorHistoryEntity operatorHistoryEntity = operatorForStoreDataSource.insertOperatorHistory(operatorHistoryHeaderEntity, operatorEntity);

        // 結果検証
        assertThat(operatorHistoryEntity).usingRecursiveComparison().isEqualTo(expectedEntity);
    }

    /**
     * {@link OperatorForStoreDataSource#insertPasswordHistory(OperatorEntryPack, OperatorEntity)}テスト
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
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // 実行値
        OperatorEntryPack operatorEntryPack = createOperatorEntryPack();
        OperatorEntity operatorEntity = operatorForStoreDataSource.insertOperator(operatorEntryPack);

        // 期待値
        PasswordHistoryEntity expectedEntity = new PasswordHistoryEntity();
        expectedEntity.setPasswordHistoryId(passwordHistoryId);
        expectedEntity.setOperatorId(operatorId);
        expectedEntity.setChangeDateTime(createdAt);
        expectedEntity.setPassword(password);
        expectedEntity.setChangeType(PasswordChangeType.初期.getCode());

        // 実行
        PasswordHistoryEntity passwordHistoryEntity = operatorForStoreDataSource.insertPasswordHistory(operatorEntryPack, operatorEntity);

        // 結果検証
        assertThat(passwordHistoryEntity).usingRecursiveComparison().isEqualTo(expectedEntity);
    }
}
