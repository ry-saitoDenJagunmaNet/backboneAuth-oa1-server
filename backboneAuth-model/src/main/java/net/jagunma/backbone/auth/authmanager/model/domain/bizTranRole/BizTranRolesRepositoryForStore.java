package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole;

/**
 * 取引ロール群登録
 */
public interface BizTranRolesRepositoryForStore {

    /**
     * 取引ロールの登録を行います。
     *
     * @param bizTranRoles 取引ロール群
     * @return 取引ロール群（登録後）
     */
    BizTranRoles store(BizTranRoles bizTranRoles);
}
