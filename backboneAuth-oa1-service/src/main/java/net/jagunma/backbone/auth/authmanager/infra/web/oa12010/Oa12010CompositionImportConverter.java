package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionImportRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.dto.Oa12010Dto;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;

/**
 * OA12010 インポート Converter
 */
public class Oa12010CompositionImportConverter implements BizTranRoleCompositionImportRequest {

    private Oa12010Dto oa12010Dto;

    // コンストラクタ
    Oa12010CompositionImportConverter(Oa12010Dto oa12010Dto) {
        this.oa12010Dto = oa12010Dto;
    }

    // ファクトリーメソッド
    public static Oa12010CompositionImportConverter with(Oa12010Dto oa12010Dto) {
        return new Oa12010CompositionImportConverter(oa12010Dto);
    }

    /**
     * サブシステムコードのＧｅｔ
     *
     * @return サブシステムコード
     */
    public String getSubSystemCode() {
        return oa12010Dto.getSubSystemCode();
    }

    /**
     * 取引ロール－取引グループ編成群のＧｅｔ
     *
     * @return 取引ロール－取引グループ編成群
     */
    public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
        return oa12010Dto.getBizTranRole_BizTranGrpsSheet();
    }

    /**
     *取引グループ－取引編成群のＧｅｔ
     *
     * @return 取引グループ－取引編成群
     */
    public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
        return oa12010Dto.getBizTranGrp_BizTransSheet();
    }
}
