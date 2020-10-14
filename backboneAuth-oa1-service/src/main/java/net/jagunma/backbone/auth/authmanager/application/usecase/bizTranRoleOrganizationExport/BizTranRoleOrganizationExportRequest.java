package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleOrganizationExport;

/**
 * 取引ロール編成エクスポートサービス Request
 */
public interface BizTranRoleOrganizationExportRequest {

    /**
     * サブシステムコードのＧｅｔ
     *
     * @return サブシステムコード
     */
    String getSubSystemCode();
}
