package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010Vo;
import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa12010CompositionExcelReadConverterTest {

    // 実行既定値
    private String subSystemCode = SubSystem.販売_畜産.getCode();
    // Excel格納データ作成
    private ByteArrayInputStream createByteArrayInputStream() {
        return new ByteArrayInputStream("0123".getBytes());
    }

    /**
     * {@link Oa12010CompositionExcelReadConverter#with(Oa12010Vo,ByteArrayInputStream)}テスト
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
        Oa12010Vo vo = new Oa12010Vo();
        vo.setSubSystemCode(subSystemCode);
        ByteArrayInputStream is = createByteArrayInputStream();

        // 期待値
        String expectedSubSystemCode = subSystemCode;
        ExcelContainer expectedExcelContainer = ExcelContainer.createFrom(createByteArrayInputStream());

        // 実行
        Oa12010CompositionExcelReadConverter converter = Oa12010CompositionExcelReadConverter.with(vo, is);

        // 結果検証
        assertTrue(converter instanceof Oa12010CompositionExcelReadConverter);
        assertThat(converter.getSubSystemCode()).isEqualTo(expectedSubSystemCode);
        assertThat(converter.getBizTranRoleCompositionBook()).usingRecursiveComparison().isEqualTo(expectedExcelContainer);
    }
}