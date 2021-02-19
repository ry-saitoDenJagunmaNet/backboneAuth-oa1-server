package net.jagunma.backbone.auth.authmanager.infra.api.oa31010;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa31010AuthenticationConverterTest {

    /**
     * {@link Oa31010AuthenticationConverter#with(String, String)}テスト
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
        Oa31010AuthenticationConverter converter = Oa31010AuthenticationConverter
            .with(operatorCode, password);

        // 結果検証
        assertTrue(converter instanceof Oa31010AuthenticationConverter);
        assertThat(converter.getOperatorCode()).isEqualTo(operatorCode);
        assertThat(converter.getPassword()).isEqualTo(password);
    }
}