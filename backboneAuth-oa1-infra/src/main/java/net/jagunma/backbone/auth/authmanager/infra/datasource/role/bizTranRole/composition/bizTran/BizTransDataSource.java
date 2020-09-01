package net.jagunma.backbone.auth.authmanager.infra.datasource.role.bizTranRole.composition.bizTran;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTran.BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTran.BizTransRepository;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntity;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * 取引検索 DataSource
 */
@Component
public class BizTransDataSource implements BizTransRepository {

    private final BizTranEntityDao bizTranEntityDao;
    private final Orders defaultOrders = Orders.empty().addOrder("bizTranCode");

    // コンストラクタ
    BizTransDataSource(BizTranEntityDao bizTranEntityDao) {
        this.bizTranEntityDao = bizTranEntityDao;
    }

    /**
     * 取引の条件検索を行います。
     *
     * @param bizTranCriteria 取引の検索条件
     * @param orders          オーダー指定
     * @return 取引群
     */
    @Override
    public BizTrans selectBy(BizTranCriteria bizTranCriteria, Orders orders) {
        List<BizTranEntity> list = bizTranEntityDao.findBy(bizTranCriteria, orders);
        return BizTrans.createFrom(list);
    }

    /**
     * 取引の条件検索を行います。
     *
     * @param bizTranCriteria 取引の検索条件
     * @return 取引群
     */
    @Override
    public BizTrans selectBy(BizTranCriteria bizTranCriteria) {
        return selectBy(bizTranCriteria, defaultOrders);
    }

    /**
     * 取引の全件検索を行います。
     *
     * @param orders オーダー指定
     * @return 取引群
     */
    @Override
    public BizTrans selectAll(Orders orders) {
        return BizTrans.createFrom(bizTranEntityDao.findAll(orders));
    }

    /**
     * 取引の全件検索を行います。
     *
     * @return 取引群
     */
    @Override
    public BizTrans selectAll() {
        return selectAll(defaultOrders);
    }
}
