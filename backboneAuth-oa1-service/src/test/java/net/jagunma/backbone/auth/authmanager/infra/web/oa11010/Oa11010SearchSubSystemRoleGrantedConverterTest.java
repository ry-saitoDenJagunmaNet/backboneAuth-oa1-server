package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11010SearchSubSystemRoleGrantedConverterTest {

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
        Boolean subSystemRoleSelected = true;
        String subSystemRoleCode = SubSystemRole.JA管理者.getCode();
        String subSystemRoleName = SubSystemRole.JA管理者.getDisplayName();
        Integer validThruSelect = 2;
        LocalDate validThruStatusDate = LocalDate.of(2020, 10, 1);
        LocalDate validThruStartDateFrom = LocalDate.of(2020, 10, 2);
        LocalDate validThruStartDateTo = LocalDate.of(2020, 10, 3);
        LocalDate validThruEndDateFrom = LocalDate.of(2020, 10, 4);
        LocalDate validThruEndDateTo = LocalDate.of(2020, 10, 5);

        // 実行
        Oa11010SearchSubSystemRoleConverter converter = Oa11010SearchSubSystemRoleConverter.with(
            subSystemRoleSelected,
            subSystemRoleCode,
            subSystemRoleName,
            validThruSelect,
            validThruStatusDate,
            validThruStartDateFrom,
            validThruStartDateTo,
            validThruEndDateFrom,
            validThruEndDateTo
            );

        // 結果検証
        assertTrue(converter instanceof Oa11010SearchSubSystemRoleConverter);
        assertThat(converter.getSubSystemRoleSelected()).isEqualTo(subSystemRoleSelected);
        assertThat(converter.getSubSystemRoleCode()).isEqualTo(subSystemRoleCode);
        assertThat(converter.getSubSystemRoleName()).isEqualTo(subSystemRoleName);
        assertThat(converter.getValidThruSelect()).isEqualTo(validThruSelect);
        assertThat(converter.getValidThruStatusDate()).isEqualTo(validThruStatusDate);
        assertThat(converter.getValidThruStartDateFrom()).isEqualTo(validThruStartDateFrom);
        assertThat(converter.getValidThruStartDateTo()).isEqualTo(validThruStartDateTo);
        assertThat(converter.getValidThruEndDateFrom()).isEqualTo(validThruEndDateFrom);
        assertThat(converter.getValidThruEndDateTo()).isEqualTo(validThruEndDateTo);
    }
}