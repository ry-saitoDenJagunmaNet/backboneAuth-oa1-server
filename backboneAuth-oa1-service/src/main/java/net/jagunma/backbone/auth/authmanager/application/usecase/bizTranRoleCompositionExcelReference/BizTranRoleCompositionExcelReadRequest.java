package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExcelReference;

import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;

/**
 * 取引ロール編成インポートExcel Readサービス Request
 */
public interface BizTranRoleCompositionExcelReadRequest {

    /**
     * サブシステムコードのＧｅｔ
     *
     * @return サブシステムコード
     */
    String getSubSystemCode();
    /**
     * 取引ロール編成 BookのＧｅｔ
     *
     * @return 取引ロール編成 Book
     */
    ExcelContainer getBizTranRoleCompositionBook();
}
