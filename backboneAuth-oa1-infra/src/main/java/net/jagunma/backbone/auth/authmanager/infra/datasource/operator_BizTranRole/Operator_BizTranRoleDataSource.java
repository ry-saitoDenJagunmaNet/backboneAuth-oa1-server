package net.jagunma.backbone.auth.authmanager.infra.datasource.operator_BizTranRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * オペレーター_取引ロール割当群検索
 */
@Component
public class Operator_BizTranRoleDataSource implements Operator_BizTranRoleRepository {

    private final Operator_BizTranRoleEntityDao operator_BizTranRoleEntityDao;
    private final OperatorRepository operatorRepository;
    private final BizTranRoleRepository bizTranRoleRepository;

    // コンストラクタ
    Operator_BizTranRoleDataSource(Operator_BizTranRoleEntityDao operator_BizTranRoleEntityDao,
        OperatorRepository operatorRepository,
        BizTranRoleRepository bizTranRoleRepository) {

        this.operator_BizTranRoleEntityDao = operator_BizTranRoleEntityDao;
        this.operatorRepository = operatorRepository;
        this.bizTranRoleRepository = bizTranRoleRepository;
    }

    /**
     * オペレーター_取引ロール割当群の検索を行います
     *
     * @param operator_BizTranRoleCriteria オペレーター_取引ロール割当の検索条件
     * @param orders                       オーダー指定
     * @return 取引ロール群
     */
    public Operator_BizTranRoles selectBy(Operator_BizTranRoleCriteria operator_BizTranRoleCriteria, Orders orders) {

        // オペレーター_取引ロール割当群の検索
        Operator_BizTranRoleEntityCriteria entityCriteria = new Operator_BizTranRoleEntityCriteria();
        entityCriteria.getBizTranRoleIdCriteria().assignFrom(operator_BizTranRoleCriteria.getBizTranRoleIdCriteria());
        entityCriteria.getOperatorIdCriteria().assignFrom(operator_BizTranRoleCriteria.getOperatorIdCriteria());
        List<Operator_BizTranRoleEntity> Operator_BizTranRoleEntityList = operator_BizTranRoleEntityDao.findBy(entityCriteria, orders);

        // 取引ロール群の検索
        BizTranRoleCriteria bizTranRoleCriteria = new BizTranRoleCriteria();
        bizTranRoleCriteria.getBizTranRoleIdCriteria().getIncludes().addAll(
            Operator_BizTranRoleEntityList.stream().map(Operator_BizTranRoleEntity::getBizTranRoleId).distinct().collect(Collectors.toList()));
        BizTranRoles bizTranRoles = bizTranRoleRepository.selectBy(bizTranRoleCriteria, Orders.empty());

        // オペレーター群の検索
        OperatorCriteria operatorCriteria = new OperatorCriteria();
        operatorCriteria.getOperatorIdCriteria().getIncludes().addAll(
            Operator_BizTranRoleEntityList.stream().map(Operator_BizTranRoleEntity::getOperatorId).distinct().collect(Collectors.toList()));
        Operators operators = operatorRepository.selectBy(operatorCriteria, Orders.empty());

        List<Operator_BizTranRole> list = newArrayList();
        for (Operator_BizTranRoleEntity entity : Operator_BizTranRoleEntityList) {
            list.add(Operator_BizTranRole.createFrom(
                entity.getOperator_BizTranRoleId(),
                entity.getOperatorId(),
                entity.getBizTranRoleId(),
                entity.getValidThruStartDate(),
                entity.getValidThruEndDate(),
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
