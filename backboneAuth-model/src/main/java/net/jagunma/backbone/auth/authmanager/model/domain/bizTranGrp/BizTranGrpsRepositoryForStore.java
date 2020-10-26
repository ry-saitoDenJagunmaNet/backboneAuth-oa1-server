package net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp;

/**
 * 取引グループ群登録
 */
public interface BizTranGrpsRepositoryForStore {

    /**
     * 取引グループの登録を行います
     *
     * @param bizTranGrps 取引グループ群
     * @return 取引グループ群（登録後）
     */
    BizTranGrps store(BizTranGrps bizTranGrps);
}
