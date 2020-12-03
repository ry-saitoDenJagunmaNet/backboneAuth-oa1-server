package net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SignOutTraceTest {
    /**
     * {@link SignOutTrace#createFrom(Long, LocalDateTime, String, Long, Integer, Operator)}テスト
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
        Long signOutTraceId = 1234L;
        LocalDateTime signOutDateTime = LocalDateTime.of(2020,10,1,8,30,12);
        String signOutIpAddress = "001.001.001.001";
        Long operatorId = 18L;
        Integer recordVersion = 1;

        String operatorCode = "yu001009";
        String operatorName = "ｙｕ００１００９";
        String mailAddress = "001.001.001.001";
        LocalDate validThruStartDate = LocalDate.of(2020, 1, 1);
        LocalDate validThruEndDate = LocalDate.of(9999, 12, 31);
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
            validThruStartDate,
            validThruEndDate,
            isDeviceAuth,
            jaId,
            jaCode,
            branchId,
            branchCode,
            availableStatus,
            operatorRecordVersion,
            branchAtMoment);

        // 実行
        SignOutTrace signOutTrace = SignOutTrace.createFrom(
            signOutTraceId,
            signOutDateTime,
            signOutIpAddress,
            operatorId,
            recordVersion,
            operator);

        // 結果検証
        assertTrue(signOutTrace instanceof SignOutTrace);
        assertThat(signOutTrace.getSignOutTraceId()).isEqualTo(signOutTraceId);
        assertThat(signOutTrace.getSignOutDateTime()).isEqualTo(signOutDateTime);
        assertThat(signOutTrace.getSignOutIpAddress()).isEqualTo(signOutIpAddress);
        assertThat(signOutTrace.getOperatorId()).isEqualTo(operatorId);
        assertThat(signOutTrace.getRecordVersion()).isEqualTo(recordVersion);
        assertThat(signOutTrace.getOperator()).usingRecursiveComparison().isEqualTo(operator);
    }
}