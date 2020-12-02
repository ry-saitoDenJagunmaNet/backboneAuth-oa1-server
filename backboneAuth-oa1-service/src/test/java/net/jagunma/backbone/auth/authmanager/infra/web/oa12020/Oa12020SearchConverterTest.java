package net.jagunma.backbone.auth.authmanager.infra.web.oa12020;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12020.vo.Oa12020SearchResultVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12020.vo.Oa12020Vo;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa12020SearchConverterTest {

    // 実行既定値
    private String jaCode = "006";
    private String branchCode = "001";
    private String subSystemCode = SubSystem.販売_畜産.getCode();
    private String bizTranGrpCode = "ANTG01";
    private String bizTranCode = "AN0001";
    private Integer suspendConditionsSelect = 1;
    private LocalDate suspendStatusDate = LocalDate.of(2020,11,1);
    private LocalDate suspendStatusStartDateFrom = LocalDate.of(2020,11,2);
    private LocalDate suspendStatusStartDateTo = LocalDate.of(2020,11,3);
    private LocalDate suspendStatusEndDateFrom = LocalDate.of(2020,11,4);
    private LocalDate suspendStatusEndDateTo = LocalDate.of(2020,11,5);
    private String suspendReason = "不具合により緊急抑止";
    private List<Oa12020SearchResultVo> searchResultList = newArrayList();
    private Integer pageNo = 2;

    /**
     * {@link Oa12020SearchConverter#with(Oa12020Vo)}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test0() {

        // 実行値
        jaCode = null;
        branchCode = null;
        subSystemCode = null;
        bizTranGrpCode = null;
        bizTranCode = null;
        suspendConditionsSelect = null;
        suspendStatusDate = null;
        suspendStatusStartDateFrom = null;
        suspendStatusStartDateTo = null;
        suspendStatusEndDateFrom = null;
        suspendStatusEndDateTo = null;
        suspendReason = null;
        searchResultList = newArrayList();
        pageNo = null;
        Oa12020Vo vo = new Oa12020Vo();
        vo.setJaCode(jaCode);
        vo.setBranchCode(branchCode);
        vo.setSubSystemCode(subSystemCode);
        vo.setBizTranGrpCode(bizTranGrpCode);
        vo.setBizTranCode(bizTranCode);
        vo.setSuspendConditionsSelect(suspendConditionsSelect);
        vo.setSuspendStatusDate(suspendStatusDate);
        vo.setSuspendStatusStartDateFrom(suspendStatusStartDateFrom);
        vo.setSuspendStatusStartDateTo(suspendStatusStartDateTo);
        vo.setSuspendStatusEndDateFrom(suspendStatusEndDateFrom);
        vo.setSuspendStatusEndDateTo(suspendStatusEndDateTo);
        vo.setSuspendReason(suspendReason);
        vo.setSearchResultList(searchResultList);
        vo.setPageNo(pageNo);

        // 期待値
        LongCriteria expectedJaIdCriteria = new LongCriteria();

        // 実行
        Oa12020SearchConverter converter = Oa12020SearchConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa12020SearchConverter);
        assertThat(converter.getJaCodeCriteria().getEqualTo()).isEqualTo(jaCode);
        assertThat(converter.getBranchCodeCriteria().getEqualTo()).isEqualTo(branchCode);
        assertThat(converter.getSubSystemCodeCriteria().getEqualTo()).isEqualTo(subSystemCode);
        assertThat(converter.getBizTranGrpCodeCriteria().getEqualTo()).isEqualTo(bizTranGrpCode);
        assertThat(converter.getBizTranCodeCriteria().getEqualTo()).isEqualTo(bizTranCode);
        assertThat(converter.getSuspendStartDateCriteria().getMoreOrEqual()).isEqualTo(suspendStatusStartDateFrom);
        assertThat(converter.getSuspendStartDateCriteria().getLessOrEqual()).isEqualTo(suspendStatusStartDateTo);
        assertThat(converter.getSuspendEndDateCriteria().getMoreOrEqual()).isEqualTo(suspendStatusEndDateFrom);
        assertThat(converter.getSuspendEndDateCriteria().getLessOrEqual()).isEqualTo(suspendStatusEndDateTo);
        assertThat(converter.getSuspendReasonCriteria().getForwardMatch()).isEqualTo(suspendReason);
    }

    /**
     * {@link Oa12020SearchConverter#with(Oa12020Vo)}のテスト
     *  ●パターン
     *    通常
     *    ・抑止期間条件選択=状態指定日
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test1() {

        // 実行値
        Oa12020Vo vo = new Oa12020Vo();
        vo.setJaCode(jaCode);
        vo.setBranchCode(branchCode);
        vo.setSubSystemCode(subSystemCode);
        vo.setBizTranGrpCode(bizTranGrpCode);
        vo.setBizTranCode(bizTranCode);
        vo.setSuspendConditionsSelect(suspendConditionsSelect);
        vo.setSuspendStatusDate(suspendStatusDate);
        vo.setSuspendReason(suspendReason);
        vo.setSearchResultList(searchResultList);
        vo.setPageNo(pageNo);

        // 期待値
        LongCriteria expectedJaIdCriteria = new LongCriteria();

        // 実行
        Oa12020SearchConverter converter = Oa12020SearchConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa12020SearchConverter);
        assertThat(converter.getJaCodeCriteria().getEqualTo()).isEqualTo(jaCode);
        assertThat(converter.getBranchCodeCriteria().getEqualTo()).isEqualTo(branchCode);
        assertThat(converter.getSubSystemCodeCriteria().getEqualTo()).isEqualTo(subSystemCode);
        assertThat(converter.getBizTranGrpCodeCriteria().getEqualTo()).isEqualTo(bizTranGrpCode);
        assertThat(converter.getBizTranCodeCriteria().getEqualTo()).isEqualTo(bizTranCode);
        assertThat(converter.getSuspendStartDateCriteria().getLessOrEqual()).isEqualTo(suspendStatusDate);
        assertThat(converter.getSuspendEndDateCriteria().getMoreOrEqual()).isEqualTo(suspendStatusDate);
        assertThat(converter.getSuspendReasonCriteria().getForwardMatch()).isEqualTo(suspendReason);
    }

    /**
     * {@link Oa12020SearchConverter#with(Oa12020Vo)}のテスト
     *  ●パターン
     *    通常
     *    ・抑止期間条件選択=条件指定
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test2() {

        // 実行値
        suspendConditionsSelect = 2;
        Oa12020Vo vo = new Oa12020Vo();
        vo.setJaCode(jaCode);
        vo.setBranchCode(branchCode);
        vo.setSubSystemCode(subSystemCode);
        vo.setBizTranGrpCode(bizTranGrpCode);
        vo.setBizTranCode(bizTranCode);
        vo.setSuspendConditionsSelect(suspendConditionsSelect);
        vo.setSuspendStatusStartDateFrom(suspendStatusStartDateFrom);
        vo.setSuspendStatusStartDateTo(suspendStatusStartDateTo);
        vo.setSuspendStatusEndDateFrom(suspendStatusEndDateFrom);
        vo.setSuspendStatusEndDateTo(suspendStatusEndDateTo);
        vo.setSuspendReason(suspendReason);
        vo.setSearchResultList(searchResultList);
        vo.setPageNo(pageNo);

        // 期待値
        LongCriteria expectedJaIdCriteria = new LongCriteria();

        // 実行
        Oa12020SearchConverter converter = Oa12020SearchConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa12020SearchConverter);
        assertThat(converter.getJaCodeCriteria().getEqualTo()).isEqualTo(jaCode);
        assertThat(converter.getBranchCodeCriteria().getEqualTo()).isEqualTo(branchCode);
        assertThat(converter.getSubSystemCodeCriteria().getEqualTo()).isEqualTo(subSystemCode);
        assertThat(converter.getBizTranGrpCodeCriteria().getEqualTo()).isEqualTo(bizTranGrpCode);
        assertThat(converter.getBizTranCodeCriteria().getEqualTo()).isEqualTo(bizTranCode);
        assertThat(converter.getSuspendStartDateCriteria().getMoreOrEqual()).isEqualTo(suspendStatusStartDateFrom);
        assertThat(converter.getSuspendStartDateCriteria().getLessOrEqual()).isEqualTo(suspendStatusStartDateTo);
        assertThat(converter.getSuspendEndDateCriteria().getMoreOrEqual()).isEqualTo(suspendStatusEndDateFrom);
        assertThat(converter.getSuspendEndDateCriteria().getLessOrEqual()).isEqualTo(suspendStatusEndDateTo);
        assertThat(converter.getSuspendReasonCriteria().getForwardMatch()).isEqualTo(suspendReason);
    }

    /**
     * {@link Oa12020SearchConverter#with(Oa12020Vo)}のテスト
     *  ●パターン
     *    通常
     *    ・抑止期間条件選択=指定なし
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test3() {

        // 実行値
        suspendConditionsSelect = 0;
        suspendStatusStartDateFrom = null;
        suspendStatusStartDateTo = null;
        suspendStatusEndDateFrom = null;
        suspendStatusEndDateTo = null;
        Oa12020Vo vo = new Oa12020Vo();
        vo.setJaCode(jaCode);
        vo.setBranchCode(branchCode);
        vo.setSubSystemCode(subSystemCode);
        vo.setBizTranGrpCode(bizTranGrpCode);
        vo.setBizTranCode(bizTranCode);
        vo.setSuspendConditionsSelect(suspendConditionsSelect);
        vo.setSuspendStatusStartDateFrom(suspendStatusStartDateFrom);
        vo.setSuspendStatusStartDateTo(suspendStatusStartDateTo);
        vo.setSuspendStatusEndDateFrom(suspendStatusEndDateFrom);
        vo.setSuspendStatusEndDateTo(suspendStatusEndDateTo);
        vo.setSuspendReason(suspendReason);
        vo.setSearchResultList(searchResultList);
        vo.setPageNo(pageNo);

        // 期待値
        LongCriteria expectedJaIdCriteria = new LongCriteria();

        // 実行
        Oa12020SearchConverter converter = Oa12020SearchConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa12020SearchConverter);
        assertThat(converter.getJaCodeCriteria().getEqualTo()).isEqualTo(jaCode);
        assertThat(converter.getBranchCodeCriteria().getEqualTo()).isEqualTo(branchCode);
        assertThat(converter.getSubSystemCodeCriteria().getEqualTo()).isEqualTo(subSystemCode);
        assertThat(converter.getBizTranGrpCodeCriteria().getEqualTo()).isEqualTo(bizTranGrpCode);
        assertThat(converter.getBizTranCodeCriteria().getEqualTo()).isEqualTo(bizTranCode);
        assertThat(converter.getSuspendStartDateCriteria().getMoreOrEqual()).isEqualTo(suspendStatusStartDateFrom);
        assertThat(converter.getSuspendStartDateCriteria().getLessOrEqual()).isEqualTo(suspendStatusStartDateTo);
        assertThat(converter.getSuspendEndDateCriteria().getMoreOrEqual()).isEqualTo(suspendStatusEndDateFrom);
        assertThat(converter.getSuspendEndDateCriteria().getLessOrEqual()).isEqualTo(suspendStatusEndDateTo);
        assertThat(converter.getSuspendReasonCriteria().getForwardMatch()).isEqualTo(suspendReason);
    }
}