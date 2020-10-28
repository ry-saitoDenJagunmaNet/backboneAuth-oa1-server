package net.jagunma.backbone.auth.authmanager.model.excel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ExcelContainerTest {

    /**
     * {@link ExcelContainer#createFrom(ByteArrayInputStream)}テスト
     *  ●パターン
     *    正常（InputStream）
     *
     *  ●検証事項
     *  ・modelへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_test0() {

        // 実行値 ＆ 期待値
        ByteArrayInputStream excelIn = new ByteArrayInputStream("abcd".getBytes());;

        // 実行
        ExcelContainer excelContainer = ExcelContainer.createFrom(excelIn);

        // 結果検証
        assertTrue(excelContainer instanceof ExcelContainer);
        assertThat(excelContainer.getExcelIn()).isEqualTo(excelIn);
    }

    /**
     * {@link ExcelContainer#createFrom(ByteArrayOutputStream)}テスト
     *  ●パターン
     *    正常（OutputStream）
     *
     *  ●検証事項
     *  ・modelへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_test1() {

        // 実行値 ＆ 期待値
        ByteArrayOutputStream excelOut = new ByteArrayOutputStream();
        excelOut.write( 0x00 );
        excelOut.write( 0x01 );
        excelOut.write( 0x02 );
        excelOut.write( 0x03 );

        // 実行
        ExcelContainer excelContainer = ExcelContainer.createFrom(excelOut);

        // 結果検証
        assertTrue(excelContainer instanceof ExcelContainer);
        assertThat(excelContainer.getExcelOut()).isEqualTo(excelOut);
    }
}