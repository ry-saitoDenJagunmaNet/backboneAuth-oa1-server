package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition.bizTranGrp;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntity;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * 取引グループ検索
 */
@Component
public class BizTranGrpDataSource implements BizTranGrpRepository {

    private final BizTranGrpEntityDao bizTranGrpEntityDao;

    // コンストラクタ
    BizTranGrpDataSource(BizTranGrpEntityDao bizTranGrpEntityDao) {
        this.bizTranGrpEntityDao = bizTranGrpEntityDao;
    }

    /**
     * 取引グループの条件検索を行います
     *
     * @param bizTranGrpCriteria 取引グループの検索条件
     * @return 取引グループ
     */
    public BizTranGrp findOneBy(BizTranGrpCriteria bizTranGrpCriteria) {

        // 取引グループ検索
        BizTranGrpEntityCriteria entityCriteria = new BizTranGrpEntityCriteria();
        entityCriteria.getBizTranGrpIdCriteria().assignFrom(bizTranGrpCriteria.getBizTranGrpIdCriteria());
        entityCriteria.getBizTranGrpCodeCriteria().assignFrom(bizTranGrpCriteria.getBizTranGrpCodeCriteria());
        entityCriteria.getSubSystemCodeCriteria().assignFrom(bizTranGrpCriteria.getSubSystemCodeCriteria());

        BizTranGrpEntity entity = bizTranGrpEntityDao.findOneBy(entityCriteria);
        return BizTranGrp.createFrom(
            entity.getBizTranGrpId(),
            entity.getBizTranGrpCode(),
            entity.getBizTranGrpName(),
            entity.getSubSystemCode(),
            entity.getRecordVersion(),
            SubSystem.codeOf(entity.getSubSystemCode()));
    }

    /**
     * 取引グループ群の検索を行います
     *
     * @param bizTranGrpCriteria 取引グループの検索条件
     * @param orders             オーダー指定
     * @return 取引グループ群
     */
    public BizTranGrps selectBy(BizTranGrpCriteria bizTranGrpCriteria, Orders orders) {

        // 取引グループ群検索
        BizTranGrpEntityCriteria entityCriteria = new BizTranGrpEntityCriteria();
        entityCriteria.getBizTranGrpIdCriteria().assignFrom(bizTranGrpCriteria.getBizTranGrpIdCriteria());
        entityCriteria.getSubSystemCodeCriteria().assignFrom(bizTranGrpCriteria.getSubSystemCodeCriteria());

        List<BizTranGrp> list = newArrayList();
        for (BizTranGrpEntity entity : bizTranGrpEntityDao.findBy(entityCriteria, orders)) {
            list.add(BizTranGrp.createFrom(
                entity.getBizTranGrpId(),
                entity.getBizTranGrpCode(),
                entity.getBizTranGrpName(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                SubSystem.codeOf(entity.getSubSystemCode())
            ));
        }

        return BizTranGrps.createFrom(list);
    }

    /**
     * 取引グループ群の全件検索を行います
     *
     * @param orders オーダー指定
     * @return 取引グループ群
     */
    public BizTranGrps selectAll(Orders orders) {

        // 取引グループ群検索
        List<BizTranGrp> list = newArrayList();
        for (BizTranGrpEntity entity : bizTranGrpEntityDao.findAll(orders)) {
            list.add(BizTranGrp.createFrom(
                entity.getBizTranGrpId(),
                entity.getBizTranGrpCode(),
                entity.getBizTranGrpName(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                SubSystem.codeOf(entity.getSubSystemCode())
            ));
        }

        return BizTranGrps.createFrom(list);
    }
}
