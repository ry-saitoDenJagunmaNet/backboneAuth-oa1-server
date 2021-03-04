package net.jagunma.backbone.auth.authmanager.infra.datasource.operator_SubSystemRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.OperatorHistoryPackRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntity;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Operator_SubSystemRoleForStoreDataSourceTest {

    // 実行既定値
    private Long operator_SubSystemRoleId = 1L;
    private Long operatorId = 123456L;
    private List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList(
        Operator_SubSystemRole.createFrom(301L, operatorId, SubSystemRole.JA管理者.getCode(), LocalDate.of(2020, 9, 1), LocalDate.of(2020, 9, 21), 391, null, SubSystemRole.JA管理者),
        Operator_SubSystemRole.createFrom(302L, operatorId, SubSystemRole.業務統括者_購買.getCode(), LocalDate.of(2020, 9, 2), LocalDate.of(2020, 9, 22), 392, null, SubSystemRole.業務統括者_購買),
        Operator_SubSystemRole.createFrom(303L, operatorId, SubSystemRole.業務統括者_販売_青果.getCode(), LocalDate.of(2020, 9, 3), LocalDate.of(2020, 9, 23), 393, null, SubSystemRole.業務統括者_販売_青果),
        Operator_SubSystemRole.createFrom(304L, operatorId, SubSystemRole.業務統括者_販売_米.getCode(), LocalDate.of(2020, 9, 4), LocalDate.of(2020, 9, 24), 394, null, SubSystemRole.業務統括者_販売_米),
        Operator_SubSystemRole.createFrom(305L, operatorId, SubSystemRole.業務統括者_販売_畜産.getCode(), LocalDate.of(2020, 9, 5), LocalDate.of(2020, 9, 25), 395, null, SubSystemRole.業務統括者_販売_畜産));
    private Operator_SubSystemRoles operator_SubSystemRoles = Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);
    private String changeCause = "業務統括者（販売・青果）に昇格";
    private Long createdBy = 100L;
    private LocalDateTime createdAt = LocalDateTime.of(2020, 10, 30,1,2,3);
    private String createdIpAddress = "100.100.100.100";
    private Integer recordVersion = 1;

    // 検証値
    private Operator_SubSystemRoleEntityCriteria actualOperator_SubSystemRoleEntityCriteria;

    // テスト対象クラス生成
    private Operator_SubSystemRoleForStoreDataSource createOperator_SubSystemRoleForStoreDataSource() {
        Operator_SubSystemRoleEntityDao operator_SubSystemRoleEntityDao = new Operator_SubSystemRoleEntityDao() {
            @Override
            public List<Operator_SubSystemRoleEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public Operator_SubSystemRoleEntity findOneBy(Operator_SubSystemRoleEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<Operator_SubSystemRoleEntity> findBy(Operator_SubSystemRoleEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public int countBy(Operator_SubSystemRoleEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(Operator_SubSystemRoleEntity entity) {
                entity.setOperator_SubSystemRoleId(operator_SubSystemRoleId++);
                entity.setCreatedBy(createdBy);
                entity.setCreatedAt(createdAt);
                entity.setCreatedIpAddress(createdIpAddress);
                entity.setRecordVersion(recordVersion);
                return 0;
            }
            @Override
            public int update(Operator_SubSystemRoleEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(Operator_SubSystemRoleEntity entity) {
                return 0;
            }
            @Override
            public int delete(Operator_SubSystemRoleEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(Operator_SubSystemRoleEntityCriteria criteria) {
                actualOperator_SubSystemRoleEntityCriteria = criteria;
                return 0;
            }
            @Override
            public int[] insertBatch(List<Operator_SubSystemRoleEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<Operator_SubSystemRoleEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<Operator_SubSystemRoleEntity> entities) {
                return new int[0];
            }
        };
        OperatorHistoryPackRepositoryForStore operatorHistoryPackRepositoryForStore = new OperatorHistoryPackRepositoryForStore() {
            @Override
            public void store(Long operatorId, LocalDateTime changeDateTime, String changeCause) {
            }
        };

        return new Operator_SubSystemRoleForStoreDataSource(
            operator_SubSystemRoleEntityDao,
            operatorHistoryPackRepositoryForStore);
    }

    /**
     * {@link Operator_SubSystemRoleForStoreDataSource#store(Long operatorId, Operator_SubSystemRoles operator_SubSystemRoles, String changeCause)}テスト
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
        Operator_SubSystemRoleForStoreDataSource operator_SubSystemRoleForStoreDataSource = createOperator_SubSystemRoleForStoreDataSource();

        assertThatCode(() ->
            // 実行
            operator_SubSystemRoleForStoreDataSource.store(operatorId, operator_SubSystemRoles, changeCause))
            .doesNotThrowAnyException();
    }

    /**
     * {@link Operator_SubSystemRoleForStoreDataSource#deleteOperator_SubSystemRole(Long operatorId)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Criteriaへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void deleteOperator_SubSystemRole_test() {
        // テスト対象クラス生成
        Operator_SubSystemRoleForStoreDataSource operator_SubSystemRoleForStoreDataSource = createOperator_SubSystemRoleForStoreDataSource();

        // 期待値
        Operator_SubSystemRoleEntityCriteria expectedOperator_SubSystemRoleEntityCriteria = new Operator_SubSystemRoleEntityCriteria();
        expectedOperator_SubSystemRoleEntityCriteria.getOperatorIdCriteria().setEqualTo(operatorId);

        // 実行
        operator_SubSystemRoleForStoreDataSource.deleteOperator_SubSystemRole(operatorId);

        // 結果検証
        assertThat(actualOperator_SubSystemRoleEntityCriteria.toString()).isEqualTo(expectedOperator_SubSystemRoleEntityCriteria.toString());
    }

    /**
     * {@link Operator_SubSystemRoleForStoreDataSource#insertOperator_SubSystemRole(Operator_SubSystemRoles operator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Entityへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void insertOperator_SubSystemRole_test() {
        // テスト対象クラス生成
        Operator_SubSystemRoleForStoreDataSource operator_SubSystemRoleForStoreDataSource = createOperator_SubSystemRoleForStoreDataSource();

        // 期待値
        List<Operator_SubSystemRoleEntity> expectedEntityList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : operator_SubSystemRoles.getValues()) {
            Operator_SubSystemRoleEntity expectedEntity = new Operator_SubSystemRoleEntity();

            expectedEntity.setOperator_SubSystemRoleId(operator_SubSystemRoleId++);
            expectedEntity.setOperatorId(operator_SubSystemRole.getOperatorId());
            expectedEntity.setSubSystemRoleCode(operator_SubSystemRole.getSubSystemRoleCode());
            expectedEntity.setValidThruStartDate(operator_SubSystemRole.getValidThruStartDate());
            expectedEntity.setValidThruEndDate(operator_SubSystemRole.getValidThruEndDate());
            expectedEntity.setCreatedBy(createdBy);
            expectedEntity.setCreatedAt(createdAt);
            expectedEntity.setCreatedIpAddress(createdIpAddress);
            expectedEntity.setUpdatedBy(null);
            expectedEntity.setUpdatedAt(null);
            expectedEntity.setUpdatedIpAddress(null);
            expectedEntity.setRecordVersion(recordVersion);

            expectedEntityList.add(expectedEntity);
        }
        operator_SubSystemRoleId = 1L;

        // 実行
        List<Operator_SubSystemRoleEntity> operator_SubSystemRoleEntityList = operator_SubSystemRoleForStoreDataSource.insertOperator_SubSystemRole(operator_SubSystemRoles);

        // 結果検証
        assertThat(operator_SubSystemRoleEntityList).usingRecursiveComparison().isEqualTo(expectedEntityList);
    }
}
