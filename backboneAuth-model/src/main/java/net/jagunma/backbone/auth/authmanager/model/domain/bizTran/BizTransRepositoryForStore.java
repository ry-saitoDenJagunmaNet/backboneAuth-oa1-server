package net.jagunma.backbone.auth.authmanager.model.domain.bizTran;

import java.util.List;

/**
 * 取引群登録
 */
public interface BizTransRepositoryForStore {

    /**
     * 取引の登録を行います。
     *
     * @param bizTrans 取引群
     * @return 取引群（登録後）
     */
    BizTrans store(BizTrans bizTrans);
}
