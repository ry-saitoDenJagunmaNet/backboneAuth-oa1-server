package net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition;

/**
 * 取引ロール編成 Book 書き出し
 */
public interface BizTranRoleCompositionBookRepositoryForWrite {

    /**
     * 取引ロール編成Excelを作成します
     *
     * @param bizTranRole_BizTranGrpsSheet 取引ロール－取引グループ編成群
     * @param bizTranGrp_BizTransSheet     取引グループ－取引編成群
     * @return 取引ロール編成作成結果
     */
    BizTranRoleCompositionBook create(BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet,
        BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet);
}
