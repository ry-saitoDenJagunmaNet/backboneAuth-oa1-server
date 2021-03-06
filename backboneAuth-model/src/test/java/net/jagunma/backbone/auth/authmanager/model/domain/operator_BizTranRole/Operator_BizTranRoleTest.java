package net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Operator_BizTranRoleTest {

    /**
     * {@link Operator_BizTranRole#createFrom(Long,Long,Long,LocalDate,LocalDate,Integer,Operator,BizTranRole )}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_test0() {

        // 実行値 ＆ 期待値
        Long operator_BizTranRoleId = 123L;
        Long operatorId = 18L;
        Long bizTranRoleId = 456L;
        LocalDate validThruStartDate = LocalDate.of(2020,1,1);
        LocalDate validThruEndDate = LocalDate.of(9999,12,31);
        Integer recordVersion = 1;

        String operatorCode = "yu001009";
        String operatorName = "ｙｕ００１００９";
        String mailAddress = "001.001.001.001";
        LocalDate operatorValidThruStartDate = LocalDate.of(2020, 1, 1);
        LocalDate operatorValidThruEndDate = LocalDate.of(9999, 12, 31);
        Boolean isDeviceAuth = false;
        Long jaId = 6L;
        String jaCode = "006";
        Long branchId = 33L;
        String branchCode = "001";
        AvailableStatus availableStatus = AvailableStatus.利用可能;
        Integer operatorRecordVersion = 1;
        BranchAtMoment branchAtMoment = null;
        Operator operator = Operator.createFrom(
            operatorId,
            operatorCode,
            operatorName,
            mailAddress,
            operatorValidThruStartDate,
            operatorValidThruEndDate,
            isDeviceAuth,
            jaId,
            jaCode,
            branchId,
            branchCode,
            availableStatus,
            operatorRecordVersion,
            branchAtMoment);

        String bizTranRoleCode = "KBAG01";
        String bizTranRoleName = "（購買）購買業務基本";
        String subSystemCode = "KB";
        Integer bizTranRoleRecordVersion = 1;
        SubSystem subSystem = SubSystem.購買;
        BizTranRole bizTranRole = BizTranRole.createFrom(
            bizTranRoleId,
            bizTranRoleCode,
            bizTranRoleName,
            subSystemCode,
            bizTranRoleRecordVersion,
            subSystem);

        // 実行
        Operator_BizTranRole operator_BizTranRole = Operator_BizTranRole.createFrom(
            operator_BizTranRoleId,
            operatorId,
            bizTranRoleId,
            validThruStartDate,
            validThruEndDate,
            recordVersion,
            operator,
            bizTranRole);

        // 結果検証
        assertTrue(operator_BizTranRole instanceof Operator_BizTranRole);
        assertThat(operator_BizTranRole.getOperator_BizTranRoleId()).isEqualTo(operator_BizTranRoleId);
        assertThat(operator_BizTranRole.getOperatorId()).isEqualTo(operatorId);
        assertThat(operator_BizTranRole.getBizTranRoleId()).isEqualTo(bizTranRoleId);
        assertThat(operator_BizTranRole.getValidThruStartDate()).isEqualTo(validThruStartDate);
        assertThat(operator_BizTranRole.getValidThruEndDate()).isEqualTo(validThruEndDate);
        assertThat(operator_BizTranRole.getRecordVersion()).isEqualTo(recordVersion);
        assertThat(operator_BizTranRole.getOperator()).usingRecursiveComparison().isEqualTo(operator);
        assertThat(operator_BizTranRole.getBizTranRole()).usingRecursiveComparison().isEqualTo(bizTranRole);
    }
}