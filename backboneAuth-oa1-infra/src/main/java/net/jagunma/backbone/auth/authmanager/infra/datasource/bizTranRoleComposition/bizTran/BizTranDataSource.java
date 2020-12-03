package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition.bizTran;

import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntity;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntityDao;
import org.springframework.stereotype.Component;

/**
 * 取引検索
 */
@Component
public class BizTranDataSource implements BizTranRepository {

    private final BizTranEntityDao bizTranEntityDao;

    // コンストラクタ
    BizTranDataSource(BizTranEntityDao bizTranEntityDao) {
        this.bizTranEntityDao = bizTranEntityDao;
    }

    /**
     * 取引の検索を行います
     *
     * @param bizTranCriteria 取引の検索条件
     * @return 取引群
     */
    public BizTran findOneBy(BizTranCriteria bizTranCriteria) {

        // 取引検索
        BizTranEntityCriteria entityCriteria = new BizTranEntityCriteria();
        entityCriteria.getBizTranIdCriteria().assignFrom(bizTranCriteria.getBizTranIdCriteria());
        entityCriteria.getBizTranCodeCriteria().assignFrom(bizTranCriteria.getBizTranCodeCriteria());
        entityCriteria.getSubSystemCodeCriteria().assignFrom(bizTranCriteria.getSubSystemCodeCriteria());

        BizTranEntity entity = bizTranEntityDao.findOneBy(entityCriteria);
        return BizTran.createFrom(
            entity.getBizTranId(),
            entity.getBizTranCode(),
            entity.getBizTranName(),
            entity.getIsCenterBizTran(),
            entity.getValidThruStartDate(),
            entity.getValidThruEndDate(),
            entity.getSubSystemCode(),
            entity.getRecordVersion(),
            SubSystem.codeOf(entity.getSubSystemCode()));
    }
}
