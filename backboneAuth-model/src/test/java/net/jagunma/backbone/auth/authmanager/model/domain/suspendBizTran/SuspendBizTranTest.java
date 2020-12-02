package net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
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

class SuspendBizTranTest {

    // 実行 ＆ 期待 既定値
    private Long suspendBizTranId = 1L;
    private LocalDate suspendStartDate = LocalDate.of(2020,4,1);
    private LocalDate suspendEndDate = LocalDate.of(2020,4,2);
    private String suspendReason = "抑止理由";
    private Integer recordVersion = 1;

    private SubSystem subSystem = SubSystem.販売_畜産;
    private String subSystemCode = subSystem.getCode();
    private Integer subSystemDisplaySortOrder = SubSystem.販売_畜産.getDisplaySortOrder();

    private final Long bizTranGrpId = 10001L;
    private String bizTranGrpCode = "ANTG01";
    private final String bizTranGrpName = "データ入力取引グループ";
    private BizTranGrp bizTranGrp = BizTranGrp.createFrom(bizTranGrpId,bizTranGrpCode,bizTranGrpName,subSystemCode,1,subSystem);

    private final Long bizTranId = 100001L;
    private String bizTranCode = "AN0001";
    private final String bizTranName = "畜産メインメニュー";
    private BizTran bizTran = BizTran.createFrom(bizTranId,bizTranCode,bizTranName,false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),subSystemCode,1,subSystem);

    private final Long jaId = 6L;
    private String jaCode = "006";
    private final String jaName = "テストＪＡ";
    private JaAtMoment jaAtMoment = JaAtMoment.builder()
        .withIdentifier(jaId)
        .withJaAttribute(JaAttribute
            .builder()
            .withJaCode(JaCode.of(jaCode))
            .withName(jaName)
            .withFormalName("")
            .withAbbreviatedName("")
            .build())
        .build();

    private final Long branchId = 33L;
    private String branchCode = "001";
    private final String branchName = "テスト店舗";
    private BranchAtMoment branchAtMoment = BranchAtMoment.builder()
        .withIdentifier(branchId)
        .withJaAtMoment(jaAtMoment)
        .withBranchAttribute(BranchAttribute.builder()
            .withBranchType(BranchType.一般)
            .withBranchCode(BranchCode.of(branchCode))
            .withName(branchName)
            .build())
        .build();

    /**
     * {@link SuspendBizTran#createFrom(Long,String,String,String,String,String,LocalDate,LocalDate,String,Integer,JaAtMoment,BranchAtMoment,SubSystem,BizTranGrp,BizTran)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_test0() {

        // 実行
        SuspendBizTran suspendBizTran = SuspendBizTran.createFrom(
            suspendBizTranId,
            jaCode,
            branchCode,
            subSystemCode,
            bizTranGrpCode,
            bizTranCode,
            suspendStartDate,
            suspendEndDate,
            suspendReason,
            recordVersion,
            jaAtMoment,
            branchAtMoment,
            subSystem,
            bizTranGrp,
            bizTran);

        // 結果検証
        assertTrue(suspendBizTran instanceof SuspendBizTran);
        assertThat(suspendBizTran.getSuspendBizTranId()).isEqualTo(suspendBizTranId);
        assertThat(suspendBizTran.getJaCode()).isEqualTo(jaCode);
        assertThat(suspendBizTran.getBranchCode()).isEqualTo(branchCode);
        assertThat(suspendBizTran.getSubSystemCode()).isEqualTo(subSystemCode);
        assertThat(suspendBizTran.getBizTranGrpCode()).isEqualTo(bizTranGrpCode);
        assertThat(suspendBizTran.getBizTranCode()).isEqualTo(bizTranCode);
        assertThat(suspendBizTran.getSuspendStartDate()).isEqualTo(suspendStartDate);
        assertThat(suspendBizTran.getSuspendEndDate()).isEqualTo(suspendEndDate);
        assertThat(suspendBizTran.getSuspendReason()).isEqualTo(suspendReason);
        assertThat(suspendBizTran.getRecordVersion()).isEqualTo(recordVersion);
        assertThat(suspendBizTran.getJaAtMoment()).usingRecursiveComparison().isEqualTo(jaAtMoment);
        assertThat(suspendBizTran.getBranchAtMoment()).usingRecursiveComparison().isEqualTo(branchAtMoment);
        assertThat(suspendBizTran.getSubSystem()).usingRecursiveComparison().isEqualTo(subSystem);
        assertThat(suspendBizTran.getSubSystemDisplaySortOrder()).isEqualTo(subSystem.getDisplaySortOrder());
        assertThat(suspendBizTran.getBizTranGrp()).usingRecursiveComparison().isEqualTo(bizTranGrp);
        assertThat(suspendBizTran.getBizTran()).usingRecursiveComparison().isEqualTo(bizTran);
    }

    /**
     * {@link SuspendBizTran#createFrom(Long,String,String,String,String,String,LocalDate,LocalDate,String,Integer,JaAtMoment,BranchAtMoment,SubSystem,BizTranGrp,BizTran)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_test1() {

        // 実行値
        suspendBizTranId = null;
        jaCode = null;
        branchCode = null;
        subSystemCode = null;
        bizTranGrpCode = null;
        bizTranCode = null;
        suspendStartDate = null;
        suspendEndDate = null;
        suspendReason = null;
        recordVersion = null;
        jaAtMoment = null;
        branchAtMoment = null;
        subSystem = null;
        subSystemDisplaySortOrder = 0;
        bizTranGrp = null;
        bizTran = null;

        // 実行
        SuspendBizTran suspendBizTran = SuspendBizTran.createFrom(
            suspendBizTranId,
            jaCode,
            branchCode,
            subSystemCode,
            bizTranGrpCode,
            bizTranCode,
            suspendStartDate,
            suspendEndDate,
            suspendReason,
            recordVersion,
            jaAtMoment,
            branchAtMoment,
            subSystem,
            bizTranGrp,
            bizTran);

        // 結果検証
        assertTrue(suspendBizTran instanceof SuspendBizTran);
        assertThat(suspendBizTran.getSuspendBizTranId()).isEqualTo(suspendBizTranId);
        assertThat(suspendBizTran.getJaCode()).isEqualTo(jaCode);
        assertThat(suspendBizTran.getBranchCode()).isEqualTo(branchCode);
        assertThat(suspendBizTran.getSubSystemCode()).isEqualTo(subSystemCode);
        assertThat(suspendBizTran.getBizTranGrpCode()).isEqualTo(bizTranGrpCode);
        assertThat(suspendBizTran.getBizTranCode()).isEqualTo(bizTranCode);
        assertThat(suspendBizTran.getSuspendStartDate()).isEqualTo(suspendStartDate);
        assertThat(suspendBizTran.getSuspendEndDate()).isEqualTo(suspendEndDate);
        assertThat(suspendBizTran.getSuspendReason()).isEqualTo(suspendReason);
        assertThat(suspendBizTran.getRecordVersion()).isEqualTo(recordVersion);
        assertThat(suspendBizTran.getJaAtMoment()).usingRecursiveComparison().isEqualTo(jaAtMoment);
        assertThat(suspendBizTran.getBranchAtMoment()).usingRecursiveComparison().isEqualTo(branchAtMoment);
        assertThat(suspendBizTran.getSubSystem()).usingRecursiveComparison().isEqualTo(subSystem);
        assertThat(suspendBizTran.getSubSystemDisplaySortOrder()).isEqualTo(subSystemDisplaySortOrder);
        assertThat(suspendBizTran.getBizTranGrp()).usingRecursiveComparison().isEqualTo(bizTranGrp);
        assertThat(suspendBizTran.getBizTran()).usingRecursiveComparison().isEqualTo(bizTran);
    }

}
