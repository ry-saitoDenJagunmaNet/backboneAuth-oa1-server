package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition.bizTranGrp_BizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTransRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpsRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTransRepository;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntity;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * 取引グループ_取引割当群検索
 */
@Component
public class BizTranGrp_BizTransDataSource implements BizTranGrp_BizTransRepository {

    private final BizTranGrp_BizTranEntityDao bizTranGrp_BizTranEntityDao;
    private final BizTranGrpsRepository bizTranGrpsRepository;
    private final BizTransRepository bizTransRepository;

    // コンストラクタ
    BizTranGrp_BizTransDataSource(BizTranGrp_BizTranEntityDao bizTranGrp_BizTranEntityDao,
        BizTranGrpsRepository bizTranGrpsRepository,
        BizTransRepository bizTransRepository) {

        this.bizTranGrp_BizTranEntityDao = bizTranGrp_BizTranEntityDao;
        this.bizTranGrpsRepository = bizTranGrpsRepository;
        this.bizTransRepository = bizTransRepository;
    }

    /**
     * 取引グループ_取引割当群の検索を行います
     *
     * @param bizTranGrp_BizTranCriteria 取引グループ_取引割当の検索条件
     * @param orders                     オーダー指定
     * @return 取引グループ_取引割当群
     */
    public BizTranGrp_BizTrans selectBy(BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria, Orders orders) {

        // 取引グループ群検索
        BizTranGrpCriteria bizTranGrpCriteria = new BizTranGrpCriteria();
        bizTranGrpCriteria.getSubSystemCodeCriteria().setEqualTo(bizTranGrp_BizTranCriteria.getSubSystemCodeCriteria().getEqualTo());
        BizTranGrps bizTranGrps = bizTranGrpsRepository.selectBy(bizTranGrpCriteria, Orders.empty());

        // 取引群検索
        BizTranCriteria bizTranCriteria = new BizTranCriteria();
        bizTranCriteria.getSubSystemCodeCriteria().setEqualTo(bizTranGrp_BizTranCriteria.getSubSystemCodeCriteria().getEqualTo());
        BizTrans bizTrans = bizTransRepository.selectBy(bizTranCriteria, Orders.empty());

        // 取引グループ_取引割当群検索
        BizTranGrp_BizTranEntityCriteria entityCriteria = new BizTranGrp_BizTranEntityCriteria();
        entityCriteria.getSubSystemCodeCriteria().setEqualTo(bizTranGrp_BizTranCriteria.getSubSystemCodeCriteria().getEqualTo());

        List<BizTranGrp_BizTran> list = newArrayList();
        for (BizTranGrp_BizTranEntity entity : bizTranGrp_BizTranEntityDao.findBy(entityCriteria, orders)) {
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

    /**
     * 取引グループ_取引割当群の全件検索を行います
     *
     * @param orders オーダー指定
     * @return 取引グループ_取引割当群
     */
    public BizTranGrp_BizTrans selectAll(Orders orders) {

        // 取引グループ群検索
        BizTranGrps bizTranGrps = bizTranGrpsRepository.selectAll(Orders.empty().addOrder("BizTranGrpCode"));

        // 取引群検索
        BizTrans bizTrans = bizTransRepository.selectAll(Orders.empty().addOrder("BizTranGrpCode"));

        // 取引グループ_取引割当群検索
        List<BizTranGrp_BizTran> list = newArrayList();
        for (BizTranGrp_BizTranEntity entity : bizTranGrp_BizTranEntityDao.findAll(orders)) {
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
