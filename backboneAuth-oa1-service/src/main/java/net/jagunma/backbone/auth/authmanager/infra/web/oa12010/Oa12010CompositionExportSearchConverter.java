package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionReference.BizTranRoleCompositionExportSearchRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010Vo;

/**
 * OA12010 取引ロール編成インポート＆エクスポート エクスポート検索 Request Converter
 */
public class Oa12010CompositionExportSearchConverter implements BizTranRoleCompositionExportSearchRequest {

    /**
     * OA12010 ViewObject
     */
    private final Oa12010Vo vo;

    // コンストラクタ
    Oa12010CompositionExportSearchConverter(Oa12010Vo vo) {
        this.vo = vo;
    }

    // ファクトリーメソッド
    public static Oa12010CompositionExportSearchConverter with(Oa12010Vo vo) {
        return new Oa12010CompositionExportSearchConverter(vo);
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
