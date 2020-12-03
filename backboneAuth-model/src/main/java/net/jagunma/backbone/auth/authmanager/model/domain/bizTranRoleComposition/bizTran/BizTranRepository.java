package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran;

/**
 * 取引検索
 */
public interface BizTranRepository {

    /**
     * 取引の条件検索を行います
     *
     * @param bizTranCriteria 取引の検索条件
     * @return 取引
     */
    BizTran findOneBy(BizTranCriteria bizTranCriteria);
}
