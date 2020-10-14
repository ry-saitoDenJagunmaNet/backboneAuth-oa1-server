package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import java.io.ByteArrayOutputStream;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleOrganizationExport.BizTranRoleOrganizationExportResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010Vo;
import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;

/**
 * OA12010 取引ロール編成インポート＆エクスポート エクスポート Response Presenter
 */
public class Oa12010OrganizationExportPresenter implements BizTranRoleOrganizationExportResponse {

    private ExcelContainer excelContainer;

    /**
     * 取引ロール編成エクスポートExcelのＳｅｔ
     *
     * @param excelContainer 取引ロール編成エクスポートExcel
     */
    public void setExcelContainer(ExcelContainer excelContainer) {
        this.excelContainer = excelContainer;
    }

    /**
     * responseに変換
     *
     * @param vo ViewObject
     */
    public void bindTo(Oa12010Vo vo) {
        vo.setExportExcelBook(excelContainer.getExcelOut().toByteArray());
    }
}
