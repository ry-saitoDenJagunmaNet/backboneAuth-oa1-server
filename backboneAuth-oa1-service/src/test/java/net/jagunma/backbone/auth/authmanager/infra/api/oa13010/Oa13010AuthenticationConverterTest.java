package net.jagunma.backbone.auth.authmanager.infra.api.oa13010;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa13010AuthenticationConverterTest {

    /**
     * {@link Oa13010AuthenticationConverter#of(String, String)}テスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Converterへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void of_test0() {

        // 実行値
        String operatorCode = "yu001009";
        String password = "PassWord1234";

        // 実行
        Oa13010AuthenticationConverter converter = Oa13010AuthenticationConverter.of(operatorCode, password);

        // 結果検証
        assertTrue(converter instanceof Oa13010AuthenticationConverter);
        assertThat(converter.getOperatorCode()).isEqualTo(operatorCode);
        assertThat(converter.getPassword()).isEqualTo(password);
    }
}