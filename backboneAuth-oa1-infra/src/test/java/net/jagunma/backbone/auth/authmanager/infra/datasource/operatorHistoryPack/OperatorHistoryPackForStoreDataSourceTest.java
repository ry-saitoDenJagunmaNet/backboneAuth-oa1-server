package net.jagunma.backbone.auth.authmanager.infra.datasource.operatorHistoryPack;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
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
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OperatorHistoryPackForStoreDataSourceTest {

    // オペレーター系
    private Long operatorId = 123456L;
    private String operatorCode = "yu123456";
    private String operatorName = "オペレーター名";
    private String mailAddress = "test@den.jagunma.net";
    private LocalDate validThruStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate validThruEndDate = LocalDate.of(2020, 9, 30);
    private Boolean isDeviceAuth = false;
    private Long jaId = 6L;
    private String jaCode = "006";
    private Long branchId = 1L;
    private String branchCode = "001";
    private AvailableStatus availableStatus = AvailableStatus.利用可能;
    private LocalDateTime createdAt = LocalDateTime.of(2020, 10, 30,1,2,3);
    private Integer recordVersion = 1;

    // オペレーター履歴ヘッダー系
    private Long operatorHistoryId = 234567L;
    private String changeCause = "新職員の入組による登録";

    // オペレーター_サブシステムロール割当履歴系
    private List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList(
        Operator_SubSystemRole.createFrom(301L, operatorId, SubSystemRole.JA管理者.getCode(), validThruStartDate, validThruEndDate, 391, null, SubSystemRole.JA管理者),
        Operator_SubSystemRole.createFrom(302L, operatorId, SubSystemRole.業務統括者_購買.getCode(), validThruStartDate, validThruEndDate, 392, null, SubSystemRole.業務統括者_購買),
        Operator_SubSystemRole.createFrom(303L, operatorId, SubSystemRole.業務統括者_販売_青果.getCode(), validThruStartDate, validThruEndDate, 393, null, SubSystemRole.業務統括者_販売_青果),
        Operator_SubSystemRole.createFrom(304L, operatorId, SubSystemRole.業務統括者_販売_米.getCode(), validThruStartDate, validThruEndDate, 394, null, SubSystemRole.業務統括者_販売_米),
        Operator_SubSystemRole.createFrom(305L, operatorId, SubSystemRole.業務統括者_販売_畜産.getCode(), validThruStartDate, validThruEndDate, 395, null, SubSystemRole.業務統括者_販売_畜産));
    private Operator_SubSystemRoles operator_SubSystemRoles = Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);

    // オペレーター_取引ロール割当履歴系
    private List<BizTranRole> bizTranRoleList = newArrayList(
        BizTranRole.createFrom(401L, "KBAG01", "（購買）購買業務基本", SubSystem.購買.getCode(), recordVersion, SubSystem.購買),
        BizTranRole.createFrom(402L, "YSAG19", "（青果）管理者（仕切実績修正）", SubSystem.販売_青果.getCode(), recordVersion, SubSystem.販売_青果),
        BizTranRole.createFrom(403L, "HKAG10", "（米）ＪＡ取引全般", SubSystem.販売_米.getCode(), recordVersion, SubSystem.販売_米),
        BizTranRole.createFrom(404L, "ANAG01", "（畜産）取引全般", SubSystem.販売_畜産.getCode(), recordVersion, SubSystem.販売_畜産));
    private List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList(
        Operator_BizTranRole.createFrom(501L, operatorId, bizTranRoleList.get(0).getBizTranRoleId(), validThruStartDate, validThruEndDate, recordVersion, null, bizTranRoleList.get(0)),
        Operator_BizTranRole.createFrom(502L, operatorId, bizTranRoleList.get(1).getBizTranRoleId(), validThruStartDate, validThruEndDate, recordVersion, null, bizTranRoleList.get(1)),
        Operator_BizTranRole.createFrom(503L, operatorId, bizTranRoleList.get(2).getBizTranRoleId(), validThruStartDate, validThruEndDate, recordVersion, null, bizTranRoleList.get(2)),
        Operator_BizTranRole.createFrom(504L, operatorId, bizTranRoleList.get(3).getBizTranRoleId(), validThruStartDate, validThruEndDate, recordVersion, null, bizTranRoleList.get(3)));
    private Operator_BizTranRoles operator_BizTranRoles = Operator_BizTranRoles.createFrom(operator_BizTranRoleList);

    // 検証値
    Long actualOperatorId;
    Operator_SubSystemRoleCriteria actualOperator_SubSystemRoleCriteria;
    Orders actualOperator_SubSystemRoleOrders;
    Operator_BizTranRoleCriteria actualOperator_BizTranRoleCriteria;
    Orders actualOperator_BizTranRoleOrders;

    // テスト対象クラス生成
    private OperatorHistoryPackForStoreDataSource createOperatorHistoryPackForStoreDataSource() {
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
            public int insert(OperatorHistoryEntity entity) {
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
        Operator_SubSystemRoleHistoryEntityDao operator_SubSystemRoleHistoryEntityDao = new Operator_SubSystemRoleHistoryEntityDao() {
            @Override
            public List<Operator_SubSystemRoleHistoryEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public Operator_SubSystemRoleHistoryEntity findOneBy(Operator_SubSystemRoleHistoryEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<Operator_SubSystemRoleHistoryEntity> findBy(Operator_SubSystemRoleHistoryEntityCriteria criteria, Orders orders) {
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
            public Operator_BizTranRoleHistoryEntity findOneBy(Operator_BizTranRoleHistoryEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<Operator_BizTranRoleHistoryEntity> findBy(Operator_BizTranRoleHistoryEntityCriteria criteria, Orders orders) {
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
        OperatorRepository operatorRepository = new OperatorRepository() {
            @Override
            public Operator findOneById(Long operatorId) {
                actualOperatorId = operatorId;
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
                    recordVersion,
                    null);
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
            @Override
            public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
                return null;
            }
        };
        Operator_SubSystemRoleRepository operator_SubSystemRoleRepository = new Operator_SubSystemRoleRepository() {
            @Override
            public Operator_SubSystemRoles selectBy(Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria, Orders orders) {
                actualOperator_SubSystemRoleCriteria = operator_SubSystemRoleCriteria;
                actualOperator_SubSystemRoleOrders = orders;
                return operator_SubSystemRoles;
            }
        };
        Operator_BizTranRoleRepository operator_BizTranRoleRepository = new Operator_BizTranRoleRepository() {
            @Override
            public Operator_BizTranRoles selectBy(Operator_BizTranRoleCriteria operator_BizTranRoleCriteria, Orders orders) {
                actualOperator_BizTranRoleCriteria = operator_BizTranRoleCriteria;
                actualOperator_BizTranRoleOrders = orders;
                return operator_BizTranRoles;
            }
        };

        return new OperatorHistoryPackForStoreDataSource(
            operatorHistoryHeaderEntityDao,
            operatorHistoryEntityDao,
            operator_SubSystemRoleHistoryEntityDao,
            operator_BizTranRoleHistoryEntityDao,
            operatorRepository,
            operator_SubSystemRoleRepository,
            operator_BizTranRoleRepository);
    }

    /**
     * {@link OperatorHistoryPackForStoreDataSource#store(Long operatorId, LocalDateTime changeDateTime, String changeCause)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void store_test() {
        // テスト対象クラス生成
        OperatorHistoryPackForStoreDataSource operatorHistoryPackForStoreDataSource = createOperatorHistoryPackForStoreDataSource();

        assertThatCode(() ->
            // 実行
            operatorHistoryPackForStoreDataSource.store(operatorId, createdAt, changeCause))
            .doesNotThrowAnyException();
    }

    /**
     * {@link OperatorHistoryPackForStoreDataSource#getOperator(Long operatorId)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Modelへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void getOperator_test() {
        // テスト対象クラス生成
        OperatorHistoryPackForStoreDataSource operatorHistoryPackForStoreDataSource = createOperatorHistoryPackForStoreDataSource();

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
            availableStatus,
            recordVersion,
            null);
        Long expectedOperatorId = operatorId;

        // 実行
        Operator operator = operatorHistoryPackForStoreDataSource.getOperator(operatorId);

        // 結果検証
        assertThat(operator).usingRecursiveComparison().isEqualTo(expectedOperator);
        assertThat(actualOperatorId).isEqualTo(expectedOperatorId);
    }

    /**
     * {@link OperatorHistoryPackForStoreDataSource#insertOperatorHistoryHeader(Long operatorId, LocalDateTime changeDateTime, String changeCause)}テスト
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
        OperatorHistoryPackForStoreDataSource operatorHistoryPackForStoreDataSource = createOperatorHistoryPackForStoreDataSource();

        // 期待値
        OperatorHistoryHeaderEntity expectedEntity = new OperatorHistoryHeaderEntity();
        expectedEntity.setOperatorHistoryId(operatorHistoryId);
        expectedEntity.setOperatorId(operatorId);
        expectedEntity.setChangeDateTime(createdAt);
        expectedEntity.setChangeCause(changeCause);

        // 実行
        OperatorHistoryHeaderEntity operatorHistoryHeaderEntity = operatorHistoryPackForStoreDataSource.insertOperatorHistoryHeader(operatorId, createdAt, changeCause);

        // 結果検証
        assertThat(operatorHistoryHeaderEntity).usingRecursiveComparison().isEqualTo(expectedEntity);
    }

    /**
     * {@link OperatorHistoryPackForStoreDataSource#insertOperatorHistory(Long operatorHistoryId, Operator operator)}テスト
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
        OperatorHistoryPackForStoreDataSource operatorHistoryPackForStoreDataSource = createOperatorHistoryPackForStoreDataSource();

        // 実行値
        Operator operator = Operator.createFrom(
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
            recordVersion,
            null);

        // 期待値
        OperatorHistoryEntity expectedEntity = new OperatorHistoryEntity();
        expectedEntity.setOperatorHistoryId(operatorHistoryId);
        expectedEntity.setOperatorId(operator.getOperatorId());
        expectedEntity.setOperatorCode(operator.getOperatorCode());
        expectedEntity.setOperatorName(operator.getOperatorName());
        expectedEntity.setMailAddress(operator.getMailAddress());
        expectedEntity.setValidThruStartDate(operator.getValidThruStartDate());
        expectedEntity.setValidThruEndDate(operator.getValidThruEndDate());
        expectedEntity.setIsDeviceAuth(operator.getIsDeviceAuth());
        expectedEntity.setJaId(operator.getJaId());
        expectedEntity.setJaCode(operator.getJaCode());
        expectedEntity.setBranchId(operator.getBranchId());
        expectedEntity.setBranchCode(operator.getBranchCode());
        expectedEntity.setAvailableStatus(operator.getAvailableStatus().getCode());

        // 実行
        OperatorHistoryEntity operatorHistoryEntity = operatorHistoryPackForStoreDataSource.insertOperatorHistory(operatorHistoryId, operator);

        // 結果検証
        assertThat(operatorHistoryEntity).usingRecursiveComparison().isEqualTo(expectedEntity);
    }

    /**
     * {@link OperatorHistoryPackForStoreDataSource#insertOperator_SubSystemRoleHistory(Long operatorHistoryId, Long operatorId)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Entityへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void insertOperator_SubSystemRoleHistory_test() {
        // テスト対象クラス生成
        OperatorHistoryPackForStoreDataSource operatorHistoryPackForStoreDataSource = createOperatorHistoryPackForStoreDataSource();

        // 期待値
        List<Operator_SubSystemRoleHistoryEntity> expectedEntityList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : operator_SubSystemRoles.getValues()) {
            Operator_SubSystemRoleHistoryEntity expectedEntity = new Operator_SubSystemRoleHistoryEntity();
            expectedEntity.setOperatorHistoryId(operatorHistoryId);

            expectedEntity.setOperator_SubSystemRoleId(operator_SubSystemRole.getOperator_SubSystemRoleId());
            expectedEntity.setOperatorId(operator_SubSystemRole.getOperatorId());
            expectedEntity.setSubSystemRoleCode(operator_SubSystemRole.getSubSystemRoleCode());
            expectedEntity.setValidThruStartDate(operator_SubSystemRole.getValidThruStartDate());
            expectedEntity.setValidThruEndDate(operator_SubSystemRole.getValidThruEndDate());

            expectedEntityList.add(expectedEntity);
        }
        Operator_SubSystemRoleCriteria expectedOperator_SubSystemRoleCriteria = new Operator_SubSystemRoleCriteria();
        expectedOperator_SubSystemRoleCriteria.getOperatorIdCriteria().setEqualTo(operatorId);
        Orders expectedOperator_SubSystemRoleOrders = Orders.empty().addOrder("operator_SubSystemRoleId");

        // 実行
        List<Operator_SubSystemRoleHistoryEntity> operator_SubSystemRoleHistoryEntityList = operatorHistoryPackForStoreDataSource.insertOperator_SubSystemRoleHistory(operatorHistoryId, operatorId);

        // 結果検証
        assertThat(operator_SubSystemRoleHistoryEntityList).usingRecursiveComparison().isEqualTo(expectedEntityList);
        assertThat(actualOperator_SubSystemRoleCriteria.toString()).isEqualTo(expectedOperator_SubSystemRoleCriteria.toString());
        assertThat(actualOperator_SubSystemRoleOrders).usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoleOrders);
    }

    /**
     * {@link OperatorHistoryPackForStoreDataSource#insertOperator_BizTranRoleHistory(Long operatorHistoryId, Long operatorId)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Entityへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void insertOperator_BizTranRoleHistory_test() {
        // テスト対象クラス生成
        OperatorHistoryPackForStoreDataSource operatorHistoryPackForStoreDataSource = createOperatorHistoryPackForStoreDataSource();

        // 期待値
        List<Operator_BizTranRoleHistoryEntity> expectedEntityList = newArrayList();
        for (Operator_BizTranRole operator_BizTranRole : operator_BizTranRoles.getValues()) {
            Operator_BizTranRoleHistoryEntity expectedEntity = new Operator_BizTranRoleHistoryEntity();
            expectedEntity.setOperatorHistoryId(operatorHistoryId);

            expectedEntity.setOperator_BizTranRoleId(operator_BizTranRole.getOperator_BizTranRoleId());
            expectedEntity.setOperatorId(operator_BizTranRole.getOperatorId());
            expectedEntity.setBizTranRoleId(operator_BizTranRole.getBizTranRoleId());
            expectedEntity.setValidThruStartDate(operator_BizTranRole.getValidThruStartDate());
            expectedEntity.setValidThruEndDate(operator_BizTranRole.getValidThruEndDate());

            expectedEntityList.add(expectedEntity);
        }
        Operator_BizTranRoleCriteria expectedOperator_BizTranRoleCriteria = new Operator_BizTranRoleCriteria();
        expectedOperator_BizTranRoleCriteria.getOperatorIdCriteria().setEqualTo(operatorId);
        Orders expectedOperator_BizTranRoleOrders = Orders.empty().addOrder("operator_BizTranRoleId");

        // 実行
        List<Operator_BizTranRoleHistoryEntity> Operator_BizTranRoleHistoryEntityList = operatorHistoryPackForStoreDataSource.insertOperator_BizTranRoleHistory(operatorHistoryId, operatorId);

        // 結果検証
        assertThat(Operator_BizTranRoleHistoryEntityList).usingRecursiveComparison().isEqualTo(expectedEntityList);
        assertThat(actualOperator_BizTranRoleCriteria.toString()).isEqualTo(expectedOperator_BizTranRoleCriteria.toString());
        assertThat(actualOperator_BizTranRoleOrders).usingRecursiveComparison().isEqualTo(expectedOperator_BizTranRoleOrders);
    }
}