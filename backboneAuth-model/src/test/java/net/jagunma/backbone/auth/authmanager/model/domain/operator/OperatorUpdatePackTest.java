package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OperatorUpdatePackTest {

    /**
     * {@link OperatorUpdatePack#createFrom(
     *      Long operatorId,
     *      String operatorName,
     *      String mailAddress,
     *      LocalDate expirationStartDate,
     *      LocalDate expirationEndDate,
     *      Boolean isDeviceAuth,
     *      Long branchId,
     *      String branchCode,
     *      AvailableStatus availableStatus,
     *      Integer recordVersion,
     *      String changeCause)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_test() {
        // 実行値 ＆ 期待値
        Long operatorId = 123456L;
        String operatorName = "オペレーター名";
        String mailAddress = "test@den.jagunma.net";
        LocalDate expirationStartDate = LocalDate.of(2020, 9, 1);
        LocalDate expirationEndDate = LocalDate.of(2020, 9, 30);
        Boolean isDeviceAuth = true;
        Long branchId = 1L;
        String branchCode = "001";
        AvailableStatus availableStatus = AvailableStatus.利用可能;
        Integer recordVersion = 1;
        String changeCause = "認証機器使用開始";

        // 実行
        OperatorUpdatePack operatorUpdatePack = OperatorUpdatePack.createFrom(
            operatorId,
            operatorName,
            mailAddress,
            expirationStartDate,
            expirationEndDate,
            isDeviceAuth,
            branchId,
            branchCode,
            availableStatus,
            recordVersion,
            changeCause);

        // 結果検証
        assertTrue(operatorUpdatePack instanceof OperatorUpdatePack);
        assertThat(operatorUpdatePack.getOperatorId()).isEqualTo(operatorId);
        assertThat(operatorUpdatePack.getOperatorName()).isEqualTo(operatorName);
        assertThat(operatorUpdatePack.getMailAddress()).isEqualTo(mailAddress);
        assertThat(operatorUpdatePack.getExpirationStartDate()).isEqualTo(expirationStartDate);
        assertThat(operatorUpdatePack.getExpirationEndDate()).isEqualTo(expirationEndDate);
        assertThat(operatorUpdatePack.getIsDeviceAuth()).isEqualTo(isDeviceAuth);
        assertThat(operatorUpdatePack.getBranchId()).isEqualTo(branchId);
        assertThat(operatorUpdatePack.getBranchCode()).isEqualTo(branchCode);
        assertThat(operatorUpdatePack.getAvailableStatus()).isEqualTo(availableStatus);
        assertThat(operatorUpdatePack.getRecordVersion()).isEqualTo(recordVersion);
        assertThat(operatorUpdatePack.getChangeCause()).isEqualTo(changeCause);
    }
}