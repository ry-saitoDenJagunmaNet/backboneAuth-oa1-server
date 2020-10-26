package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11010SearchBizTranRoleConverterTest {

    /**
     * {@link Oa11010SearchBizTranRoleConverter}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Converterへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test0() {

        // 実行既定値
        Short bizTranRoleSelected = 0;
        long bizTranRoleId = 1L;
        String bizTranRoleCode = "KBAG01";
        String bizTranRoleName = "（購買）購買業務基本";
        String subSystemCode = SubSystem.購買.getCode();
        Integer expirationSelect = 2;
        LocalDate expirationStatusDate = LocalDate.of(2020, 10, 1);
        LocalDate expirationStartDateFrom = LocalDate.of(2020, 10, 2);
        LocalDate expirationStartDateTo = LocalDate.of(2020, 10, 3);
        LocalDate expirationEndDateFrom = LocalDate.of(2020, 10, 4);
        LocalDate expirationEndDateTo = LocalDate.of(2020, 10, 5);

        // 実行
        Oa11010SearchBizTranRoleConverter converter = Oa11010SearchBizTranRoleConverter.with(
            bizTranRoleSelected,
            bizTranRoleId,
            bizTranRoleCode,
            bizTranRoleName,
            subSystemCode,
            expirationSelect,
            expirationStatusDate,
            expirationStartDateFrom,
            expirationStartDateTo,
            expirationEndDateFrom,
            expirationEndDateTo
            );

        // 結果検証
        assertTrue(converter instanceof Oa11010SearchBizTranRoleConverter);
        assertThat(converter.getBizTranRoleSelected()).isEqualTo(bizTranRoleSelected);
        assertThat(converter.getBizTranRoleId()).isEqualTo(bizTranRoleId);
        assertThat(converter.getBizTranRoleCode()).isEqualTo(bizTranRoleCode);
        assertThat(converter.getBizTranRoleName()).isEqualTo(bizTranRoleName);
        assertThat(converter.getSubSystemCode()).isEqualTo(subSystemCode);
        assertThat(converter.getExpirationSelect()).isEqualTo(expirationSelect);
        assertThat(converter.getExpirationStatusDate()).isEqualTo(expirationStatusDate);
        assertThat(converter.getExpirationStartDateFrom()).isEqualTo(expirationStartDateFrom);
        assertThat(converter.getExpirationStartDateTo()).isEqualTo(expirationStartDateTo);
        assertThat(converter.getExpirationEndDateFrom()).isEqualTo(expirationEndDateFrom);
        assertThat(converter.getExpirationEndDateTo()).isEqualTo(expirationEndDateTo);
    }
}