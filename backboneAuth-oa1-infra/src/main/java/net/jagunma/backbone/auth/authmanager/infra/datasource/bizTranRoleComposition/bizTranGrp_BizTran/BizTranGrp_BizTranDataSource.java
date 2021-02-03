package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition.bizTranGrp_BizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntity;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.cache.annotation.Cacheable;
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
     * 取引グループ_取引割当群の検索を行います
     *
     * @param bizTranGrp_BizTranCriteria 取引グループ_取引割当の検索条件
     * @param orders                     オーダー指定
     * @return 取引グループ_取引割当群
     */
    public BizTranGrp_BizTrans selectBy(BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria, Orders orders) {

        // 取引グループ_取引割当群検索
        return selectAll().select(bizTranGrp_BizTranCriteria).sortBy(orders);
    }

    /**
     * 取引グループ_取引割当群の全件検索を行います
     *
     * @param orders オーダー指定
     * @return 取引グループ_取引割当群
     */
    public BizTranGrp_BizTrans selectAll(Orders orders) {

        // 取引グループ_取引割当群検索
        return selectAll().sortBy(orders);
    }

    /**
     * 取引グループ_取引割当群の全件検索を行います
     *
     * @return 取引グループ_取引割当群
     */
    @Cacheable("auth_bizTranGrp_BizTran")
    public BizTranGrp_BizTrans selectAll() {

        // 取引グループ_取引割当群検索
        List<BizTranGrp_BizTranEntity> entityList = bizTranGrp_BizTranEntityDao.findAll(Orders.empty());

        // 取引グループ群検索
        BizTranGrps bizTranGrps = bizTranGrpRepository.selectAll(Orders.empty());

        // 取引群検索
        BizTrans bizTrans = bizTranRepository.selectAll(Orders.empty());

        // 取引グループ_取引割当群検索
        List<BizTranGrp_BizTran> list = newArrayList();
        for (BizTranGrp_BizTranEntity entity : entityList) {
            list.add(BizTranGrp_BizTran.createFrom(
                entity.getBizTranGrp_BizTranId(),
                entity.getBizTranGrpId(),
                entity.getBizTranId(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                bizTranGrps.getValues().stream().filter(b->b.getBizTranGrpId().equals(entity.getBizTranGrpId())).findFirst().orElse(null),
                bizTrans.getValues().stream().filter(b->b.getBizTranId().equals(entity.getBizTranId())).findFirst().orElse(null),
                SubSystem.codeOf(entity.getSubSystemCode())
            ));
        }

        return BizTranGrp_BizTrans.createFrom(list);
    }
}
