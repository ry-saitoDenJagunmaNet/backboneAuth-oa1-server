package net.jagunma.backbone.auth.authmanager.infra.web.oa12010.dto;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;

/**
 * Oa12010 Importデータ Dto
 *  Import チェック後に、登録コントローラに渡すためのオブジェクト
 */
public class Oa12010Dto {

    private String subSystemCode;
    private BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet;
    private BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet;

    /**
     * サブシステムコードのＧｅｔ
     *
     * @return サブシステムコード
     */
    public String getSubSystemCode() {
        return subSystemCode;
    }

    /**
     * サブシステムコードのＳｅｔ
     *
     * @param subSystemCode サブシステムコード
     */
    public void setSubSystemCode(String subSystemCode) {
        this.subSystemCode = subSystemCode;
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
     * 取引ロール－取引グループ編成群のＳｅｔ
     *
     * @param bizTranRole_BizTranGrpsSheet 取引ロール－取引グループ編成群
     */
    public void setBizTranRole_BizTranGrpsSheet(BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet) {
        this.bizTranRole_BizTranGrpsSheet = bizTranRole_BizTranGrpsSheet;
    }

    /**
     * 取引グループ－取引編成群のＧｅｔ
     *
     * @return 取引グループ－取引編成群
     */
    public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
        return bizTranGrp_BizTransSheet;
    }

    /**
     * 取引グループ－取引編成群のＳｅｔ
     *
     * @param bizTranGrp_BizTransSheet 取引グループ－取引編成群
     */
    public void setBizTranGrp_BizTransSheet(BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet) {
        this.bizTranGrp_BizTransSheet = bizTranGrp_BizTransSheet;
    }
}
