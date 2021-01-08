package net.jagunma.backbone.auth.authmanager.infra.web.oa11030;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo.Oa11030Vo;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11030InitConverterTest {

    // 実行既定値
    private Long operatorId = 123456L;

    /**
     * {@link Oa11030InitConverter#with(Oa11030Vo vo)}テスト
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
        Oa11030Vo vo = new Oa11030Vo();
        vo.setOperatorId(operatorId);

        // 実行
        Oa11030InitConverter converter = Oa11030InitConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11030InitConverter);
        assertThat(converter.getOperatorId()).isEqualTo(operatorId);
    }
}