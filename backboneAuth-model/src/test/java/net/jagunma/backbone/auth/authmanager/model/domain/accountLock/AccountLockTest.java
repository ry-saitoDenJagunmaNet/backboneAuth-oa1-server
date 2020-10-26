package net.jagunma.backbone.auth.authmanager.model.domain.accountLock;

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

class AccountLockTest {

    /**
     * {@link AccountLock#createFrom(Long, Long, LocalDateTime, Short, Integer, Operator)}テスト
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
        Long accountLockId = 123L;
        Long operatorId = 18L;
        LocalDateTime occurredDateTime = LocalDateTime.of(2020, 10, 1, 8,30,12);
        Short lockStatus = (short) 2;
        Integer recordVersion = 78;

        String operatorCode = "yu001009";
        String operatorName = "ｙｕ００１００９";
        String mailAddress = "001.001.001.001";
        LocalDate expirationStartDate = LocalDate.of(2020, 1, 1);
        LocalDate expirationEndDate = LocalDate.of(9999, 12, 31);
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
            expirationStartDate,
            expirationEndDate,
            isDeviceAuth,
            jaId,
            jaCode,
            branchId,
            branchCode,
            availableStatus,
            operatorRecordVersion,
            branchAtMoment);

        // 実行
        AccountLock accountLock = AccountLock.createFrom(
            accountLockId,
            operatorId,
            occurredDateTime,
            lockStatus,
            recordVersion,
            operator);

        // 結果検証
        assertTrue(accountLock instanceof AccountLock);
        assertThat(accountLock.getAccountLockId()).isEqualTo(accountLockId);
        assertThat(accountLock.getOperatorId()).isEqualTo(operatorId);
        assertThat(accountLock.getOccurredDateTime()).isEqualTo(occurredDateTime);
        assertThat(accountLock.getLockStatus()).isEqualTo(lockStatus);
        assertThat(accountLock.getRecordVersion()).isEqualTo(recordVersion);
        assertThat(accountLock.getOperator()).usingRecursiveComparison().isEqualTo(operator);
    }
}