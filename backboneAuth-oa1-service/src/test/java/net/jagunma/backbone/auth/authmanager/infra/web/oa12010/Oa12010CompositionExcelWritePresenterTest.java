package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010Vo;
import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa12010CompositionExcelWritePresenterTest {

    // 実行既定値
    // Excel格納データ作成
    private ExcelContainer createExcelContainer() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        os.write( 0x00 );
        os.write( 0x01 );
        os.write( 0x02 );
        os.write( 0x03 );
        return ExcelContainer.createFrom(os);
    }

    /**
     * {@link Oa12010CompositionExcelWritePresenter#bindTo(Oa12010Vo)}のテスト
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
        Oa12010CompositionExcelWritePresenter presenter = new Oa12010CompositionExcelWritePresenter();
        presenter.setExcelContainer(createExcelContainer());

        // 期待値
        Oa12010Vo expectedVo = new Oa12010Vo();
        expectedVo.setExportExcelBook(createExcelContainer().getExcelOut().toByteArray());

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }
}