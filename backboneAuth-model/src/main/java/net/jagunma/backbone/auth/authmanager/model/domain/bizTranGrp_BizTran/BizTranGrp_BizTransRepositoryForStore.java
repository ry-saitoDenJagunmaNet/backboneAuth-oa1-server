package net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp_BizTran;

/**
 * 取引グループ_取引割当群登録
 */
public interface BizTranGrp_BizTransRepositoryForStore {

    /**
     * 取引グループ_取引割当の削除を行います。
     *
     * @param subSystemCode サブシステムコード
     */
    void delete(String subSystemCode);
    /**
     * 取引グループ_取引割当の追加を行います。
     *
     * @param bizTranGrp_BizTrans 取引グループ_取引割当群
     */
    void insert(BizTranGrp_BizTrans bizTranGrp_BizTrans);
}
