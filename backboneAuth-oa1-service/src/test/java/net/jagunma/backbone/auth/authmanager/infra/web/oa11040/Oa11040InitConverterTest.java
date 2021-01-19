package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040Vo;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11040InitConverterTest {

    // 実行既定値
    private Long operatorId = 123456L;

    /**
     * {@link Oa11040InitConverter#with(Oa11040Vo vo)}テスト
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
        Oa11040Vo vo = new Oa11040Vo();
        vo.setOperatorId(operatorId);

        // 実行
//        Oa11040InitConverter converter = Oa11040InitConverter.with(vo);

        // 結果検証
//        assertTrue(converter instanceof Oa11040InitConverter);
//        assertThat(converter.getOperatorId()).isEqualTo(operatorId);
    }
}