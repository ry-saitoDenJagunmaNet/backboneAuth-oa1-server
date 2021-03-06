package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTranSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTranSearchResponse;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTransSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTransSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTrans;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SearchSuspendBizTranTest {

    // ???????????????
    private List<SuspendBizTran> suspendBizTranList = createSuspendBizTranList();

    // ?????????
    private SuspendBizTrans actualSuspendBizTrans;
    private Long actualSuspendBizTranId;
    private String actualJaCode;
    private String actualBranchCode;
    private String actualSubSystemCode;
    private String actualBizTranGrpCode;
    private String actualBizTranCode;
    private LocalDate actualSuspendStartDate;
    private LocalDate actualSuspendEndDate;
    private String actualSuspendReason;
    private Integer actualRecordVersion;


    // ??????????????????????????????????????????
    private List<SuspendBizTran> createSuspendBizTranList() {
        List<SuspendBizTran> list = newArrayList();
        list.add(SuspendBizTran.createFrom(1L,"006","001",SubSystem.??????_??????.getCode(),"ANTG01","AN0001",LocalDate.of(2020,11,1),LocalDate.of(2020,11,2),"???????????????????????????",1,
            createJaAtMoment().stream().filter(j->j.getIdentifier().equals(33L)).findFirst().orElse(null),
            createBranchAtMoment().stream().filter(j->j.getIdentifier().equals(6L)).findFirst().orElse(null),
            SubSystem.??????_??????,
            createBizTranGrpList().stream().filter(b->b.getBizTranGrpId().equals(10001L)).findFirst().orElse(null),
            createBizTranList().stream().filter(b->b.getBizTranId().equals(100001L)).findFirst().orElse(null)));
        list.add(SuspendBizTran.createFrom(2L,null,null,SubSystem.??????.getCode(),null,null,LocalDate.of(2020,11,1),LocalDate.of(2020,11,2),"???????????????????????????",1,
            null,null,SubSystem.??????,null,null));
        return list;
    }
    // ??????????????????????????????
    private List<BranchAtMoment> createBranchAtMoment() {
        List<BranchAtMoment> list = newArrayList();
        list.add(BranchAtMoment.builder().withIdentifier(32L)
            .withJaAtMoment(createJaAtMoment().stream().filter(j->j.getIdentifier().equals(6L)).findFirst().orElse(null))
            .withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.??????).withBranchCode(BranchCode.of("005")).withName("005??????").build()).build());
        list.add(BranchAtMoment.builder().withIdentifier(33L)
            .withJaAtMoment(createJaAtMoment().stream().filter(j->j.getIdentifier().equals(6L)).findFirst().orElse(null))
            .withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.??????).withBranchCode(BranchCode.of("006")).withName("006??????").build()).build());
        list.add(BranchAtMoment.builder().withIdentifier(34L)
            .withJaAtMoment(createJaAtMoment().stream().filter(j->j.getIdentifier().equals(6L)).findFirst().orElse(null))
            .withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.??????).withBranchCode(BranchCode.of("007")).withName("007??????").build()).build());
        return list;
    }
    // ??????????????????????????????
    private List<JaAtMoment> createJaAtMoment() {
        List<JaAtMoment> list = newArrayList();
        list.add(JaAtMoment.builder().withIdentifier(6L)
            .withJaAttribute(JaAttribute.builder().withJaCode(JaCode.of("006")).withName("JA006").withFormalName("").withAbbreviatedName("").build()).build());
        return list;
    }
    // ??????????????????????????????????????????
    private List<BizTranGrp> createBizTranGrpList() {
        List<BizTranGrp> list = newArrayList();
        list.add(BizTranGrp.createFrom(10001L,"ANTG01","?????????????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        return list;
    }
    // ??????????????????????????????????????????
    private List<BizTran> createBizTranList() {
        List<BizTran> list = newArrayList();
        list.add(BizTran.createFrom(100001L,"AN0001","???????????????????????????",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTran.createFrom(100002L,"AN1110","??????????????????",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        return list;
    }
    // ??????????????????????????????
    private SuspendBizTranRepository createSuspendBizTranRepository() {
        return new SuspendBizTranRepository() {
            @Override
            public SuspendBizTran findOneById(Long suspendBizTranId) {
                return suspendBizTranList.get(0);
            }
            @Override
            public SuspendBizTrans selectBy(SuspendBizTranCriteria suspendBizTranCriteria, Orders orders) {
                return SuspendBizTrans.createFrom(suspendBizTranList);
            }
        };
    }
    // ??????????????????????????????????????? Request??????
    private SuspendBizTransSearchRequest createSuspendBizTransSearchRequest() {
        return new SuspendBizTransSearchRequest() {
            @Override
            public StringCriteria getJaCodeCriteria() {
                return new StringCriteria();
            }
            @Override
            public StringCriteria getBranchCodeCriteria() {
                return new StringCriteria();
            }
            @Override
            public StringCriteria getSubSystemCodeCriteria() {
                return new StringCriteria();
            }
            @Override
            public StringCriteria getBizTranGrpCodeCriteria() {
                return new StringCriteria();
            }
            @Override
            public StringCriteria getBizTranCodeCriteria() {
                return new StringCriteria();
            }
            @Override
            public LocalDateCriteria getSuspendStartDateCriteria() {
                return new LocalDateCriteria();
            }
            @Override
            public LocalDateCriteria getSuspendEndDateCriteria() {
                return new LocalDateCriteria();
            }
            @Override
            public StringCriteria getSuspendReasonCriteria() {
                return new StringCriteria();
            }
        };
    }
    // ??????????????????????????????????????? Response??????
    private SuspendBizTransSearchResponse createSuspendBizTransSearchResponse() {
        return new SuspendBizTransSearchResponse() {
            @Override
            public void setSuspendBizTrans(SuspendBizTrans suspendBizTrans) {
                actualSuspendBizTrans = suspendBizTrans;
            }
            @Override
            public void setJaCode(String jaCode) {
            }
            @Override
            public void setBranchCode(String branchCode) {
            }
            @Override
            public void setSubSystemCode(String subSystemCode) {
            }
            @Override
            public void setBizTranGrpCode(String bizTranGrpCode) {
            }
            @Override
            public void setBizTranCode(String bizTranCode) {
            }
            @Override
            public void setSuspendConditionsSelect(Integer suspendConditionsSelect) {
            }
            @Override
            public void setSuspendStatusDate(LocalDate suspendStatusDate) {
            }
            @Override
            public void setSuspendStatusStartDateFrom(LocalDate suspendStatusStartDateFrom) {
            }
            @Override
            public void setSuspendStatusStartDateTo(LocalDate suspendStatusStartDateTo) {
            }
            @Override
            public void setSuspendStatusEndDateFrom(LocalDate suspendStatusEndDateFrom) {
            }
            @Override
            public void setSuspendStatusEndDateTo(LocalDate suspendStatusEndDateTo) {
            }
            @Override
            public void setSuspendReason(String suspendReason) {
            }
            @Override
            public void setPageNo(Integer pageNo) {
            }
        };
    }
    // ??????????????????????????????????????? Request??????
    private SuspendBizTranSearchRequest createSuspendBizTranSearchRequest() {
        return new SuspendBizTranSearchRequest() {
            @Override
            public Long getSuspendBizTranId() {
                return null;
            }
        };
    }
    // ???????????????????????????????????? Response??????
    private SuspendBizTranSearchResponse createSuspendBizTranSearchResponse() {
        return new SuspendBizTranSearchResponse() {
            @Override
            public void setSuspendBizTranId(Long suspendBizTranId) {
                actualSuspendBizTranId = suspendBizTranId;
            }
            @Override
            public void setJaCode(String jaCode) {
                actualJaCode = jaCode;
            }
            @Override
            public void setBranchCode(String branchCode) {
                actualBranchCode = branchCode;
            }
            @Override
            public void setSubSystemCode(String subSystemCode) {
                actualSubSystemCode = subSystemCode;
            }
            @Override
            public void setBizTranGrpCode(String bizTranGrpCode) {
                actualBizTranGrpCode = bizTranGrpCode;
            }
            @Override
            public void setBizTranCode(String bizTranCode) {
                actualBizTranCode = bizTranCode;
            }
            @Override
            public void setSuspendStartDate(LocalDate suspendStartDate) {
                actualSuspendStartDate = suspendStartDate;
            }
            @Override
            public void setSuspendEndDate(LocalDate suspendEndDate) {
                actualSuspendEndDate = suspendEndDate;
            }
            @Override
            public void setSuspendReason(String suspendReason) {
                actualSuspendReason = suspendReason;
            }
            @Override
            public void setRecordVersion(Integer recordVersion) {
                actualRecordVersion = recordVersion;
            }
        };
    }

    /**
     * {@link SearchSuspendBizTran#execute(SuspendBizTransSearchRequest, SuspendBizTransSearchResponse)}????????????
     *  ???????????????
     *    ??????
     *    ??????????????????????????????
     *
     *  ???????????????
     *  ???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test0() {

        // ??????????????????????????????
        SearchSuspendBizTran searchSuspendBizTran = new SearchSuspendBizTran(createSuspendBizTranRepository());

        // ?????????
        List<SuspendBizTran> expectedSuspendBizTranList = suspendBizTranList;
        
        // ??????
        searchSuspendBizTran.execute(
            createSuspendBizTransSearchRequest(),
            createSuspendBizTransSearchResponse());

        // ????????????
        for(int i = 0; i < actualSuspendBizTrans.getValues().size(); i++) {
            assertThat(actualSuspendBizTrans.getValues().get(i)).as(i + 1 + "???????????????????????????")
                .usingRecursiveComparison().isEqualTo(expectedSuspendBizTranList.get(i));
        }
    }

    /**
     * {@link SearchSuspendBizTran#execute(SuspendBizTransSearchRequest, SuspendBizTransSearchResponse)}????????????
     *  ???????????????
     *    ??????
     *    ?????????????????????????????????????????????0??????
     *
     *  ???????????????
     *  ???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test1() {

        // ?????????
        suspendBizTranList = newArrayList();

        // ??????????????????????????????
        SearchSuspendBizTran searchSuspendBizTran = new SearchSuspendBizTran(createSuspendBizTranRepository());

        // ??????
        searchSuspendBizTran.execute(
            createSuspendBizTransSearchRequest(),
            createSuspendBizTransSearchResponse());

        // ????????????
        assertThat(actualSuspendBizTrans.getValues().size()).isEqualTo(0);
    }

    /**
     * {@link SearchSuspendBizTran#execute(SuspendBizTransSearchRequest, SuspendBizTransSearchResponse)}????????????
     *  ???????????????
     *    ??????
     *    ???????????????????????????
     *
     *  ???????????????
     *  ???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test2() {

        // ??????????????????????????????
        SearchSuspendBizTran searchSuspendBizTran = new SearchSuspendBizTran(createSuspendBizTranRepository());

        // ?????????
        List<SuspendBizTran> expectedSuspendBizTranList = suspendBizTranList;

        // ??????
        searchSuspendBizTran.execute(
            createSuspendBizTranSearchRequest(),
            createSuspendBizTranSearchResponse());

        // ????????????
        assertThat(actualSuspendBizTranId).isEqualTo(suspendBizTranList.get(0).getSuspendBizTranId());
        assertThat(actualJaCode).isEqualTo(suspendBizTranList.get(0).getJaCode());
        assertThat(actualBranchCode).isEqualTo(suspendBizTranList.get(0).getBranchCode());
        assertThat(actualSubSystemCode).isEqualTo(suspendBizTranList.get(0).getSubSystemCode());
        assertThat(actualBizTranGrpCode).isEqualTo(suspendBizTranList.get(0).getBizTranGrpCode());
        assertThat(actualBizTranCode).isEqualTo(suspendBizTranList.get(0).getBizTranCode());
        assertThat(actualSuspendStartDate).isEqualTo(suspendBizTranList.get(0).getSuspendStartDate());
        assertThat(actualSuspendEndDate).isEqualTo(suspendBizTranList.get(0).getSuspendEndDate());
        assertThat(actualSuspendReason).isEqualTo(suspendBizTranList.get(0).getSuspendReason());
        assertThat(actualRecordVersion).isEqualTo(suspendBizTranList.get(0).getRecordVersion());
    }

}