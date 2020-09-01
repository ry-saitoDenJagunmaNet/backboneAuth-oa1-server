package net.jagunma.backbone.auth.authmanager.infra.datasource.role.bizTranRole.composition.bizTranGrp_BizTran;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTranGrp_BizTran.BizTranGrp_BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTranGrp_BizTran.BizTranGrp_BizTransRepository;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntity;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * 取引グループ_取引割当検索 DataSource
 */
@Component
public class BizTranGrp_BizTransDataSource implements BizTranGrp_BizTransRepository {

    private final BizTranGrp_BizTranEntityDao bizTranGrp_BizTranEntityDao;
    private final Orders defaultOrders = Orders.empty().addOrder("bizTranGrpId")
        .addOrder("bizTranId");

    // コンストラクタ
    BizTranGrp_BizTransDataSource(BizTranGrp_BizTranEntityDao bizTranGrp_BizTranEntityDao) {
        this.bizTranGrp_BizTranEntityDao = bizTranGrp_BizTranEntityDao;
    }

    /**
     * 取引グループ_取引割当の条件検索を行います。
     *
     * @param bizTranGrp_BizTranCriteria 取引グループ_取引割当の検索条件
     * @param orders                     オーダー指定
     * @return 取引グループ_取引割当群
     */
    @Override
    public BizTranGrp_BizTrans selectBy(BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria,
        Orders orders) {
        List<BizTranGrp_BizTranEntity> list = bizTranGrp_BizTranEntityDao
            .findBy(bizTranGrp_BizTranCriteria, orders);
        return BizTranGrp_BizTrans.createFrom(list);
    }

    /**
     * 取引グループ_取引割当の条件検索を行います。
     *
     * @param bizTranGrp_BizTranCriteria 取引グループ_取引割当の検索条件
     * @return 取引グループ_取引割当群
     */
    @Override
    public BizTranGrp_BizTrans selectBy(BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria) {
        return selectBy(bizTranGrp_BizTranCriteria, defaultOrders);
    }

    /**
     * 取引グループ_取引割当の全件検索を行います。
     *
     * @param orders オーダー指定
     * @return 取引グループ_取引割当群
     */
    @Override
    public BizTranGrp_BizTrans selectAll(Orders orders) {
        return BizTranGrp_BizTrans.createFrom(bizTranGrp_BizTranEntityDao.findAll(orders));
    }

    /**
     * 取引グループ_取引割当の全件検索を行います。
     *
     * @return 取引グループ_取引割当群
     */
    @Override
    public BizTranGrp_BizTrans selectAll() {
        return selectAll(defaultOrders);
    }
}
