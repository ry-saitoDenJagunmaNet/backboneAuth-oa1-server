package net.jagunma.backbone.auth.authmanager.infra.web.oa12020;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12020.vo.Oa12020SearchResultVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12020.vo.Oa12020Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTrans;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
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

class Oa12020PresenterTest {

    // 実行既定値
    private Long jaId = 6L;
    private String jaCode = "006";
    private String jaName = "ＪＡ006";
    private Long branchId = 33L;
    private String branchCode = "001";
    private String branchName = "店舗001";
    private String subSystemCode = SubSystem.販売_畜産.getCode();
    private Long bizTranGrpId = 10001L;
    private String bizTranGrpCode = "ANTG01";
    private String bizTranGrpName = "データ入力取引グループ";
    private Long bizTranId = 100001L;
    private String bizTranCode = "AN0001";
    private String bizTranName = "畜産メインメニュー";
    private Integer suspendConditionsSelect = 1;
    private LocalDate suspendStatusDate = LocalDate.of(2020,11,1);
    private LocalDate suspendStatusStartDateFrom = LocalDate.of(2020,11,2);
    private LocalDate suspendStatusStartDateTo = LocalDate.of(2020,11,3);
    private LocalDate suspendStatusEndDateFrom = LocalDate.of(2020,11,4);
    private LocalDate suspendStatusEndDateTo = LocalDate.of(2020,11,5);
    private String suspendReason = "不具合により緊急抑止";
    private Integer pageNo = 1;
    private SuspendBizTrans suspendBizTrans = SuspendBizTrans.createFrom(createSuspendBizTranList());
    private Integer paginationLastPageNo = 1;

