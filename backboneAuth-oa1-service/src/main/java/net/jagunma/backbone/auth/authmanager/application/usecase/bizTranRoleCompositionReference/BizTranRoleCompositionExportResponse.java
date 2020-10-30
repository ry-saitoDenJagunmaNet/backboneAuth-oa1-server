package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionReference;

import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;

/**
 * 取引ロール編成エクスポート検索サービス Response
 */
public interface BizTranRoleCompositionExportResponse {

    /**
     * 取引ロール－取引グループ編成群のＳｅｔ
     *
     * @param bizTranRole_BizTranGrpsSheet 取引ロール－取引グループ編成群
     */
    void setBizTranRole_BizTranGrpsSheet(BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet);
    /**
     * 取引グループ－取引編成群のＳｅｔ
     *
     * @param bizTranGrp_BizTransSheet 取引グループ－取引編成群
     */
    void setBizTranGrp_BizTransSheet(BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet);
}
