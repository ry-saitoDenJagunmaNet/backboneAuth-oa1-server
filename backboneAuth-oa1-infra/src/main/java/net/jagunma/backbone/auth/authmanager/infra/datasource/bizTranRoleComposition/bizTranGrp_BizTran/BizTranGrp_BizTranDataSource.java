package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition.bizTranGrp_BizTran;

import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntity;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntityDao;
import org.springframework.stereotype.Component;

/**
 * 取引グループ_取引割当検索
 */
@Component
public class BizTranGrp_BizTranDataSource implements BizTranGrp_BizTranRepository {

    private final BizTranGrp_BizTranEntityDao bizTranGrp_BizTranEntityDao;
    private final BizTranGrpRepository bizTranGrpRepository;
    private final BizTranRepository bizTranRepository;

    // コンストラクタ
    BizTranGrp_BizTranDataSource(BizTranGrp_BizTranEntityDao bizTranGrp_BizTranEntityDao,
        BizTranGrpRepository bizTranGrpRepository,
        BizTranRepository bizTranRepository) {

        this.bizTranGrp_BizTranEntityDao = bizTranGrp_BizTranEntityDao;
        this.bizTranGrpRepository = bizTranGrpRepository;
        this.bizTranRepository = bizTranRepository;
    }

    /**
     * 取引グループ_取引割当の条件検索を行います
     *
     * @param bizTranGrp_BizTranCriteria 取引グループ_取引割当の検索条件
     * @return 取引グループ_取引割当
     */
    public BizTranGrp_BizTran findOneBy(BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria) {

        // 取引グループ検索
        BizTranGrpCriteria bizTranGrpCriteria = new BizTranGrpCriteria();
        bizTranGrpCriteria.getBizTranGrpIdCriteria().assignFrom(bizTranGrp_BizTranCriteria.getBizTranGrpIdCriteria());
        bizTranGrpCriteria.getSubSystemCodeCriteria().assignFrom(bizTranGrp_BizTranCriteria.getSubSystemCodeCriteria());
        BizTranGrp bizTranGrp = bizTranGrpRepository.findOneBy(bizTranGrpCriteria);

        // 取引検索
        BizTranCriteria bizTranCriteria = new BizTranCriteria();
        bizTranCriteria.getBizTranIdCriteria().assignFrom(bizTranGrp_BizTranCriteria.getBizTranIdCriteria());
        bizTranCriteria.getSubSystemCodeCriteria().assignFrom(bizTranGrp_BizTranCriteria.getSubSystemCodeCriteria());
        BizTran bizTran = bizTranRepository.findOneBy(bizTranCriteria);

        // 取引グループ_取引割当検索
        BizTranGrp_BizTranEntityCriteria entityCriteria = new BizTranGrp_BizTranEntityCriteria();
        entityCriteria.getBizTranGrpIdCriteria().assignFrom(bizTranGrp_BizTranCriteria.getBizTranGrpIdCriteria());
        entityCriteria.getBizTranIdCriteria().assignFrom(bizTranGrp_BizTranCriteria.getBizTranIdCriteria());
        entityCriteria.getSubSystemCodeCriteria().assignFrom(bizTranGrp_BizTranCriteria.getSubSystemCodeCriteria());

        BizTranGrp_BizTranEntity entity = bizTranGrp_BizTranEntityDao.findOneBy(entityCriteria);
        return BizTranGrp_BizTran.createFrom(
            entity.getBizTranGrp_BizTranId(),
            entity.getBizTranGrpId(),
            entity.getBizTranId(),
            entity.getSubSystemCode(),
            entity.getRecordVersion(),
            bizTranGrp,
            bizTran,
            SubSystem.codeOf(entity.getSubSystemCode()));
    }

}