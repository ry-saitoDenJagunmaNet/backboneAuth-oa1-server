package net.jagunma.backbone.auth.authmanager.infra.api.oa31020;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa31020ConverterTest {

    /**
     * {@link Oa31020Converter#with(Long)}テスト
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
        Long operatorId = 123456789L;

        // 実行
        Oa31020Converter converter = Oa31020Converter.with(operatorId);

        // 結果検証
        assertTrue(converter instanceof Oa31020Converter);
        assertThat(converter.getOperatorId()).isEqualTo(operatorId);
    }
}