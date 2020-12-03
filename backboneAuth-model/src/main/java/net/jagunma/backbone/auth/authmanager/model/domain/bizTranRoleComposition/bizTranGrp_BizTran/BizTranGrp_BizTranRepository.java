package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran;

/**
 * 取引グループ_取引割当検索
 */
public interface BizTranGrp_BizTranRepository {

    /**
     * 取引グループ_取引割当の条件検索を行います
     *
     * @param bizTranGrp_BizTranCriteria 取引グループ_取引割当の検索条件
     * @return 取引グループ_取引割当
     */
    BizTranGrp_BizTran findOneBy(BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria);
}
