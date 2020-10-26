package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole_BizTranGrp;

/**
 * 取引ロール_取引グループ割当群登録
 */
public interface BizTranRole_BizTranGrpsRepositoryForStore {

    /**
     * 取引ロール_取引グループ割当の削除を行います
     *
     * @param subSystemCode サブシステムコード
     */
    void delete(String subSystemCode);
    /**
     * 取引ロール_取引グループ割当の追加を行います
     *
     * @param bizTranRole_BizTranGrps 取引ロール_取引グループ割当群
     */
    void insert(BizTranRole_BizTranGrps bizTranRole_BizTranGrps);
}
