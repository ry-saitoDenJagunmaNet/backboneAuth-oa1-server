package net.jagunma.backbone.auth.authmanager.model.excel;

import java.io.ByteArrayOutputStream;

public class ExcelContainer {

    private final ByteArrayOutputStream excelOut;

    // コンストラクタ
    ExcelContainer(ByteArrayOutputStream excelOut) {
        this.excelOut = excelOut;
    }

    // ファクトリーメソッド
    public static ExcelContainer createFrom(ByteArrayOutputStream excelOut) {
        return new ExcelContainer(excelOut);
    }

    // Getter
    public ByteArrayOutputStream getExcelOut() {
        return excelOut;
    }
}
