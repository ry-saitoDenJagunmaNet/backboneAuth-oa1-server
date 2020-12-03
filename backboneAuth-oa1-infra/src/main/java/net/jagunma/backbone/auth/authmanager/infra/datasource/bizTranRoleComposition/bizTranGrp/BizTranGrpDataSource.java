package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition.bizTranGrp;

import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntity;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntityDao;
import org.springframework.stereotype.Component;

/**
 * 取引グループ検索
 */
@Component
public class BizTranGrpDataSource implements BizTranGrpRepository {

    private final BizTranGrpEntityDao bizTranGrpEntityDao;

    // コンストラクタ
    BizTranGrpDataSource(BizTranGrpEntityDao bizTranGrpEntityDao) {
        this.bizTranGrpEntityDao = bizTranGrpEntityDao;
    }

    /**
     * 取引グループの条件検索を行います
     *
     * @param bizTranGrpCriteria 取引グループの検索条件
     * @return 取引グループ群
     */
    public BizTranGrp findOneBy(BizTranGrpCriteria bizTranGrpCriteria) {

        // 取引グループ運検索
        BizTranGrpEntityCriteria entityCriteria = new BizTranGrpEntityCriteria();
        entityCriteria.getBizTranGrpIdCriteria().assignFrom(bizTranGrpCriteria.getBizTranGrpIdCriteria());
        entityCriteria.getBizTranGrpCodeCriteria().assignFrom(bizTranGrpCriteria.getBizTranGrpCodeCriteria());
        entityCriteria.getSubSystemCodeCriteria().assignFrom(bizTranGrpCriteria.getSubSystemCodeCriteria());

        BizTranGrpEntity entity = bizTranGrpEntityDao.findOneBy(entityCriteria);
        return BizTranGrp.createFrom(
            entity.getBizTranGrpId(),
            entity.getBizTranGrpCode(),
            entity.getBizTranGrpName(),
            entity.getSubSystemCode(),
            entity.getRecordVersion(),
            SubSystem.codeOf(entity.getSubSystemCode()));
    }
}
