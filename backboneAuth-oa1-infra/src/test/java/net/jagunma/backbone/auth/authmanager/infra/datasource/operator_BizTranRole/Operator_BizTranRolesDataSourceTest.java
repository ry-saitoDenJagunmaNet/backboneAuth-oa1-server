package net.jagunma.backbone.auth.authmanager.infra.datasource.operator_BizTranRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorsRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Operator_BizTranRolesDataSourceTest {

    // 実行既定値
    // オペレーター_取引ロールDaoの作成
    private Operator_BizTranRoleEntityDao createOperator_BizTranRoleEntityDao() {
        return new Operator_BizTranRoleEntityDao() {
            @Override
            public List<Operator_BizTranRoleEntity> findBy(Operator_BizTranRoleEntityCriteria criteria, Orders orders) {
                return createOperator_BizTranRoleEntityList();
            }
            @Override
            public List<Operator_BizTranRoleEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public Operator_BizTranRoleEntity findOneBy(Operator_BizTranRoleEntityCriteria criteria) {
                return null;
            }
            @Override
            public int countBy(Operator_BizTranRoleEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(Operator_BizTranRoleEntity entity) {
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
    }
    // アオペレーター_取引ロール割当リストデータ作成
    private List<Operator_BizTranRoleEntity> createOperator_BizTranRoleEntityList() {
        List<Operator_BizTranRoleEntity> list = newArrayList();
        list.add(createOperator_BizTranRoleEntity(1L,18L,101L,LocalDate.of(2010,8,23),LocalDate.of(9999,12,31),null,null,null,null,null,null,1));
        list.add(createOperator_BizTranRoleEntity(2L,19L,102L,LocalDate.of(2010,8,23),LocalDate.of(9999,12,31),null,null,null,null,null,null,1));
        list.add(createOperator_BizTranRoleEntity(3L,20L,103L,LocalDate.of(2010,8,23),LocalDate.of(9999,12,31),null,null,null,null,null,null,1));
        return list;
    }
    // アオペレーター_取引ロール割当データ作成
    private Operator_BizTranRoleEntity createOperator_BizTranRoleEntity(
        Long operator_BizTranRoleId,
        Long operatorId,
        Long bizTranRoleId,
        LocalDate validThruStartDate,
        LocalDate validThruEndDate,
        Long createdBy,
        LocalDateTime createdAt,
        String createdIpAddress,
        Long updatedBy,
        LocalDateTime updatedAt,
        String updatedIpAddress,
        Integer recordVersion) {

        Operator_BizTranRoleEntity entity = new Operator_BizTranRoleEntity();
        entity.setOperator_BizTranRoleId(operator_BizTranRoleId);
        entity.setOperatorId(operatorId);
        entity.setBizTranRoleId(bizTranRoleId);
        entity.setValidThruStartDate(validThruStartDate);
        entity.setValidThruEndDate(validThruEndDate);
        entity.setCreatedBy(createdBy);
        entity.setCreatedAt(createdAt);
        entity.setCreatedIpAddress(createdIpAddress);
        entity.setUpdatedBy(updatedBy);
        entity.setUpdatedAt(updatedAt);
        entity.setUpdatedIpAddress(updatedIpAddress);
        entity.setRecordVersion(recordVersion);
        return entity;
    }
    // オペレータRepositoryの作成
    private OperatorsRepository createOperatorsRepository() {
        return new OperatorsRepository() {
            @Override
            public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
                return createOperators();
            }
        };
    }
    // オペレータ群データ作成
    private Operators createOperators() {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L,"yu001009","ｙｕ００１００９","yu001009@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        list.add(Operator.createFrom(19L,"yu001010","ｙｕ００１０１０","yu001010@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),true,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        list.add(Operator.createFrom(20L,"yu001011","ｙｕ００１０１１","yu001011@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.利用不可,1,null));
        return Operators.createFrom(list);
    }
    // 取引ロールRepositoryの作成
    private BizTranRolesRepository createBizTranRolesRepository() {
        return new BizTranRolesRepository() {
            @Override
            public BizTranRoles selectBy(BizTranRoleCriteria bizTranRoleCriteria, Orders orders) {
                return createBizTranRoles();
            }
            @Override
            public BizTranRoles selectAll(Orders orders) {
                return null;
            }
        };
    }
    // 取引ロール群データ作成
    private BizTranRoles createBizTranRoles() {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(101L,"ANAG01","（畜産）取引全般",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranRole.createFrom(102L,"ANAG02","（畜産）維持管理担当者",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranRole.createFrom(103L,"ANAG99","（畜産）維持管理責任者",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return BizTranRoles.createFrom(list);
    }

    /**
     * {@link Operator_BizTranRolesDataSource#selectBy(Operator_BizTranRoleCriteria,Orders)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void selectBy_test0() {

        // 実行値
        Operator_BizTranRoleCriteria criteria = new Operator_BizTranRoleCriteria();
        Orders orders = Orders.empty();

        // テスト対象クラス生成
        Operator_BizTranRolesDataSource operator_BizTranRolesDataSource = new Operator_BizTranRolesDataSource(
            createOperator_BizTranRoleEntityDao(),
            createOperatorsRepository(),
            createBizTranRolesRepository());

        // 期待値
        Operators operators = createOperators();
        BizTranRoles bizTranRoles = createBizTranRoles();
        List<Operator_BizTranRole> expectedOperator_BizTranRoleList = newArrayList();
        for(Operator_BizTranRoleEntity entity : createOperator_BizTranRoleEntityList()) {
            expectedOperator_BizTranRoleList.add(Operator_BizTranRole.createFrom(
                entity.getOperator_BizTranRoleId(),
                entity.getOperatorId(),
                entity.getBizTranRoleId(),
                entity.getValidThruStartDate(),
                entity.getValidThruEndDate(),
                entity.getRecordVersion(),
                operators.getValues().stream().filter(o->o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null),
                bizTranRoles.getValues().stream().filter(b->b.getBizTranRoleId().equals(entity.getBizTranRoleId())).findFirst().orElse(null)));
        }

        // 実行
        Operator_BizTranRoles actualOperator_BizTranRoles = operator_BizTranRolesDataSource.selectBy(criteria, orders);

        // 結果検証
        for(int i = 0; i < actualOperator_BizTranRoles.getValues().size(); i++) {
            assertThat(actualOperator_BizTranRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedOperator_BizTranRoleList.get(i));
        }
    }
}