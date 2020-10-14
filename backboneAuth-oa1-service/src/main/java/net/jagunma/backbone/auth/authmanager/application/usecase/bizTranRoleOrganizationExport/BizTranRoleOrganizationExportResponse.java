package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleOrganizationExport;

import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;

/**
 * 取引ロール編成エクスポートサービス Response
 */
public interface BizTranRoleOrganizationExportResponse {

    /**
     * 取引ロール編成エクスポートExcelのＳｅｔ
     *
     * @param excelContainer 取引ロール編成エクスポートExcel
     */
    void setExcelContainer(ExcelContainer excelContainer);
}
