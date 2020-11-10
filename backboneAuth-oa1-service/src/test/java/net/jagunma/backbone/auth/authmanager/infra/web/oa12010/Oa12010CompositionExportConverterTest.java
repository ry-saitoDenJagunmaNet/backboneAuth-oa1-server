package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010Vo;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa12010CompositionExportConverterTest {

    // 実行既定値
    private String subSystemCode = SubSystem.販売_畜産.getCode();

    /**
     * {@link Oa12010CompositionExportConverter#with(Oa12010Vo)}テスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Converterへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test1() {

        // 実行値
        Oa12010Vo vo = new Oa12010Vo();
        vo.setSubSystemCode(subSystemCode);

        // 期待値
        String expectedSubSystemCode = subSystemCode;

        // 実行
        Oa12010CompositionExportConverter converter = Oa12010CompositionExportConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa12010CompositionExportConverter);
        assertThat(converter.getSubSystemCode()).isEqualTo(expectedSubSystemCode);
    }
}