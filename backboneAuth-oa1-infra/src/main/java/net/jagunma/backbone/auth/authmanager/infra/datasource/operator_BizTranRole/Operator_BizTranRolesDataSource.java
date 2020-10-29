package net.jagunma.backbone.auth.authmanager.infra.datasource.operator_BizTranRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRole.BizTranRolesDataSource;
import net.jagunma.backbone.auth.authmanager.infra.datasource.operator.OperatorsDataSource;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRolesRepository;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * オペレーター_取引ロール割当群検索
 */
@Component
public class Operator_BizTranRolesDataSource implements Operator_BizTranRolesRepository {

    private final Operator_BizTranRoleEntityDao operator_BizTranRoleEntityDao;
    private final OperatorsDataSource operatorsDataSource;
    private final BizTranRolesDataSource bizTranRolesDataSource;

    // コンストラクタ
    Operator_BizTranRolesDataSource(Operator_BizTranRoleEntityDao operator_BizTranRoleEntityDao,
        OperatorsDataSource operatorsDataSource,
        BizTranRolesDataSource bizTranRolesDataSource) {

        this.operator_BizTranRoleEntityDao = operator_BizTranRoleEntityDao;
        this.operatorsDataSource = operatorsDataSource;
        this.bizTranRolesDataSource = bizTranRolesDataSource;
    }

    /**
     * オペレーター_取引ロール割当群の検索を行います
     *
     * @param operator_BizTranRoleCriteria オペレーター_取引ロール割当の検索条件
     * @param orders                       オーダー指定
     * @return 取引ロール群
     */
    public Operator_BizTranRoles selectBy(Operator_BizTranRoleCriteria operator_BizTranRoleCriteria, Orders orders) {

        // オペレーター群の検索
        OperatorCriteria operatorCriteria = new OperatorCriteria();
        operatorCriteria.getOperatorIdCriteria().assignFrom(operator_BizTranRoleCriteria.getOperatorIdCriteria());
        Operators operators = operatorsDataSource.selectBy(operatorCriteria, Orders.empty());

        // オペレーター_取引ロール割当群検索
        Operator_BizTranRoleEntityCriteria entityCriteria = new Operator_BizTranRoleEntityCriteria();
        entityCriteria.getOperatorIdCriteria().assignFrom(operatorCriteria.getOperatorIdCriteria());
        entityCriteria.getBizTranRoleIdCriteria().assignFrom(operator_BizTranRoleCriteria.getBizTranRoleIdCriteria());

        entityCriteria.getOperatorIdCriteria().getIncludes().addAll(operator_BizTranRoleCriteria.getOperatorIdCriteria().getIncludes());
        List<Operator_BizTranRoleEntity> OperatorBizTranRoleList = operator_BizTranRoleEntityDao.findBy(entityCriteria, orders);

        // オペレーター_取引ロール割当から取引ロールIDリストを取得
        List<Long> bizTranRoleIdList = OperatorBizTranRoleList.stream().
            map(Operator_BizTranRoleEntity::getBizTranRoleId).distinct().collect(Collectors.toList());

        // 取引ロール群の検索
        BizTranRoleCriteria bizTranRoleCriteria = new BizTranRoleCriteria();
        bizTranRoleCriteria.getBizTranRoleIdCriteria().getIncludes().addAll(bizTranRoleIdList);
        BizTranRoles bizTranRoles = bizTranRolesDataSource.selectBy(bizTranRoleCriteria, Orders.empty());

        List<Operator_BizTranRole> list = newArrayList();
        for (Operator_BizTranRoleEntity entity : OperatorBizTranRoleList) {
            list.add(Operator_BizTranRole.createFrom(
                entity.getOperator_BizTranRoleId(),
                entity.getOperatorId(),
                entity.getBizTranRoleId(),
                entity.getExpirationStartDate(),
                entity.getExpirationEndDate(),
                entity.getRecordVersion(),
                operators.getValues().stream().filter(o->
                    o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null),
                bizTranRoles.getValues().stream().filter(o->
                    o.getBizTranRoleId().equals(entity.getBizTranRoleId())).findFirst().orElse(null)
            ));
        }

        return Operator_BizTranRoles.createFrom(list);
    }
}
