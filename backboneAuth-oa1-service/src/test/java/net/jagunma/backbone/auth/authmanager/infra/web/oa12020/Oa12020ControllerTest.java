package net.jagunma.backbone.auth.authmanager.infra.web.oa12020;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchSuspendBizTran;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTransSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTransSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.Oa12010Controller;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12020.vo.Oa12020SearchResultVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12020.vo.Oa12020Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTransRepository;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class Oa12020ControllerTest {

    // 実行 ＆ 期待 既定値
    private Integer suspendConditionsSelect = 0;

    private final String GunmaRuntimeExceptionMessageCode = "EOA13008";
    private final String GunmaRuntimeExceptionMessageArg1 = "抑止期間開始";

    // テスト対象クラス生成
    private Oa12020Controller createOa12020Controller(Integer throwExceptio) {
        // 一時取引抑止群検索の作成
        SearchSuspendBizTran searchSuspendBizTran = new SearchSuspendBizTran(
            new SuspendBizTransRepository() {
                @Override
                public SuspendBizTrans selectBy(SuspendBizTranCriteria suspendBizTranCriteria, Orders orders) { return null; }
            },
            new SuspendBizTranRepository() {
                @Override
                public SuspendBizTran findOneBy(SuspendBizTranCriteria suspendBizTranCriteria) { return null; }
            }) {
            public void execute(SuspendBizTransSearchRequest request, SuspendBizTransSearchResponse response) {
                if (throwExceptio == null) {
                    response.setSuspendBizTrans(SuspendBizTrans.createFrom(cresteSuspendBizTran()));
                    return;
                }
                // createOa12020Controllerの引数 throwExceptio == -1 の場合：RuntimeException を発生させる
                if (throwExceptio == -1) {
                    throw new RuntimeException();
                }
                // createOa12020Controllerの引数 throwExceptio == -2 の場合：GunmaRuntimeException を発生させる
                if (throwExceptio == -2) {
                    Preconditions.checkNotEmpty("", () -> new GunmaRuntimeException(GunmaRuntimeExceptionMessageCode, GunmaRuntimeExceptionMessageArg1));
                }
            };
        };
//        // searchJaAtMomentの作成
//        SearchJaAtMoment searchJaAtMoment = new SearchJaAtMoment(new JaAtMomentRepository() {
//            @Override
//            public JasAtMoment selectBy(JaAtMomentCriteria criteria, Orders orders) {
//                return JasAtMoment.of(createJaAtMomentList());
//            }
//            @Override
//            public JaAtMoment findOneBy(JaAtMomentCriteria criteria) {
//                return null;
//            }
//        }) {
//            public JasAtMoment selectBy() {
//                return JasAtMoment.of(createJaAtMomentList());
//            }
//        };
//        // SearchBranchAtMomentの作成
//        SearchBranchAtMoment searchBranchAtMoment = new SearchBranchAtMoment(new BranchAtMomentRepository() {
//            @Override
//            public BranchesAtMoment selectBy(BranchAtMomentCriteria criteria, Orders orders) {
//                List<BranchAtMoment> list = createBranchAtMomentList().stream().filter(b->b.getJaAtMoment().getJaAttribute().getJaCode().getValue().equals(criteria.getNarrowedJaCodeCriteria().getEqualTo().getValue())).collect(Collectors.toList());
//                return BranchesAtMoment.of(list);
//            }
//            @Override
//            public BranchAtMoment findOneBy(BranchAtMomentCriteria criteria) {
//                return null;
//            }
//        }) {
//            public BranchesAtMoment selectBy(long jaId) {
//                return BranchesAtMoment.of(createBranchAtMomentList().stream().filter(b-> ((Long)jaId).equals(b.getJaAtMoment().getIdentifier())).collect(Collectors.toList()));
//            }
//        };
//        // 取引グループ群検索の作成
//        BizTranGrpsRepository bizTranGrpsRepository = new BizTranGrpsRepository() {
//            @Override
//            public BizTranGrps selectBy(BizTranGrpCriteria bizTranGrpCriteria, Orders orders) {
//                return BizTranGrps.createFrom(createBizTranGrpList());
//            }
//            @Override
//            public BizTranGrps selectAll(Orders orders) {
//                return null;
//            }
//        };
//        // 取引グループ検索の作成
//        BizTranGrpRepository bizTranGrpRepository = new BizTranGrpRepository() {
//            @Override
//            public BizTranGrp findOneBy(BizTranGrpCriteria bizTranGrpCriteria) {
//                return createBizTranGrpList().stream().filter(b->b.getBizTranGrpCode().equals(bizTranGrpCriteria.getBizTranGrpCodeCriteria().getEqualTo())).findFirst().orElse(null);
//            }
//        };
//        // 取引グループ_取引割当群検索の作成
//        BizTranGrp_BizTransRepository bizTranGrp_BizTransRepository = new BizTranGrp_BizTransRepository() {
//            @Override
//            public BizTranGrp_BizTrans selectBy(BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria, Orders orders) {
//                List<BizTranGrp_BizTran> list = newArrayList();
//                Integer ix = 0;
//                List<BizTran> bizTranList = createBizTranList();
//                for (BizTranGrp bizTranGrp : createBizTranGrpList()) {
//                    list.add(BizTranGrp_BizTran.createFrom(
//                        Long.valueOf(ix+1),
//                        bizTranGrp.getBizTranGrpId(),
//                        bizTranList.get(ix).getBizTranId(),
//                        bizTranGrp.getSubSystemCode(),
//                        1,
//                        bizTranGrp,
//                        bizTranList.get(ix),
//                        bizTranGrp.getSubSystem()
//                    ));
//                    ix++;
//                }
//                return BizTranGrp_BizTrans.createFrom(list);
//            }
//            @Override
//            public BizTranGrp_BizTrans selectAll(Orders orders) {
//                return null;
//            }
//        };

        return new Oa12020Controller(searchSuspendBizTran);
    }

    // 一時取引抑止リストデータ作成
    private List<SuspendBizTran> cresteSuspendBizTran() {
        List<SuspendBizTran> list = newArrayList();
        list.add(SuspendBizTran.createFrom(1L,"006","001",SubSystem.販売_畜産.getCode(),"ANTG01","AN0001",LocalDate.of(2020,11,1),LocalDate.of(2020,11,2),"不具合により緊急抑止",1,
            createJaAtMomentList().stream().filter(j->j.getIdentifier().equals(6L)).findFirst().orElse(null),
            createBranchAtMomentList().stream().filter(b->b.getIdentifier().equals(33L)).findFirst().orElse(null),
            SubSystem.販売_畜産,
            createBizTranGrp(10001L),
            createBizTran(100001L)));
        list.add(SuspendBizTran.createFrom(2L,null,null,null,null,null,LocalDate.of(2020,11,1),LocalDate.of(2020,11,2),null,1,
            null,null,null,null,null));
        list.add(SuspendBizTran.createFrom(3L,"006",null,null,null,null,null,null,null,1,
            createJaAtMoment(6L,"006","ＪＡ００６"),
            null,null,null,null));
        return list;
    }
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
        list.add(BizTranGrp.createFrom(10001L,"ANTG01","データ入力取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
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
        list.add(BizTran.createFrom(100001L,"AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTran.createFrom(100002L,"AN1510","精算取消",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTran.createFrom(100003L,"AN0002","畜産業務（センター）メニュー",true,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return list;
    }
    // 取引データ作成
    private BizTran createBizTran(Long bizTranId) {
        return createBizTranList().stream().filter(b -> b.getBizTranId().equals(bizTranId)).findFirst().orElse(null);
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
        T castObj = (T) obj;
        return castObj;
    }


    /**
     * {@link Oa12020Controller#get(Model)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test0() {

        // テスト対象クラス生成
        Oa12020Controller oa12020Controller = createOa12020Controller(null);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();

        // 期待値
        String expectedViewName = "oa12020";
        Oa12020Vo expectedVo = new Oa12020Vo();
        expectedVo.setSuspendConditionsSelect(suspendConditionsSelect);
        expectedVo.setSearchResultList(newArrayList());
        expectedVo.setPaginationLastPageNo(0);

        // 実行
        String actualViewName = oa12020Controller.get(model);
        Oa12020Vo actualVo = (Oa12020Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa12010Controller#get(Model)}テスト
     *  ●パターン
     *    例外（GunmaRuntimeException）発生
     *
     *  ●検証事項
     *  ・なし
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test1() {
        // getメソッドでGunmaRuntimeExceptionを発生させるテストは不可
        assertThat(true);
    }

    /**
     * {@link Oa12010Controller#get(Model)}テスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・なし
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test2() {
        // getメソッドでRuntimeExceptionを発生させるテストは実現不可
        assertThat(true);
    }

    /**
     * {@link Oa12020Controller#search(Model, Oa12020Vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void search_test0() {

        // テスト対象クラス生成
        Oa12020Controller oa12020Controller = createOa12020Controller(null);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa12020Vo oa12020Vo = new Oa12020Vo();
        oa12020Vo.setPageNo(1);

        // 期待値
        String expectedViewName = "oa12020";
        Oa12020Vo expectedVo = new Oa12020Vo();
        List<Oa12020SearchResultVo> searchResultVoList = newArrayList();
        for (SuspendBizTran suspendBizTran : cresteSuspendBizTran()) {
            Oa12020SearchResultVo searchResultVo = new Oa12020SearchResultVo();
            searchResultVo.setSuspendBizTranId(suspendBizTran.getSuspendBizTranId());
            searchResultVo.setJaCode((suspendBizTran.getJaAtMoment() == null)? null : suspendBizTran.getJaCode());
            searchResultVo.setJaName((suspendBizTran.getJaAtMoment() == null)? null : suspendBizTran.getJaAtMoment().getJaAttribute().getName());
            searchResultVo.setBranchCode((suspendBizTran.getBranchAtMoment() == null)? null : suspendBizTran.getBranchCode());
            searchResultVo.setBranchName((suspendBizTran.getBranchAtMoment() == null)? null : suspendBizTran.getBranchAtMoment().getBranchAttribute().getName());
            searchResultVo.setSubSystemName((suspendBizTran.getSubSystem() == null)? null : suspendBizTran.getSubSystem().getName());
            searchResultVo.setBizTranGrpCode((suspendBizTran.getBizTranGrp() == null)? null : suspendBizTran.getBizTranGrpCode());
            searchResultVo.setBizTranGrpName((suspendBizTran.getBizTranGrp() == null)? null : suspendBizTran.getBizTranGrp().getBizTranGrpName());
            searchResultVo.setBizTranCode((suspendBizTran.getBizTran() == null)? null : suspendBizTran.getBizTranCode());
            searchResultVo.setBizTranName((suspendBizTran.getBizTran() == null)? null : suspendBizTran.getBizTran().getBizTranName());
            searchResultVo.setSuspendStartDate(suspendBizTran.getSuspendStartDate());
            searchResultVo.setSuspendEndDate(suspendBizTran.getSuspendEndDate());
            searchResultVo.setSuspendReason(suspendBizTran.getSuspendReason());
            searchResultVoList.add(searchResultVo);
        }
        expectedVo.setSearchResultList(searchResultVoList);
        expectedVo.setPaginationLastPageNo(1);
        expectedVo.setPageNo(1);

        // 実行
        String actualViewName = oa12020Controller.search(model, oa12020Vo);
        Oa12020Vo actualVo = (Oa12020Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa12020Controller#search(Model, Oa12020Vo)}テスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void search_test1() {

        // テスト対象クラス生成
        Oa12020Controller oa12020Controller = createOa12020Controller(-1);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa12020Vo oa12020Vo = new Oa12020Vo();
        oa12020Vo.setPageNo(1);

        // 期待値
        String expectedViewName = "oa19999";
        String messageCode = "EOA10001";

        // 実行
        String actualViewName = oa12020Controller.search(model, oa12020Vo);
        Oa12020Vo actualVo = (Oa12020Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(messageCode);
    }

    /**
     * {@link Oa12020Controller#search(Model, Oa12020Vo)}テスト
     *  ●パターン
     *    例外（GunmaRuntimeException ）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void search_test2() {

        // テスト対象クラス生成
        Oa12020Controller oa12020Controller = createOa12020Controller(-2);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa12020Vo oa12020Vo = new Oa12020Vo();
        oa12020Vo.setPageNo(1);

        // 期待値
        String expectedViewName = "oa12020";
        String messageCode = "EOA10001";

        // 実行
        String actualViewName = oa12020Controller.search(model, oa12020Vo);
        Oa12020Vo actualVo = (Oa12020Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(GunmaRuntimeExceptionMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(GunmaRuntimeExceptionMessageArg1);
    }

    /**
     * {@link Oa12020Controller#backSearch(Oa12020Vo, Model)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void backSearch_test0() {

        // テスト対象クラス生成
        Oa12020Controller oa12020Controller = createOa12020Controller(null);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa12020Vo oa12020Vo = new Oa12020Vo();
        oa12020Vo.setPageNo(1);

        // 期待値
        String expectedViewName = "oa12020";
        Oa12020Vo expectedVo = new Oa12020Vo();
        List<Oa12020SearchResultVo> searchResultVoList = newArrayList();
        for (SuspendBizTran suspendBizTran : cresteSuspendBizTran()) {
            Oa12020SearchResultVo searchResultVo = new Oa12020SearchResultVo();
            searchResultVo.setSuspendBizTranId(suspendBizTran.getSuspendBizTranId());
            searchResultVo.setJaCode((suspendBizTran.getJaAtMoment() == null)? null : suspendBizTran.getJaCode());
            searchResultVo.setJaName((suspendBizTran.getJaAtMoment() == null)? null : suspendBizTran.getJaAtMoment().getJaAttribute().getName());
            searchResultVo.setBranchCode((suspendBizTran.getBranchAtMoment() == null)? null : suspendBizTran.getBranchCode());
            searchResultVo.setBranchName((suspendBizTran.getBranchAtMoment() == null)? null : suspendBizTran.getBranchAtMoment().getBranchAttribute().getName());
            searchResultVo.setSubSystemName((suspendBizTran.getSubSystem() == null)? null : suspendBizTran.getSubSystem().getName());
            searchResultVo.setBizTranGrpCode((suspendBizTran.getBizTranGrp() == null)? null : suspendBizTran.getBizTranGrpCode());
            searchResultVo.setBizTranGrpName((suspendBizTran.getBizTranGrp() == null)? null : suspendBizTran.getBizTranGrp().getBizTranGrpName());
            searchResultVo.setBizTranCode((suspendBizTran.getBizTran() == null)? null : suspendBizTran.getBizTranCode());
            searchResultVo.setBizTranName((suspendBizTran.getBizTran() == null)? null : suspendBizTran.getBizTran().getBizTranName());
            searchResultVo.setSuspendStartDate(suspendBizTran.getSuspendStartDate());
            searchResultVo.setSuspendEndDate(suspendBizTran.getSuspendEndDate());
            searchResultVo.setSuspendReason(suspendBizTran.getSuspendReason());
            searchResultVoList.add(searchResultVo);
        }
        expectedVo.setSearchResultList(searchResultVoList);
        expectedVo.setPaginationLastPageNo(1);
        expectedVo.setPageNo(1);

        // 実行
        String actualViewName = oa12020Controller.backSearch(oa12020Vo, model);
        Oa12020Vo actualVo = (Oa12020Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa12020Controller#backSearch(Oa12020Vo, Model)}テスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void backSearch_test1() {

        // テスト対象クラス生成
        Oa12020Controller oa12020Controller = createOa12020Controller(-1);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa12020Vo oa12020Vo = new Oa12020Vo();
        oa12020Vo.setPageNo(1);

        // 期待値
        String expectedViewName = "oa19999";
        String messageCode = "EOA10001";

        // 実行
        String actualViewName = oa12020Controller.backSearch(oa12020Vo, model);
        Oa12020Vo actualVo = (Oa12020Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(messageCode);
    }

    /**
     * {@link Oa12020Controller#backSearch(Oa12020Vo, Model)}テスト
     *  ●パターン
     *    例外（GunmaRuntimeException ）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void backSearch_test2() {

        // テスト対象クラス生成
        Oa12020Controller oa12020Controller = createOa12020Controller(-2);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa12020Vo oa12020Vo = new Oa12020Vo();
        oa12020Vo.setPageNo(1);

        // 期待値
        String expectedViewName = "oa12020";
        String messageCode = "EOA10001";

        // 実行
        String actualViewName = oa12020Controller.backSearch(oa12020Vo, model);
        Oa12020Vo actualVo = (Oa12020Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(GunmaRuntimeExceptionMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(GunmaRuntimeExceptionMessageArg1);
    }

//    /**
//     * {@link Oa12020Controller#getJaItemsSource(ModelMap, Oa12020Vo)}テスト
//     *  ●パターン
//     *    正常
//     *
//     *  ●検証事項
//     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
//     */
//    @Test
//    @Tag(TestSize.SMALL)
//    void getJaItemsSource_test0() {
//
//        // テスト対象クラス生成
//        Oa12020Controller oa12020Controller = createOa12020Controller(-2);
//
//        // 実行値
//        ModelMap model = new ModelMap();
//        Oa12020Vo vo = new Oa12020Vo();
//
//        // 期待値
//        String expectedItemsSourceName = "oa12020::ajaxSelectJa";
//        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
//        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
//        for (JaAtMoment jaAtMoment : createJaAtMomentList()) {
//            expectedItemsSourcelist.add(new SelectOptionItemSource(
//                jaAtMoment.getIdentifier(), jaAtMoment.getJaAttribute().getJaCode().getValue(), jaAtMoment.getJaAttribute().getName()
//            ));
//        }
//
//        // 実行
//        String actualItemsSourceName = oa12020Controller.getJaItemsSource(model, vo);
//        //List<SelectOptionItemsSource> actualItemsSourceList = (List<SelectOptionItemsSource>) model.getAttribute("selectAjaxItems");
//        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));
//
//        // 結果検証
//        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
//        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
//    }
//
//    /**
//     * {@link Oa12020Controller#getBranchItemsSource(ModelMap, Oa12020Vo)}テスト
//     *  ●パターン
//     *    正常
//     *
//     *  ●検証事項
//     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
//     */
//    @Test
//    @Tag(TestSize.SMALL)
//    void getBranchItemsSource_test0() {
//
//        // テスト対象クラス生成
//        Oa12020Controller oa12020Controller = createOa12020Controller(-2);
//
//        // 実行値
//        String jaCode = null;
//        ModelMap model = new ModelMap();
//        Oa12020Vo vo = new Oa12020Vo();
//        vo.setJaCode(jaCode);
//
//        // 期待値
//        String expectedItemsSourceName = "oa12020::ajaxSelectBranch";
//        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
//
//        // 実行
//        String actualItemsSourceName = oa12020Controller.getBranchItemsSource(model, vo);
////        List<SelectOptionItemsSource> actualItemsSourceList = (List<SelectOptionItemsSource>) model.getAttribute("selectAjaxItems");
//        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));
//
//        // 結果検証
//        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
//        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
//    }
//
//    /**
//     * {@link Oa12020Controller#getBranchItemsSource(ModelMap, Oa12020Vo)}テスト
//     *  ●パターン
//     *    正常
//     *
//     *  ●検証事項
//     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
//     */
//    @Test
//    @Tag(TestSize.SMALL)
//    void getBranchItemsSource_test1() {
//
//        // テスト対象クラス生成
//        Oa12020Controller oa12020Controller = createOa12020Controller(-2);
//
//        // 実行値
//        String jaCode = "006";
//        ModelMap model = new ModelMap();
//        Oa12020Vo vo = new Oa12020Vo();
//        vo.setJaCode(jaCode);
//
//        // 期待値
//        String expectedItemsSourceName = "oa12020::ajaxSelectBranch";
//        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
//        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
//        for (BranchAtMoment branchAtMoment : createBranchAtMomentList().stream().filter(b-> jaCode.equals(b.getJaAtMoment().getJaAttribute().getJaCode().getValue())).collect(Collectors.toList())) {
//            expectedItemsSourcelist.add(new SelectOptionItemSource(
//                branchAtMoment.getIdentifier(), branchAtMoment.getBranchAttribute().getBranchCode().getValue(), branchAtMoment.getBranchAttribute().getName()
//            ));
//        }
//
//        // 実行
//        String actualItemsSourceName = oa12020Controller.getBranchItemsSource(model, vo);
////        List<SelectOptionItemsSource> actualItemsSourceList = (List<SelectOptionItemsSource>) model.getAttribute("selectAjaxItems");
//        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));
//
//        // 結果検証
//        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
//        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
//    }
//
//    /**
//     * {@link Oa12020Controller#getSubSystemItemsSource(ModelMap, Oa12020Vo)}テスト
//     *  ●パターン
//     *    正常
//     *
//     *  ●検証事項
//     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
//     */
//    @Test
//    @Tag(TestSize.SMALL)
//    void getSubSystemItemsSource_test0() {
//
//        // テスト対象クラス生成
//        Oa12020Controller oa12020Controller = createOa12020Controller(-2);
//
//        // 実行値
//        ModelMap model = new ModelMap();
//        Oa12020Vo vo = new Oa12020Vo();
//
//        // 期待値
//        String expectedItemsSourceName = "oa12020::ajaxSelectSubSystem";
//        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
//        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
//        for (SubSystem subSystem : SubSystem.values()) {
//            if (subSystem.getCode().isEmpty()) {continue;}
//            expectedItemsSourcelist.add(new SelectOptionItemSource(
//                null, subSystem.getCode(), subSystem.getName()
//            ));
//        }
//
//        // 実行
//        String actualItemsSourceName = oa12020Controller.getSubSystemItemsSource(model, vo);
////        List<SelectOptionItemsSource> actualItemsSourceList = (List<SelectOptionItemsSource>) model.getAttribute("selectAjaxItems");
//        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));
//
//        // 結果検証
//        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
//        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
//    }
//
//    /**
//     * {@link Oa12020Controller#getBizTranGrpItemsSource(ModelMap, Oa12020Vo)}テスト
//     *  ●パターン
//     *    正常
//     *
//     *  ●検証事項
//     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
//     */
//    @Test
//    @Tag(TestSize.SMALL)
//    void getBizTranGrpItemsSource_test0() {
//
//        // テスト対象クラス生成
//        Oa12020Controller oa12020Controller = createOa12020Controller(-2);
//
//        // 実行値
//        String subSystemCode = null;
//        ModelMap model = new ModelMap();
//        Oa12020Vo vo = new Oa12020Vo();
//        vo.setSubSystemCode(subSystemCode);
//
//        // 期待値
//        String expectedItemsSourceName = "oa12020::ajaxSelectBizTranGrp";
//        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
//
//        // 実行
//        String actualItemsSourceName = oa12020Controller.getBizTranGrpItemsSource(model, vo);
////        List<SelectOptionItemsSource> actualItemsSourceList = (List<SelectOptionItemsSource>) model.getAttribute("selectAjaxItems");
//        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));
//
//        // 結果検証
//        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
//        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
//    }
//
//    /**
//     * {@link Oa12020Controller#getBizTranGrpItemsSource(ModelMap, Oa12020Vo)}テスト
//     *  ●パターン
//     *    正常
//     *
//     *  ●検証事項
//     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
//     */
//    @Test
//    @Tag(TestSize.SMALL)
//    void getBizTranGrpItemsSource_test1() {
//
//        // テスト対象クラス生成
//        Oa12020Controller oa12020Controller = createOa12020Controller(-2);
//
//        // 実行値
//        String subSystemCode = SubSystem.販売_畜産.getCode();
//        ModelMap model = new ModelMap();
//        Oa12020Vo vo = new Oa12020Vo();
//        vo.setSubSystemCode(subSystemCode);
//
//        // 期待値
//        String expectedItemsSourceName = "oa12020::ajaxSelectBizTranGrp";
//        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
//        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
//        for (BizTranGrp bizTranGrp : createBizTranGrpList()) {
//            expectedItemsSourcelist.add(new SelectOptionItemSource(
//                bizTranGrp.getBizTranGrpId(), bizTranGrp.getBizTranGrpCode(), bizTranGrp.getBizTranGrpName()
//            ));
//        }
//
//        // 実行
//        String actualItemsSourceName = oa12020Controller.getBizTranGrpItemsSource(model, vo);
////        List<SelectOptionItemsSource> actualItemsSourceList = (List<SelectOptionItemsSource>) model.getAttribute("selectAjaxItems");
//        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));
//
//        // 結果検証
//        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
//        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
//    }
//
//    /**
//     * {@link Oa12020Controller#getBizTranItemsSource(ModelMap, Oa12020Vo)}テスト
//     *  ●パターン
//     *    正常
//     *
//     *  ●検証事項
//     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
//     */
//    @Test
//    @Tag(TestSize.SMALL)
//    void getBizTranItemsSource_test0() {
//
//        // テスト対象クラス生成
//        Oa12020Controller oa12020Controller = createOa12020Controller(-2);
//
//        // 実行値
//        String subSystemCode = null;
//        ModelMap model = new ModelMap();
//        Oa12020Vo vo = new Oa12020Vo();
//        vo.setSubSystemCode(subSystemCode);
//
//        // 期待値
//        String expectedItemsSourceName = "oa12020::ajaxSelectBizTran";
//        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
//
//        // 実行
//        String actualItemsSourceName = oa12020Controller.getBizTranItemsSource(model, vo);
////        List<SelectOptionItemsSource> actualItemsSourceList = (List<SelectOptionItemsSource>) model.getAttribute("selectAjaxItems");
//        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));
//
//        // 結果検証
//        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
//        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
//    }
//
//    /**
//     * {@link Oa12020Controller#getBizTranItemsSource(ModelMap, Oa12020Vo)}テスト
//     *  ●パターン
//     *    正常
//     *
//     *  ●検証事項
//     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
//     */
//    @Test
//    @Tag(TestSize.SMALL)
//    void getBizTranItemsSource_test1() {
//
//        // テスト対象クラス生成
//        Oa12020Controller oa12020Controller = createOa12020Controller(-2);
//
//        // 実行値
//        String subSystemCode = SubSystem.販売_畜産.getCode();
//        ModelMap model = new ModelMap();
//        Oa12020Vo vo = new Oa12020Vo();
//        vo.setSubSystemCode(subSystemCode);
//
//        // 期待値
//        String expectedItemsSourceName = "oa12020::ajaxSelectBizTran";
//        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
//        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
//        for (BizTran bizTran : createBizTranList()) {
//            expectedItemsSourcelist.add(new SelectOptionItemSource(
//                bizTran.getBizTranId(), bizTran.getBizTranCode(), bizTran.getBizTranName()
//            ));
//        }
//
//        // 実行
//        String actualItemsSourceName = oa12020Controller.getBizTranItemsSource(model, vo);
////        List<SelectOptionItemsSource> actualItemsSourceList = (List<SelectOptionItemsSource>) model.getAttribute("selectAjaxItems");
//        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));
//
//        // 結果検証
//        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
//        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
//    }
//
//    /**
//     * {@link Oa12020Controller#getBizTranItemsSource(ModelMap, Oa12020Vo)}テスト
//     *  ●パターン
//     *    正常（）
//     *
//     *  ●検証事項
//     *  ・戻り値（ItemsSource名、SelectOptionItemsSource）
//     */
//    @Test
//    @Tag(TestSize.SMALL)
//    void getBizTranItemsSource_test2() {
//
//        // テスト対象クラス生成
//        Oa12020Controller oa12020Controller = createOa12020Controller(-2);
//
//        // 実行値
//        String subSystemCode = SubSystem.販売_畜産.getCode();
//        String bizTranGrpCode = "ANTG01";
//        ModelMap model = new ModelMap();
//        Oa12020Vo vo = new Oa12020Vo();
//        vo.setSubSystemCode(subSystemCode);
//        vo.setBizTranGrpCode(bizTranGrpCode);
//
//        // 期待値
//        String expectedItemsSourceName = "oa12020::ajaxSelectBizTran";
//        List<SelectOptionItemSource> expectedItemsSourcelist = newArrayList();
//        expectedItemsSourcelist.add(SelectOptionItemSource.empty());
//        for (BizTran bizTran : createBizTranList()) {
//            expectedItemsSourcelist.add(new SelectOptionItemSource(
//                bizTran.getBizTranId(), bizTran.getBizTranCode(), bizTran.getBizTranName()
//            ));
//        }
//
//        // 実行
//        String actualItemsSourceName = oa12020Controller.getBizTranItemsSource(model, vo);
////        List<SelectOptionItemsSource> actualItemsSourceList = (List<SelectOptionItemsSource>) model.getAttribute("selectAjaxItems");
//        List<SelectOptionItemsSource> actualItemsSourceList = autoCast(model.getAttribute("selectAjaxItems"));
//
//        // 結果検証
//        assertThat(actualItemsSourceName).isEqualTo(expectedItemsSourceName);
//        assertThat(actualItemsSourceList).usingRecursiveComparison().isEqualTo(expectedItemsSourcelist);
//    }
}