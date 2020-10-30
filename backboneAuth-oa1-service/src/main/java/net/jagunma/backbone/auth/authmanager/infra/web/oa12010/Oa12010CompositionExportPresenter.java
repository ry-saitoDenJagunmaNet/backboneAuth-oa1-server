package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionReference.BizTranRoleCompositionExportResponse;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;

/**
 * OA12010 取引ロール編成インポート＆エクスポート エクスポート検索 Response Presenter
 */
public class Oa12010CompositionExportPresenter implements BizTranRoleCompositionExportResponse {

    private BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet;
    private BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet;

    /**
     * 取引ロール－取引グループ編成群のＳｅｔ
     *
     * @param bizTranRole_BizTranGrpsSheet 取引ロール－取引グループ編成群
     */
    public void setBizTranRole_BizTranGrpsSheet(BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet) {
        this.bizTranRole_BizTranGrpsSheet = bizTranRole_BizTranGrpsSheet;
    }

    /**
     * 取引グループ－取引編成群のＳｅｔ
     *
     * @param bizTranGrp_BizTransSheet 取引グループ－取引編成群
     */
    public void setBizTranGrp_BizTransSheet(BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet) {
        this.bizTranGrp_BizTransSheet = bizTranGrp_BizTransSheet;
    }

    /**
     * converterに変換
     *
     * @return 取引ロール編成エクスポートExcel Weiteサービス Request Converter
     */
    public Oa12010CompositionExcelWriteConverter ConverterTo() {
        return Oa12010CompositionExcelWriteConverter
            .with(bizTranRole_BizTranGrpsSheet, bizTranGrp_BizTransSheet);
    }
}
