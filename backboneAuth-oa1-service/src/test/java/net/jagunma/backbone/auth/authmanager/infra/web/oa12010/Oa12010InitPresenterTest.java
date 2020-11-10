package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import static org.assertj.core.api.Assertions.assertThat;

import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010Vo;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa12010InitPresenterTest {

    // 実行既定値
    private String mode = "export";
    private String subSystemCode = SubSystem.販売_畜産.getCode();

    /**
     * {@link Oa12010InitPresenter#bindTo(Oa12010Vo)}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test0() {

        // 実行値
        Oa12010Vo vo = new Oa12010Vo();
        Oa12010InitPresenter presenter = new Oa12010InitPresenter();
        presenter.setMode(mode);
        presenter.setSubSystemCode(subSystemCode);

        // 期待値
        Oa12010Vo expectedVo = new Oa12010Vo();
        expectedVo.setMode(mode);
        expectedVo.setSubSystemCode(subSystemCode);
        expectedVo.setSubSystemList(SelectOptionItemsSource.createFrom(SubSystem.values()).getValue());

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

}