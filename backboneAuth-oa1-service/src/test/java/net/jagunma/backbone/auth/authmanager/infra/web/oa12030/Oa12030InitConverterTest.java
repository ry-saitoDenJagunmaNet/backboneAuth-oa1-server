package net.jagunma.backbone.auth.authmanager.infra.web.oa12030;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jagunma.backbone.auth.authmanager.infra.web.oa12030.vo.Oa12030Vo;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa12030InitConverterTest {

    // 実行既定値
    private Long suspendBizTranId = 12345678L;

    /**
     * {@link Oa12030InitConverter#with(Oa12030Vo)}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test0() {

        // 実行値
        suspendBizTranId = null;
        Oa12030Vo vo = new Oa12030Vo();
        vo.setSuspendBizTranId(suspendBizTranId);

        // 期待値
        Long expectedSuspendBizTranId = suspendBizTranId;

        // 実行
        Oa12030InitConverter converter = Oa12030InitConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa12030InitConverter);
        assertThat(converter.getSuspendBizTranId()).isEqualTo(expectedSuspendBizTranId);
    }

    /**
     * {@link Oa12030InitConverter#with(Oa12030Vo)}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test1() {

        // 実行値
        Oa12030Vo vo = new Oa12030Vo();
        vo.setSuspendBizTranId(suspendBizTranId);

        // 期待値
        Long expectedSuspendBizTranId = suspendBizTranId;

        // 実行
        Oa12030InitConverter converter = Oa12030InitConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa12030InitConverter);
        assertThat(converter.getSuspendBizTranId()).isEqualTo(expectedSuspendBizTranId);
    }
}