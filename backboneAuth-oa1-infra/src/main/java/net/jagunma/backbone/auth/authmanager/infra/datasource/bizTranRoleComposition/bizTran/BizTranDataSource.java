package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition.bizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntity;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * 取引検索
 */
@Component
public class BizTranDataSource implements BizTranRepository {

    private final BizTranEntityDao bizTranEntityDao;

    // コンストラクタ
    BizTranDataSource(BizTranEntityDao bizTranEntityDao) {
        this.bizTranEntityDao = bizTranEntityDao;
    }

    /**
     * 取引の検索を行います
     *
     * @param bizTranCode 取引コード
     * @return 取引群
     */
    public BizTran findOneByCode(String bizTranCode) {

        // 取引検索
        BizTranEntityCriteria entityCriteria = new BizTranEntityCriteria();
        entityCriteria.getBizTranCodeCriteria().setEqualTo(bizTranCode);

        BizTranEntity entity = bizTranEntityDao.findOneBy(entityCriteria);
        return BizTran.createFrom(
            entity.getBizTranId(),
            entity.getBizTranCode(),
            entity.getBizTranName(),
            entity.getIsCenterBizTran(),
            entity.getValidThruStartDate(),
            entity.getValidThruEndDate(),
            entity.getSubSystemCode(),
            entity.getRecordVersion(),
            SubSystem.codeOf(entity.getSubSystemCode()));
    }

    /**
     * 取引群の検索を行います
     *
     * @param bizTranCriteria 取引の検索条件
     * @param orders          オーダー指定
     * @return 取引群
     */
    public BizTrans selectBy(BizTranCriteria bizTranCriteria, Orders orders) {

        // 取引検索
        BizTranEntityCriteria entityCriteria = new BizTranEntityCriteria();
        entityCriteria.getBizTranIdCriteria().assignFrom(bizTranCriteria.getBizTranIdCriteria());
        entityCriteria.getSubSystemCodeCriteria().assignFrom(bizTranCriteria.getSubSystemCodeCriteria());

        List<BizTran> list = newArrayList();
        for (BizTranEntity entity : bizTranEntityDao.findBy(entityCriteria, orders)) {
            list.add(BizTran.createFrom(
                entity.getBizTranId(),
                entity.getBizTranCode(),
                entity.getBizTranName(),
                entity.getIsCenterBizTran(),
                entity.getValidThruStartDate(),
                entity.getValidThruEndDate(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                SubSystem.codeOf(entity.getSubSystemCode())
            ));
        }

        return BizTrans.createFrom(list);
    }

    /**
     * 取引群の全件検索を行います
     *
     * @param orders オーダー指定
     * @return 取引群
     */
    public BizTrans selectAll(Orders orders) {

        // 取引検索
        List<BizTran> list = newArrayList();
        for (BizTranEntity entity : bizTranEntityDao.findAll(orders)) {
            list.add(BizTran.createFrom(
                entity.getBizTranId(),
                entity.getBizTranCode(),
                entity.getBizTranName(),
                entity.getIsCenterBizTran(),
                entity.getValidThruStartDate(),
                entity.getValidThruEndDate(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                SubSystem.codeOf(entity.getSubSystemCode())
            ));
        }

        return BizTrans.createFrom(list);
    }
}
