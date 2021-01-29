package net.jagunma.backbone.auth.authmanager.infra.datasource.operator_SubSystemRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntity;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Operator_SubSystemRoleDataSourceTest {

    // 実行既定値
    // オペレーター_サブシステムロールDaoの作成
    private Operator_SubSystemRoleEntityDao createOperator_SubSystemRoleEntityDao() {
        return new Operator_SubSystemRoleEntityDao() {
            @Override
            public List<Operator_SubSystemRoleEntity> findBy(Operator_SubSystemRoleEntityCriteria criteria, Orders orders) {
                return createOperator_SubSystemRoleEntityList();
            }
            @Override
            public List<Operator_SubSystemRoleEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public Operator_SubSystemRoleEntity findOneBy(Operator_SubSystemRoleEntityCriteria criteria) {
                return null;
            }
            @Override
            public int countBy(Operator_SubSystemRoleEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(Operator_SubSystemRoleEntity entity) {
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
    }
    // オペレーター_サブシステムロールリストデータ作成
    private List<Operator_SubSystemRoleEntity> createOperator_SubSystemRoleEntityList() {
        List<Operator_SubSystemRoleEntity> list = newArrayList();
        list.add(createOperator_SubSystemRoleEntity(1L,18L,SubSystemRole.JA管理者.getCode(),LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),null,null,null,null,null,null,1));
        list.add(createOperator_SubSystemRoleEntity(2L,19L,SubSystemRole.業務統括者_販売_畜産.getCode(),LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),null,null,null,null,null,null,1));
        list.add(createOperator_SubSystemRoleEntity(3L,20L,SubSystemRole.業務統括者_販売_畜産.getCode(),LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),null,null,null,null,null,null,1));
        return list;
    }
    // オペレーター_サブシステムロールデータ作成
    private Operator_SubSystemRoleEntity createOperator_SubSystemRoleEntity(
        Long operator_SubSystemRoleId,
        Long operatorId,
        String subSystemRoleCode,
        LocalDate validThruStartDate,
        LocalDate validThruEndDate,
        Long createdBy,
        LocalDateTime createdAt,
        String createdIpAddress,
        Long updatedBy,
        LocalDateTime updatedAt,
        String updatedIpAddress,
        Integer recordVersion) {

        Operator_SubSystemRoleEntity entity = new Operator_SubSystemRoleEntity();
        entity.setOperator_SubSystemRoleId(operator_SubSystemRoleId);
        entity.setOperatorId(operatorId);
        entity.setSubSystemRoleCode(subSystemRoleCode);
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
    private OperatorRepository createOperatorRepository() {
        return new OperatorRepository() {
            @Override
            public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
                return createOperators();
            }
            @Override
            public Operator findOneById(Long operatorId) {
                return null;
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

    /**
     * {@link Operator_SubSystemRoleDataSource#selectBy(Operator_SubSystemRoleCriteria,Orders)}のテスト
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
        Operator_SubSystemRoleCriteria criteria = new Operator_SubSystemRoleCriteria();
        Orders orders = Orders.empty();

        // テスト対象クラス生成
        Operator_SubSystemRoleDataSource operator_SubSystemRoleDataSource = new Operator_SubSystemRoleDataSource(
            createOperator_SubSystemRoleEntityDao(),
            createOperatorRepository());

        // 期待値
        Operators operators = createOperators();
        List<Operator_SubSystemRole> expectedOperator_SubSystemRoleList = newArrayList();
        for(Operator_SubSystemRoleEntity entity : createOperator_SubSystemRoleEntityList()) {
            expectedOperator_SubSystemRoleList.add(Operator_SubSystemRole.createFrom(
                entity.getOperator_SubSystemRoleId(),
                entity.getOperatorId(),
                entity.getSubSystemRoleCode(),
                entity.getValidThruStartDate(),
                entity.getValidThruEndDate(),
                entity.getRecordVersion(),
                operators.getValues().stream().filter(o->o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null),
                SubSystemRole.codeOf(entity.getSubSystemRoleCode())
                ));
        }

        // 実行
        Operator_SubSystemRoles actualOperator_SubSystemRoles = operator_SubSystemRoleDataSource.selectBy(criteria, orders);

        // 結果検証
        for(int i = 0; i < actualOperator_SubSystemRoles.getValues().size(); i++) {
            assertThat(actualOperator_SubSystemRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoleList.get(i));
        }
    }
}