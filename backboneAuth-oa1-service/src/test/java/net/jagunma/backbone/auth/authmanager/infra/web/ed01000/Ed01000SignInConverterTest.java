package net.jagunma.backbone.auth.authmanager.infra.web.ed01000;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jagunma.backbone.auth.authmanager.infra.web.ed01000.vo.Ed01000Vo;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Ed01000SignInConverterTest {

    /**
     * {@link Ed01000SignInConverter#with(Ed01000Vo, String)}テスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Converterへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test0() {

        // 実行値
        String operatorCode = "yu001009";
        String password = "password12345";
        Short mode = SignInCause.サインイン.getCode();
        String clientIpaddress = "001.002.003.004";
        Ed01000Vo vo = new Ed01000Vo();
        vo.setOperatorCode(operatorCode);
        vo.setPassword(password);
        vo.setMode(mode);

        // 実行
        Ed01000SignInConverter converter = Ed01000SignInConverter.with(vo, clientIpaddress);

        // 結果検証
        assertTrue(converter instanceof Ed01000SignInConverter);
        assertThat(converter.getOperatorCode()).isEqualTo(operatorCode);
        assertThat(converter.getPassword()).isEqualTo(password);
        assertThat(converter.getMode()).isEqualTo(mode);
        assertThat(converter.getClientIpaddress()).isEqualTo(clientIpaddress);
    }
}