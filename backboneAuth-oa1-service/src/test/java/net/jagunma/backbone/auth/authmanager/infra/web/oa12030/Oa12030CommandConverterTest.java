package net.jagunma.backbone.auth.authmanager.infra.web.oa12030;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12030.vo.Oa12030Vo;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa12030CommandConverterTest {

    // 実行既定値
    private Long suspendBizTranId = 123456789L;
    private Boolean jaCheck = true;
    private String jaCode = "006";
    private Boolean branchCheck = true;
    private String branchCode = "001";
    private Boolean subSystemCheck = true;
    private String subSystemCode = SubSystem.販売_畜産.getCode();
    private Boolean bizTranGrpCheck = true;
    private String bizTranGrpCode = "ANTG01";
    private Boolean bizTranCheck = true;
    private String bizTranCode = "AN0001";
    private LocalDate suspendStartDate = LocalDate.of(2020,12,1);
    private LocalDate suspendEndDate = LocalDate.of(2020,12,2);
    private String suspendReason = "抑止理由";
    private Integer recordVersion = 1;

    // 実行値作成
    private Oa12030Vo createOa12030Vo() {
        Oa12030Vo vo = new Oa12030Vo();
        vo.setSuspendBizTranId(suspendBizTranId);
        vo.setJaCheck(jaCheck);
        vo.setJaCode(jaCode);
        vo.setBranchCheck(branchCheck);
        vo.setBranchCode(branchCode);
        vo.setSubSystemCheck(subSystemCheck);
        vo.setSubSystemCode(subSystemCode);
        vo.setBizTranGrpCheck(bizTranGrpCheck);
        vo.setBizTranGrpCode(bizTranGrpCode);
        vo.setBizTranCheck(bizTranCheck);
        vo.setBizTranCode(bizTranCode);
        vo.setSuspendStartDate(suspendStartDate);
        vo.setSuspendEndDate(suspendEndDate);
        vo.setSuspendReason(suspendReason);
        vo.setRecordVersion(recordVersion);
        return vo;
    }

    /**
     * {@link Oa12030CommandConverter#with(Oa12030Vo)}のテスト
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
        suspendBizTranId = null;
        jaCheck = null;
        jaCode = null;
        branchCheck = null;
        branchCode = null;
        subSystemCheck = null;
        subSystemCode = null;
        bizTranGrpCheck = null;
        bizTranGrpCode = null;
        bizTranCheck = null;
        bizTranCode = null;
        suspendStartDate = null;
        suspendEndDate = null;
        suspendReason = null;
        recordVersion = null;
        Oa12030Vo vo = createOa12030Vo();

        // 期待値
        Long expectedSuspendBizTranId = suspendBizTranId;
        String expectedJaCode = jaCode;
        String expectedBranchCode = branchCode;
        String expectedSubSystemCode = subSystemCode;
        String expectedBizTranGrpCode = bizTranGrpCode;
        String expectedBizTranCode = bizTranCode;
        LocalDate expectedsuspendStartDate = suspendStartDate;
        LocalDate expectedSuspendEndDate = suspendEndDate;
        String expectedSuspendReason = suspendReason;
        Integer expectedRecordVersion = recordVersion;

        // 実行
        Oa12030CommandConverter converter = Oa12030CommandConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa12030CommandConverter);
        assertThat(converter.getSuspendBizTranId()).isEqualTo(expectedSuspendBizTranId);
        assertThat(converter.getJaCode()).isEqualTo(expectedJaCode);
        assertThat(converter.getBranchCode()).isEqualTo(expectedBranchCode);
        assertThat(converter.getSubSystemCode()).isEqualTo(expectedSubSystemCode);
        assertThat(converter.getBizTranGrpCode()).isEqualTo(expectedBizTranGrpCode);
        assertThat(converter.getBizTranCode()).isEqualTo(expectedBizTranCode);
        assertThat(converter.getSuspendStartDate()).isEqualTo(expectedsuspendStartDate);
        assertThat(converter.getSuspendEndDate()).isEqualTo(expectedSuspendEndDate);
        assertThat(converter.getSuspendReason()).isEqualTo(expectedSuspendReason);
        assertThat(converter.getRecordVersion()).isEqualTo(expectedRecordVersion);
    }

    /**
     * {@link Oa12030CommandConverter#with(Oa12030Vo)}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test1() {

        // 実行値
        Oa12030Vo vo = createOa12030Vo();

        // 期待値
        Long expectedSuspendBizTranId = suspendBizTranId;
        String expectedJaCode = jaCode;
        String expectedBranchCode = branchCode;
        String expectedSubSystemCode = subSystemCode;
        String expectedBizTranGrpCode = bizTranGrpCode;
        String expectedBizTranCode = bizTranCode;
        LocalDate expectedsuspendStartDate = suspendStartDate;
        LocalDate expectedSuspendEndDate = suspendEndDate;
        String expectedSuspendReason = suspendReason;
        Integer expectedRecordVersion = recordVersion;

        // 実行
        Oa12030CommandConverter converter = Oa12030CommandConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa12030CommandConverter);
        assertThat(converter.getSuspendBizTranId()).isEqualTo(expectedSuspendBizTranId);
        assertThat(converter.getJaCode()).isEqualTo(expectedJaCode);
        assertThat(converter.getBranchCode()).isEqualTo(expectedBranchCode);
        assertThat(converter.getSubSystemCode()).isEqualTo(expectedSubSystemCode);
        assertThat(converter.getBizTranGrpCode()).isEqualTo(expectedBizTranGrpCode);
        assertThat(converter.getBizTranCode()).isEqualTo(expectedBizTranCode);
        assertThat(converter.getSuspendStartDate()).isEqualTo(expectedsuspendStartDate);
        assertThat(converter.getSuspendEndDate()).isEqualTo(expectedSuspendEndDate);
        assertThat(converter.getSuspendReason()).isEqualTo(expectedSuspendReason);
        assertThat(converter.getRecordVersion()).isEqualTo(expectedRecordVersion);
    }

    /**
     * {@link Oa12030CommandConverter#with(Oa12030Vo)}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test2() {

        // 実行値
        jaCheck = null;
        branchCheck = null;
        subSystemCheck = null;
        bizTranGrpCheck = null;
        bizTranCheck = null;
        Oa12030Vo vo = createOa12030Vo();

        // 期待値
        Long expectedSuspendBizTranId = suspendBizTranId;
        String expectedJaCode = null;
        String expectedBranchCode = null;
        String expectedSubSystemCode = null;
        String expectedBizTranGrpCode = null;
        String expectedBizTranCode = null;
        LocalDate expectedsuspendStartDate = suspendStartDate;
        LocalDate expectedSuspendEndDate = suspendEndDate;
        String expectedSuspendReason = suspendReason;
        Integer expectedRecordVersion = recordVersion;

        // 実行
        Oa12030CommandConverter converter = Oa12030CommandConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa12030CommandConverter);
        assertThat(converter.getSuspendBizTranId()).isEqualTo(expectedSuspendBizTranId);
        assertThat(converter.getJaCode()).isEqualTo(expectedJaCode);
        assertThat(converter.getBranchCode()).isEqualTo(expectedBranchCode);
        assertThat(converter.getSubSystemCode()).isEqualTo(expectedSubSystemCode);
        assertThat(converter.getBizTranGrpCode()).isEqualTo(expectedBizTranGrpCode);
        assertThat(converter.getBizTranCode()).isEqualTo(expectedBizTranCode);
        assertThat(converter.getSuspendStartDate()).isEqualTo(expectedsuspendStartDate);
        assertThat(converter.getSuspendEndDate()).isEqualTo(expectedSuspendEndDate);
        assertThat(converter.getSuspendReason()).isEqualTo(expectedSuspendReason);
        assertThat(converter.getRecordVersion()).isEqualTo(expectedRecordVersion);
    }
}