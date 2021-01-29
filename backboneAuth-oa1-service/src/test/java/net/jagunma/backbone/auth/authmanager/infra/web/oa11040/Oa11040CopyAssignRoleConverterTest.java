package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11040CopyAssignRoleConverterTest {

    // 実行既定値
    private SubSystemRole subSystemRole = SubSystemRole.JA管理者;
    private LocalDate validThruStartDate = LocalDate.of(2020, 9, 10);
    private LocalDate validThruEndDate = LocalDate.of(2020, 9, 20);
    private Boolean isModifiable = true;

    /**
     * {@link Oa11040CopyAssignRoleConverter#with(Oa11040AssignRoleTableVo vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Converterへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test() {
        // 実行値
        Oa11040AssignRoleTableVo vo = new Oa11040AssignRoleTableVo();
        vo.setRoleCode(subSystemRole.getCode());
        vo.setRoleName(subSystemRole.getDisplayName());
        vo.setValidThruStartDate(validThruStartDate);
        vo.setValidThruEndDate(validThruEndDate);
        vo.setIsModifiable(isModifiable);

        // 実行
        Oa11040CopyAssignRoleConverter converter = Oa11040CopyAssignRoleConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11040CopyAssignRoleConverter);
        assertThat(converter.getSubSystemRole()).isEqualTo(subSystemRole);
        assertThat(converter.getValidThruStartDate()).isEqualTo(validThruStartDate);
        assertThat(converter.getValidThruEndDate()).isEqualTo(validThruEndDate);
        assertThat(converter.getIsModifiable()).isEqualTo(isModifiable);
    }
}