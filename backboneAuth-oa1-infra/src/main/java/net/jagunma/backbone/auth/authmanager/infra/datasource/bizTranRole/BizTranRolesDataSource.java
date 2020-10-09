package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * 取引ロール群検索
 */
@Component
public class BizTranRolesDataSource implements BizTranRolesRepository {

    private final BizTranRoleEntityDao bizTranRoleEntityDao;

    // コンストラクタ
    BizTranRolesDataSource(BizTranRoleEntityDao bizTranRoleEntityDao) {
        this.bizTranRoleEntityDao = bizTranRoleEntityDao;
    }

    /**
     * 取引ロール群の検索を行います。
     *
     * @param bizTranRoleCriteria 取引ロールの検索条件
     * @param orders              オーダー指定
     * @return 取引ロール群
     */
    public BizTranRoles selectBy(BizTranRoleCriteria bizTranRoleCriteria, Orders orders) {

        // 取引ロール検索
        BizTranRoleEntityCriteria entityCriteria = new BizTranRoleEntityCriteria();
        entityCriteria.getBizTranRoleIdCriteria().getIncludes().addAll(bizTranRoleCriteria.getBizTranRoleIdCriteria().getIncludes());

        List<BizTranRole> list = newArrayList();
        for (BizTranRoleEntity entity : bizTranRoleEntityDao.findBy(entityCriteria, orders)) {
            list.add(BizTranRole.createFrom(
                entity.getBizTranRoleId(),
                entity.getBizTranRoleCode(),
                entity.getBizTranRoleName(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                SubSystem.codeOf(entity.getSubSystemCode())
            ));
        }

        return BizTranRoles.createFrom(list);
    }

    /**
     * 取引ロール群の全件検索を行います。
     *
     * @param orders オーダー指定
     * @return 取引ロール群
     */
    public BizTranRoles selectAll(Orders orders) {
        List<BizTranRole> list = newArrayList();
        for (BizTranRoleEntity entity : bizTranRoleEntityDao.findAll(orders)) {
            list.add(BizTranRole.createFrom(
                entity.getBizTranRoleId(),
                entity.getBizTranRoleCode(),
                entity.getBizTranRoleName(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                SubSystem.codeOf(entity.getSubSystemCode())
            ));
        }

        return BizTranRoles.createFrom(list);
    }
}
