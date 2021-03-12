package net.jagunma.backbone.auth.authmanager.infra.web.ed01010;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jagunma.backbone.auth.authmanager.infra.web.ed01010.vo.Ed01010Vo;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Ed01010InitConverterTest {

    // 実行既定値
    private Long operatorId = 123456L;

    /**
     * {@link Ed01010InitConverter#with(Long operatorId)}テスト
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
        vo.setOperatorId(operatorId);

        // 実行
        Ed01010InitConverter converter = Ed01010InitConverter.with(operatorId);

        // 結果検証
        assertTrue(converter instanceof Ed01010InitConverter);
        assertThat(converter.getOperatorId()).isEqualTo(operatorId);
    }
}