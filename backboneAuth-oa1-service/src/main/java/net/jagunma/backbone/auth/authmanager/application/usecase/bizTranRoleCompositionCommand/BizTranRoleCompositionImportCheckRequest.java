package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand;

import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;

/**
 * 取引ロール編成エクスポートExcelインポートチェックサービス Request
 */
public interface BizTranRoleCompositionImportCheckRequest {

    /**
     * サブシステムコードのＧｅｔ
     *
     * @return サブシステムコード
     */
    String getSubSystemCode();
    /**
     * 取引ロール－取引グループ編成群のＧｅｔ
     *
     * @return 取引ロール－取引グループ編成群
     */
    public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet();
    /**
     * 取引グループ－取引編成群のＧｅｔ
     *
     * @return 取引グループ－取引編成群
     */
    public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet();
}
