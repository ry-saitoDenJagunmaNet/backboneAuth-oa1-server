package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition.bizTranRole_BizTranGrp;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 取引ロール_取引グループ割当検索
 */
@Component
public class BizTranRole_BizTranGrpDataSource implements BizTranRole_BizTranGrpRepository {

    private final BizTranRole_BizTranGrpEntityDao bizTranRole_BizTranGrpEntityDao;
    private final BizTranRoleRepository bizTranRoleRepository;
    private final BizTranGrpRepository bizTranGrpRepository;

    // コンストラクタ
    BizTranRole_BizTranGrpDataSource(BizTranRole_BizTranGrpEntityDao bizTranRole_BizTranGrpEntityDao,
        BizTranRoleRepository bizTranRoleRepository,
        BizTranGrpRepository bizTranGrpRepository) {

        this.bizTranRole_BizTranGrpEntityDao = bizTranRole_BizTranGrpEntityDao;
        this.bizTranRoleRepository = bizTranRoleRepository;
        this.bizTranGrpRepository = bizTranGrpRepository;
    }

    /**
     * 取引ロール_取引グループ割当群の検索を行います
     *
     * @param bizTranRole_BizTranGrpCriteria 取引ロール_取引グループ割当の検索条件
     * @param orders                         オーダー指定
     * @return 取引ロール_取引グループ割当群
     */
    public BizTranRole_BizTranGrps selectBy(BizTranRole_BizTranGrpCriteria bizTranRole_BizTranGrpCriteria, Orders orders) {

        // 取引ロール_取引グループ割当群検索
        return selectAll().select(bizTranRole_BizTranGrpCriteria).sortBy(orders);
    }

    /**
     * 取引ロール_取引グループ割当群の全件検索を行います
     *
     * @param orders オーダー指定
     * @return 取引ロール_取引グループ割当群
     */
    public BizTranRole_BizTranGrps selectAll(Orders orders) {

        // 取引ロール_取引グループ割当群検索
        return selectAll().sortBy(orders);
    }

    /**
     * 取引ロール_取引グループ割当群の全件検索を行います
     *
     * @return 取引ロール_取引グループ割当群
     */
    @Cacheable("auth_bizTranRole_BizTranGrp")
    public BizTranRole_BizTranGrps selectAll() {

        List<BizTranRole_BizTranGrpEntity> entityList = bizTranRole_BizTranGrpEntityDao.findAll(Orders.empty());

        // 取引ロール群検索
        BizTranRoles bizTranRoles = bizTranRoleRepository.selectAll(Orders.empty());

        // 取引グループ群検索
        BizTranGrps bizTranGrps = bizTranGrpRepository.selectAll(Orders.empty());

        // 取引ロール_取引グループ割当群検索
        List<BizTranRole_BizTranGrp> list = newArrayList();
        for (BizTranRole_BizTranGrpEntity entity : entityList) {
            list.add(BizTranRole_BizTranGrp.createFrom(
                entity.getBizTranRole_BizTranGrpId(),
                entity.getBizTranRoleId(),
                entity.getBizTranGrpId(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                bizTranRoles.getValues().stream().filter(b->b.getBizTranRoleId().equals(entity.getBizTranRoleId())).findFirst().orElse(null),
                bizTranGrps.getValues().stream().filter(b->b.getBizTranGrpId().equals(entity.getBizTranGrpId())).findFirst().orElse(null),
                SubSystem.codeOf(entity.getSubSystemCode())
            ));
        }

        return BizTranRole_BizTranGrps.createFrom(list);
    }
}
