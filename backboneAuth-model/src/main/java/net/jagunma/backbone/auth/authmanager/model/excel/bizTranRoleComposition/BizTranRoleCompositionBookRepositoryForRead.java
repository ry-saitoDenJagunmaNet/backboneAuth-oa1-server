package net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;

/**
 * 取引ロール編成 Book 読み込み
 */
public interface BizTranRoleCompositionBookRepositoryForRead {

    /**
     * 取引ロール編成Excelを読み込みます
     *
     * @param excelContainer                  Excel格納
     * @param bizTranRole_BizTranGrpSheetList 取引ロール－取引グループ編成リスト
     * @param bizTranGrp_BizTranSheetList     取引グループ－取引編成群
     */
    void read(ExcelContainer excelContainer,
        List<BizTranRole_BizTranGrpSheet> bizTranRole_BizTranGrpSheetList,
        List<BizTranGrp_BizTranSheet> bizTranGrp_BizTranSheetList);
}
