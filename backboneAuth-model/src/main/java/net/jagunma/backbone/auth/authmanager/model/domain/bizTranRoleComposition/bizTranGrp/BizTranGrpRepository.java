package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp;

/**
 * 取引グループ検索
 */
public interface BizTranGrpRepository {

    /**
     * 取引グループの条件検索を行います
     *
     * @param bizTranGrpCriteria 取引グループの検索条件
     * @return 取引グループ群
     */
    BizTranGrp findOneBy(BizTranGrpCriteria bizTranGrpCriteria);
}
