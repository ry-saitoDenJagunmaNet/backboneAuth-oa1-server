package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition.bizTranRole_BizTranGrp;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpsRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpsRepository;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * 取引ロール_取引グループ割当群検索
 */
@Component
public class BizTranRole_BizTranGrpsDataSource implements BizTranRole_BizTranGrpsRepository {

    private final BizTranRole_BizTranGrpEntityDao bizTranRole_BizTranGrpEntityDao;
    private final BizTranRolesRepository bizTranRolesRepository;
    private final BizTranGrpsRepository bizTranGrpsRepository;

    // コンストラクタ
    BizTranRole_BizTranGrpsDataSource(BizTranRole_BizTranGrpEntityDao bizTranRole_BizTranGrpEntityDao,
        BizTranRolesRepository bizTranRolesRepository,
        BizTranGrpsRepository bizTranGrpsRepository) {

        this.bizTranRole_BizTranGrpEntityDao = bizTranRole_BizTranGrpEntityDao;
        this.bizTranRolesRepository = bizTranRolesRepository;
        this.bizTranGrpsRepository = bizTranGrpsRepository;
    }

    /**
     * 取引ロール_取引グループ割当群の検索を行います
     *
     * @param bizTranRole_BizTranGrpCriteria 取引ロール_取引グループ割当の検索条件
     * @param orders                         オーダー指定
     * @return 取引ロール_取引グループ割当群
     */
    public BizTranRole_BizTranGrps selectBy(BizTranRole_BizTranGrpCriteria bizTranRole_BizTranGrpCriteria, Orders orders) {

        // 取引ロール群検索
        BizTranRoleCriteria bizTranRoleCriteria = new BizTranRoleCriteria();
        bizTranRoleCriteria.getSubSystemCodeCriteria().assignFrom(bizTranRole_BizTranGrpCriteria.getSubSystemCodeCriteria());
        BizTranRoles bizTranRoles = bizTranRolesRepository.selectBy(bizTranRoleCriteria, Orders.empty().addOrder("BizTranRoleCode"));

        // 取引グループ群検索
        BizTranGrpCriteria bizTranGrpCriteria = new BizTranGrpCriteria();
        bizTranGrpCriteria.getSubSystemCodeCriteria().assignFrom(bizTranRole_BizTranGrpCriteria.getSubSystemCodeCriteria());
        BizTranGrps bizTranGrps = bizTranGrpsRepository.selectBy(bizTranGrpCriteria, Orders.empty().addOrder("BizTranGrpCode"));

        // 取引ロール_取引グループ割当群検索
        BizTranRole_BizTranGrpEntityCriteria entityCriteria = new BizTranRole_BizTranGrpEntityCriteria();
        entityCriteria.getSubSystemCodeCriteria().assignFrom(bizTranRole_BizTranGrpCriteria.getSubSystemCodeCriteria());

        List<BizTranRole_BizTranGrp> list = newArrayList();
        for (BizTranRole_BizTranGrpEntity entity : bizTranRole_BizTranGrpEntityDao.findBy(entityCriteria, orders)) {
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

    /**
     * 取引ロール_取引グループ割当群の全件検索を行います
     *
     * @param orders オーダー指定
     * @return 取引ロール_取引グループ割当群
     */
    public BizTranRole_BizTranGrps selectAll(Orders orders) {

        // 取引ロール群検索
        BizTranRoles bizTranRoles = bizTranRolesRepository.selectAll(Orders.empty().addOrder("BizTranRoleCode"));

        // 取引グループ群検索
        BizTranGrps bizTranGrps = bizTranGrpsRepository.selectAll(Orders.empty().addOrder("BizTranGrpCode"));

        // 取引ロール_取引グループ割当群検索
        List<BizTranRole_BizTranGrp> list = newArrayList();
        for (BizTranRole_BizTranGrpEntity entity : bizTranRole_BizTranGrpEntityDao.findAll(orders)) {
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
