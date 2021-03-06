package net.jagunma.backbone.auth.authmanager.infra.datasource.suspendBizTran;

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
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTrans;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntity;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntityCriteria;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntityDao;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.backbone.shared.application.ja.JaAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.strings2.Strings2;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchNotFoundException;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAtMomentCriteria;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import net.jagunma.common.values.model.ja.JasAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SuspendBizTranDataSourceTest {

    // ???????????????
    private final Long suspendBizTranId = 12345678L;
    private final Long jaId = 6L;
    private String jaCode = "006";
    private final String jaName = "???????????????";
    private final Long branchId = 33L;
    private String branchCode = "001";
    private final String branchName = "???????????????";
    private String subSystemCode = SubSystem.??????_??????.getCode();
    private final Long bizTranGrpId = 10001L;
    private String bizTranGrpCode = "ANTG01";
    private final String bizTranGrpName = "?????????????????????????????????";
    private final Integer bizTranGrpRecordVersion = 1;
    private final Long bizTranId = 100001L;
    private String bizTranCode = "AN0001";
    private final Boolean isCenterBizTran = false;
    private final LocalDate validThruStartDate = LocalDate.of(2010,6,21);
    private final LocalDate validThruEndDate = LocalDate.of(9999,12,31);
    private final String bizTranName = "???????????????????????????";
    private final Integer bizTranRecordVersion  = 1;
    private final LocalDate suspendStartDate = LocalDate.of(2020,11,1);
    private final LocalDate suspendEndDate = LocalDate.of(2020,11,30);
    private final String suspendReason = "??????????????????????????????";
    private final Long createdBy = 18L;
    private final LocalDateTime createdAt =  LocalDateTime.of(2020,12,1,8,31,12);
    private final String createdIpAddress = "001.001.001.001";
    private Long updatedBy = 18L;
    private LocalDateTime updatedAt =  LocalDateTime.of(2020,12,2,9,32,23);
    private String updatedIpAddress = "001.001.001.002";
    private final Integer recordVersion = 1;
    private List<SuspendBizTranEntity> suspendBizTranEntityList = createSuspendBizTranEntityList();

    // JaAtMoment???????????????
    private JaAtMoment createJaAtMoment() {
        if (Strings2.isEmpty(jaCode)) { return null; }
        return JaAtMoment.builder()
            .withIdentifier(jaId)
            .withJaAttribute(JaAttribute
                .builder()
                .withJaCode(JaCode.of(jaCode))
                .withName(jaName)
                .withFormalName("")
                .withAbbreviatedName("")
                .build())
            .build();
    }
    // BranchAtMoment???????????????
    private BranchAtMoment createBranchAtMoment() {
        if (Strings2.isEmpty(branchCode)) { return null; }
        return BranchAtMoment.builder()
            .withIdentifier(branchId)
            .withJaAtMoment(createJaAtMoment())
            .withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.??????)
                .withBranchCode(BranchCode.of(branchCode))
                .withName(branchName)
                .build())
            .build();
    }
    // ?????????????????????????????????
    private BizTranGrp createBizTranGrp() {
        if (Strings2.isEmpty(bizTranGrpCode)) { return null; }
        return BizTranGrp.createFrom(
            bizTranGrpId,
            bizTranGrpCode,
            bizTranGrpName,
            subSystemCode,
            bizTranGrpRecordVersion,
            (subSystemCode == null)? null : SubSystem.codeOf(subSystemCode)
        );
    }
    // ?????????????????????
    private BizTran createBizTran() {
        if (Strings2.isEmpty(bizTranCode)) { return null; }
        return BizTran.createFrom(
            bizTranId,
            bizTranCode,
            bizTranName,
            isCenterBizTran,
            validThruStartDate,
            validThruEndDate,
            subSystemCode,
            bizTranRecordVersion,
            (subSystemCode == null)? null : SubSystem.codeOf(subSystemCode)
        );
    }
    // ?????????????????????
    private SuspendBizTranCriteria  createSuspendBizTranCriteria () {
        SuspendBizTranCriteria criteria = new SuspendBizTranCriteria();
        criteria.getJaCodeCriteria().setEqualTo(jaCode);
        criteria.getBranchCodeCriteria().setEqualTo(branchCode);
        criteria.getSubSystemCodeCriteria().setEqualTo(subSystemCode);
        criteria.getBizTranGrpCodeCriteria().setEqualTo(bizTranGrpCode);
        criteria.getBizTranCodeCriteria().setEqualTo(bizTranCode);
        criteria.getSuspendStartDateCriteria().setLessOrEqual(suspendStartDate);
        criteria.getSuspendEndDateCriteria().setMoreOrEqual(suspendEndDate);
        criteria.getSuspendReasonCriteria().setForwardMatch(suspendReason);

        return criteria;
    }

    // ??????????????????Entity????????????????????????
    private List<SuspendBizTranEntity> createSuspendBizTranEntityList() {
        List<SuspendBizTranEntity> list = newArrayList();
        list.add(createSuspendBizTranEntity(1L,"006","001",SubSystem.??????_??????.getCode(),"ANTG01","",LocalDate.of(2020,11,1),LocalDate.of(2020,11,2),"????????????",18L,LocalDateTime.of(2020,10,31,8,30,12),"001.001.001.001",null,null,null,1));
        list.add(createSuspendBizTranEntity(2L,"","",SubSystem.??????_??????.getCode(),"","AN0001",LocalDate.of(2020,11,1),LocalDate.of(2020,11,2),"????????????",18L,LocalDateTime.of(2020,10,31,8,30,12),"001.001.001.001",null,null,null,1));
        list.add(createSuspendBizTranEntity(3L,null,null,null,null,null,LocalDate.of(2020,11,1),LocalDate.of(2020,11,2),"????????????",18L,LocalDateTime.of(2020,10,31,8,30,12),"001.001.001.001",null,null,null,1));
        return list;
    }
    // ??????????????????Entity???????????????
    private SuspendBizTranEntity createSuspendBizTranEntity(
        Long suspendBizTranId,
        String jaCode,
        String branchCode,
        String subSystemCode,
        String bizTranGrpCode,
        String bizTranCode,
        LocalDate suspendStartDate,
        LocalDate suspendEndDate,
        String suspendReason,
        Long createdBy,
        LocalDateTime createdAt,
        String createdIpAddress,
        Long updatedBy,
        LocalDateTime updatedAt,
        String updatedIpAddress,
        Integer recordVersion) {

        SuspendBizTranEntity entity = new SuspendBizTranEntity();
        entity.setSuspendBizTranId(suspendBizTranId);
        entity.setJaCode(jaCode);
        entity.setBranchCode(branchCode);
        entity.setSubSystemCode(subSystemCode);
        entity.setBizTranGrpCode(bizTranGrpCode);
        entity.setBizTranCode(bizTranCode);
        entity.setSuspendStartDate(suspendStartDate);
        entity.setSuspendEndDate(suspendEndDate);
        entity.setSuspendReason(suspendReason);
        entity.setCreatedBy(createdBy);
        entity.setCreatedAt(createdAt);
        entity.setCreatedIpAddress(createdIpAddress);
        entity.setUpdatedBy(updatedBy);
        entity.setUpdatedAt(updatedAt);
        entity.setUpdatedIpAddress(updatedIpAddress);
        entity.setRecordVersion(recordVersion);
        return entity;
    }
    // BranchAtMoment????????????????????????
    private List<BranchAtMoment> createBranchAtMomentList() {
        List<JaAtMoment> jaAtMomentList = createJaAtMomentList();
        List<BranchAtMoment> list = newArrayList();
        list.add(BranchAtMoment.builder()
            .withIdentifier(33L).withJaAtMoment(jaAtMomentList.stream().filter(j->j.getIdentifier().equals(6L)).findFirst().orElse(null))
            .withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.??????).withBranchCode(BranchCode.of("001")).withName("??????001").build()).build());
        list.add(BranchAtMoment.builder()
            .withIdentifier(35L).withJaAtMoment(jaAtMomentList.stream().filter(j->j.getIdentifier().equals(6L)).findFirst().orElse(null))
            .withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.??????).withBranchCode(BranchCode.of("003")).withName("??????003").build()).build());
        return list;
    }
    // JaAtMoment????????????????????????
    private List<JaAtMoment> createJaAtMomentList() {
        List<JaAtMoment> list = newArrayList();
        list.add(JaAtMoment.builder().withIdentifier(6L).withJaAttribute(JaAttribute.builder().withJaCode(JaCode.of("006")).withName("??????006").withFormalName("").withAbbreviatedName("").build()).build());
        return list;
    }
    // ??????????????????????????????????????????
    private List<BizTranGrp> createBizTranGrpList() {
        List<BizTranGrp> list = newArrayList();
        list.add(BizTranGrp.createFrom(10001L,"ANTG01","?????????????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTranGrp.createFrom(10002L,"ANTG02","????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        return list;
    }
    // ??????????????????????????????
    private List<BizTran> createBizTranList() {
        List<BizTran> list = newArrayList();
        list.add(BizTran.createFrom(100001L,"AN0001","???????????????????????????",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTran.createFrom(100002L,"AN1110","??????????????????",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        return list;
    }

    // ?????????????????????????????????????????????
    private SuspendBizTranEntityDao createSuspendBizTranEntityDao() {
        return new SuspendBizTranEntityDao() {
            @Override
            public SuspendBizTranEntity findOneBy(SuspendBizTranEntityCriteria criteria) {
                SuspendBizTranEntity entity = new SuspendBizTranEntity();
                entity.setSuspendBizTranId(suspendBizTranId);
                entity.setJaCode(jaCode);
                entity.setBranchCode(branchCode);
                entity.setSubSystemCode(subSystemCode);
                entity.setBizTranGrpCode(bizTranGrpCode);
                entity.setBizTranCode(bizTranCode);
                entity.setSuspendStartDate(suspendStartDate);
                entity.setSuspendEndDate(suspendEndDate);
                entity.setSuspendReason(suspendReason);
                entity.setCreatedBy(createdBy);
                entity.setCreatedAt(createdAt);
                entity.setCreatedIpAddress(createdIpAddress);
                entity.setUpdatedBy(updatedBy);
                entity.setUpdatedAt(updatedAt);
                entity.setUpdatedIpAddress(updatedIpAddress);
                entity.setRecordVersion(recordVersion);
                return entity;
            }
            @Override
            public List<SuspendBizTranEntity> findBy(SuspendBizTranEntityCriteria criteria, Orders orders) {
                return suspendBizTranEntityList;
            }
            @Override
            public List<SuspendBizTranEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public int countBy(SuspendBizTranEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(SuspendBizTranEntity entity) {
                return 0;
            }
            @Override
            public int update(SuspendBizTranEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(SuspendBizTranEntity entity) {
                return 0;
            }
            @Override
            public int delete(SuspendBizTranEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(SuspendBizTranEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<SuspendBizTranEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<SuspendBizTranEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<SuspendBizTranEntity> entities) {
                return new int[0];
            }
        };
    }
    // JaAtMoment???????????????????????????
    private JaAtMomentRepository createJaAtMomentRepository() {
        return new JaAtMomentRepository() {
            @Override
            public JaAtMoment findOneBy(JaAtMomentCriteria criteria) {
                return createJaAtMoment();
            }
            @Override
            public JasAtMoment selectBy(JaAtMomentCriteria criteria, Orders orders) {
                return JasAtMoment.of(createJaAtMomentList());
            }
        };
    }
    // BranchAtMoment???????????????????????????
    private BranchAtMomentRepository createBranchAtMomentRepository() {
        return new BranchAtMomentRepository() {
            @Override
            public BranchAtMoment findOneBy(BranchAtMomentCriteria criteria) throws BranchNotFoundException {
                return createBranchAtMoment();
            }
            @Override
            public BranchesAtMoment selectBy(BranchAtMomentCriteria criteria, Orders orders) {
                return BranchesAtMoment.of(createBranchAtMomentList());
            }
        };
    }
    // ?????????????????????????????????????????????
    private BizTranGrpRepository createBizTranGrpRepository() {
        return new BizTranGrpRepository() {
            @Override
            public BizTranGrp findOneByCode(String bizTranGrpCode) {
                return createBizTranGrp();
            }
            @Override
            public BizTranGrps selectBy(BizTranGrpCriteria bizTranGrpCriteria, Orders orders) {
                return BizTranGrps.createFrom(createBizTranGrpList());
            }
            @Override
            public BizTranGrps selectAll(Orders orders) {
                return null;
            }
        };
    }
    // ?????????????????????????????????
    private BizTranRepository createBizTranRepository() {
        return new BizTranRepository() {
            @Override
            public BizTran findOneByCode(String bizTranCode) {
                return createBizTran();
            }
            @Override
            public BizTrans selectBy(BizTranCriteria bizTranCriteria, Orders orders) {
                return BizTrans.createFrom(createBizTranList());
            }
            @Override
            public BizTrans selectAll(Orders orders) {
                return null;
            }
        };
    }

    /**
     * {@link SuspendBizTranDataSource#findOneById(Long)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void findOneById_test0() {

        // ??????????????????????????????
        SuspendBizTranDataSource suspendBizTranDataSource = new SuspendBizTranDataSource(
            createSuspendBizTranEntityDao(),
            createJaAtMomentRepository(),
            createBranchAtMomentRepository(),
            createBizTranGrpRepository(),
            createBizTranRepository());

        // ?????????
        SuspendBizTran expectedSuspendBizTran = SuspendBizTran.createFrom(
            suspendBizTranId,
            jaCode,
            branchCode,
            subSystemCode,
            bizTranGrpCode,
            bizTranCode,
            suspendStartDate,
            suspendEndDate,
            suspendReason,
            recordVersion,
            createJaAtMoment(),
            createBranchAtMoment(),
            (Strings2.isEmpty(subSystemCode))? null : SubSystem.codeOf(subSystemCode),
            createBizTranGrp(),
            createBizTran()
        );

        // ??????
        SuspendBizTran actualSuspendBizTran = suspendBizTranDataSource.findOneById(suspendBizTranId);

        // ????????????
        assertThat(actualSuspendBizTran).usingRecursiveComparison().isEqualTo(expectedSuspendBizTran);
    }

    /**
     * {@link SuspendBizTranDataSource#findOneById(Long)}?????????
     *  ???????????????
     *    ??????
     *    ??????????????????JA?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     *
     *  ???????????????
     *  ???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void findOneById_test1() {

        // ?????????
        jaCode = null;
        branchCode = null;
        subSystemCode = null;
        bizTranGrpCode = null;
        bizTranCode = null;
        updatedBy = null;
        updatedAt =  null;
        updatedIpAddress = null;


        // ??????????????????????????????
        SuspendBizTranDataSource suspendBizTranDataSource = new SuspendBizTranDataSource(
            createSuspendBizTranEntityDao(),
            createJaAtMomentRepository(),
            createBranchAtMomentRepository(),
            createBizTranGrpRepository(),
            createBizTranRepository());

        // ?????????
        SuspendBizTran expectedSuspendBizTran = SuspendBizTran.createFrom(
            suspendBizTranId,
            jaCode,
            branchCode,
            subSystemCode,
            bizTranGrpCode,
            bizTranCode,
            suspendStartDate,
            suspendEndDate,
            suspendReason,
            recordVersion,
            createJaAtMoment(),
            createBranchAtMoment(),
            (Strings2.isEmpty(subSystemCode))? null : SubSystem.codeOf(subSystemCode),
            createBizTranGrp(),
            createBizTran()
        );

        // ??????
        SuspendBizTran actualSuspendBizTran = suspendBizTranDataSource.findOneById(suspendBizTranId);

        // ????????????
        assertThat(actualSuspendBizTran).usingRecursiveComparison().isEqualTo(expectedSuspendBizTran);
    }

    /**
     * {@link SuspendBizTranDataSource#selectBy(SuspendBizTranCriteria,Orders)}?????????
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
        SuspendBizTranCriteria criteria = createSuspendBizTranCriteria();
        Orders orders = Orders.empty()
            .addOrder("suspendStartDate")
            .addOrder("suspendEndDate")
            .addOrder("jaCode")
            .addOrder("branchCode")
            .addOrder("subSystemDisplaySortOrder")
            .addOrder("bizTranGrpCode")
            .addOrder("bizTranCode");

        // ??????????????????????????????
        SuspendBizTranDataSource suspendBizTranDataSource = new SuspendBizTranDataSource(
            createSuspendBizTranEntityDao(),
            createJaAtMomentRepository(),
            createBranchAtMomentRepository(),
            createBizTranGrpRepository(),
            createBizTranRepository());

        // ?????????
        List<JaAtMoment> jaAtMomentList = createJaAtMomentList();
        List<BranchAtMoment> branchAtMomentList = createBranchAtMomentList();
        List<BizTranGrp> bizTranGrpList = createBizTranGrpList();
        List<BizTran> bizTranList = createBizTranList();
        List<SuspendBizTran> expectedSuspendBizTranList = newArrayList();
        for(SuspendBizTranEntity entity : suspendBizTranEntityList) {
            expectedSuspendBizTranList.add(SuspendBizTran.createFrom(
                entity.getSuspendBizTranId(),
                (Strings2.isNull(entity.getJaCode()))? "" : entity.getJaCode(),
                (Strings2.isNull(entity.getBranchCode()))? "" : entity.getBranchCode(),
                (Strings2.isNull(entity.getSubSystemCode()))? "" : entity.getSubSystemCode(),
                (Strings2.isNull(entity.getBizTranGrpCode()))? "" : entity.getBizTranGrpCode(),
                (Strings2.isNull(entity.getBizTranCode()))? "" : entity.getBizTranCode(),
                entity.getSuspendStartDate(),
                entity.getSuspendEndDate(),
                entity.getSuspendReason(),
                entity.getRecordVersion(),
                jaAtMomentList.stream().filter(j->j.getJaAttribute().getJaCode().getValue().equals(entity.getJaCode())).findFirst().orElse(null),
                branchAtMomentList.stream().filter(b->b.getBranchAttribute().getBranchCode().getValue().equals(entity.getBranchCode())).findFirst().orElse(null),
                SubSystem.codeOf(entity.getSubSystemCode()),
                bizTranGrpList.stream().filter(b->b.getBizTranGrpCode().equals(entity.getBizTranGrpCode())).findFirst().orElse(null),
                bizTranList.stream().filter(b->b.getBizTranCode().equals(entity.getBizTranCode())).findFirst().orElse(null)));
        }
        expectedSuspendBizTranList = expectedSuspendBizTranList.stream().sorted(orders.toComparator()).collect(
            Collectors.toList());

        // ??????
        SuspendBizTrans actualSuspendBizTrans = suspendBizTranDataSource.selectBy(criteria, orders);

        // ????????????
        assertThat(actualSuspendBizTrans.getValues().size()).isEqualTo(expectedSuspendBizTranList.size());
        for(int i = 0; i < actualSuspendBizTrans.getValues().size(); i++) {
            assertThat(actualSuspendBizTrans.getValues().get(i)).as(i + 1 + "???????????????????????????")
                .usingRecursiveComparison().isEqualTo(expectedSuspendBizTranList.get(i));
        }
    }

    /**
     * {@link SuspendBizTranDataSource#selectBy(SuspendBizTranCriteria,Orders)}?????????
     *  ???????????????
     *    ?????????????????????0??????
     *
     *  ???????????????
     *  ???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void selectBy_test1() {

        // ?????????
        SuspendBizTranCriteria criteria = new SuspendBizTranCriteria();
        Orders orders = Orders.empty();
        suspendBizTranEntityList = newArrayList();

        // ??????????????????????????????
        SuspendBizTranDataSource suspendBizTranDataSource = new SuspendBizTranDataSource(
            createSuspendBizTranEntityDao(),
            createJaAtMomentRepository(),
            createBranchAtMomentRepository(),
            createBizTranGrpRepository(),
            createBizTranRepository());

        // ?????????
        int expectedSuspendBizTranListSize = 0;

        // ??????
        SuspendBizTrans actualSuspendBizTrans = suspendBizTranDataSource.selectBy(criteria, orders);

        // ????????????
        assertThat(actualSuspendBizTrans.getValues().size()).isEqualTo(expectedSuspendBizTranListSize);
    }
}