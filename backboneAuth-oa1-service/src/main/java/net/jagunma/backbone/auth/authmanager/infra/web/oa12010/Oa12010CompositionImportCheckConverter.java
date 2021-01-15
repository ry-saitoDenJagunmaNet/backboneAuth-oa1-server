package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionImportCheckRequest;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;

/**
 * OA12010 インポートチェック Converter
 */
public class Oa12010CompositionImportCheckConverter implements BizTranRoleCompositionImportCheckRequest {

    private String subSystemCode;
    private BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet;
    private BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet;

    // コンストラクタ
    Oa12010CompositionImportCheckConverter(
        String subSystemCode,
        BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet,
        BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet) {

        this.subSystemCode = subSystemCode;
        this.bizTranRole_BizTranGrpsSheet = bizTranRole_BizTranGrpsSheet;
        this.bizTranGrp_BizTransSheet = bizTranGrp_BizTransSheet;
    }

    // ファクトリーメソッド
    public static Oa12010CompositionImportCheckConverter with(
        String subSystemCode,
        BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet,
        BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet) {

        return new Oa12010CompositionImportCheckConverter(
            subSystemCode,
            bizTranRole_BizTranGrpsSheet,
            bizTranGrp_BizTransSheet);
    }

    /**
     * サブシステムコードのＧｅｔ
     *
     * @return サブシステムコード
     */
    public String getSubSystemCode() {
        return subSystemCode;
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
     *取引グループ－取引編成群のＧｅｔ
     *
     * @return 取引グループ－取引編成群
     */
    public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
        return bizTranGrp_BizTransSheet;
    }

}
