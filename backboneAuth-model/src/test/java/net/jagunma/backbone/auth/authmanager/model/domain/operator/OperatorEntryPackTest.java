package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OperatorEntryPackTest {

    /**
     * {@link OperatorEntryPack#createFrom(
     *      String operatorCode,
     *      String operatorName,
     *      String mailAddress,
     *      LocalDate expirationStartDate,
     *      LocalDate expirationEndDate,
     *      Long jaId,
     *      String jaCode,
     *      Long branchId,
     *      String branchCode,
     *      String changeCause,
     *      String password,
     *      String confirmPassword)}テスト
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
        String operatorCode = "yu123456";
        String operatorName = "オペレーター名";
        String mailAddress = "test@den.jagunma.net";
        LocalDate expirationStartDate = LocalDate.of(2020, 9, 1);
        LocalDate expirationEndDate = LocalDate.of(2020, 9, 30);
        Long jaId = 6L;
        String jaCode = "006";
        Long branchId = 1L;
        String branchCode = "001";
        String changeCause = "新職員の入組による登録";
        String password = "PaSsWoRd";
        String confirmPassword = "PaSsWoRd";

        // 実行
        OperatorEntryPack operatorEntryPack = OperatorEntryPack.createFrom(
            operatorCode,
            operatorName,
            mailAddress,
            expirationStartDate,
            expirationEndDate,
            jaId,
            jaCode,
            branchId,
            branchCode,
            changeCause,
            password,
            confirmPassword);

        // 結果検証
        assertTrue(operatorEntryPack instanceof OperatorEntryPack);
        assertThat(operatorEntryPack.getOperatorCode()).isEqualTo(operatorCode);
        assertThat(operatorEntryPack.getOperatorName()).isEqualTo(operatorName);
        assertThat(operatorEntryPack.getMailAddress()).isEqualTo(mailAddress);
        assertThat(operatorEntryPack.getExpirationStartDate()).isEqualTo(expirationStartDate);
        assertThat(operatorEntryPack.getExpirationEndDate()).isEqualTo(expirationEndDate);
        assertThat(operatorEntryPack.getJaId()).isEqualTo(jaId);
        assertThat(operatorEntryPack.getJaCode()).isEqualTo(jaCode);
        assertThat(operatorEntryPack.getBranchId()).isEqualTo(branchId);
        assertThat(operatorEntryPack.getBranchCode()).isEqualTo(branchCode);
        assertThat(operatorEntryPack.getChangeCause()).isEqualTo(changeCause);
        assertThat(operatorEntryPack.getPassword()).isEqualTo(password);
        assertThat(operatorEntryPack.getConfirmPassword()).isEqualTo(confirmPassword);
    }
}