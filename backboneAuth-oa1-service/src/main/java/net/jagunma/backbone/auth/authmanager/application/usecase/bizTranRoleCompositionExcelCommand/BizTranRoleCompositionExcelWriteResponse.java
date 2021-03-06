package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExcelCommand;

import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;

/**
 * 取引ロール編成エクスポートExcel Writeサービス Response
 */
public interface BizTranRoleCompositionExcelWriteResponse {

    /**
     * 取引ロール編成エクスポートExcelのＳｅｔ
     *
     * @param excelContainer 取引ロール編成エクスポートExcel
     */
    void setExcelContainer(ExcelContainer excelContainer);

}
