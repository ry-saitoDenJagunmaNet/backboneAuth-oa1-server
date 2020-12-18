package net.jagunma.backbone.auth.authmanager.infra.datasource.operator_BizTranRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static net.jagunma.common.util.objects2.Objects2.toStringHelper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.OperatorHistoryPackRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Operator_BizTranRoleForStoreDataSourceTest {

    // 実行既定値
    private Long operator_BizTranRoleId = 1L;
    private Long operatorId = 123456L;
    private Integer recordVersion = 1;
    private List<BizTranRole> bizTranRoleList = newArrayList(
        BizTranRole.createFrom(401L, "KBAG01", "（購買）購買業務基本", SubSystem.購買.getCode(), recordVersion, SubSystem.購買),
        BizTranRole.createFrom(402L, "YSAG19", "（青果）管理者（仕切実績修正）", SubSystem.販売_青果.getCode(), recordVersion, SubSystem.販売_青果),
        BizTranRole.createFrom(403L, "HKAG10", "（米）ＪＡ取引全般", SubSystem.販売_米.getCode(), recordVersion, SubSystem.販売_米),
        BizTranRole.createFrom(404L, "ANAG01", "（畜産）取引全般", SubSystem.販売_畜産.getCode(), recordVersion, SubSystem.販売_畜産));
    private List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList(
        Operator_BizTranRole.createFrom(501L, operatorId, bizTranRoleList.get(0).getBizTranRoleId(), LocalDate.of(2020, 9, 1), LocalDate.of(2020, 9, 21), recordVersion, null, bizTranRoleList.get(0)),
        Operator_BizTranRole.createFrom(502L, operatorId, bizTranRoleList.get(1).getBizTranRoleId(), LocalDate.of(2020, 9, 2), LocalDate.of(2020, 9, 22), recordVersion, null, bizTranRoleList.get(1)),
        Operator_BizTranRole.createFrom(503L, operatorId, bizTranRoleList.get(2).getBizTranRoleId(), LocalDate.of(2020, 9, 3), LocalDate.of(2020, 9, 23), recordVersion, null, bizTranRoleList.get(2)),
        Operator_BizTranRole.createFrom(504L, operatorId, bizTranRoleList.get(3).getBizTranRoleId(), LocalDate.of(2020, 9, 4), LocalDate.of(2020, 9, 24), recordVersion, null, bizTranRoleList.get(3)));
    private Operator_BizTranRoles operator_BizTranRoles = Operator_BizTranRoles.createFrom(operator_BizTranRoleList);
    private String changeCause = "（青果）管理者（仕切実績修正）を担当";
    private Long createdBy = 100L;
    private LocalDateTime createdAt = LocalDateTime.of(2020, 10, 30,1,2,3);
    private String createdIpAddress = "100.100.100.100";

    // 検証値
    Operator_BizTranRoleEntityCriteria actualOperator_BizTranRoleEntityCriteria;

    // テスト対象クラス生成
    private Operator_BizTranRoleForStoreDataSource createOperator_BizTranRoleForStoreDataSource() {
        Operator_BizTranRoleEntityDao operator_BizTranRoleEntityDao = new Operator_BizTranRoleEntityDao() {
            @Override
            public List<Operator_BizTranRoleEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public Operator_BizTranRoleEntity findOneBy(Operator_BizTranRoleEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<Operator_BizTranRoleEntity> findBy(Operator_BizTranRoleEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public int countBy(Operator_BizTranRoleEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(Operator_BizTranRoleEntity entity) {
                entity.setOperator_BizTranRoleId(operator_BizTranRoleId++);
                entity.setCreatedBy(createdBy);
                entity.setCreatedAt(createdAt);
                entity.setCreatedIpAddress(createdIpAddress);
                entity.setRecordVersion(recordVersion);
                return 0;
            }
            @Override
            public int update(Operator_BizTranRoleEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(Operator_BizTranRoleEntity entity) {
                return 0;
            }
            @Override
            public int delete(Operator_BizTranRoleEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(Operator_BizTranRoleEntityCriteria criteria) {
                actualOperator_BizTranRoleEntityCriteria = criteria;
                return 0;
            }
            @Override
            public int[] insertBatch(List<Operator_BizTranRoleEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<Operator_BizTranRoleEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<Operator_BizTranRoleEntity> entities) {
                return new int[0];
            }
        };
        OperatorHistoryPackRepositoryForStore operatorHistoryPackRepositoryForStore = new OperatorHistoryPackRepositoryForStore() {
            @Override
            public void store(Long operatorId, LocalDateTime changeDateTime, String changeCause) {
            }
        };

        return new Operator_BizTranRoleForStoreDataSource(
            operator_BizTranRoleEntityDao,
            operatorHistoryPackRepositoryForStore);
    }

    /**
     * {@link Operator_BizTranRoleForStoreDataSource#store(Operator_BizTranRoles operator_BizTranRoles, String changeCause)}テスト
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
        Operator_BizTranRoleForStoreDataSource operator_BizTranRoleForStoreDataSource = createOperator_BizTranRoleForStoreDataSource();

        assertThatCode(() ->
            // 実行
            operator_BizTranRoleForStoreDataSource.store(operator_BizTranRoles, changeCause))
            .doesNotThrowAnyException();
    }

    /**
     * {@link Operator_BizTranRoleForStoreDataSource#deleteOperator_BizTranRole(Long operatorId)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Criteriaへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void deleteOperator_BizTranRole_test() {
        // テスト対象クラス生成
        Operator_BizTranRoleForStoreDataSource operator_BizTranRoleForStoreDataSource = createOperator_BizTranRoleForStoreDataSource();

        // 期待値
        Operator_BizTranRoleEntityCriteria expectedOperator_BizTranRoleEntityCriteria = new Operator_BizTranRoleEntityCriteria();
        expectedOperator_BizTranRoleEntityCriteria.getOperatorIdCriteria().setEqualTo(operatorId);

        // 実行
        operator_BizTranRoleForStoreDataSource.deleteOperator_BizTranRole(operatorId);

        // 結果検証 // Todo:継承元のメソッド追加後要修正
        assertThat(toStringHelper(actualOperator_BizTranRoleEntityCriteria).defaultConfig().toString()).isEqualTo(toStringHelper(expectedOperator_BizTranRoleEntityCriteria).defaultConfig().toString());
    }

    /**
     * {@link Operator_BizTranRoleForStoreDataSource#insertOperator_BizTranRole(Operator_BizTranRoles operator_BizTranRoles)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Entityへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void insertOperator_BizTranRole_test() {
        // テスト対象クラス生成
        Operator_BizTranRoleForStoreDataSource operator_BizTranRoleForStoreDataSource = createOperator_BizTranRoleForStoreDataSource();

        // 期待値
        List<Operator_BizTranRoleEntity> expectedEntityList = newArrayList();
        for (Operator_BizTranRole operator_BizTranRole : operator_BizTranRoles.getValues()) {
            Operator_BizTranRoleEntity expectedEntity = new Operator_BizTranRoleEntity();

            expectedEntity.setOperator_BizTranRoleId(operator_BizTranRoleId++);
            expectedEntity.setOperatorId(operator_BizTranRole.getOperatorId());
            expectedEntity.setBizTranRoleId(operator_BizTranRole.getBizTranRoleId());
            expectedEntity.setValidThruStartDate(operator_BizTranRole.getValidThruStartDate());
            expectedEntity.setValidThruEndDate(operator_BizTranRole.getValidThruEndDate());
            expectedEntity.setCreatedBy(createdBy);
            expectedEntity.setCreatedAt(createdAt);
            expectedEntity.setCreatedIpAddress(createdIpAddress);
            expectedEntity.setUpdatedBy(null);
            expectedEntity.setUpdatedAt(null);
            expectedEntity.setUpdatedIpAddress(null);
            expectedEntity.setRecordVersion(recordVersion);

            expectedEntityList.add(expectedEntity);
        }
        operator_BizTranRoleId = 1L;

        // 実行
        List<Operator_BizTranRoleEntity> operator_BizTranRoleEntityList = operator_BizTranRoleForStoreDataSource.insertOperator_BizTranRole(operator_BizTranRoles);

        // 結果検証
        assertThat(operator_BizTranRoleEntityList).usingRecursiveComparison().isEqualTo(expectedEntityList);
    }
}
