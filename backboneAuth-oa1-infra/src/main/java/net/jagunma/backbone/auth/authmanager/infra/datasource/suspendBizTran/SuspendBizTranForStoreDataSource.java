package net.jagunma.backbone.auth.authmanager.infra.datasource.suspendBizTran;

import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepository;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntity;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntityDao;
import org.springframework.stereotype.Component;

/**
 * 一時取引抑止格納
 */
@Component
public class SuspendBizTranForStoreDataSource implements SuspendBizTranRepositoryForStore {

    private final SuspendBizTranEntityDao suspendBizTranEntityDao;
    private final SuspendBizTranRepository suspendBizTranRepository;

    // コンストラクタ
    public SuspendBizTranForStoreDataSource(SuspendBizTranEntityDao suspendBizTranEntityDao,
        SuspendBizTranRepository suspendBizTranRepository) {

        this.suspendBizTranEntityDao = suspendBizTranEntityDao;
        this.suspendBizTranRepository = suspendBizTranRepository;
    }

    /**
     * 一時取引抑止の追加を行います
     *
     * @param suspendBizTran 一時取引抑止
     * @return 一時取引抑止
     */
    @Override
    public SuspendBizTran insert(SuspendBizTran suspendBizTran) {

        SuspendBizTranEntity entity = new SuspendBizTranEntity();
        entity.setJaCode(suspendBizTran.getJaCode());
        entity.setBranchCode(suspendBizTran.getBranchCode());
        entity.setSubSystemCode(suspendBizTran.getSubSystemCode());
        entity.setBizTranGrpCode(suspendBizTran.getBizTranGrpCode());
        entity.setBizTranCode(suspendBizTran.getBizTranCode());
        entity.setSuspendStartDate(suspendBizTran.getSuspendStartDate());
        entity.setSuspendEndDate(suspendBizTran.getSuspendEndDate());
        entity.setSuspendReason(suspendBizTran.getSuspendReason());

        suspendBizTranEntityDao.insert(entity);

        SuspendBizTranCriteria criteria = new SuspendBizTranCriteria();
        criteria.getSuspendBizTranIdCriteria().setEqualTo(entity.getSuspendBizTranId());
        return suspendBizTranRepository.findOneBy(criteria);
    }

    /**
     * 一時取引抑止を更新を行います
     *
     * @param suspendBizTran 一時取引抑止
     * @return 一時取引抑止
     */
    @Override
    public SuspendBizTran update(SuspendBizTran suspendBizTran) {

        SuspendBizTranEntity entity = new SuspendBizTranEntity();
        entity.setSuspendBizTranId(suspendBizTran.getSuspendBizTranId());
        entity.setJaCode(suspendBizTran.getJaCode());
        entity.setBranchCode(suspendBizTran.getBranchCode());
        entity.setSubSystemCode(suspendBizTran.getSubSystemCode());
        entity.setBizTranGrpCode(suspendBizTran.getBizTranGrpCode());
        entity.setBizTranCode(suspendBizTran.getBizTranCode());
        entity.setSuspendStartDate(suspendBizTran.getSuspendStartDate());
        entity.setSuspendEndDate(suspendBizTran.getSuspendEndDate());
        entity.setSuspendReason(suspendBizTran.getSuspendReason());
        entity.setRecordVersion(suspendBizTran.getRecordVersion());

        suspendBizTranEntityDao.updateExcludeNull(entity);

        SuspendBizTranCriteria criteria = new SuspendBizTranCriteria();
        criteria.getSuspendBizTranIdCriteria().setEqualTo(entity.getSuspendBizTranId());
        return suspendBizTranRepository.findOneBy(criteria);
    }

    /**
     * 一時取引抑止を削除を行います
     *
     * @param suspendBizTran 一時取引抑止
     */
    @Override
    public void delete(SuspendBizTran suspendBizTran) {

        SuspendBizTranEntity entity = new SuspendBizTranEntity();
        entity.setSuspendBizTranId(suspendBizTran.getSuspendBizTranId());
        entity.setRecordVersion(suspendBizTran.getRecordVersion());

        suspendBizTranEntityDao.delete(entity);
    }
}
