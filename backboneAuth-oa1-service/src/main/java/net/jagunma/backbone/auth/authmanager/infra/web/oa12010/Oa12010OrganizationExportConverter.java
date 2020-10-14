package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleOrganizationExport.BizTranRoleOrganizationExportRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010Vo;

/**
 * OA12010 取引ロール編成エクスポートサービス Request Converter
 */
public class Oa12010OrganizationExportConverter implements BizTranRoleOrganizationExportRequest {

    /**
     * OA12010 ViewObject
     */
    private final Oa12010Vo vo;

    // コンストラクタ
    Oa12010OrganizationExportConverter(Oa12010Vo vo) {
        this.vo = vo;
    }

    // ファクトリーメソッド
    public static Oa12010OrganizationExportConverter with(Oa12010Vo vo) {
        return new Oa12010OrganizationExportConverter(vo);
    }

    /**
     * サブシステムコードのＧｅｔ
     *
     * @return サブシステムコード
     */
    public String getSubSystemCode() {
        return vo.getSubSystemCode();
    }
}
