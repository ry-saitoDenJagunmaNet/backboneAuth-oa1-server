package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranGrp;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp.BizTranGrpsRepositoryForStore;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntity;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntityDao;
import org.springframework.stereotype.Component;

/**
 * 取引グループ群登録
 */
@Component
public class BizTranGrpsForStoreDataSource implements BizTranGrpsRepositoryForStore {

    private final BizTranGrpEntityDao bizTranGrpEntityDao;

    // コンストラクタ
    BizTranGrpsForStoreDataSource(BizTranGrpEntityDao bizTranGrpEntityDao) {
        this.bizTranGrpEntityDao = bizTranGrpEntityDao;
    }

    /**
     * 取引グループの登録を行います。
     *
     * @param bizTranGrps 取引グループ群
     * @return 取引グループ群（登録後）
     */
    public BizTranGrps store(BizTranGrps bizTranGrps) {

        // 取引グループ削除
        delete(bizTranGrps.getValues().get(0).getSubSystemCode());

        // 取引グループ追加
        List<BizTranGrp> returnList = newArrayList();
        for (BizTranGrp bizTranGrp : bizTranGrps.getValues()) {
            returnList.add(insert(bizTranGrp));
        }
        return BizTranGrps.createFrom(returnList);
    }

    /**
     * 取引グループの削除を行います。
     *
     * @param subSystemCode サブシステムコード
     */
    void delete(String subSystemCode) {
        BizTranGrpEntityCriteria criteria = new BizTranGrpEntityCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(subSystemCode);
        bizTranGrpEntityDao.forceDelete(criteria);
    }

    /**
     * 取引グループの追加を行います。
     *
     * @param bizTranGrp 取引グループ
     * @return 取引グループ
     */
    BizTranGrp insert(BizTranGrp bizTranGrp) {
        BizTranGrpEntity entity = new BizTranGrpEntity();
        entity.setBizTranGrpCode(bizTranGrp.getBizTranGrpCode());
        entity.setBizTranGrpName(bizTranGrp.getBizTranGrpName());
        entity.setSubSystemCode(bizTranGrp.getSubSystemCode());
        bizTranGrpEntityDao.insert(entity);
        return BizTranGrp.createFrom(
            entity.getBizTranGrpId(),
            entity.getBizTranGrpCode(),
            entity.getBizTranGrpName(),
            entity.getSubSystemCode(),
            entity.getRecordVersion(),
            null);
    }
}
