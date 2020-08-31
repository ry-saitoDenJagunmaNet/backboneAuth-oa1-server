package net.jagunma.backbone.auth.authmanager.infra.datasource.role.bizTranRole.composition.bizTranGrp_BizTran;

import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTranGrp_BizTran.BizTranGrp_BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTranGrp_BizTran.BizTranGrp_BizTranRepository;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntity;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntityDao;
import org.springframework.stereotype.Component;

/**
 * 取引グループ_取引割当検索 DataSource
 */
@Component
public class BizTranGrp_BizTranDataSource implements BizTranGrp_BizTranRepository {

    private final BizTranGrp_BizTranEntityDao bizTranGrp_BizTranEntityDao;

    // コンストラクタ
    public BizTranGrp_BizTranDataSource(BizTranGrp_BizTranEntityDao bizTranGrp_BizTranEntityDao) {
        this.bizTranGrp_BizTranEntityDao = bizTranGrp_BizTranEntityDao;
    }

    /**
     * 取引グループ_取引割当の条件検索を行います。
     *
     * @param bizTranGrp_BizTranCriteria 取引グループ_取引割当の検索条件
     * @return 取引グループ_取引割当
     */
    @Override
    public BizTranGrp_BizTran findOneBy(BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria) {
        BizTranGrp_BizTranEntity bizTranGrp_BizTranEntity = bizTranGrp_BizTranEntityDao
            .findOneBy(bizTranGrp_BizTranCriteria);
        return BizTranGrp_BizTran.of(bizTranGrp_BizTranEntity);
    }
}
