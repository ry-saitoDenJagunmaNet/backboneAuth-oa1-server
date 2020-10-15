package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExport;

import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;

/**
 * 取引ロール編成エクスポートExcel Weiteサービス Request
 */
public interface BizTranRoleCompositionExportWriteRequest {

    /**
     * 取引ロール＋取引グループ群のＧｅｔ
     *
     * @return 取引ロール＋取引グループ群
     */
    BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet();
    /**
     * 取引グループ＋取引群のＧｅｔ
     *
     * @return 取引グループ＋取引群
     */
    BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet();
}
