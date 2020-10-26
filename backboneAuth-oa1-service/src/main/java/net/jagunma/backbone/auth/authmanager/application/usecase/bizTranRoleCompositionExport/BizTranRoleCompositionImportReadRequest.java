package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExport;

import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRoleCompositionBook;

/**
 * 取引ロール編成インポート＆エクスポート Excel Readサービス Request
 */
public interface BizTranRoleCompositionImportReadRequest {

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
    BizTranRoleCompositionBook getBizTranRoleCompositionBook();
}
