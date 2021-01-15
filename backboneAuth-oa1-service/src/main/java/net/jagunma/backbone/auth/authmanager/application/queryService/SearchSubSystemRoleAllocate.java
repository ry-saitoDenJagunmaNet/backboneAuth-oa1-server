package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleAllocateReference.SubSystemRoleAllocateSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleAllocateReference.SubSystemRoleAllocateSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;

/**
 * オペレーター_サブシステムロール割当検索サービス
 */
@Service
public class SearchSubSystemRoleAllocate {

    private final Operator_SubSystemRoleRepository operator_SubSystemRoleRepository;

    // コンストラクタ
    public SearchSubSystemRoleAllocate(Operator_SubSystemRoleRepository operator_SubSystemRoleRepository) {
        this.operator_SubSystemRoleRepository = operator_SubSystemRoleRepository;
    }

    /**
     * オペレーター_サブシステムロール割当検索します
     *
     * @param request  オペレーター_サブシステムロール割当検索サービス Request
     * @param response オペレーター_サブシステムロール割当検索サービス Response
     */
    public void execute(SubSystemRoleAllocateSearchRequest request, SubSystemRoleAllocateSearchResponse response) {

        // オペレーターID に null が含まれていたら処理しない
        if (request.getOperatorIdCriteria().getIncludes().contains(null)) {
            response.setOperator_SubSystemRoles(Operator_SubSystemRoles.createFrom(newArrayList()));
            return;
        }

        // オペレーター_サブシステムロール割当群を検索します
        Operator_SubSystemRoles operator_SubSystemRoles = searchOperator_SubSystemRoles(request);

        // Responseへセット
        response.setOperator_SubSystemRoles(operator_SubSystemRoles);
    }

    /**
     * オペレーター_サブシステムロール割当群を検索します
     *
     * @param request  オペレーター_サブシステムロール割当検索サービス Request
     * @return オペレーター_サブシステムロール割当群
     */
    Operator_SubSystemRoles searchOperator_SubSystemRoles(SubSystemRoleAllocateSearchRequest request) {
        Operator_SubSystemRoleCriteria criteria = new Operator_SubSystemRoleCriteria();
        criteria.getOperatorIdCriteria().assignFrom(request.getOperatorIdCriteria());
        return operator_SubSystemRoleRepository.selectBy(criteria, Orders.empty().addOrder("OperatorId").addOrder("Operator_SubSystemRoleId"));
    }
}
