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
        Boolean bizTranRoleSelected = false;
        long bizTranRoleId = 1L;
        String bizTranRoleCode = "KBAG01";
        String bizTranRoleName = "（購買）購買業務基本";
        String subSystemCode = SubSystem.購買.getCode();
        Integer validThruSelect = 2;
        LocalDate validThruStatusDate = LocalDate.of(2020, 10, 1);
        LocalDate validThruStartDateFrom = LocalDate.of(2020, 10, 2);
        LocalDate validThruStartDateTo = LocalDate.of(2020, 10, 3);
        LocalDate validThruEndDateFrom = LocalDate.of(2020, 10, 4);
        LocalDate validThruEndDateTo = LocalDate.of(2020, 10, 5);

        // 実行
        Oa11010SearchBizTranRoleConverter converter = Oa11010SearchBizTranRoleConverter.with(
            bizTranRoleSelected,
            bizTranRoleId,
            bizTranRoleCode,
            bizTranRoleName,
            subSystemCode,
            validThruSelect,
            validThruStatusDate,
            validThruStartDateFrom,
            validThruStartDateTo,
            validThruEndDateFrom,
            validThruEndDateTo
            );

        // 結果検証
        assertTrue(converter instanceof Oa11010SearchBizTranRoleConverter);
        assertThat(converter.getBizTranRoleSelected()).isEqualTo(bizTranRoleSelected);
        assertThat(converter.getBizTranRoleId()).isEqualTo(bizTranRoleId);
        assertThat(converter.getBizTranRoleCode()).isEqualTo(bizTranRoleCode);
        assertThat(converter.getBizTranRoleName()).isEqualTo(bizTranRoleName);
        assertThat(converter.getSubSystemCode()).isEqualTo(subSystemCode);
        assertThat(converter.getValidThruSelect()).isEqualTo(validThruSelect);
        assertThat(converter.getValidThruStatusDate()).isEqualTo(validThruStatusDate);
        assertThat(converter.getValidThruStartDateFrom()).isEqualTo(validThruStartDateFrom);
        assertThat(converter.getValidThruStartDateTo()).isEqualTo(validThruStartDateTo);
        assertThat(converter.getValidThruEndDateFrom()).isEqualTo(validThruEndDateFrom);
        assertThat(converter.getValidThruEndDateTo()).isEqualTo(validThruEndDateTo);
    }
}