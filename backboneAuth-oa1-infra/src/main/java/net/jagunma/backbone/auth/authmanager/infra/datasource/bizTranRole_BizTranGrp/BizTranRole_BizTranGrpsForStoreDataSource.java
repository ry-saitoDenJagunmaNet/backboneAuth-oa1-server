package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRole_BizTranGrp;

import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpsRepositoryForStore;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntityDao;
import org.springframework.stereotype.Component;

/**
 * 取引ロール_取引グループ割当群登録
 */
@Component
public class BizTranRole_BizTranGrpsForStoreDataSource implements BizTranRole_BizTranGrpsRepositoryForStore {

    private final BizTranRole_BizTranGrpEntityDao bizTranRole_BizTranGrpEntityDao;

    // コンストラクタ
    BizTranRole_BizTranGrpsForStoreDataSource(BizTranRole_BizTranGrpEntityDao bizTranRole_BizTranGrpEntityDao) {
        this.bizTranRole_BizTranGrpEntityDao = bizTranRole_BizTranGrpEntityDao;
    }

    /**
     * 取引ロール_取引グループ割当の削除を行います
     *
     * @param subSystemCode サブシステムコード
     */
    public void delete(String subSystemCode) {
        BizTranRole_BizTranGrpEntityCriteria criteria = new BizTranRole_BizTranGrpEntityCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(subSystemCode);
        bizTranRole_BizTranGrpEntityDao.forceDelete(criteria);
    }

    /**
     * 取引ロール_取引グループ割当の追加を行います
     *
     * @param bizTranRole_BizTranGrps 取引ロール_取引グループ割当群
     */
    public void insert(BizTranRole_BizTranGrps bizTranRole_BizTranGrps) {
        // 取引ロール_取引グループ割当追加
        for (BizTranRole_BizTranGrp bizTranRole_BizTranGrp : bizTranRole_BizTranGrps.getValues()) {
            BizTranRole_BizTranGrpEntity entity = new BizTranRole_BizTranGrpEntity();
            entity.setBizTranRoleId(bizTranRole_BizTranGrp.getBizTranRoleId());
            entity.setBizTranGrpId(bizTranRole_BizTranGrp.getBizTranGrpId());
            entity.setSubSystemCode(bizTranRole_BizTranGrp.getSubSystemCode());
            bizTranRole_BizTranGrpEntityDao.insert(entity);
        }
    }
}
