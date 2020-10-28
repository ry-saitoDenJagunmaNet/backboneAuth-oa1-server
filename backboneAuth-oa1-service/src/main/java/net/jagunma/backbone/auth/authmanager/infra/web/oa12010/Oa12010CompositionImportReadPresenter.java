package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionReference.BizTranRoleCompositionImportReadResponse;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;

/**
 * OA12010 取引ロール編成インポート＆エクスポート Excel Readサービス Response Presenter
 */
public class Oa12010CompositionImportReadPresenter implements BizTranRoleCompositionImportReadResponse {

    private String subSystemCode;
    private BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet;
    private BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet;

    /**
     * サブシステムコードのＳｅｔ
     *
     * @param subSystemCode　サブシステムコード
     */
    public void setSubSystemCode(String subSystemCode) {
        this.subSystemCode = subSystemCode;
    }

    /**
     * 取引ロール－取引グループ編成群のＳｅｔ
     *
     * @param bizTranRole_BizTranGrpsSheet 取引ロール－取引グループ編成群
     */
    public void setBizTranRole_BizTranGrpsSheet(BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet) {
        this.bizTranRole_BizTranGrpsSheet = bizTranRole_BizTranGrpsSheet;
    }

    /**
     * 取引グループ－取引編成群のＳｅｔ
     *
     * @param bizTranGrp_BizTransSheet 取引グループ－取引編成群
     */
    public void setBizTranGrp_BizTransSheet(BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet) {
        this.bizTranGrp_BizTransSheet = bizTranGrp_BizTransSheet;
    }

    /**
     * converterに変換
     *
     * @return 取引ロール編成エクスポートExcel 登録サービス Request Converter
     */
    public Oa12010CompositionImportStoreConverter ConverterTo() {
        return Oa12010CompositionImportStoreConverter.with(
            subSystemCode,
            bizTranRole_BizTranGrpsSheet,
            bizTranGrp_BizTransSheet);
    }
}
