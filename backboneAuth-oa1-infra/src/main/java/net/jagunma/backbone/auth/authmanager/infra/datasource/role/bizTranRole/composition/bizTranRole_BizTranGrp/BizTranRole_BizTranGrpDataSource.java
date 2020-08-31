package net.jagunma.backbone.auth.authmanager.infra.datasource.role.bizTranRole.composition.bizTranRole_BizTranGrp;

import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpRepository;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntityDao;
import org.springframework.stereotype.Component;

/**
 * 取引ロール_取引グループ割当検索 DataSource
 */
@Component
public class BizTranRole_BizTranGrpDataSource implements BizTranRole_BizTranGrpRepository {

    private final BizTranRole_BizTranGrpEntityDao bizTranRole_BizTranGrpEntityDao;

    // コンストラクタ
    public BizTranRole_BizTranGrpDataSource(
        BizTranRole_BizTranGrpEntityDao bizTranRole_BizTranGrpEntityDao) {
        this.bizTranRole_BizTranGrpEntityDao = bizTranRole_BizTranGrpEntityDao;
    }

    /**
     * 取引ロール_取引グループ割当の条件検索を行います。
     *
     * @param bizTranRole_BizTranGrpCriteria 取引ロール_取引グループ割当の検索条件
     * @return 取引ロール_取引グループ割当
     */
    @Override
    public BizTranRole_BizTranGrp findOneBy(
        BizTranRole_BizTranGrpCriteria bizTranRole_BizTranGrpCriteria) {
        BizTranRole_BizTranGrpEntity bizTranRole_BizTranGrpEntity = bizTranRole_BizTranGrpEntityDao
            .findOneBy(bizTranRole_BizTranGrpCriteria);
        return BizTranRole_BizTranGrp.of(bizTranRole_BizTranGrpEntity);
    }
}
