package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AllocateRoleTableVo;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11040ApplyChildConverterTest {

    // 実行既定値
    private SubSystemRole subSystemRole = SubSystemRole.JA管理者;
    private LocalDate validThruStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate validThruEndDate = LocalDate.of(2020, 9, 21);

    /**
     * {@link Oa11040ApplyChildConverter#with(Oa11040AllocateRoleTableVo vo)}テスト
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
        Oa11040AllocateRoleTableVo vo = new Oa11040AllocateRoleTableVo();
        vo.setRoleCode(subSystemRole.getCode());
        vo.setRoleName(subSystemRole.getName());
        vo.setValidThruStartDate(validThruStartDate);
        vo.setValidThruEndDate(validThruEndDate);

        // 実行
        Oa11040ApplyChildConverter converter = Oa11040ApplyChildConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11040ApplyChildConverter);
        assertThat(converter.getSubSystemRole()).isEqualTo(subSystemRole);
        assertThat(converter.getValidThruStartDate()).isEqualTo(validThruStartDate);
        assertThat(converter.getValidThruEndDate()).isEqualTo(validThruEndDate);
    }
}