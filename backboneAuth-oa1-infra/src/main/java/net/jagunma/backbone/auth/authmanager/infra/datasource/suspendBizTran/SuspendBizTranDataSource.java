package net.jagunma.backbone.auth.authmanager.infra.datasource.suspendBizTran;

import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepository;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntity;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntityDao;
import org.springframework.stereotype.Component;

/**
 * 一時取引抑止検索 DataSource
 */
@Component
public class SuspendBizTranDataSource implements SuspendBizTranRepository {

    private final SuspendBizTranEntityDao suspendBizTranEntityDao;

    // コンストラクタ
    public SuspendBizTranDataSource(SuspendBizTranEntityDao suspendBizTranEntityDao) {
        this.suspendBizTranEntityDao = suspendBizTranEntityDao;
    }

    /**
     * 一時取引抑止の条件検索を行います。
     *
     * @param suspendBizTranCriteria 一時取引抑止の検索条件
     * @return 一時取引抑止
     */
    @Override
    public SuspendBizTran findOneBy(SuspendBizTranCriteria suspendBizTranCriteria) {
        SuspendBizTranEntity suspendBizTranEntity = suspendBizTranEntityDao
            .findOneBy(suspendBizTranCriteria);
        return SuspendBizTran.of(suspendBizTranEntity);
    }
}
