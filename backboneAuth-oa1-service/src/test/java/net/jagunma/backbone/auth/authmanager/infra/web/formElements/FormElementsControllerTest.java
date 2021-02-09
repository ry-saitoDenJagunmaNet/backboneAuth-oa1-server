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

    // 実行 ＆ 期待 既定値

    // JaAtMomentリストデータの作成
    private List<JaAtMoment> createJaAtMomentList() {
        List<JaAtMoment> list = newArrayList();
        list.add(createJaAtMoment(1L,"001","ＪＡ００１"));
        list.add(createJaAtMoment(6L,"006","ＪＡ００６"));
        list.add(createJaAtMoment(10L,"010","ＪＡ０１０"));
        return list;
    }
    // JaAtMomentデータの作成
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
    // BranchesAtMomentリストデータの作成
    private List<BranchAtMoment> createBranchAtMomentList() {
        List<BranchAtMoment> list = newArrayList();
        list.add(createBranchAtMoment(1L,"123","店舗１２３",1L));
        list.add(createBranchAtMoment(33L,"001","店舗００１",6L));
        list.add(createBranchAtMoment(34L,"002","店舗００２",6L));
        list.add(createBranchAtMoment(35L,"003","店舗００３",6L));
        list.add(createBranchAtMoment(100L,"456","店舗４５６",10L));
        return list;
    }
    // BranchesAtMomentデータの作成
    private BranchAtMoment createBranchAtMoment(Long branchId, String branchCode, String branchName, Long jaId) {
        return BranchAtMoment.builder()
            .withIdentifier(branchId)
            .withJaAtMoment(createJaAtMomentList().stream().filter(j->j.getIdentifier().equals(jaId)).findFirst().orElse(null))
            .withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.一般).withBranchCode(BranchCode.of(branchCode)).withName(branchName).build())
            .build();
    }
    // 取引グループリストデータ作成
    private List<BizTranGrp> createBizTranGrpList() {
        List<BizTranGrp> list = newArrayList();
        list.add(BizTranGrp.createFrom(10001L,"ANTG01","データ入力取引グループ", SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranGrp.createFrom(10002L,"ANTG02","精算取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranGrp.createFrom(10003L,"ANTG10","センター維持管理グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return list;
    }
    // 取引グループデータ作成
    private BizTranGrp createBizTranGrp(Long bizTranGrpId) {
        return createBizTranGrpList().stream().filter(b -> b.getBizTranGrpId().equals(bizTranGrpId)).findFirst().orElse(null);
    }
    // 取引リストデータ作成
    private List<BizTran> createBizTranList() {
        List<BizTran> list = newArrayList();
        list.add(BizTran.createFrom(100001L,"AN0001","畜産メインメニュー",false, LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTran.createFrom(100002L,"AN1510","精算取消",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTran.createFrom(100003L,"AN0002","畜産業務（センター）メニュー",true,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return list;
    }
    // 取引データ作成
    private BizTran createBizTran(Long bizTranId) {
        return createBizTranList().stream().filter(b -> b.getBizTranId().equals(bizTranId)).findFirst().orElse(null);
    }

    // テスト対象クラス生成
    private FormElementsController createFormElementsController() {
        // searchJaAtMomentのスタブ
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
        // SearchBranchAtMomentのスタブ
        SearchBranchAtMoment searchBranchAtMoment = new SearchBranchAtMoment(new BranchAtMomentRepository() {
            @Override
            public BranchesAtMoment selectBy(BranchAtMomentCriteria criteria, Orders orders) {
                List<BranchAtMoment> list = createBranchAtMomentList().stream().filter(b->b.getJaAtMoment().getJaAttribute().getJaCode().getValue().equals(criteria.getNarrowedJaCodeCriteria().getEqualTo().getValue())).collect(Collectors.toList());
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
        // 取引グループ群検索リポジトリのスタブ
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
        // 取引グループ_取引割当群検索リポジトリのスタブ
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
     * 戻り値の型に合わせてキャスト
     *
     * @param obj キャスト対象オブジェクト
     * @param <T> 戻り値の型Generics
     * @return キャスト後オブジェクト
     */
    @SuppressWarnings("unchecked")
    public static <T> T autoCast(Object obj) {
        return (T) obj;
    }

    /**
     * {@link FormElementsController#getJaItemsSource(String, ModelMap)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
     */
    @Test
    @Tag(TestSize.SMALL)
    void getJaItemsSource_test0() {

        // テスト対象クラス生成
        FormElementsController formElementsController = createFormElementsController();

        // 実行値
        String viewId = "oa12020";
        ModelMap model = new ModelMap();

        // 期待値
        String expectedItemsSourceName = "oa12020::ajaxSelectJa";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
        for (JaAtMoment jaAtMoment : createJaAtMomentList()) {
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                jaAtMoment.getIdentifier(), jaAtMoment.getJaAttribute().getJaCode().getValue(), jaAtMoment.getJaAttribute().getName()
            ));
        }

        // 実行
        String actualItemsSourceName = formElementsController.getJaItemsSource(viewId, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // 結果検証
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBranchItemsSource(String, String, ModelMap)}テスト
     *  ●パターン
     *    正常
     *    ・JaCode：null（結果0件）
     *
     *  ●検証事項
     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBranchItemsSource_test0() {

        // テスト対象クラス生成
        FormElementsController formElementsController = createFormElementsController();

        // 実行値
        String viewId = "oa12020";
        String jaCode = null;
        ModelMap model = new ModelMap();

        // 期待値
        String expectedItemsSourceName = "oa12020::ajaxSelectBranch";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();

        // 実行
        String actualItemsSourceName = formElementsController.getBranchItemsSource(viewId, jaCode, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // 結果検証
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBranchItemsSource(String, String, ModelMap)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBranchItemsSource_test1() {

        // テスト対象クラス生成
        FormElementsController formElementsController = createFormElementsController();

        // 実行値
        String viewId = "oa12020";
        String jaCode = "006";
        ModelMap model = new ModelMap();

        // 期待値
        String expectedItemsSourceName = "oa12020::ajaxSelectBranch";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
        for (BranchAtMoment branchAtMoment : createBranchAtMomentList().stream().filter(b-> jaCode.equals(b.getJaAtMoment().getJaAttribute().getJaCode().getValue())).collect(Collectors.toList())) {
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                branchAtMoment.getIdentifier(), branchAtMoment.getBranchAttribute().getBranchCode().getValue(), branchAtMoment.getBranchAttribute().getName()
            ));
        }

        // 実行
        String actualItemsSourceName = formElementsController.getBranchItemsSource(viewId, jaCode, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // 結果検証
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getSubSystemItemsSource(String, ModelMap)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
     */
    @Test
    @Tag(TestSize.SMALL)
    void getSubSystemItemsSource_test0() {

        // テスト対象クラス生成
        FormElementsController formElementsController = createFormElementsController();

        // 実行値
        String viewId = "oa12020";
        ModelMap model = new ModelMap();

        // 期待値
        String expectedItemsSourceName = "oa12020::ajaxSelectSubSystem";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
        for (SubSystem subSystem : SubSystem.values()) {
            if (subSystem.getCode().isEmpty()) { continue; }
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                null, subSystem.getCode(), subSystem.getDisplayName()));
        }

        // 実行
        String actualItemsSourceName = formElementsController.getSubSystemItemsSource(viewId, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // 結果検証
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBizTranGrpItemsSource(String, String, String, ModelMap)}テスト
     *  ●パターン
     *    正常
     *    ・SunSystem：null（結果0件）
     *
     *  ●検証事項
     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBizTranGrpItemsSource_test0() {

        // テスト対象クラス生成
        FormElementsController formElementsController = createFormElementsController();

        // 実行値
        String viewId = "oa12020";
        String subSystemCode = null;
        String firstRowStatus = null;
        ModelMap model = new ModelMap();

        // 期待値
        String expectedItemsSourceName = "oa12020::ajaxSelectBizTranGrp";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();

        // 実行
        String actualItemsSourceName = formElementsController.getBizTranGrpItemsSource(viewId, subSystemCode, firstRowStatus, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // 結果検証
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBizTranGrpItemsSource(String, String, String, ModelMap)}テスト
     *  ●パターン
     *    正常
     *    ・SunSystem：指定
     *    ・firstRowStatus：null（先頭空行なし）
     *
     *  ●検証事項
     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBizTranGrpItemsSource_test1() {

        // テスト対象クラス生成
        FormElementsController formElementsController = createFormElementsController();

        // 実行値
        String viewId = "oa12020";
        String subSystemCode = SubSystem.販売_畜産.getCode();
        String firstRowStatus = null;
        ModelMap model = new ModelMap();

        // 期待値
        String expectedItemsSourceName = "oa12020::ajaxSelectBizTranGrp";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        for (BizTranGrp bizTranGrp : createBizTranGrpList()) {
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                bizTranGrp.getBizTranGrpId(), bizTranGrp.getBizTranGrpCode(), bizTranGrp.getBizTranGrpName()
            ));
        }

        // 実行
        String actualItemsSourceName = formElementsController.getBizTranGrpItemsSource(viewId, subSystemCode, firstRowStatus, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // 結果検証
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBizTranGrpItemsSource(String, String, String, ModelMap)}テスト
     *  ●パターン
     *    正常
     *    ・SunSystem：指定
     *    ・firstRowStatus：""（先頭空行あり）
     *
     *  ●検証事項
     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBizTranGrpItemsSource_test2() {

        // テスト対象クラス生成
        FormElementsController formElementsController = createFormElementsController();

        // 実行値
        String viewId = "oa12020";
        String subSystemCode = SubSystem.販売_畜産.getCode();
        String firstRowStatus = "";
        ModelMap model = new ModelMap();

        // 期待値
        String expectedItemsSourceName = "oa12020::ajaxSelectBizTranGrp";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
        for (BizTranGrp bizTranGrp : createBizTranGrpList()) {
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                bizTranGrp.getBizTranGrpId(), bizTranGrp.getBizTranGrpCode(), bizTranGrp.getBizTranGrpName()
            ));
        }

        // 実行
        String actualItemsSourceName = formElementsController.getBizTranGrpItemsSource(viewId, subSystemCode, firstRowStatus, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // 結果検証
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBizTranGrpItemsSource(String, String, String, ModelMap)}テスト
     *  ●パターン
     *    正常
     *    ・SunSystem：指定
     *    ・firstRowStatus："指定なし"（先頭行文字列挿入）
     *
     *  ●検証事項
     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBizTranGrpItemsSource_test3() {

        // テスト対象クラス生成
        FormElementsController formElementsController = createFormElementsController();

        // 実行値
        String viewId = "oa12020";
        String subSystemCode = SubSystem.販売_畜産.getCode();
        String firstRowStatus = "指定なし";
        ModelMap model = new ModelMap();

        // 期待値
        String expectedItemsSourceName = "oa12020::ajaxSelectBizTranGrp";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
        expectedItemsSourcelist.get(0).setName(firstRowStatus);
        for (BizTranGrp bizTranGrp : createBizTranGrpList()) {
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                bizTranGrp.getBizTranGrpId(), bizTranGrp.getBizTranGrpCode(), bizTranGrp.getBizTranGrpName()
            ));
        }

        // 実行
        String actualItemsSourceName = formElementsController.getBizTranGrpItemsSource(viewId, subSystemCode, firstRowStatus, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // 結果検証
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBizTranItemsSource(String, String, String, String, ModelMap)}テスト
     *  ●パターン
     *    正常
     *    ・SunSystem：null（結果0件）
     *
     *  ●検証事項
     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBizTranItemsSource_test0() {

        // テスト対象クラス生成
        FormElementsController formElementsController = createFormElementsController();

        // 実行値
        String viewId = "oa12020";
        String subSystemCode = null;
        String bizTranGrpCode = null;
        String firstRowStatus = null;
        ModelMap model = new ModelMap();

        // 期待値
        String expectedItemsSourceName = "oa12020::ajaxSelectBizTran";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();

        // 実行
        String actualItemsSourceName = formElementsController.getBizTranItemsSource(viewId, subSystemCode, bizTranGrpCode, firstRowStatus, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // 結果検証
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBizTranItemsSource(String, String, String, String, ModelMap)}テスト
     *  ●パターン
     *    正常
     *    ・SunSystem：指定
     *    ・bizTranGrpCode：null
     *    ・firstRowStatus：null（先頭空行なし）
     *
     *  ●検証事項
     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBizTranItemsSource_test1() {

        // テスト対象クラス生成
        FormElementsController formElementsController = createFormElementsController();

        // 実行値
        String viewId = "oa12020";
        String subSystemCode = SubSystem.販売_畜産.getCode();
        String bizTranGrpCode = null;
        String firstRowStatus = null;
        ModelMap model = new ModelMap();

        // 期待値
        String expectedItemsSourceName = "oa12020::ajaxSelectBizTran";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        Comparator<BizTran> comparator = Comparator.comparing(BizTran::getBizTranCode, nullsLast(naturalOrder()));
        for (BizTran bizTran : createBizTranList().stream().sorted(comparator).collect(Collectors.toList())) {
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                bizTran.getBizTranId(), bizTran.getBizTranCode(), bizTran.getBizTranName()
            ));
        }

        // 実行
        String actualItemsSourceName = formElementsController.getBizTranItemsSource(viewId, subSystemCode, bizTranGrpCode, firstRowStatus, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // 結果検証
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBizTranItemsSource(String, String, String, String, ModelMap)}テスト
     *  ●パターン
     *    正常
     *    ・SunSystem：指定
     *    ・bizTranGrpCode：指定
     *    ・firstRowStatus：null（先頭空行なし）
     *
     *  ●検証事項
     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBizTranItemsSource_test2() {

        // テスト対象クラス生成
        FormElementsController formElementsController = createFormElementsController();

        // 実行値
        String viewId = "oa12020";
        String subSystemCode = SubSystem.販売_畜産.getCode();
        String bizTranGrpCode = "ANTG01";
        String firstRowStatus = null;
        ModelMap model = new ModelMap();

        // 期待値
        String expectedItemsSourceName = "oa12020::ajaxSelectBizTran";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        Comparator<BizTran> comparator = Comparator.comparing(BizTran::getBizTranCode, nullsLast(naturalOrder()));
        for (BizTran bizTran : createBizTranList().stream().sorted(comparator).collect(Collectors.toList())) {
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                bizTran.getBizTranId(), bizTran.getBizTranCode(), bizTran.getBizTranName()
            ));
        }

        // 実行
        String actualItemsSourceName = formElementsController.getBizTranItemsSource(viewId, subSystemCode, bizTranGrpCode, firstRowStatus, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // 結果検証
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBizTranItemsSource(String, String, String, String, ModelMap)}テスト
     *  ●パターン
     *    正常
     *    ・SunSystem：指定
     *    ・bizTranGrpCode：指定
     *    ・firstRowStatus：""（先頭空行あり）
     *
     *  ●検証事項
     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBizTranItemsSource_test3() {

        // テスト対象クラス生成
        FormElementsController formElementsController = createFormElementsController();

        // 実行値
        String viewId = "oa12020";
        String subSystemCode = SubSystem.販売_畜産.getCode();
        String bizTranGrpCode = "ANTG01";
        String firstRowStatus = "";
        ModelMap model = new ModelMap();

        // 期待値
        String expectedItemsSourceName = "oa12020::ajaxSelectBizTran";
        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
        Comparator<BizTran> comparator = Comparator.comparing(BizTran::getBizTranCode, nullsLast(naturalOrder()));
        for (BizTran bizTran : createBizTranList().stream().sorted(comparator).collect(Collectors.toList())) {
            expectedItemsSourcelist.add(new SelectOptionItemSource(
                bizTran.getBizTranId(), bizTran.getBizTranCode(), bizTran.getBizTranName()
            ));
        }

        // 実行
        String actualItemsSourceName = formElementsController.getBizTranItemsSource(viewId, subSystemCode, bizTranGrpCode, firstRowStatus, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // 結果検証
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }

    /**
     * {@link FormElementsController#getBizTranItemsSource(String, String, String, String, ModelMap)}テスト
     *  ●パターン
     *    正常
     *    ・SunSystem：指定
     *    ・bizTranGrpCode：指定
     *    ・firstRowStatus："指定なし"（先頭行文字列挿入）
     *
     *  ●検証事項
     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBizTranItemsSource_test4() {

        // テスト対象クラス生成
        FormElementsController formElementsController = createFormElementsController();

        // 実行値
        String viewId = "oa12020";
        String subSystemCode = SubSystem.販売_畜産.getCode();
        String bizTranGrpCode = "ANTG01";
        String firstRowStatus = "指定なし";
        ModelMap model = new ModelMap();

        // 期待値
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

        // 実行
        String actualItemsSourceName = formElementsController.getBizTranItemsSource(viewId, subSystemCode, bizTranGrpCode, firstRowStatus, model);
        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));

        // 結果検証
        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
    }
}