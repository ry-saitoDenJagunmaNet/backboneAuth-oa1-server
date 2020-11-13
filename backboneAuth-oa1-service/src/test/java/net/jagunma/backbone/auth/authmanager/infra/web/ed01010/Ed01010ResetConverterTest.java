package net.jagunma.backbone.auth.authmanager.infra.web.ed01010;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jagunma.backbone.auth.authmanager.infra.web.ed01010.vo.Ed01010Vo;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Ed01010ResetConverterTest {

    // 実行既定値
    private String mode = "Reset";
    private String ja = "006 JA前橋市";
    private Long operatorId = 123456L;
    private String operator = "yu123456 オペレーター名";
    private String password = "PaSsWoRd";
    private String confirmPassword = "PaSsWoRd";

    /**
     * {@link Ed01010ResetConverter#with(Ed01010Vo vo)}テスト
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
        Ed01010Vo vo = new Ed01010Vo();
        vo.setMode(mode);
        vo.setJa(ja);
        vo.setOperatorId(operatorId);
        vo.setOperator(operator);
        vo.setOldPassword(null);
        vo.setNewPassword(password);
        vo.setConfirmPassword(confirmPassword);

        // 実行
        Ed01010ResetConverter converter = Ed01010ResetConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Ed01010ResetConverter);
        assertThat(converter.getOperatorId()).isEqualTo(operatorId);
        assertThat(converter.getPassword()).isEqualTo(password);
        assertThat(converter.getConfirmPassword()).isEqualTo(confirmPassword);
    }
}