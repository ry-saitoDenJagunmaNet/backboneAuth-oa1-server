package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition.bizTranGrp_BizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntity;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranGrp_BizTranDataSourceTest {

    // ???????????????
    private final List<BizTranGrp_BizTranEntity> bizTranGrp_BizTranEntityList = createBizTranGrp_BizTranEntityList();
    private final List<BizTranGrp> bizTranGrpList = createBizTranGrpList();
    private final List<BizTran> bizTranList = createBizTranList();

    // ??????????????????_????????????????????????????????????
    private List<BizTranGrp_BizTranEntity> createBizTranGrp_BizTranEntityList() {
        List<BizTranGrp_BizTranEntity> list = newArrayList();
        list.add(createBizTranGrp_BizTranEntity(1L,200001L,20000001L,SubSystem.??????.getCode(),null,null,null,null,null,null,1));
        list.add(createBizTranGrp_BizTranEntity(2L,100001L,10000001L,SubSystem.??????_??????.getCode(),null,null,null,null,null,null,1));
        list.add(createBizTranGrp_BizTranEntity(3L,100002L,10000002L,SubSystem.??????_??????.getCode(),null,null,null,null,null,null,1));
        list.add(createBizTranGrp_BizTranEntity(4L,100003L,10000003L,SubSystem.??????_??????.getCode(),null,null,null,null,null,null,1));
        return list;
    }
    // ??????????????????_???????????????????????????
    private BizTranGrp_BizTranEntity createBizTranGrp_BizTranEntity(
        Long bizTranGrp_BizTranId,
        Long bizTranGrpId,
        Long bizTranId,
        String subSystemCode,
        Long createdBy,
        LocalDateTime createdAt,
        String createdIpAddress,
        Long updatedBy,
        LocalDateTime updatedAt,
        String updatedIpAddress,
        Integer recordVersion) {

        BizTranGrp_BizTranEntity entity = new BizTranGrp_BizTranEntity();
        entity.setBizTranGrp_BizTranId(bizTranGrp_BizTranId);
        entity.setBizTranGrpId(bizTranGrpId);
        entity.setBizTranId(bizTranId);
        entity.setSubSystemCode(subSystemCode);
        entity.setCreatedBy(createdBy);
        entity.setCreatedAt(createdAt);
        entity.setCreatedIpAddress(createdIpAddress);
        entity.setUpdatedBy(updatedBy);
        entity.setUpdatedAt(updatedAt);
        entity.setUpdatedIpAddress(updatedIpAddress);
        entity.setRecordVersion(recordVersion);
        return entity;
    }
    // ??????????????????????????????????????????
    private List<BizTranGrp> createBizTranGrpList() {
        List<BizTranGrp> list = newArrayList();
        list.add(BizTranGrp.createFrom(100001L,"ANTG01","?????????????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTranGrp.createFrom(100002L,"ANTG02","????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTranGrp.createFrom(100003L,"ANTG01","????????????????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTranGrp.createFrom(200001L,"KBTG01","??????????????????",SubSystem.??????.getCode(),1,SubSystem.??????));
        return list;
    }
    // ??????????????????????????????
    private List<BizTran> createBizTranList() {
        List<BizTran> list = newArrayList();
        list.add(BizTran.createFrom(10000001L,"AN0001","???????????????????????????",false,LocalDate.of(2020,6,21),LocalDate.of(9999,12,31),SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTran.createFrom(10000002L,"AN1510","????????????",false,LocalDate.of(2020,6,21),LocalDate.of(9999,12,31),SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTran.createFrom(10000003L,"AN0002","??????????????????????????????????????????",true,LocalDate.of(2020,6,21),LocalDate.of(9999,12,31),SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTran.createFrom(20000001L,"KB0000","???????????????????????????",true,LocalDate.of(2020,3,11),LocalDate.of(9999,12,31),SubSystem.??????.getCode(),1,SubSystem.??????));
        return list;
    }

    // ??????????????????_????????????Dao????????????
    private BizTranGrp_BizTranEntityDao createBizTranGrp_BizTranEntityDao() {
        return new BizTranGrp_BizTranEntityDao() {
            @Override
            public List<BizTranGrp_BizTranEntity> findAll(Orders orders) {
                return bizTranGrp_BizTranEntityList;
            }
            @Override
            public BizTranGrp_BizTranEntity findOneBy(BizTranGrp_BizTranEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<BizTranGrp_BizTranEntity> findBy(BizTranGrp_BizTranEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public int countBy(BizTranGrp_BizTranEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(BizTranGrp_BizTranEntity entity) {
                return 0;
            }
            @Override
            public int update(BizTranGrp_BizTranEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(BizTranGrp_BizTranEntity entity) {
                return 0;
            }
            @Override
            public int delete(BizTranGrp_BizTranEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(BizTranGrp_BizTranEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<BizTranGrp_BizTranEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<BizTranGrp_BizTranEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<BizTranGrp_BizTranEntity> entities) {
                return new int[0];
            }
        };
    }
    // ?????????????????????????????????????????????
    private BizTranGrpRepository createBizTranGrpRepository() {
        return new BizTranGrpRepository() {
            @Override
            public BizTranGrps selectAll(Orders orders) {
                return BizTranGrps.createFrom(bizTranGrpList);
            }
            @Override
            public BizTranGrp findOneByCode(String bizTranGrpCode) {
                return null;
            }
            @Override
            public BizTranGrps selectBy(BizTranGrpCriteria bizTranGrpCriteria, Orders orders) {
                return null;
            }
        };
    }
    // ?????????????????????????????????
    private BizTranRepository createBizTranRepository() {
        return new BizTranRepository() {
            @Override
            public BizTrans selectAll(Orders orders) {
                return BizTrans.createFrom(bizTranList);
            }
            @Override
            public BizTran findOneByCode(String bizTranCode) {
                return null;
            }
            @Override
            public BizTrans selectBy(BizTranCriteria bizTranCriteria, Orders orders) {
                return null;
            }
        };
    }

    /**
     * {@link BizTranGrp_BizTranDataSource#selectBy(BizTranGrp_BizTranCriteria,Orders)}?????????
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
        String subSystemCode = SubSystem.??????_??????.getCode();
        BizTranGrp_BizTranCriteria criteria = new BizTranGrp_BizTranCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(subSystemCode);
        Orders orders = Orders.empty().addOrder("subsystemCode");

        // ??????????????????????????????
        BizTranGrp_BizTranDataSource bizTranGrp_BizTranDataSource = new BizTranGrp_BizTranDataSource(
            createBizTranGrp_BizTranEntityDao(),
            createBizTranGrpRepository(),
            createBizTranRepository());

        // ?????????
        List<BizTranGrp_BizTran> expectedBizTranGrp_BizTranList = newArrayList();
        for(BizTranGrp_BizTranEntity entity : bizTranGrp_BizTranEntityList.stream().sorted(orders.toComparator()).collect(Collectors.toList())) {
            if (entity.getSubSystemCode().equals(subSystemCode)) {
                expectedBizTranGrp_BizTranList.add(BizTranGrp_BizTran.createFrom(
                    entity.getBizTranGrp_BizTranId(),
                    entity.getBizTranGrpId(),
                    entity.getBizTranId(),
                    entity.getSubSystemCode(),
                    entity.getRecordVersion(),
                    bizTranGrpList.stream().filter(b->b.getBizTranGrpId().equals(entity.getBizTranGrpId())).findFirst().orElse(null),
                    bizTranList.stream().filter(b->b.getBizTranId().equals(entity.getBizTranId())).findFirst().orElse(null),
                    SubSystem.codeOf(entity.getSubSystemCode())));
            }
        }

        // ??????
        BizTranGrp_BizTrans actualBizTranGrp_BizTrans = bizTranGrp_BizTranDataSource.selectBy(criteria, orders);

        // ????????????
        assertThat(actualBizTranGrp_BizTrans.size()).isEqualTo(expectedBizTranGrp_BizTranList.size());
        for(int i = 0; i < actualBizTranGrp_BizTrans.getValues().size(); i++) {
            assertThat(actualBizTranGrp_BizTrans.getValues().get(i)).as(i + 1 + "???????????????????????????")
                .usingRecursiveComparison().isEqualTo(expectedBizTranGrp_BizTranList.get(i));
        }
    }

    /**
     * {@link BizTranGrp_BizTranDataSource#selectAll(Orders)}?????????
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
        Orders orders = Orders.empty().addOrder("subsystemCode");

        // ??????????????????????????????
        BizTranGrp_BizTranDataSource bizTranGrp_BizTranDataSource = new BizTranGrp_BizTranDataSource(
            createBizTranGrp_BizTranEntityDao(),
            createBizTranGrpRepository(),
            createBizTranRepository());

        // ?????????
        List<BizTranGrp_BizTran> expectedBizTranGrp_BizTranList = newArrayList();
        for(BizTranGrp_BizTranEntity entity : bizTranGrp_BizTranEntityList.stream().sorted(orders.toComparator()).collect(Collectors.toList())) {
            expectedBizTranGrp_BizTranList.add(BizTranGrp_BizTran.createFrom(
                entity.getBizTranGrp_BizTranId(),
                entity.getBizTranGrpId(),
                entity.getBizTranId(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                bizTranGrpList.stream().filter(b->b.getBizTranGrpId().equals(entity.getBizTranGrpId())).findFirst().orElse(null),
                bizTranList.stream().filter(b->b.getBizTranId().equals(entity.getBizTranId())).findFirst().orElse(null),
                SubSystem.codeOf(entity.getSubSystemCode())));
        }

        // ??????
        BizTranGrp_BizTrans actualBizTranGrp_BizTrans = bizTranGrp_BizTranDataSource.selectAll(orders);

        // ????????????
        assertThat(actualBizTranGrp_BizTrans.size()).isEqualTo(expectedBizTranGrp_BizTranList.size());
        for(int i = 0; i < actualBizTranGrp_BizTrans.getValues().size(); i++) {
            assertThat(actualBizTranGrp_BizTrans.getValues().get(i)).as(i + 1 + "???????????????????????????")
                .usingRecursiveComparison().isEqualTo(expectedBizTranGrp_BizTranList.get(i));
        }
    }
}