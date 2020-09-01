package net.jagunma.backbone.auth.authmanager.infra.datasource.role.bizTranRole.composition.bizTranRole_BizTranGrp;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpsRepository;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * 取引ロール_取引グループ割当検索 DataSource
 */
@Component
public class BizTranRole_BizTranGrpsDataSource implements BizTranRole_BizTranGrpsRepository {

    private final BizTranRole_BizTranGrpEntityDao bizTranRole_BizTranGrpEntityDao;
    private final Orders defaultOrders = Orders.empty().addOrder("bizTranRoleId")
        .addOrder("bizTranGrpId");

    // コンストラクタ
    BizTranRole_BizTranGrpsDataSource(
        BizTranRole_BizTranGrpEntityDao bizTranRole_BizTranGrpEntityDao) {
        this.bizTranRole_BizTranGrpEntityDao = bizTranRole_BizTranGrpEntityDao;
    }

    /**
     * 取引ロール_取引グループ割当の条件検索を行います。
     *
     * @param bizTranRole_BizTranGrpCriteria 取引ロール_取引グループ割当の検索条件
     * @param orders                         オーダー指定
     * @return 取引ロール_取引グループ割当群
     */
    @Override
    public BizTranRole_BizTranGrps selectBy(
        BizTranRole_BizTranGrpCriteria bizTranRole_BizTranGrpCriteria, Orders orders) {
        List<BizTranRole_BizTranGrpEntity> list = bizTranRole_BizTranGrpEntityDao
            .findBy(bizTranRole_BizTranGrpCriteria, orders);
        return BizTranRole_BizTranGrps.createFrom(list);
    }

    /**
     * 取引ロール_取引グループ割当の条件検索を行います。
     *
     * @param bizTranRole_BizTranGrpCriteria 取引ロール_取引グループ割当の検索条件
     * @return 取引ロール_取引グループ割当群
     */
    @Override
    public BizTranRole_BizTranGrps selectBy(
        BizTranRole_BizTranGrpCriteria bizTranRole_BizTranGrpCriteria) {
        return selectBy(bizTranRole_BizTranGrpCriteria, defaultOrders);
    }

    /**
     * 取引ロール_取引グループ割当の全件検索を行います。
     *
     * @param orders オーダー指定
     * @return 取引ロール_取引グループ割当群
     */
    @Override
    public BizTranRole_BizTranGrps selectAll(Orders orders) {
        return BizTranRole_BizTranGrps.createFrom(bizTranRole_BizTranGrpEntityDao.findAll(orders));
    }

    /**
     * 取引ロール_取引グループ割当の全件検索を行います。
     *
     * @return 取引ロール_取引グループ割当群
     */
    @Override
    public BizTranRole_BizTranGrps selectAll() {
        return selectAll(defaultOrders);
    }
}
