package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.role.bizTranRole.composition.bizTran;

import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTran.BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTran.BizTranRepository;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntity;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntityDao;
import org.springframework.stereotype.Component;

/**
 * 取引検索 DataSource
 */
@Component
public class BizTranDataSource implements BizTranRepository {

    private final BizTranEntityDao bizTranEntityDao;

    // コンストラクタ
    public BizTranDataSource(BizTranEntityDao bizTranEntityDao) {
        this.bizTranEntityDao = bizTranEntityDao;
    }

    /**
     * 取引の条件検索を行います。
     *
     * @param bizTranCriteria 取引の検索条件
     * @return 取引
     */
    @Override
    public BizTran findOneBy(BizTranCriteria bizTranCriteria) {
        BizTranEntity bizTranEntity = bizTranEntityDao.findOneBy(bizTranCriteria);
        return BizTran.of(bizTranEntity);
    }
}
