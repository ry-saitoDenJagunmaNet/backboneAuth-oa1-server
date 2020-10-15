package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRolesRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;

/**
 * 取引ロール検索サービス
 */
@Service
public class BizTranRoleReference {

    private final BizTranRolesRepository bizTranRolesRepository;

    // コンストラクタ
    BizTranRoleReference(BizTranRolesRepository bizTranRolesRepository) {
        this.bizTranRolesRepository = bizTranRolesRepository;
    }

    /**
     * 取引ロール群を取得します。
     *
     * @return 取引ロール群
     */
    public BizTranRoles getBizTranRoles() {
        return bizTranRolesRepository.selectAll(Orders.empty().addOrder("BizTranRoleCode"));
    }
}