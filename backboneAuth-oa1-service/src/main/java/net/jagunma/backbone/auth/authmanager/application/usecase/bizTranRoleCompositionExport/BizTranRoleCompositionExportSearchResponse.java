package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExport;

import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;

/**
 * 取引ロール編成エクスポート検索サービス Response
 */
public interface BizTranRoleCompositionExportSearchResponse {

    /**
     * 取引ロール＋取引グループ群のＳｅｔ
     *
     * @param bizTranRole_BizTranGrpsSheet 取引ロール＋取引グループ群
     */
    void setBizTranRole_BizTranGrpsSheet(BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet);
    /**
     * 取引グループ＋取引群のＳｅｔ
     *
     * @param bizTranGrp_BizTransSheet 取引グループ＋取引群
     */
    void setBizTranGrp_BizTransSheet(BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet);
}
