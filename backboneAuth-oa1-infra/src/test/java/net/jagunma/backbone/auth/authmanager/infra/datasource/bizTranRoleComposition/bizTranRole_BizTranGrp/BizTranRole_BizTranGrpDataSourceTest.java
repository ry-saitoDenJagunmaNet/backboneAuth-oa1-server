package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition.bizTranRole_BizTranGrp;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranRole_BizTranGrpDataSourceTest {

    // ???????????????
    private final List<BizTranRole_BizTranGrpEntity> bizTranRole_BizTranGrpEntityList = createBizTranRole_BizTranGrpEntityList();
    private final List<BizTranRole> bizTranRoleList = createBizTranRoleList();
    private final List<BizTranGrp> bizTranGrpList = createBizTranGrpList();

    // ???????????????_????????????????????????????????????????????????
    private List<BizTranRole_BizTranGrpEntity> createBizTranRole_BizTranGrpEntityList() {
        List<BizTranRole_BizTranGrpEntity> list = newArrayList();
        list.add(createBizTranRole_BizTranGrpEntity(1L,20001L,200001L,SubSystem.??????.getCode(),null,null,null,null,null,null,1));
        list.add(createBizTranRole_BizTranGrpEntity(2L,10001L,100001L,SubSystem.??????_??????.getCode(),null,null,null,null,null,null,1));
        list.add(createBizTranRole_BizTranGrpEntity(3L,10002L,100003L,SubSystem.??????_??????.getCode(),null,null,null,null,null,null,1));
        list.add(createBizTranRole_BizTranGrpEntity(4L,10003L,100003L,SubSystem.??????_??????.getCode(),null,null,null,null,null,null,1));
        return list;
    }
    // ??????????????????_???????????????????????????
    private BizTranRole_BizTranGrpEntity createBizTranRole_BizTranGrpEntity(
        Long bizTranRole_BizTranGrpId,
        Long bizTranRoleId,
        Long bizTranGrpId,
        String subSystemCode,
        Long createdBy,
        LocalDateTime createdAt,
        String createdIpAddress,
        Long updatedBy,
        LocalDateTime updatedAt,
        String updatedIpAddress,
        Integer recordVersion) {

        BizTranRole_BizTranGrpEntity entity = new BizTranRole_BizTranGrpEntity();
        entity.setBizTranRole_BizTranGrpId(bizTranRole_BizTranGrpId);
        entity.setBizTranRoleId(bizTranRoleId);
        entity.setBizTranGrpId(bizTranGrpId);
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
    // ???????????????????????????????????????
    private List<BizTranRole> createBizTranRoleList() {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(10001L,"ANAG01","????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTranRole.createFrom(10002L,"ANAG02","?????????????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTranRole.createFrom(10003L,"ANAG99","?????????????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTranRole.createFrom(20001L,"KBAG01","??????????????????????????????",SubSystem.??????.getCode(),1,SubSystem.??????));
        return list;
    }
    // ??????????????????????????????????????????
    private List<BizTranGrp> createBizTranGrpList() {
        List<BizTranGrp> list = newArrayList();
        list.add(BizTranGrp.createFrom(100001L,"ANTG01","?????????????????????????????????", SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTranGrp.createFrom(100002L,"ANTG02","????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTranGrp.createFrom(100003L,"ANTG01","????????????????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTranGrp.createFrom(200001L,"KBTG01","??????????????????",SubSystem.??????.getCode(),1,SubSystem.??????));
        return list;
    }

    // ???????????????_????????????????????????Dao????????????
    private BizTranRole_BizTranGrpEntityDao createBizTranRole_BizTranGrpEntityDao() {
        return new BizTranRole_BizTranGrpEntityDao() {
            @Override
            public List<BizTranRole_BizTranGrpEntity> findAll(Orders orders) {
                return bizTranRole_BizTranGrpEntityList;
            }
            @Override
            public BizTranRole_BizTranGrpEntity findOneBy(BizTranRole_BizTranGrpEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<BizTranRole_BizTranGrpEntity> findBy(BizTranRole_BizTranGrpEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public int countBy(BizTranRole_BizTranGrpEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(BizTranRole_BizTranGrpEntity entity) {
                return 0;
            }
            @Override
            public int update(BizTranRole_BizTranGrpEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(BizTranRole_BizTranGrpEntity entity) {
                return 0;
            }
            @Override
            public int delete(BizTranRole_BizTranGrpEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(BizTranRole_BizTranGrpEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<BizTranRole_BizTranGrpEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<BizTranRole_BizTranGrpEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<BizTranRole_BizTranGrpEntity> entities) {
                return new int[0];
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

    /**
     * {@link BizTranRole_BizTranGrpDataSource#selectBy(BizTranRole_BizTranGrpCriteria,Orders)}?????????
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
        BizTranRole_BizTranGrpCriteria criteria = new BizTranRole_BizTranGrpCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(subSystemCode);
        Orders orders = Orders.empty().addOrder("subsystemCode");

        // ??????????????????????????????
        BizTranRole_BizTranGrpDataSource bizTranRole_BizTranGrpDataSource = new BizTranRole_BizTranGrpDataSource(
            createBizTranRole_BizTranGrpEntityDao(),
            createBizTranRoleRepository(),
            createBizTranGrpRepository());

        // ?????????
        List<BizTranRole_BizTranGrp> expectedBizTranRole_BizTranGrpList = newArrayList();
        for(BizTranRole_BizTranGrpEntity entity : bizTranRole_BizTranGrpEntityList) {
            if (entity.getSubSystemCode().equals(subSystemCode)) {
                expectedBizTranRole_BizTranGrpList.add(BizTranRole_BizTranGrp.createFrom(
                    entity.getBizTranRole_BizTranGrpId(),
                    entity.getBizTranRoleId(),
                    entity.getBizTranGrpId(),
                    entity.getSubSystemCode(),
                    entity.getRecordVersion(),
                    bizTranRoleList.stream().filter(b->b.getBizTranRoleId().equals(entity.getBizTranRoleId())).findFirst().orElse(null),
                    bizTranGrpList.stream().filter(b->b.getBizTranGrpId().equals(entity.getBizTranGrpId())).findFirst().orElse(null),
                    SubSystem.codeOf(entity.getSubSystemCode())));
            }
        }

        // ??????
        BizTranRole_BizTranGrps actualBizTranRole_BizTranGrps = bizTranRole_BizTranGrpDataSource.selectBy(criteria, orders);

        // ????????????
        assertThat(actualBizTranRole_BizTranGrps.size()).isEqualTo(expectedBizTranRole_BizTranGrpList.size());
        for(int i = 0; i < actualBizTranRole_BizTranGrps.getValues().size(); i++) {
            assertThat(actualBizTranRole_BizTranGrps.getValues().get(i)).as(i + 1 + "???????????????????????????")
                .usingRecursiveComparison().isEqualTo(expectedBizTranRole_BizTranGrpList.get(i));
        }
    }

    /**
     * {@link BizTranRole_BizTranGrpDataSource#selectAll(Orders)}?????????
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
        BizTranRole_BizTranGrpDataSource bizTranRole_BizTranGrpDataSource = new BizTranRole_BizTranGrpDataSource(
            createBizTranRole_BizTranGrpEntityDao(),
            createBizTranRoleRepository(),
            createBizTranGrpRepository());

        // ?????????
        List<BizTranRole_BizTranGrp> expectedBizTranRole_BizTranGrpList = newArrayList();
        for(BizTranRole_BizTranGrpEntity entity : bizTranRole_BizTranGrpEntityList.stream().sorted(orders.toComparator()).collect(Collectors.toList())) {
            expectedBizTranRole_BizTranGrpList.add(BizTranRole_BizTranGrp.createFrom(
                entity.getBizTranRole_BizTranGrpId(),
                entity.getBizTranRoleId(),
                entity.getBizTranGrpId(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                bizTranRoleList.stream().filter(b->b.getBizTranRoleId().equals(entity.getBizTranRoleId())).findFirst().orElse(null),
                bizTranGrpList.stream().filter(b->b.getBizTranGrpId().equals(entity.getBizTranGrpId())).findFirst().orElse(null),
                SubSystem.codeOf(entity.getSubSystemCode())));
        }

        // ??????
        BizTranRole_BizTranGrps actualBizTranRole_BizTranGrps = bizTranRole_BizTranGrpDataSource.selectAll(orders);

        // ????????????
        assertThat(actualBizTranRole_BizTranGrps.size()).isEqualTo(expectedBizTranRole_BizTranGrpList.size());
        for(int i = 0; i < actualBizTranRole_BizTranGrps.getValues().size(); i++) {
            assertThat(actualBizTranRole_BizTranGrps.getValues().get(i)).as(i + 1 + "???????????????????????????")
                .usingRecursiveComparison().isEqualTo(expectedBizTranRole_BizTranGrpList.get(i));
        }
    }
}