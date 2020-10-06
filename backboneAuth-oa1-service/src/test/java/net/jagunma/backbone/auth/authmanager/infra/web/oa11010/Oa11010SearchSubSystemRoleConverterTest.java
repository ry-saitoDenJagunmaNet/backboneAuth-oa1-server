package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11010SearchSubSystemRoleConverterTest {

    /**
     * {@link Oa11010SearchSubSystemRoleConverter}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Converterへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test() {

        // 実行既定値
        Short subSystemRoleSelected = 1;
        String subSystemRoleCode = "JaAdmin";
        String subSystemRoleName = "JA管理者";
        Integer expirationSelect = 2;
        LocalDate expirationStatusDate = LocalDate.of(2020, 10, 1);
        LocalDate expirationStartDateFrom = LocalDate.of(2020, 10, 2);
        LocalDate expirationStartDateTo = LocalDate.of(2020, 10, 3);
        LocalDate expirationEndDateFrom = LocalDate.of(2020, 10, 4);
        LocalDate expirationEndDateTo = LocalDate.of(2020, 10, 5);

        // 実行
        Oa11010SearchSubSystemRoleConverter converter = Oa11010SearchSubSystemRoleConverter.with(
            subSystemRoleSelected,
            subSystemRoleCode,
            subSystemRoleName,
            expirationSelect,
            expirationStatusDate,
            expirationStartDateFrom,
            expirationStartDateTo,
            expirationEndDateFrom,
            expirationEndDateTo
            );

        // 結果検証
        assertTrue(converter instanceof Oa11010SearchSubSystemRoleConverter);
        assertThat(converter.getSubSystemRoleSelected()).isEqualTo(subSystemRoleSelected);
        assertThat(converter.getSubSystemRoleCode()).isEqualTo(subSystemRoleCode);
        assertThat(converter.getSubSystemRoleName()).isEqualTo(subSystemRoleName);
        assertThat(converter.getExpirationSelect()).isEqualTo(expirationSelect);
        assertThat(converter.getExpirationStatusDate()).isEqualTo(expirationStatusDate);
        assertThat(converter.getExpirationStartDateFrom()).isEqualTo(expirationStartDateFrom);
        assertThat(converter.getExpirationStartDateTo()).isEqualTo(expirationStartDateTo);
        assertThat(converter.getExpirationEndDateFrom()).isEqualTo(expirationEndDateFrom);
        assertThat(converter.getExpirationEndDateTo()).isEqualTo(expirationEndDateTo);
    }
}