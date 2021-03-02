package net.jagunma.backbone.auth.authmanager.infra.web.oa11050;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11050InitConverterTest {

    // 実行既定値
    private Long signInOperatorId = 987654L;
    private Long targetOperatorId = 123456L;

    /**
     * {@link Oa11050InitConverter#with(Long signInOperatorId, Long targetOperatorId)}テスト
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
        // 実行
        Oa11050InitConverter converter = Oa11050InitConverter.with(signInOperatorId, targetOperatorId);

        // 結果検証
        assertTrue(converter instanceof Oa11050InitConverter);
        assertThat(converter.getSignInOperatorId()).isEqualTo(signInOperatorId);
        assertThat(converter.getTargetOperatorId()).isEqualTo(targetOperatorId);
    }
}