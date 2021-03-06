package net.jagunma.backbone.auth.authmanager.infra.datasource.operator_BizTranRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
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

class Operator_BizTranRoleDataSourceTest {

    // ???????????????
    private final List<Operator_BizTranRoleEntity> operator_BizTranRoleEntityList = createOperator_BizTranRoleEntityList();
    private final List<Operator> operatorList = createOperatorList();
    private final List<BizTranRole> bizTranRoleList = createBizTranRoleList();

    // ?????????????????????_?????????????????????????????????????????????
    private List<Operator_BizTranRoleEntity> createOperator_BizTranRoleEntityList() {
        List<Operator_BizTranRoleEntity> list = newArrayList();
        list.add(createOperator_BizTranRoleEntity(1L,18L,101L,LocalDate.of(2010,8,23),LocalDate.of(9999,12,31),null,null,null,null,null,null,1));
        list.add(createOperator_BizTranRoleEntity(2L,19L,102L,LocalDate.of(2010,8,23),LocalDate.of(9999,12,31),null,null,null,null,null,null,1));
        list.add(createOperator_BizTranRoleEntity(3L,20L,101L,LocalDate.of(2010,8,23),LocalDate.of(9999,12,31),null,null,null,null,null,null,1));
        list.add(createOperator_BizTranRoleEntity(4L,20L,103L,LocalDate.of(2010,8,23),LocalDate.of(9999,12,31),null,null,null,null,null,null,1));
        list.add(createOperator_BizTranRoleEntity(5L,20L,102L,LocalDate.of(2010,8,23),LocalDate.of(9999,12,31),null,null,null,null,null,null,1));
        return list;
    }
    // ?????????????????????_????????????????????????????????????
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
    // ???????????????????????????????????????
    private List<Operator> createOperatorList() {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L,"yu001009","????????????????????????","yu001009@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.????????????,1,null));
        list.add(Operator.createFrom(19L,"yu001010","????????????????????????","yu001010@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),true,6L,"006",33L,"001",AvailableStatus.????????????,1,null));
        list.add(Operator.createFrom(20L,"yu001011","????????????????????????","yu001011@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.????????????,1,null));
        return list;
    }
    // ???????????????????????????????????????
    private List<BizTranRole> createBizTranRoleList() {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(101L,"ANAG01","????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTranRole.createFrom(102L,"ANAG02","?????????????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTranRole.createFrom(103L,"ANAG99","?????????????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        return list;
    }


    // ??????????????????_???????????????Dao????????????
    private Operator_BizTranRoleEntityDao createOperator_BizTranRoleEntityDao() {
        return new Operator_BizTranRoleEntityDao() {
            @Override
            public List<Operator_BizTranRoleEntity> findAll(Orders orders) {
                return operator_BizTranRoleEntityList;
            }
            @Override
            public List<Operator_BizTranRoleEntity> findBy(Operator_BizTranRoleEntityCriteria criteria, Orders orders) {
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
    // ??????????????????????????????????????????
    private OperatorRepository createOperatorRepository() {
        return new OperatorRepository() {
            @Override
            public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
                return Operators.createFrom(operatorList);
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
    // ??????????????????????????????????????????
    private BizTranRoleRepository createBizTranRoleRepository() {
        return new BizTranRoleRepository() {
            @Override
            public BizTranRoles selectAll(Orders orders) {
                return BizTranRoles.createFrom(bizTranRoleList);
            }
            @Override
            public BizTranRoles selectBy(BizTranRoleCriteria bizTranRoleCriteria, Orders orders) {
                return null;
            }
        };
    }

    /**
     * {@link Operator_BizTranRoleDataSource#selectBy(Operator_BizTranRoleCriteria,Orders)}????????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void selectBy_test0() {

        // ?????????
        Long operatorId = 20L;
        Operator_BizTranRoleCriteria criteria = new Operator_BizTranRoleCriteria();
        criteria.getOperatorIdCriteria().setEqualTo(operatorId);
        Orders orders = Orders.empty().addOrder("bizTranRoleId");

        // ??????????????????????????????
        Operator_BizTranRoleDataSource operator_BizTranRoleDataSource = new Operator_BizTranRoleDataSource(
            createOperator_BizTranRoleEntityDao(),
            createOperatorRepository(),
            createBizTranRoleRepository());

        // ?????????
        List<Operator_BizTranRole> expectedOperator_BizTranRoleList = newArrayList();
        for(Operator_BizTranRoleEntity entity : operator_BizTranRoleEntityList.stream().sorted(orders.toComparator()).collect(Collectors.toList())) {
            if (entity.getOperatorId().equals(operatorId)) {
                expectedOperator_BizTranRoleList.add(Operator_BizTranRole.createFrom(
                    entity.getOperator_BizTranRoleId(),
                    entity.getOperatorId(),
                    entity.getBizTranRoleId(),
                    entity.getValidThruStartDate(),
                    entity.getValidThruEndDate(),
                    entity.getRecordVersion(),
                    operatorList.stream().filter(o->o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null),
                    bizTranRoleList.stream().filter(b->b.getBizTranRoleId().equals(entity.getBizTranRoleId())).findFirst().orElse(null)));
            }
        }

        // ??????
        Operator_BizTranRoles actualOperator_BizTranRoles = operator_BizTranRoleDataSource.selectBy(criteria, orders);

        // ????????????
        assertThat(actualOperator_BizTranRoles.size()).isEqualTo(expectedOperator_BizTranRoleList.size());
        for(int i = 0; i < actualOperator_BizTranRoles.getValues().size(); i++) {
            assertThat(actualOperator_BizTranRoles.getValues().get(i)).as(i + 1 + "???????????????????????????")
                .usingRecursiveComparison().isEqualTo(expectedOperator_BizTranRoleList.get(i));
        }
    }

    /**
     * {@link Operator_BizTranRoleDataSource#selectAll(Orders)}????????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void selectAll_test0() {

        // ?????????
        Operator_BizTranRoleCriteria criteria = new Operator_BizTranRoleCriteria();
        Orders orders = Orders.empty().addOrder("bizTranRoleId");

        // ??????????????????????????????
        Operator_BizTranRoleDataSource operator_BizTranRoleDataSource = new Operator_BizTranRoleDataSource(
            createOperator_BizTranRoleEntityDao(),
            createOperatorRepository(),
            createBizTranRoleRepository());

        // ?????????
        List<Operator_BizTranRole> expectedOperator_BizTranRoleList = newArrayList();
        for(Operator_BizTranRoleEntity entity : operator_BizTranRoleEntityList.stream().sorted(orders.toComparator()).collect(Collectors.toList())) {
            expectedOperator_BizTranRoleList.add(Operator_BizTranRole.createFrom(
                entity.getOperator_BizTranRoleId(),
                entity.getOperatorId(),
                entity.getBizTranRoleId(),
                entity.getValidThruStartDate(),
                entity.getValidThruEndDate(),
                entity.getRecordVersion(),
                operatorList.stream().filter(o->o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null),
                bizTranRoleList.stream().filter(b->b.getBizTranRoleId().equals(entity.getBizTranRoleId())).findFirst().orElse(null)));
        }

        // ??????
        Operator_BizTranRoles actualOperator_BizTranRoles = operator_BizTranRoleDataSource.selectAll(orders);

        // ????????????
        assertThat(actualOperator_BizTranRoles.size()).isEqualTo(expectedOperator_BizTranRoleList.size());
        for(int i = 0; i < actualOperator_BizTranRoles.getValues().size(); i++) {
            assertThat(actualOperator_BizTranRoles.getValues().get(i)).as(i + 1 + "???????????????????????????")
                .usingRecursiveComparison().isEqualTo(expectedOperator_BizTranRoleList.get(i));
        }
    }
}