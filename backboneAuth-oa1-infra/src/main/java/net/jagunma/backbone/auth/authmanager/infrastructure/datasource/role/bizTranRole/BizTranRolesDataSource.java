package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.role.bizTranRole;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.BizTranRolesRepository;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * 取引ロール検索 DataSource
 */
@Component
public class BizTranRolesDataSource implements BizTranRolesRepository {

    private final BizTranRoleEntityDao bizTranRoleEntityDao;
    private final Orders defaultOrders = Orders.empty().addOrder("bizTranRoleCode");

    // コンストラクタ
    BizTranRolesDataSource(BizTranRoleEntityDao bizTranRoleEntityDao) {
        this.bizTranRoleEntityDao = bizTranRoleEntityDao;
    }

    /**
     * 取引ロールの条件検索を行います。
     *
     * @param bizTranRoleCriteria 取引ロールの検索条件
     * @param orders              オーダー指定
     * @return 取引ロール群
     */
    @Override
    public BizTranRoles selectBy(BizTranRoleCriteria bizTranRoleCriteria, Orders orders) {
        List<BizTranRoleEntity> list = bizTranRoleEntityDao.findBy(bizTranRoleCriteria, orders);
        return BizTranRoles.createFrom(list);
    }

    /**
     * 取引ロールの条件検索を行います。
     *
     * @param bizTranRoleCriteria 取引ロールの検索条件
     * @return 取引ロール群
     */
    @Override
    public BizTranRoles selectBy(BizTranRoleCriteria bizTranRoleCriteria) {
        return selectBy(bizTranRoleCriteria, defaultOrders);
    }

    /**
     * 取引ロールの全件検索を行います。
     *
     * @param orders オーダー指定
     * @return 取引ロール群
     */
    @Override
    public BizTranRoles selectAll(Orders orders) {
        return BizTranRoles.createFrom(bizTranRoleEntityDao.findAll(orders));
    }

    /**
     * 取引ロールの全件検索を行います。
     *
     * @return 取引ロール群
     */
    @Override
    public BizTranRoles selectAll() {
        return selectAll(defaultOrders);
    }
}
