package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExport;

/**
 * 取引ロール編成エクスポート検索サービス Request
 */
public interface BizTranRoleCompositionExportSearchRequest {

    /**
     * サブシステムコードのＧｅｔ
     *
     * @return サブシステムコード
     */
    String getSubSystemCode();
}
