package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranGrp_BizTran;

import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp_BizTran.BizTranGrp_BizTransRepositoryForStore;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntity;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntityDao;
import org.springframework.stereotype.Component;

/**
 * 取引グループ_取引割当群登録
 */
@Component
public class BizTranGrp_BizTransForStoreDataSource implements BizTranGrp_BizTransRepositoryForStore {

    private final BizTranGrp_BizTranEntityDao bizTranGrp_BizTranEntityDao;

    // コンストラクタ
    BizTranGrp_BizTransForStoreDataSource(BizTranGrp_BizTranEntityDao bizTranGrp_BizTranEntityDao) {
        this.bizTranGrp_BizTranEntityDao = bizTranGrp_BizTranEntityDao;
    }

    /**
     * 取引グループ_取引割当の削除を行います
     *
     * @param subSystemCode サブシステムコード
     */
    public void delete(String subSystemCode) {
        BizTranGrp_BizTranEntityCriteria criteria = new BizTranGrp_BizTranEntityCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(subSystemCode);
        bizTranGrp_BizTranEntityDao.forceDelete(criteria);
    }

    /**
     * 取引グループ_取引割当の追加を行います
     *
     * @param bizTranGrp_BizTrans 取引グループ_取引割当群
     */
    public void insert(BizTranGrp_BizTrans bizTranGrp_BizTrans) {
        for (BizTranGrp_BizTran bizTranGrp_BizTran : bizTranGrp_BizTrans.getValues()) {
            BizTranGrp_BizTranEntity entity = new BizTranGrp_BizTranEntity();
            entity.setBizTranGrpId(bizTranGrp_BizTran.getBizTranGrpId());
            entity.setBizTranId(bizTranGrp_BizTran.getBizTranId());
            entity.setSubSystemCode(bizTranGrp_BizTran.getSubSystemCode());
            bizTranGrp_BizTranEntityDao.insert(entity);
        }
    }
}
