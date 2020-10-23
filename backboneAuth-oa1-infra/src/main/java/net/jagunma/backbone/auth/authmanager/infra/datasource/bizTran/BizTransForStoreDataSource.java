package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTran.BizTransRepositoryForStore;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntity;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntityDao;
import org.springframework.stereotype.Component;

/**
 * 取引群登録
 */
@Component
public class BizTransForStoreDataSource implements BizTransRepositoryForStore {

    private final BizTranEntityDao bizTranEntityDao;

    // コンストラクタ
    BizTransForStoreDataSource(BizTranEntityDao bizTranEntityDao) {
        this.bizTranEntityDao = bizTranEntityDao;
    }

    /**
     * 取引の登録を行います。
     *
     * @param bizTrans 取引群]
     * @return 取引群（登録後）
     */
    public BizTrans store(BizTrans bizTrans) {

        // 取引削除
        delete(bizTrans.getValues().get(0).getSubSystemCode());

        // 取引追加
        List<BizTran> returnList = newArrayList();
        for (BizTran bizTran : bizTrans.getValues()) {
            returnList.add(insert(bizTran));
        }
        return BizTrans.createFrom(returnList);
    }

    /**
     * 取引の削除を行います。
     *
     * @param subSystemCode サブシステムコード
     */
    void delete(String subSystemCode) {
        BizTranEntityCriteria criteria = new BizTranEntityCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(subSystemCode);
        bizTranEntityDao.forceDelete(criteria);
    }

    /**
     * 取引の追加を行います。
     *
     * @param bizTran 取引
     * @return 取引
     */
    BizTran insert(BizTran bizTran) {
        BizTranEntity entity = new BizTranEntity();
        entity.setBizTranCode(bizTran.getBizTranCode());
        entity.setBizTranName(bizTran.getBizTranName());
        entity.setIsCenterBizTran(bizTran.getCenterBizTran());
        entity.setExpirationStartDate(bizTran.getExpirationStartDate());
        entity.setExpirationEndDate(bizTran.getExpirationStartDate());
        entity.setSubSystemCode(bizTran.getSubSystemCode());
        bizTranEntityDao.insert(entity);
        return BizTran.createFrom(
            entity.getBizTranId(),
            entity.getBizTranCode(),
            entity.getBizTranName(),
            entity.getIsCenterBizTran(),
            entity.getExpirationStartDate(),
            entity.getExpirationEndDate(),
            entity.getSubSystemCode(),
            entity.getRecordVersion(),
            null);
    }
}