    // 一時取引抑止リスト作成
    private List<SuspendBizTran> createSuspendBizTranList() {
        List<SuspendBizTran> list = newArrayList();
        list.add(SuspendBizTran.createFrom(
            1L,jaId,branchId,SubSystem.販売_畜産.getCode(),bizTranGrpId,bizTranId,LocalDate.of(2020,11,1),LocalDate.of(2020,11,2),"不具合により緊急抑止",1,
            createJaAtMoment(),
            createBranchAtMoment(),
            SubSystem.販売_畜産,
            BizTranGrp.createFrom(bizTranGrpId,bizTranGrpCode,bizTranGrpName,SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            BizTran.createFrom(bizTranId,bizTranCode,bizTranName,false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産)
        ));
        list.add(SuspendBizTran.createFrom(
            2L,null,null,null,null,null,LocalDate.of(2020,11,1),LocalDate.of(2020,11,2),"不具合により緊急抑止",1,
            null,
            null,
            null,
            null,
            null
        ));
        return list;
    }
    // JaAtMomentデータの作成
    private JaAtMoment createJaAtMoment() {
        return JaAtMoment.builder().withIdentifier(jaId).withJaAttribute(JaAttribute.builder()
            .withJaCode(JaCode.of(jaCode)).withName(jaName).withFormalName("").withAbbreviatedName("").build())
            .build();
    }
    private BranchAtMoment createBranchAtMoment() {
        return BranchAtMoment.builder()
            .withIdentifier(branchId).withJaAtMoment(
                createJaAtMoment()).withBranchAttribute(
                BranchAttribute.builder().withBranchType(BranchType.一般).withBranchCode(BranchCode.of(branchCode)).withName(branchName).build())
            .build();
    }

    // 実行値作成
    private Oa12020Presenter createPresenter() {
        Oa12020Presenter presenter = new Oa12020Presenter();
        presenter.setJaId(jaId);
        presenter.setBranchId(branchId);
        presenter.setSubSystemCode(subSystemCode);
        presenter.setBizTranGrpId(bizTranGrpId);
        presenter.setBizTranId(bizTranId);
        presenter.setSuspendConditionsSelect(suspendConditionsSelect);
        presenter.setSuspendStatusDate(suspendStatusDate);
        presenter.setSuspendStatusStartDateFrom(suspendStatusStartDateFrom);
        presenter.setSuspendStatusStartDateTo(suspendStatusStartDateTo);
        presenter.setSuspendStatusEndDateFrom(suspendStatusEndDateFrom);
        presenter.setSuspendStatusEndDateTo(suspendStatusEndDateTo);
        presenter.setSuspendReason(suspendReason);
        presenter.setPageNo(pageNo);
        presenter.setSuspendBizTrans(suspendBizTrans);
        presenter.setPaginationLastPageNo(paginationLastPageNo);
        return presenter;
    }

    /**
     * {@link Oa12020Presenter#bindTo(Oa12020Vo)}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test0() {

        // テスト対象クラス生成 & 実行値
        Oa12020Vo vo = new Oa12020Vo();
        jaId = null;
        branchId = null;
        subSystemCode = null;
        bizTranGrpId = null;
        bizTranId = null;
        suspendConditionsSelect = null;
        suspendStatusDate = null;
        suspendStatusStartDateFrom = null;
        suspendStatusStartDateTo = null;
        suspendStatusEndDateFrom = null;
        suspendStatusEndDateTo = null;
        suspendReason = null;
        pageNo = null;
        suspendBizTrans = null;
        paginationLastPageNo = 0;

        Oa12020Presenter presenter = createPresenter();

        // 期待値
        Oa12020Vo expectedVo = new Oa12020Vo();
        expectedVo.setJaId(jaId);
        expectedVo.setBranchId(branchId);
        expectedVo.setSubSystemCode(subSystemCode);
        expectedVo.setBizTranGrpId(bizTranGrpId);
        expectedVo.setBizTranId(bizTranId);
        expectedVo.setSuspendConditionsSelect(suspendConditionsSelect);
        expectedVo.setSuspendStatusDate(suspendStatusDate);
        expectedVo.setSuspendStatusStartDateFrom(suspendStatusStartDateFrom);
        expectedVo.setSuspendStatusStartDateTo(suspendStatusStartDateTo);
        expectedVo.setSuspendStatusEndDateFrom(suspendStatusEndDateFrom);
        expectedVo.setSuspendStatusEndDateTo(suspendStatusEndDateTo);
        expectedVo.setSuspendReason(suspendReason);
        expectedVo.setPageNo(pageNo);
        expectedVo.setSearchResultList(newArrayList());
        expectedVo.setPaginationLastPageNo(paginationLastPageNo);

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa12020Presenter#bindTo(Oa12020Vo)}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test1() {

        // テスト対象クラス生成 & 実行値
        Oa12020Vo vo = new Oa12020Vo();
        Oa12020Presenter presenter = createPresenter();

        // 期待値
        Oa12020Vo expectedVo = new Oa12020Vo();
        expectedVo.setJaId(jaId);
        expectedVo.setBranchId(branchId);
        expectedVo.setSubSystemCode(subSystemCode);
        expectedVo.setBizTranGrpId(bizTranGrpId);
        expectedVo.setBizTranId(bizTranId);
        expectedVo.setSuspendConditionsSelect(suspendConditionsSelect);
        expectedVo.setSuspendStatusDate(suspendStatusDate);
        expectedVo.setSuspendStatusStartDateFrom(suspendStatusStartDateFrom);
        expectedVo.setSuspendStatusStartDateTo(suspendStatusStartDateTo);
        expectedVo.setSuspendStatusEndDateFrom(suspendStatusEndDateFrom);
        expectedVo.setSuspendStatusEndDateTo(suspendStatusEndDateTo);
        expectedVo.setSuspendReason(suspendReason);
        expectedVo.setPageNo(pageNo);
        List<Oa12020SearchResultVo> expectedSearchResultVoList = newArrayList();
        for(SuspendBizTran suspendBizTran : createSuspendBizTranList()) {
            Oa12020SearchResultVo searchResultVo = new Oa12020SearchResultVo();
            searchResultVo.setSuspendBizTranId(suspendBizTran.getSuspendBizTranId());
            searchResultVo.setJaCode((suspendBizTran.getJaAtMoment() == null)? null : suspendBizTran.getJaAtMoment().getJaAttribute().getJaCode().getValue());
            searchResultVo.setJaName((suspendBizTran.getJaAtMoment() == null)? null :  suspendBizTran.getJaAtMoment().getJaAttribute().getName());
            searchResultVo.setBranchCode((suspendBizTran.getBranchAtMoment() == null)? null : suspendBizTran.getBranchAtMoment().getBranchAttribute().getBranchCode().getValue());
            searchResultVo.setBranchName((suspendBizTran.getBranchAtMoment() == null)? null : suspendBizTran.getBranchAtMoment().getBranchAttribute().getName());
            searchResultVo.setSubSystemName((suspendBizTran.getSubSystem() == null)? null : suspendBizTran.getSubSystem().getName());
            searchResultVo.setBizTranGrpCode((suspendBizTran.getBizTranGrp() == null)? null : suspendBizTran.getBizTranGrp().getBizTranGrpCode());
            searchResultVo.setBizTranGrpName((suspendBizTran.getBizTranGrp() == null)? null : suspendBizTran.getBizTranGrp().getBizTranGrpName());
            searchResultVo.setBizTranCode((suspendBizTran.getBizTran() == null)? null : suspendBizTran.getBizTran().getBizTranCode());
            searchResultVo.setBizTranName((suspendBizTran.getBizTran() == null)? null : suspendBizTran.getBizTran().getBizTranName());
            searchResultVo.setSuspendStartDate(suspendBizTran.getSuspendStartDate());
            searchResultVo.setSuspendEndDate(suspendBizTran.getSuspendEndDate());
            searchResultVo.setSuspendReason(suspendBizTran.getSuspendReason());
            expectedSearchResultVoList.add(searchResultVo);
        }
        expectedVo.setSearchResultList(expectedSearchResultVoList);
        expectedVo.setPaginationLastPageNo(paginationLastPageNo);

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa12020Presenter#with(Oa12020Vo)}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Presenterへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test0() {

        // 実行値
        Oa12020Vo vo = new Oa12020Vo();

        // 期待値
        Oa12020Presenter expectedPresenter = new Oa12020Presenter();

        // 実行
        Oa12020Presenter presenter = Oa12020Presenter.with(vo);

        // 結果検証
        assertTrue(presenter instanceof Oa12020Presenter);
        assertThat(presenter).usingRecursiveComparison().isEqualTo(expectedPresenter);
    }

    /**
     * {@link Oa12020Presenter#with(Oa12020Vo)}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Presenterへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test1() {

        // 実行値
        Oa12020Vo vo = new Oa12020Vo();
        vo.setJaId(jaId);
        vo.setBranchId(branchId);
        vo.setSubSystemCode(subSystemCode);
        vo.setBizTranGrpId(bizTranGrpId);
        vo.setBizTranId(bizTranId);
        vo.setSuspendConditionsSelect(suspendConditionsSelect);
        vo.setSuspendStatusDate(suspendStatusDate);
        vo.setSuspendStatusStartDateFrom(suspendStatusStartDateFrom);
        vo.setSuspendStatusStartDateTo(suspendStatusStartDateTo);
        vo.setSuspendStatusEndDateFrom(suspendStatusEndDateFrom);
        vo.setSuspendStatusEndDateTo(suspendStatusEndDateTo);
        vo.setPageNo(pageNo);
        vo.setSuspendReason(suspendReason);
        vo.setPaginationLastPageNo(paginationLastPageNo);

        // 期待値
        Oa12020Presenter expectedPresenter = new Oa12020Presenter();
        expectedPresenter.setJaId(jaId);
        expectedPresenter.setBranchId(branchId);
        expectedPresenter.setSubSystemCode(subSystemCode);
        expectedPresenter.setBizTranGrpId(bizTranGrpId);
        expectedPresenter.setBizTranId(bizTranId);
        expectedPresenter.setSuspendConditionsSelect(suspendConditionsSelect);
        expectedPresenter.setSuspendStatusDate(suspendStatusDate);
        expectedPresenter.setSuspendStatusStartDateFrom(suspendStatusStartDateFrom);
        expectedPresenter.setSuspendStatusStartDateTo(suspendStatusStartDateTo);
        expectedPresenter.setSuspendStatusEndDateFrom(suspendStatusEndDateFrom);
        expectedPresenter.setSuspendStatusEndDateTo(suspendStatusEndDateTo);
        expectedPresenter.setPageNo(pageNo);
        expectedPresenter.setSuspendReason(suspendReason);

        // 実行
        Oa12020Presenter presenter = Oa12020Presenter.with(vo);

        // 結果検証
        assertTrue(presenter instanceof Oa12020Presenter);
        assertThat(presenter).usingRecursiveComparison().isEqualTo(expectedPresenter);
    }
}