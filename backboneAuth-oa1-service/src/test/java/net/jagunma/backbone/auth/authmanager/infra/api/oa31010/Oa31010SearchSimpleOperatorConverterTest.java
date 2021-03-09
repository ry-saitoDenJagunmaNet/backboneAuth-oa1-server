package net.jagunma.backbone.auth.authmanager.infra.api.oa31010;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa31010SearchSimpleOperatorConverterTest {

    /**
     * {@link Oa31010SearchSimpleOperatorConverter#with(String)}テスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Converterへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test0() {

        // 実行値
        String operatorCode = "yu001009";

        // 実行
        Oa31010SearchSimpleOperatorConverter converter = Oa31010SearchSimpleOperatorConverter.with(operatorCode);

        // 結果検証
        assertTrue(converter instanceof Oa31010SearchSimpleOperatorConverter);
        assertThat(converter.getOperatorCode()).isEqualTo(operatorCode);
    }
}