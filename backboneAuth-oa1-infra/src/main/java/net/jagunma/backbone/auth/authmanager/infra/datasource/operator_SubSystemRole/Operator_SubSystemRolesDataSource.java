package net.jagunma.backbone.auth.authmanager.infra.datasource.operator_SubSystemRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorsRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntity;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * オペレーター_サブシステムロール割当群検索
 */
@Component
public class Operator_SubSystemRolesDataSource implements Operator_SubSystemRolesRepository {

    private final Operator_SubSystemRoleEntityDao operator_SubSystemRoleEntityDao;
    private final OperatorsRepository operatorsRepository;

    // コンストラクタ
    Operator_SubSystemRolesDataSource(Operator_SubSystemRoleEntityDao operator_SubSystemRoleEntityDao,
        OperatorsRepository operatorsRepository) {

        this.operator_SubSystemRoleEntityDao = operator_SubSystemRoleEntityDao;
        this.operatorsRepository = operatorsRepository;
    }

    /**
     * オペレーター_サブシステムロール割当群の検索を行います
     *
     * @param operator_SubSystemRoleCriteria オペレーター_サブシステムロール割当の検索条件
     * @param orders                         オーダー指定
     * @return オペレーター_サブシステムロール割当群
     */
    public Operator_SubSystemRoles selectBy(
        Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria, Orders orders) {

        // オペレーター_サブシステムロール割当群の検索
        Operator_SubSystemRoleEntityCriteria entityCriteria = new Operator_SubSystemRoleEntityCriteria();
        entityCriteria.getOperatorIdCriteria().assignFrom(operator_SubSystemRoleCriteria.getOperatorIdCriteria());
        List<Operator_SubSystemRoleEntity> operator_SubSystemRoleEntityList = operator_SubSystemRoleEntityDao.findBy(entityCriteria, orders);

        // オペレーター群の検索
        OperatorCriteria operatorCriteria = new OperatorCriteria();
        operatorCriteria.getOperatorIdCriteria().getIncludes().addAll(
            operator_SubSystemRoleEntityList.stream().map(Operator_SubSystemRoleEntity::getOperatorId).distinct().collect(Collectors.toList()));
        Operators operators = operatorsRepository.selectBy(operatorCriteria, Orders.empty());

        List<Operator_SubSystemRole> list = newArrayList();
        for (Operator_SubSystemRoleEntity entity : operator_SubSystemRoleEntityList) {
            list.add(Operator_SubSystemRole.createFrom(
                entity.getOperator_SubSystemRoleId(),
                entity.getOperatorId(),
                entity.getSubSystemRoleCode(),
                entity.getValidThruStartDate(),
                entity.getValidThruEndDate(),
                entity.getRecordVersion(),
                operators.getValues().stream().filter(o->
                    o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null),
                SubSystemRole.codeOf(entity.getSubSystemRoleCode())
            ));
        }

        return Operator_SubSystemRoles.createFrom(list);
    }
}
