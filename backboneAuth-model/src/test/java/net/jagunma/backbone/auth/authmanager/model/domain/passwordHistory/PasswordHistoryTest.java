package net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PasswordHistoryTest {

    /**
     * {@link PasswordHistory#createFrom(Long, Long, LocalDateTime, String, PasswordChangeType, Integer, Operator)}テスト
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
        Long passwordHistoryId = 123L;
        Long operatorId = 20L;
        LocalDateTime changeDateTime = LocalDateTime.of(2020, 10, 1, 8,30,12);
        String password = "password";
        PasswordChangeType passwordChangeType = PasswordChangeType.初期;
        Integer recordVersion = 1;

        String operatorCode = "yu001011";
        String operatorName = "ｙｕ００１０１１";
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
        PasswordHistory passwordHistory = PasswordHistory.createFrom(
            passwordHistoryId,
            operatorId,
            changeDateTime,
            password,
            passwordChangeType,
            recordVersion,
            operator);

        // 結果検証
        assertTrue(passwordHistory instanceof PasswordHistory);
        assertThat(passwordHistory.getPasswordHistoryId()).isEqualTo(passwordHistoryId);
        assertThat(passwordHistory.getOperatorId()).isEqualTo(operatorId);
        assertThat(passwordHistory.getChangeDateTime()).isEqualTo(changeDateTime);
        assertThat(passwordHistory.getPassword()).isEqualTo(password);
        assertThat(passwordHistory.getPasswordChangeType()).isEqualTo(passwordChangeType);
        assertThat(passwordHistory.getRecordVersion()).isEqualTo(recordVersion);
        assertThat(passwordHistory.getOperator()).usingRecursiveComparison().isEqualTo(operator);
    }
}