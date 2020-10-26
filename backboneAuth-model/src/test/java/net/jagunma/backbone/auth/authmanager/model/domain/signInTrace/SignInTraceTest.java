package net.jagunma.backbone.auth.authmanager.model.domain.signInTrace;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SignInTraceTest {

    /**
     * {@link SignInTrace#createFrom(Long, LocalDateTime, String, String, Short, Short, Integer, Operator)}テスト
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
        Long signInTraceId = 123L;
        LocalDateTime tryDateTime = LocalDateTime.of(2020,10,1,8,30,12);
        String tryIpAddress = "001.001.001.001";
        String operatorCode = "yu001009";
        Short signInCause = SignInCause.サインイン.getCode();
        Short signInResult = SignInResult.成功.getCode();
        Integer recordVersion = 1;

        Long operatorId = 18L;
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
        SignInTrace signInTrace = SignInTrace.createFrom(
            signInTraceId,
            tryDateTime,
            tryIpAddress,
            operatorCode,
            signInCause,
            signInResult,
            recordVersion,
            operator);

        // 結果検証
        assertTrue(signInTrace instanceof SignInTrace);
        assertThat(signInTrace.getSignInTraceId()).isEqualTo(signInTraceId);
        assertThat(signInTrace.getTryDateTime()).isEqualTo(tryDateTime);
        assertThat(signInTrace.getTryIpAddress()).isEqualTo(tryIpAddress);
        assertThat(signInTrace.getOperatorCode()).isEqualTo(operatorCode);
        assertThat(signInTrace.getSignInCause()).isEqualTo(signInCause);
        assertThat(signInTrace.getSignInResult()).isEqualTo(signInResult);
        assertThat(signInTrace.getRecordVersion()).isEqualTo(recordVersion);
        assertThat(signInTrace.getOperator()).usingRecursiveComparison().isEqualTo(operator);
    }

}