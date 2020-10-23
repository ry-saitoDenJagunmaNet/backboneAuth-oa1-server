package net.jagunma.backbone.auth.authmanager.model.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ExcelContainer {

    private ByteArrayInputStream excelIn;
    private ByteArrayOutputStream excelOut;

    // コンストラクタ
    ExcelContainer(ByteArrayInputStream excelIn) {
        this.excelIn = excelIn;
    }
    ExcelContainer(ByteArrayOutputStream excelOut) {
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
