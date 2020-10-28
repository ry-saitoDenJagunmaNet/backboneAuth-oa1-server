package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionExportWriteRequest;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;

/**
 * OA12010 取引ロール編成インポート＆エクスポート Excel Weiteサービス Request Converter
 */
public class Oa12010CompositionExportWriteConverter implements BizTranRoleCompositionExportWriteRequest {

    private BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet;
    private BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet;

    // コンストラクタ
    Oa12010CompositionExportWriteConverter(BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet,
        BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet) {

        this.bizTranRole_BizTranGrpsSheet = bizTranRole_BizTranGrpsSheet;
        this.bizTranGrp_BizTransSheet = bizTranGrp_BizTransSheet;
    }

    // ファクトリーメソッド
    public static Oa12010CompositionExportWriteConverter with(BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet,
        BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet) {

        return new Oa12010CompositionExportWriteConverter(
            bizTranRole_BizTranGrpsSheet,
            bizTranGrp_BizTransSheet);
    }

    /**
     * 取引ロール－取引グループ編成群のＧｅｔ
     *
     * @return 取引ロール－取引グループ編成群
     */
    public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
        return bizTranRole_BizTranGrpsSheet;
    }

    /**
     * 取引グループ－取引編成群のＧｅｔ
     *
     * @return 取引グループ－取引編成群
     */
    public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
        return bizTranGrp_BizTransSheet;
    }
}
