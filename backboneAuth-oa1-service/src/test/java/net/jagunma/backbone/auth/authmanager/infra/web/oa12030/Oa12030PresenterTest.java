package net.jagunma.backbone.auth.authmanager.infra.web.oa12030;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12030.vo.Oa12030Vo;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa12030PresenterTest {

    // 実行既定値
    private Long suspendBizTranId = 123456789L;
    private String jaCode = "006";
    private String branchCode = "001";
    private String subSystemCode = SubSystem.販売_畜産.getCode();
    private String bizTranGrpCode = "ANTG01";
    private String bizTranCode = "AN0001";
    private LocalDate suspendStartDate = LocalDate.of(2020,12,1);
    private LocalDate suspendEndDate = LocalDate.of(2020,12,2);
    private String suspendReason = "抑止理由";
    private Integer recordVersion = 1;

    // 実行値作成
    private Oa12030Presenter createPresenter() {
        Oa12030Presenter presenter = new Oa12030Presenter();
        presenter.setSuspendBizTranId(suspendBizTranId);
        presenter.setJaCode(jaCode);
        presenter.setBranchCode(branchCode);
        presenter.setSubSystemCode(subSystemCode);
        presenter.setBizTranGrpCode(bizTranGrpCode);
        presenter.setBizTranCode(bizTranCode);
        presenter.setSuspendStartDate(suspendStartDate);
        presenter.setSuspendEndDate(suspendEndDate);
        presenter.setSuspendReason(suspendReason);
        presenter.setRecordVersion(recordVersion);
        return presenter;
    }

    /**
     * {@link Oa12030Presenter#bindTo(Oa12030Vo)}のテスト
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
        Oa12030Vo vo = new Oa12030Vo();
        suspendBizTranId = null;
        jaCode = null;
        branchCode = null;
        subSystemCode = null;
        bizTranGrpCode = null;
        bizTranCode = null;
        suspendStartDate = null;
        suspendEndDate = null;
        suspendReason = null;
        recordVersion = null;;
        Oa12030Presenter presenter = createPresenter();

        // 期待値
        Oa12030Vo expectedVo = new Oa12030Vo();
        expectedVo.setSuspendBizTranId(suspendBizTranId);
        expectedVo.setJaCode(jaCode);
        expectedVo.setBranchCode(branchCode);
        expectedVo.setSubSystemCode(subSystemCode);
        expectedVo.setBizTranGrpCode(bizTranGrpCode);
        expectedVo.setBizTranCode(bizTranCode);
        expectedVo.setSuspendStartDate(suspendStartDate);
        expectedVo.setSuspendEndDate(suspendEndDate);
        expectedVo.setSuspendReason(suspendReason);
        expectedVo.setRecordVersion(recordVersion);

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa12030Presenter#bindTo(Oa12030Vo)}のテスト
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
        Oa12030Vo vo = new Oa12030Vo();
        Oa12030Presenter presenter = createPresenter();

        // 期待値
        Oa12030Vo expectedVo = new Oa12030Vo();
        expectedVo.setSuspendBizTranId(suspendBizTranId);
        expectedVo.setJaCode(jaCode);
        expectedVo.setBranchCode(branchCode);
        expectedVo.setSubSystemCode(subSystemCode);
        expectedVo.setBizTranGrpCode(bizTranGrpCode);
        expectedVo.setBizTranCode(bizTranCode);
        expectedVo.setSuspendStartDate(suspendStartDate);
        expectedVo.setSuspendEndDate(suspendEndDate);
        expectedVo.setSuspendReason(suspendReason);
        expectedVo.setRecordVersion(recordVersion);

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }
}