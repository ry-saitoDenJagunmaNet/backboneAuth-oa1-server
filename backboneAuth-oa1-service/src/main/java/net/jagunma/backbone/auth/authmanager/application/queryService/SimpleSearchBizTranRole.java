package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;

/**
 * 取引ロール検索サービス
 */
@Service
public class SimpleSearchBizTranRole {

    private final BizTranRoleRepository bizTranRoleRepository;

    // コンストラクタ
    public SimpleSearchBizTranRole(BizTranRoleRepository bizTranRoleRepository) {
        this.bizTranRoleRepository = bizTranRoleRepository;
    }

    /**
     * 取引ロール群を取得します
     *
     * @return 取引ロール群
     */
    public BizTranRoles getBizTranRoles() {
        return bizTranRoleRepository.selectAll(Orders.empty().addOrder("BizTranRoleCode"));
    }
}
