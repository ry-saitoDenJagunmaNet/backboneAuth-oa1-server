package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTranSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTranSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTransRepository;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import net.jagunma.common.values.model.ja.JasAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SearchSuspendBizTranTest {

    // 実行既定値
    private List<SuspendBizTran> suspendBizTranList = createSuspendBizTranList();

    // 検証値
    private SuspendBizTrans actualSuspendBizTrans;

    // 一時取引抑止リストデータ作成
    private List<SuspendBizTran> createSuspendBizTranList() {
        List<SuspendBizTran> list = newArrayList();
        list.add(SuspendBizTran.createFrom(1L,6L,33L,SubSystem.販売_畜産.getCode(),10001L,100001L,LocalDate.of(2020,11,1),LocalDate.of(2020,11,2),"×××により緊急抑止",1,
            createBranchAtMoment().stream().filter(j->j.getIdentifier().equals(6L)).findFirst().orElse(null),
            SubSystem.販売_畜産,
            createBizTranGrpList().stream().filter(b->b.getBizTranGrpId().equals(10001L)).findFirst().orElse(null),
            createBizTranList().stream().filter(b->b.getBizTranId().equals(100001L)).findFirst().orElse(null)));
        list.add(SuspendBizTran.createFrom(2L,null,null,SubSystem.購買.getCode(),null,null,LocalDate.of(2020,11,1),LocalDate.of(2020,11,2),"×××により緊急抑止",1,
            null,SubSystem.購買,null,null));
        return list;
    }
    // 店舗リストデータ作成
    private List<BranchAtMoment> createBranchAtMoment() {
        List<BranchAtMoment> list = newArrayList();
        list.add(BranchAtMoment.builder().withIdentifier(32L)
            .withJaAtMoment(createJaAtMoment().stream().filter(j->j.getIdentifier().equals(6L)).findFirst().orElse(null))
            .withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.一般).withBranchCode(BranchCode.of("005")).withName("005店舗").build()).build());
        list.add(BranchAtMoment.builder().withIdentifier(33L)
            .withJaAtMoment(createJaAtMoment().stream().filter(j->j.getIdentifier().equals(6L)).findFirst().orElse(null))
            .withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.一般).withBranchCode(BranchCode.of("006")).withName("006店舗").build()).build());
        list.add(BranchAtMoment.builder().withIdentifier(34L)
            .withJaAtMoment(createJaAtMoment().stream().filter(j->j.getIdentifier().equals(6L)).findFirst().orElse(null))
            .withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.一般).withBranchCode(BranchCode.of("007")).withName("007店舗").build()).build());
        return list;
    }
    // ＪＡリストデータ作成
    private List<JaAtMoment> createJaAtMoment() {
        List<JaAtMoment> list = newArrayList();
        list.add(JaAtMoment.builder().withIdentifier(6L)
            .withJaAttribute(JaAttribute.builder().withJaCode(JaCode.of("006")).withName("JA006").withFormalName("").withAbbreviatedName("").build()).build());
        return list;
    }
    // 取引グループリストデータ作成
    private List<BizTranGrp> createBizTranGrpList() {
        List<BizTranGrp> list = newArrayList();
        list.add(BizTranGrp.createFrom(10001L,"ANTG01","データ入力取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return list;
    }
    // 取引グループリストデータ作成
    private List<BizTran> createBizTranList() {
        List<BizTran> list = newArrayList();
        list.add(BizTran.createFrom(100001L,"AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTran.createFrom(100002L,"AN1110","前日処理照会",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return list;
    }
    // 一時取引抑止群検索生成
    private SuspendBizTransRepository createSuspendBizTransRepository() {
        return new SuspendBizTransRepository() {
            @Override
            public SuspendBizTrans selectBy(SuspendBizTranCriteria suspendBizTranCriteria,Orders orders) {
                return SuspendBizTrans.createFrom(suspendBizTranList);
            }
        };
    }
    // 一時取引抑止<一覧>検索サービス Request生成
    private SuspendBizTranSearchRequest createSuspendBizTranSearchRequest() {
        return new SuspendBizTranSearchRequest() {
            @Override
            public LongCriteria getJaIdCriteria() {
                return new LongCriteria();
            }
            @Override
            public LongCriteria getBranchIdCriteria() {
                return new LongCriteria();
            }
            @Override
            public StringCriteria getSubSystemCodeCriteria() {
                return new StringCriteria();
            }
            @Override
            public LongCriteria getBizTranGrpIdCriteria() {
                return new LongCriteria();
            }
            @Override
            public LongCriteria getBizTranIdCriteria() {
                return new LongCriteria();
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
    // 一時取引抑止<一覧>検索サービス Response生成
    private SuspendBizTranSearchResponse createSuspendBizTranSearchResponse() {
        return new SuspendBizTranSearchResponse() {
            @Override
            public void setSuspendBizTrans(SuspendBizTrans suspendBizTrans) {
                actualSuspendBizTrans = suspendBizTrans;
            }
            @Override
            public void setJaId(Long jaId) {
            }
            @Override
            public void setJasAtMoment(JasAtMoment jasAtMoment) {
            }
            @Override
            public void setBranchId(Long branchId) {
            }
            @Override
            public void setBranchesAtMoment(BranchesAtMoment branchesAtMoment) {
            }
            @Override
            public void setSubSystemCode(String subSystemCode) {
            }
            @Override
            public void setBizTranGrpId(Long bizTranGrpId) {
            }
            @Override
            public void setBizTranGrps(BizTranGrps bizTranGrps) {
            }
            @Override
            public void setBizTranId(Long bizTranId) {
            }
            @Override
            public void setBizTrans(BizTrans bizTrans) {
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

    /**
     * {@link SearchSuspendBizTran#execute(SuspendBizTranSearchRequest,SuspendBizTranSearchResponse)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test0() {

        // テスト対象クラス生成
        SearchSuspendBizTran searchSuspendBizTran = new SearchSuspendBizTran(createSuspendBizTransRepository());

        // 期待値
        List<SuspendBizTran> expectedSuspendBizTranList = suspendBizTranList;
        
        // 実行
        searchSuspendBizTran.execute(
            createSuspendBizTranSearchRequest(),
            createSuspendBizTranSearchResponse());

        // 結果検証
        for(int i = 0; i < actualSuspendBizTrans.getValues().size(); i++) {
            assertThat(actualSuspendBizTrans.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedSuspendBizTranList.get(i));
        }
    }

    /**
     * {@link SearchSuspendBizTran#execute(SuspendBizTranSearchRequest,SuspendBizTranSearchResponse)}のテスト
     *  ●パターン
     *    正常（検索結果0件）
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test1() {

        // 実行値
        suspendBizTranList = newArrayList();

        // テスト対象クラス生成
        SearchSuspendBizTran searchSuspendBizTran = new SearchSuspendBizTran(createSuspendBizTransRepository());

        // 実行
        searchSuspendBizTran.execute(
            createSuspendBizTranSearchRequest(),
            createSuspendBizTranSearchResponse());

        // 結果検証
        assertThat(actualSuspendBizTrans.getValues().size()).isEqualTo(0);
    }
}