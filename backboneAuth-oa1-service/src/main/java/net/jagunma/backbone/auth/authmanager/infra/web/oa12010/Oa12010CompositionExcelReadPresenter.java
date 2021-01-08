package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExcelReference.BizTranRoleCompositionExcelReadResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.dto.Oa12010Dto;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;

/**
 * OA12010 Excel Read Presenter
 */
public class Oa12010CompositionExcelReadPresenter implements
    BizTranRoleCompositionExcelReadResponse {

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
     * @return 取引ロール編成エクスポートExcel Importチェックサービス Request Converter
     */
    public Oa12010CompositionImportCheckConverter converterTo() {
        return Oa12010CompositionImportCheckConverter.with(
            subSystemCode,
            bizTranRole_BizTranGrpsSheet,
            bizTranGrp_BizTransSheet);
    }

    /**
     * dtoに変換
     *
     * @param dto OA12010 dto
     */
    public void bindTo(Oa12010Dto dto) {
        dto.setSubSystemCode(subSystemCode);
        dto.setBizTranRole_BizTranGrpsSheet(bizTranRole_BizTranGrpsSheet);
        dto.setBizTranGrp_BizTransSheet(bizTranGrp_BizTransSheet);
    }
}
