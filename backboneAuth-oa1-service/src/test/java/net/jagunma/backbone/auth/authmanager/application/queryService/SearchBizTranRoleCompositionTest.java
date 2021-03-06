package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionReference.BizTranRoleCompositionExportRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionReference.BizTranRoleCompositionExportResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SearchBizTranRoleCompositionTest {

    // ???????????????
    private String subSystemCode = SubSystem.??????_??????.getCode();
    //  ???????????????????????????????????????
    private List<BizTranRole> createBizTranRoleList() {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(1001L,"ANAG01","????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        return list;
    }
    //  ??????????????????????????????????????????
    private List<BizTranGrp> createBizTranGrpList() {
        List<BizTranGrp> list = newArrayList();
        list.add(BizTranGrp.createFrom(101L,"ANTG01","?????????????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        return list;
    }
    //  ??????????????????????????????
    private List<BizTran> createBizTranList() {
        List<BizTran> list = newArrayList();
        list.add(BizTran.createFrom(1L,"AN0001","???????????????????????????",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        return list;
    }
    // ???????????????_????????????????????????????????????????????????
    private List<BizTranRole_BizTranGrp> createBizTranRole_BizTranGrpList() {
        List<BizTranRole> bizTranRoleList = createBizTranRoleList();
        List<BizTranGrp> bizTranGrpList = createBizTranGrpList();

        List<BizTranRole_BizTranGrp> list = newArrayList();
        list.add(BizTranRole_BizTranGrp.createFrom(10001L,1001L,101L,SubSystem.??????_??????.getCode(),1,
            bizTranRoleList.stream().filter(b->b.getBizTranRoleId().equals(1001L)).findFirst().orElse(null),
            bizTranGrpList.stream().filter(b->b.getBizTranGrpId().equals(101L)).findFirst().orElse(null),
            SubSystem.??????_??????));
        return list;
    }
    // ??????????????????_????????????????????????????????????
    private List<BizTranGrp_BizTran> createBizTranGrp_BizTranList() {
        List<BizTranGrp> bizTranGrpList = createBizTranGrpList();
        List<BizTran> bizTranList = createBizTranList();

        List<BizTranGrp_BizTran> list = newArrayList();
        list.add(BizTranGrp_BizTran.createFrom(10001L,101L,1L,SubSystem.??????_??????.getCode(),1,
            bizTranGrpList.stream().filter(b->b.getBizTranGrpId().equals(101L)).findFirst().orElse(null),
            bizTranList.stream().filter(b->b.getBizTranId().equals(1L)).findFirst().orElse(null),
            SubSystem.??????_??????));
        return list;
    }

    // ???????????????_???????????????????????????????????????
    private BizTranRole_BizTranGrpRepository createBizTranRole_BizTranGrpRepository() {
        return new BizTranRole_BizTranGrpRepository() {
            @Override
            public BizTranRole_BizTranGrps selectBy(BizTranRole_BizTranGrpCriteria bizTranRole_BizTranGrpCriteria, Orders orders) {
                return BizTranRole_BizTranGrps.createFrom(createBizTranRole_BizTranGrpList());
            }
            @Override
            public BizTranRole_BizTranGrps selectAll(Orders orders) {
                return null;
            }
        };
    }
    // ??????????????????_???????????????????????????
    private BizTranGrp_BizTranRepository createBizTranGrp_BizTranRepository() {
        return new BizTranGrp_BizTranRepository() {
            @Override
            public BizTranGrp_BizTrans selectBy(BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria, Orders orders) {
                return BizTranGrp_BizTrans.createFrom(createBizTranGrp_BizTranList());
            }
            @Override
            public BizTranGrp_BizTrans selectAll(Orders orders) {
                return null;
            }
        };
    }
    // ????????????????????????????????????????????????????????? ???????????????????????? Request???????????????
    private BizTranRoleCompositionExportRequest createBizTranRoleCompositionExportRequest() {
        return new BizTranRoleCompositionExportRequest() {
            @Override
            public String getSubSystemCode() {
                return subSystemCode;
            }
        };
    }

    /**
     * {@link SearchBizTranRoleComposition#execute(BizTranRoleCompositionExportRequest,BizTranRoleCompositionExportResponse)}????????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test0() {

        // ?????????
        List<BizTranRole_BizTranGrpSheet> expectedBizTranRole_BizTranGrpSheetList = newArrayList();
        for (BizTranRole_BizTranGrp bizTranRole_BizTranGrp : createBizTranRole_BizTranGrpList()) {
            expectedBizTranRole_BizTranGrpSheetList.add(BizTranRole_BizTranGrpSheet.createFrom(
                expectedBizTranRole_BizTranGrpSheetList.size(),
                bizTranRole_BizTranGrp.getSubSystem().getDisplayName(),
                bizTranRole_BizTranGrp.getBizTranRole().getBizTranRoleCode(),
                bizTranRole_BizTranGrp.getBizTranRole().getBizTranRoleName(),
                bizTranRole_BizTranGrp.getBizTranGrp().getBizTranGrpCode(),
                bizTranRole_BizTranGrp.getBizTranGrp().getBizTranGrpName()));
        }
        List<BizTranGrp_BizTranSheet> expectedBizTranGrp_BizTranSheetList = newArrayList();
        for (BizTranGrp_BizTran bizTranGrp_BizTran : createBizTranGrp_BizTranList()) {
            expectedBizTranGrp_BizTranSheetList.add(BizTranGrp_BizTranSheet.createFrom(
                expectedBizTranGrp_BizTranSheetList.size(),
                bizTranGrp_BizTran.getSubSystem().getDisplayName(),
                bizTranGrp_BizTran.getBizTranGrp().getBizTranGrpCode(),
                bizTranGrp_BizTran.getBizTranGrp().getBizTranGrpName(),
                bizTranGrp_BizTran.getBizTran().getBizTranCode(),
                bizTranGrp_BizTran.getBizTran().getBizTranName(),
                bizTranGrp_BizTran.getBizTran().getIsCenterBizTran(),
                bizTranGrp_BizTran.getBizTran().getValidThruStartDate(),
                bizTranGrp_BizTran.getBizTran().getValidThruEndDate()));
        }

        // ??????????????????????????????
        SearchBizTranRoleComposition searchBizTranRoleComposition = new SearchBizTranRoleComposition(
            createBizTranRole_BizTranGrpRepository(),
            createBizTranGrp_BizTranRepository());

        // ??????
        searchBizTranRoleComposition.execute(createBizTranRoleCompositionExportRequest(),
            new BizTranRoleCompositionExportResponse() {
                @Override
                public void setBizTranRole_BizTranGrpsSheet(BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet) {
                    // ????????????
                    for(int i = 0; i < bizTranRole_BizTranGrpsSheet.getValues().size(); i++) {
                        assertThat(bizTranRole_BizTranGrpsSheet.getValues().get(i)).as(i + 1 + "???????????????????????????")
                            .usingRecursiveComparison().isEqualTo(expectedBizTranRole_BizTranGrpSheetList.get(i));
                    }
                }
                @Override
                public void setBizTranGrp_BizTransSheet(BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet) {
                    // ????????????
                    for(int i = 0; i < bizTranGrp_BizTransSheet.getValues().size(); i++) {
                        assertThat(bizTranGrp_BizTransSheet.getValues().get(i)).as(i + 1 + "???????????????????????????")
                            .usingRecursiveComparison().isEqualTo(expectedBizTranGrp_BizTranSheetList.get(i));
                    }
                }
            });
    }
}