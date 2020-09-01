package net.jagunma.backbone.auth.authmanager.infra.datasource.roleAssignment.operator_SubSystemRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.roleAssignment.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.roleAssignment.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.roleAssignment.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.roleAssignment.operator_SubSystemRole.Operator_SubSystemRolesRepository;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntity;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * オペレーター_サブシステムロール割当検索 DataSource
 */
@Component
public class Operator_SubSystemRolesDataSource {//implements Operator_SubSystemRolesRepository {
//
//    private final Operator_SubSystemRoleEntityDao operator_SubSystemRoleEntityDao;
//    private final SubSystemRolesRepository subSystemRolesRepository;
//    private final Orders defaultOrders = Orders.empty().addOrder("operatorId")
//        .addOrder("subSystemRoleCode").addOrder("expirationStartDate");
//
//    // コンストラクタ
//    public Operator_SubSystemRolesDataSource(
//        Operator_SubSystemRoleEntityDao operator_SubSystemRoleEntityDao,
//        SubSystemRolesRepository subSystemRolesRepository) {
//
//        this.operator_SubSystemRoleEntityDao = operator_SubSystemRoleEntityDao;
//        this.subSystemRolesRepository = subSystemRolesRepository;
//    }
//
//    /**
//     * オペレーター_サブシステムロール割当の条件検索を行います。
//     *
//     * @param operator_SubSystemRoleCriteria オペレーター_サブシステムロール割当の検索条件
//     * @param orders                         オーダー指定
//     * @return オペレーター_サブシステムロール割当群
//     */
//    @Override
//    public Operator_SubSystemRoles selectBy(
//        Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria, Orders orders) {
//        // TODO
////		List<Operator_SubSystemRoleEntity> list = operator_SubSystemRoleEntityDao.findBy(operator_SubSystemRoleCriteria, orders);
////		return Operator_SubSystemRoles.createFrom(list);
//        return Operator_SubSystemRoles.createFrom(newArrayList());
//    }
//
//    /**
//     * オペレーター_サブシステムロール割当の条件検索を行います。
//     *
//     * @param operator_SubSystemRoleCriteria オペレーター_サブシステムロール割当の検索条件
//     * @return オペレーター_サブシステムロール割当群
//     */
//    @Override
//    public Operator_SubSystemRoles selectBy(
//        Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria) {
//        return selectBy(operator_SubSystemRoleCriteria, defaultOrders);
//    }
//
//    /**
//     * オペレーター_ブシステムロール割当群を全件検索します。
//     *
//     * @param orders オーダー指定
//     * @return オペレーター_ブシステムロール割当群
//     */
//    @Override
//    public Operator_SubSystemRoles selectAll(Orders orders) {
//
//        // オペレーター_サブシステムロール割当検索
//        List<Operator_SubSystemRoleEntity> entities = operator_SubSystemRoleEntityDao
//            .findAll(orders);
//
//        // サブシステムロール検索
//        SubSystemRoles subSystemRoles = subSystemRolesRepository.selectAll();
//
//        List<Operator_SubSystemRole> list = newArrayList();
//        entities.forEach(ossr -> {
//            SubSystemRole subSystemRole = subSystemRoles.getValues().stream().filter(sbr ->
//                sbr.getSubSystemRoleCode().equals(ossr.getSubSystemRoleCode())).findAny().get();
//            Operator_SubSystemRole item = Operator_SubSystemRole.createOf(ossr, subSystemRole);
//            list.add(item);
//        });
//
//        return Operator_SubSystemRoles.createFrom(list);
//    }
//
//    /**
//     * オペレーター_ブシステムロール割当群を全件検索します。
//     *
//     * @return オペレーター_ブシステムロール割当群
//     */
//    @Override
//    public Operator_SubSystemRoles selectAll() {
//        return selectAll(defaultOrders);
//    }
}
