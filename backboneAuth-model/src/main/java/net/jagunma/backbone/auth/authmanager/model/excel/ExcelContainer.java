package net.jagunma.backbone.auth.authmanager.model.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Excel格納
 */
public class ExcelContainer {

    private final ByteArrayInputStream excelIn;
    private final ByteArrayOutputStream excelOut;

    // コンストラクタ
    ExcelContainer(ByteArrayInputStream excelIn) {
        this.excelIn = excelIn;
        this.excelOut = null;
    }
    ExcelContainer(ByteArrayOutputStream excelOut) {
        this.excelIn = null;
        this.excelOut = excelOut;
    }

    // ファクトリーメソッド
    public static ExcelContainer createFrom(ByteArrayInputStream excelIn) {
        return new ExcelContainer(excelIn);
    }
    public static ExcelContainer createFrom(ByteArrayOutputStream excelOut) {
        return new ExcelContainer(excelOut);
    }

    // Getter
    public ByteArrayInputStream getExcelIn() {
        return excelIn;
    }
    public ByteArrayOutputStream getExcelOut() {
        return excelOut;
    }
}
