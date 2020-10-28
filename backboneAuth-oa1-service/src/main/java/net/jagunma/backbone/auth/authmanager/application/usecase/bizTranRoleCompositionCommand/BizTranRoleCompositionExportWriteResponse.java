package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand;

import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;

/**
 * 取引ロール編成エクスポートExcel Weiteサービス Response
 */
public interface BizTranRoleCompositionExportWriteResponse {

    /**
     * 取引ロール編成エクスポートExcelのＳｅｔ
     *
     * @param excelContainer 取引ロール編成エクスポートExcel
     */
    void setExcelContainer(ExcelContainer excelContainer);

}
