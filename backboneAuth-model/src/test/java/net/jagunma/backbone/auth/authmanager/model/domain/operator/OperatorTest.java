package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OperatorTest {

    /**
     * {@link Operator#createFrom(Long, String, String, String, LocalDate, LocalDate, Boolean, Long, String, Long, String, AvailableStatus, Integer, BranchAtMoment)}テスト
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
        Long operatorId = 18L;
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
        Integer recordVersion = 1;
        //TODO: BranchAtMomentをnullにしたが、値を入れたテストをする必要がある
        BranchAtMoment branchAtMoment = null;

        // 実行
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
            recordVersion,
            branchAtMoment);

        // 結果検証
        assertTrue(operator instanceof Operator);
        assertThat(operator.getOperatorId()).isEqualTo(operatorId);
        assertThat(operator.getOperatorCode()).isEqualTo(operatorCode);
        assertThat(operator.getOperatorName()).isEqualTo(operatorName);
        assertThat(operator.getMailAddress()).isEqualTo(mailAddress);
        assertThat(operator.getExpirationStartDate()).isEqualTo(expirationStartDate);
        assertThat(operator.getExpirationEndDate()).isEqualTo(expirationEndDate);
        assertThat(operator.getIsDeviceAuth()).isEqualTo(isDeviceAuth);
        assertThat(operator.getJaId()).isEqualTo(jaId);
        assertThat(operator.getJaCode()).isEqualTo(jaCode);
        assertThat(operator.getBranchId()).isEqualTo(branchId);
        assertThat(operator.getBranchCode()).isEqualTo(branchCode);
        assertThat(operator.getAvailableStatus()).isEqualTo(availableStatus);
        assertThat(operator.getRecordVersion()).isEqualTo(recordVersion);
        assertThat(operator.getBranchAtMoment()).isEqualTo(branchAtMoment);
    }
}