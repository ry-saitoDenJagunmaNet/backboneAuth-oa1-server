package net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition;

import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;

/**
 * 取引ロール編成 Book
 */
public class BizTranRoleCompositionBook {

    private ExcelContainer excelContainer;

    // コンストラクタ
    BizTranRoleCompositionBook(ExcelContainer excelContainer) {
        this.excelContainer = excelContainer;
    }

    // ファクトリーメソッド
    public static BizTranRoleCompositionBook createFrom(ExcelContainer excelContainer) {
        return new BizTranRoleCompositionBook(excelContainer);
    }

    // Getter
    public ExcelContainer getExcelContainer() {
        return excelContainer;
    }
}
