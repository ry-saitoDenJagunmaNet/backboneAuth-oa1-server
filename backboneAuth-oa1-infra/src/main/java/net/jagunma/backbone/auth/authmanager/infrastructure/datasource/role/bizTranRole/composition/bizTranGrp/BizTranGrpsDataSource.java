package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.role.bizTranRole.composition.bizTranGrp;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTranGrp.BizTranGrpsRepository;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntity;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * 取引グループ検索 DataSource
 */
@Component
public class BizTranGrpsDataSource implements BizTranGrpsRepository {

    private final BizTranGrpEntityDao bizTranGrpEntityDao;
    private final Orders defaultOrders = Orders.empty().addOrder("bizTranGrpCode");

    // コンストラクタ
    BizTranGrpsDataSource(BizTranGrpEntityDao bizTranGrpEntityDao) {
        this.bizTranGrpEntityDao = bizTranGrpEntityDao;
    }

    /**
     * 取引グループの条件検索を行います。
     *
     * @param bizTranGrpCriteria 取引グループの検索条件
     * @param orders             オーダー指定
     * @return 取引グループ群
     */
    @Override
    public BizTranGrps selectBy(BizTranGrpCriteria bizTranGrpCriteria, Orders orders) {
        List<BizTranGrpEntity> list = bizTranGrpEntityDao.findBy(bizTranGrpCriteria, orders);
        return BizTranGrps.createFrom(list);
    }

    /**
     * 取引グループの条件検索を行います。
     *
     * @param bizTranGrpCriteria 取引グループの検索条件
     * @return 取引グループ群
     */
    @Override
    public BizTranGrps selectBy(BizTranGrpCriteria bizTranGrpCriteria) {
        return selectBy(bizTranGrpCriteria, defaultOrders);
    }

    /**
     * 取引グループの全件検索を行います。
     *
     * @param orders オーダー指定
     * @return 取引グループ群
     */
    @Override
    public BizTranGrps selectAll(Orders orders) {
        return BizTranGrps.createFrom(bizTranGrpEntityDao.findAll(orders));
    }

    /**
     * 取引グループの全件検索を行います。
     *
     * @return 取引グループ群
     */
    @Override
    public BizTranGrps selectAll() {
        return selectAll(defaultOrders);
    }
}
