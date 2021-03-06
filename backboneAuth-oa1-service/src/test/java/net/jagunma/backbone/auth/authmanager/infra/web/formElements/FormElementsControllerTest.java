package net.jagunma.backbone.auth.authmanager.infra.web.formElements;

import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsLast;
import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchBranchAtMoment;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchJaAtMoment;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemSource;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.backbone.shared.application.ja.JaAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAtMomentCriteria;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import net.jagunma.common.values.model.ja.JasAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;

class FormElementsControllerTest {

    // ?????? ??? ?????? ?????????

    // JaAtMoment???????????????????????????
    private List<JaAtMoment> createJaAtMomentList() {
        List<JaAtMoment> list = newArrayList();
        list.add(createJaAtMoment(1L,"001","???????????????"));
        list.add(createJaAtMoment(6L,"006","???????????????"));
        list.add(createJaAtMoment(10L,"010","???????????????"));
        return list;
    }
    // JaAtMoment??????????????????
    private JaAtMoment createJaAtMoment(Long jaId, String jaCode, String jaName) {
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
    // BranchesAtMoment???????????????????????????
    private List<BranchAtMoment> createBranchAtMomentList() {
        List<BranchAtMoment> list = newArrayList();
        list.add(createBranchAtMoment(1L,"123","???????????????",1L));
        list.add(createBranchAtMoment(33L,"001","???????????????",6L));
        list.add(createBranchAtMoment(34L,"002","???????????????",6L));
        list.add(createBranchAtMoment(35L,"003","???????????????",6L));
        list.add(createBranchAtMoment(100L,"456","???????????????",10L));
        return list;
    }
    // BranchesAtMoment??????????????????
    private BranchAtMoment createBranchAtMoment(Long branchId, String branchCode, String branchName, Long jaId) {
        return BranchAtMoment.builder()
            .withIdentifier(branchId)
            .withJaAtMoment(createJaAtMomentList().stream().filter(j->j.getIdentifier().equals(jaId)).findFirst().orElse(null))
            .withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.??????).withBranchCode(BranchCode.of(branchCode)).withName(branchName).build())
            .build();
    }
    // ??????????????????????????????????????????
    private List<BizTranGrp> createBizTranGrpList() {
        List<BizTranGrp> list = newArrayList();
        list.add(BizTranGrp.createFrom(10001L,"ANTG01","?????????????????????????????????", SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTranGrp.createFrom(10002L,"ANTG02","????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTranGrp.createFrom(10003L,"ANTG10","????????????????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        return list;
    }
    // ?????????????????????????????????
    private BizTranGrp createBizTranGrp(Long bizTranGrpId) {
        return createBizTranGrpList().stream().filter(b -> b.getBizTranGrpId().equals(bizTranGrpId)).findFirst().orElse(null);
    }
    // ??????????????????????????????
    private List<BizTran> createBizTranList() {
        List<BizTran> list = newArrayList();
        list.add(BizTran.createFrom(100001L,"AN0001","???????????????????????????",false, LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTran.createFrom(100002L,"AN1510","????????????",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        list.add(BizTran.createFrom(100003L,"AN0002","??????????????????????????????????????????",true,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        return list;
    }
    // ?????????????????????
    private BizTran createBizTran(Long bizTranId) {
        return createBizTranList().stream().filter(b -> b.getBizTranId().equals(bizTranId)).findFirst().orElse(null);
    }

    // ??????????????????????????????
    private FormElementsController createFormElementsController() {
        // searchJaAtMoment????????????
        SearchJaAtMoment searchJaAtMoment = new SearchJaAtMoment(new JaAtMomentRepository() {
            @Override
            public JasAtMoment selectBy(JaAtMomentCriteria criteria, Orders orders) {
                return JasAtMoment.of(createJaAtMomentList());
            }
            @Override
            public JaAtMoment findOneBy(JaAtMomentCriteria criteria) {
                return null;
            }
        }) {
            public JasAtMoment selectBy() {
                return JasAtMoment.of(createJaAtMomentList());
            }
        };
        // SearchBranchAtMoment????????????
        SearchBranchAtMoment searchBranchAtMoment = new SearchBranchAtMoment(new BranchAtMomentRepository() {
            @Override
            public BranchesAtMoment selectBy(BranchAtMomentCriteria criteria, Orders orders) {
                List<BranchAtMoment> list = createBranchAtMomentList().stream().filter(b->b.getJaAtMoment().getJaAttribute().getJaCode().getValue().equals(criteria.getJaAtMomentCriteria().getJaAttributeCriteria().getJaCodeCriteria().getEqualTo().getValue())).collect(Collectors.toList());
                return BranchesAtMoment.of(list);
            }
            @Override
            public BranchAtMoment findOneBy(BranchAtMomentCriteria criteria) {
                return null;
            }
        }) {
            public BranchesAtMoment selectBy(long jaId) {
                return BranchesAtMoment.of(createBranchAtMomentList().stream().filter(b-> ((Long)jaId).equals(b.getJaAtMoment().getIdentifier())).collect(Collectors.toList()));
            }
        };
        // ??????????????????????????????????????????????????????
        BizTranGrpRepository bizTranGrpRepository = new BizTranGrpRepository() {
            @Override
            public BizTranGrps selectBy(BizTranGrpCriteria bizTranGrpCriteria, Orders orders) {
                return BizTranGrps.createFrom(createBizTranGrpList());
            }
            @Override
            public BizTranGrp findOneByCode(String bizTranGrpCode) {
                return createBizTranGrpList().stream().filter(b->b.getBizTranGrpCode().equals(bizTranGrpCode)).findFirst().orElse(null);
            }
            @Override
            public BizTranGrps selectAll(Orders orders) {
                return null;
            }
        };
        // ??????????????????_????????????????????????????????????????????????
        BizTranGrp_BizTranRepository bizTranGrp_BizTranRepository = new BizTranGrp_BizTranRepository() {
            @Override
            public BizTranGrp_BizTrans selectBy(BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria, Orders orders) {
                List<BizTranGrp_BizTran> list = newArrayList();
                int ix = 0;
                List<BizTran> bizTranList = createBizTranList();
                for (BizTranGrp bizTranGrp : createBizTranGrpList()) {
                    list.add(BizTranGrp_BizTran.createFrom(
                        (long)(ix+1),
                        bizTranGrp.getBizTranGrpId(),
                        bizTranList.get(ix).getBizTranId(),
                        bizTranGrp.getSubSystemCode(),
                        1,
                        bizTranGrp,
                        bizTranList.get(ix),
                        bizTranGrp.getSubSystem()
                    ));
                    ix++;
                }
                return BizTranGrp_BizTrans.createFrom(list);
            }
            @Override
            public BizTranGrp_BizTrans selectAll(Orders orders) {
                return null;
            }
        };
        return new FormElementsController(searchJaAtMoment, searchBranchAtMoment, bizTranGrpRepository, bizTranGrp_BizTranRepository);
    }

    /**
     * ??????????????????????????????????????????
     *
     * @param obj ????????????????????????????????????
     * @param <T> ???????????????Generics
     * @return ?????????????????????????????????
     */
    @SuppressWarnings("unchecked")
    public static <T> T autoCast(Object obj) {
        return (T) obj;
    }

    /**
     * {@link FormElementsController#getJaItemsSource(String, ModelMap)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???????????????ItemsSource??????SelectOptionItemsSource???
     */
    @Test
    @Tag(TestSize.SMALL)
    void getJaItemsSource_test0() {

        // ??????????????????????????????
        FormElementsController formElementsController = createFormElementsController();

        // ?????????
        String viewId = "oa12020";
        ModelMap model = new ModelMap();

        // ?????????
        String expectedItemsSourceName = "oa12020::ajaxSelectJa";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
        for (JaAtMoment jaAtMoment : createJaAtMomentList()) {
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                jaAtMoment.getIdentifier(), jaAtMoment.getJaAttribute().getJaCode().getValue(), jaAtMoment.getJaAttribute().getName()
            ));
        }

        // ??????
        String actualItemsSourceName = formElementsController.getJaItemsSource(viewId, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // ????????????
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBranchItemsSource(String, String, ModelMap)}?????????
     *  ???????????????
     *    ??????
     *    ???JaCode???null?????????0??????
     *
     *  ???????????????
     *  ???????????????ItemsSource??????SelectOptionItemsSource???
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBranchItemsSource_test0() {

        // ??????????????????????????????
        FormElementsController formElementsController = createFormElementsController();

        // ?????????
        String viewId = "oa12020";
        String jaCode = null;
        ModelMap model = new ModelMap();

        // ?????????
        String expectedItemsSourceName = "oa12020::ajaxSelectBranch";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();

        // ??????
        String actualItemsSourceName = formElementsController.getBranchItemsSource(viewId, jaCode, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // ????????????
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBranchItemsSource(String, String, ModelMap)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???????????????ItemsSource??????SelectOptionItemsSource???
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBranchItemsSource_test1() {

        // ??????????????????????????????
        FormElementsController formElementsController = createFormElementsController();

        // ?????????
        String viewId = "oa12020";
        String jaCode = "006";
        ModelMap model = new ModelMap();

        // ?????????
        String expectedItemsSourceName = "oa12020::ajaxSelectBranch";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
        for (BranchAtMoment branchAtMoment : createBranchAtMomentList().stream().filter(b-> jaCode.equals(b.getJaAtMoment().getJaAttribute().getJaCode().getValue())).collect(Collectors.toList())) {
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                branchAtMoment.getIdentifier(), branchAtMoment.getBranchAttribute().getBranchCode().getValue(), branchAtMoment.getBranchAttribute().getName()
            ));
        }

        // ??????
        String actualItemsSourceName = formElementsController.getBranchItemsSource(viewId, jaCode, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // ????????????
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getSubSystemItemsSource(String, ModelMap)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???????????????ItemsSource??????SelectOptionItemsSource???
     */
    @Test
    @Tag(TestSize.SMALL)
    void getSubSystemItemsSource_test0() {

        // ??????????????????????????????
        FormElementsController formElementsController = createFormElementsController();

        // ?????????
        String viewId = "oa12020";
        ModelMap model = new ModelMap();

        // ?????????
        String expectedItemsSourceName = "oa12020::ajaxSelectSubSystem";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
        for (SubSystem subSystem : SubSystem.getValidValues()) {
            if (subSystem.getCode().isEmpty()) { continue; }
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                null, subSystem.getCode(), subSystem.getDisplayName()));
        }

        // ??????
        String actualItemsSourceName = formElementsController.getSubSystemItemsSource(viewId, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // ????????????
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBizTranGrpItemsSource(String, String, String, ModelMap)}?????????
     *  ???????????????
     *    ??????
     *    ???SunSystem???null?????????0??????
     *
     *  ???????????????
     *  ???????????????ItemsSource??????SelectOptionItemsSource???
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBizTranGrpItemsSource_test0() {

        // ??????????????????????????????
        FormElementsController formElementsController = createFormElementsController();

        // ?????????
        String viewId = "oa12020";
        String subSystemCode = null;
        String firstRowStatus = null;
        ModelMap model = new ModelMap();

        // ?????????
        String expectedItemsSourceName = "oa12020::ajaxSelectBizTranGrp";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();

        // ??????
        String actualItemsSourceName = formElementsController.getBizTranGrpItemsSource(viewId, subSystemCode, firstRowStatus, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // ????????????
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBizTranGrpItemsSource(String, String, String, ModelMap)}?????????
     *  ???????????????
     *    ??????
     *    ???SunSystem?????????
     *    ???firstRowStatus???null????????????????????????
     *
     *  ???????????????
     *  ???????????????ItemsSource??????SelectOptionItemsSource???
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBizTranGrpItemsSource_test1() {

        // ??????????????????????????????
        FormElementsController formElementsController = createFormElementsController();

        // ?????????
        String viewId = "oa12020";
        String subSystemCode = SubSystem.??????_??????.getCode();
        String firstRowStatus = null;
        ModelMap model = new ModelMap();

        // ?????????
        String expectedItemsSourceName = "oa12020::ajaxSelectBizTranGrp";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        for (BizTranGrp bizTranGrp : createBizTranGrpList()) {
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                bizTranGrp.getBizTranGrpId(), bizTranGrp.getBizTranGrpCode(), bizTranGrp.getBizTranGrpName()
            ));
        }

        // ??????
        String actualItemsSourceName = formElementsController.getBizTranGrpItemsSource(viewId, subSystemCode, firstRowStatus, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // ????????????
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBizTranGrpItemsSource(String, String, String, ModelMap)}?????????
     *  ???????????????
     *    ??????
     *    ???SunSystem?????????
     *    ???firstRowStatus???""????????????????????????
     *
     *  ???????????????
     *  ???????????????ItemsSource??????SelectOptionItemsSource???
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBizTranGrpItemsSource_test2() {

        // ??????????????????????????????
        FormElementsController formElementsController = createFormElementsController();

        // ?????????
        String viewId = "oa12020";
        String subSystemCode = SubSystem.??????_??????.getCode();
        String firstRowStatus = "";
        ModelMap model = new ModelMap();

        // ?????????
        String expectedItemsSourceName = "oa12020::ajaxSelectBizTranGrp";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
        for (BizTranGrp bizTranGrp : createBizTranGrpList()) {
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                bizTranGrp.getBizTranGrpId(), bizTranGrp.getBizTranGrpCode(), bizTranGrp.getBizTranGrpName()
            ));
        }

        // ??????
        String actualItemsSourceName = formElementsController.getBizTranGrpItemsSource(viewId, subSystemCode, firstRowStatus, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // ????????????
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBizTranGrpItemsSource(String, String, String, ModelMap)}?????????
     *  ???????????????
     *    ??????
     *    ???SunSystem?????????
     *    ???firstRowStatus???"????????????"??????????????????????????????
     *
     *  ???????????????
     *  ???????????????ItemsSource??????SelectOptionItemsSource???
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBizTranGrpItemsSource_test3() {

        // ??????????????????????????????
        FormElementsController formElementsController = createFormElementsController();

        // ?????????
        String viewId = "oa12020";
        String subSystemCode = SubSystem.??????_??????.getCode();
        String firstRowStatus = "????????????";
        ModelMap model = new ModelMap();

        // ?????????
        String expectedItemsSourceName = "oa12020::ajaxSelectBizTranGrp";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
        expectedItemsSourcelist.get(0).setName(firstRowStatus);
        for (BizTranGrp bizTranGrp : createBizTranGrpList()) {
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                bizTranGrp.getBizTranGrpId(), bizTranGrp.getBizTranGrpCode(), bizTranGrp.getBizTranGrpName()
            ));
        }

        // ??????
        String actualItemsSourceName = formElementsController.getBizTranGrpItemsSource(viewId, subSystemCode, firstRowStatus, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // ????????????
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBizTranItemsSource(String, String, String, String, ModelMap)}?????????
     *  ???????????????
     *    ??????
     *    ???SunSystem???null?????????0??????
     *
     *  ???????????????
     *  ???????????????ItemsSource??????SelectOptionItemsSource???
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBizTranItemsSource_test0() {

        // ??????????????????????????????
        FormElementsController formElementsController = createFormElementsController();

        // ?????????
        String viewId = "oa12020";
        String subSystemCode = null;
        String bizTranGrpCode = null;
        String firstRowStatus = null;
        ModelMap model = new ModelMap();

        // ?????????
        String expectedItemsSourceName = "oa12020::ajaxSelectBizTran";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();

        // ??????
        String actualItemsSourceName = formElementsController.getBizTranItemsSource(viewId, subSystemCode, bizTranGrpCode, firstRowStatus, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // ????????????
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBizTranItemsSource(String, String, String, String, ModelMap)}?????????
     *  ???????????????
     *    ??????
     *    ???SunSystem?????????
     *    ???bizTranGrpCode???null
     *    ???firstRowStatus???null????????????????????????
     *
     *  ???????????????
     *  ???????????????ItemsSource??????SelectOptionItemsSource???
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBizTranItemsSource_test1() {

        // ??????????????????????????????
        FormElementsController formElementsController = createFormElementsController();

        // ?????????
        String viewId = "oa12020";
        String subSystemCode = SubSystem.??????_??????.getCode();
        String bizTranGrpCode = null;
        String firstRowStatus = null;
        ModelMap model = new ModelMap();

        // ?????????
        String expectedItemsSourceName = "oa12020::ajaxSelectBizTran";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        Comparator<BizTran> comparator = Comparator.comparing(BizTran::getBizTranCode, nullsLast(naturalOrder()));
        for (BizTran bizTran : createBizTranList().stream().sorted(comparator).collect(Collectors.toList())) {
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                bizTran.getBizTranId(), bizTran.getBizTranCode(), bizTran.getBizTranName()
            ));
        }

        // ??????
        String actualItemsSourceName = formElementsController.getBizTranItemsSource(viewId, subSystemCode, bizTranGrpCode, firstRowStatus, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // ????????????
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBizTranItemsSource(String, String, String, String, ModelMap)}?????????
     *  ???????????????
     *    ??????
     *    ???SunSystem?????????
     *    ???bizTranGrpCode?????????
     *    ???firstRowStatus???null????????????????????????
     *
     *  ???????????????
     *  ???????????????ItemsSource??????SelectOptionItemsSource???
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBizTranItemsSource_test2() {

        // ??????????????????????????????
        FormElementsController formElementsController = createFormElementsController();

        // ?????????
        String viewId = "oa12020";
        String subSystemCode = SubSystem.??????_??????.getCode();
        String bizTranGrpCode = "ANTG01";
        String firstRowStatus = null;
        ModelMap model = new ModelMap();

        // ?????????
        String expectedItemsSourceName = "oa12020::ajaxSelectBizTran";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        Comparator<BizTran> comparator = Comparator.comparing(BizTran::getBizTranCode, nullsLast(naturalOrder()));
        for (BizTran bizTran : createBizTranList().stream().sorted(comparator).collect(Collectors.toList())) {
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                bizTran.getBizTranId(), bizTran.getBizTranCode(), bizTran.getBizTranName()
            ));
        }

        // ??????
        String actualItemsSourceName = formElementsController.getBizTranItemsSource(viewId, subSystemCode, bizTranGrpCode, firstRowStatus, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // ????????????
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBizTranItemsSource(String, String, String, String, ModelMap)}?????????
     *  ???????????????
     *    ??????
     *    ???SunSystem?????????
     *    ???bizTranGrpCode?????????
     *    ???firstRowStatus???""????????????????????????
     *
     *  ???????????????
     *  ???????????????ItemsSource??????SelectOptionItemsSource???
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBizTranItemsSource_test3() {

        // ??????????????????????????????
        FormElementsController formElementsController = createFormElementsController();

        // ?????????
        String viewId = "oa12020";
        String subSystemCode = SubSystem.??????_??????.getCode();
        String bizTranGrpCode = "ANTG01";
        String firstRowStatus = "";
        ModelMap model = new ModelMap();

        // ?????????
        String expectedItemsSourceName = "oa12020::ajaxSelectBizTran";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
        Comparator<BizTran> comparator = Comparator.comparing(BizTran::getBizTranCode, nullsLast(naturalOrder()));
        for (BizTran bizTran : createBizTranList().stream().sorted(comparator).collect(Collectors.toList())) {
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                bizTran.getBizTranId(), bizTran.getBizTranCode(), bizTran.getBizTranName()
            ));
        }

        // ??????
        String actualItemsSourceName = formElementsController.getBizTranItemsSource(viewId, subSystemCode, bizTranGrpCode, firstRowStatus, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // ????????????
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBizTranItemsSource(String, String, String, String, ModelMap)}?????????
     *  ???????????????
     *    ??????
     *    ???SunSystem?????????
     *    ???bizTranGrpCode?????????
     *    ???firstRowStatus???"????????????"??????????????????????????????
     *
     *  ???????????????
     *  ???????????????ItemsSource??????SelectOptionItemsSource???
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBizTranItemsSource_test4() {

        // ??????????????????????????????
        FormElementsController formElementsController = createFormElementsController();

        // ?????????
        String viewId = "oa12020";
        String subSystemCode = SubSystem.??????_??????.getCode();
        String bizTranGrpCode = "ANTG01";
        String firstRowStatus = "????????????";
        ModelMap model = new ModelMap();

        // ?????????
        String expectedItemsSourceName = "oa12020::ajaxSelectBizTran";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
        expectedItemsSourcelist.get(0).setName(firstRowStatus);
        Comparator<BizTran> comparator = Comparator.comparing(BizTran::getBizTranCode, nullsLast(naturalOrder()));
        for (BizTran bizTran : createBizTranList().stream().sorted(comparator).collect(Collectors.toList())) {
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                bizTran.getBizTranId(), bizTran.getBizTranCode(), bizTran.getBizTranName()
            ));
        }

        // ??????
        String actualItemsSourceName = formElementsController.getBizTranItemsSource(viewId, subSystemCode, bizTranGrpCode, firstRowStatus, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // ????????????
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }
}