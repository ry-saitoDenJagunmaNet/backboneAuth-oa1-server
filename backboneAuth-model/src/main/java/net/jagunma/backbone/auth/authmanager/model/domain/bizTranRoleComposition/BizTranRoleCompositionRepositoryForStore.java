package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition;

/**
 * 取引ロール編成登録
 */
public interface BizTranRoleCompositionRepositoryForStore {

    /**
     * 取引ロール編成の登録を行います
     *
     * @param bizTranRoleComposition 取引ロール編成
     */
    BizTranRoleComposition store(BizTranRoleComposition bizTranRoleComposition);
}
