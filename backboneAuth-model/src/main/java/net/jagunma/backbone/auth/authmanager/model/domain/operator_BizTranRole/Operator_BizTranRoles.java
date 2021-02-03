package net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import net.jagunma.common.ddd.model.entity2.AbstractEntities2;
import net.jagunma.common.ddd.model.orders.Orders;

/**
 * オペレーター_取引ロール割当群
 */
public class Operator_BizTranRoles extends AbstractEntities2<Operator_BizTranRole, Operator_BizTranRoles> {

    // コンストラクタ
    Operator_BizTranRoles(Collection<Operator_BizTranRole> collection) {
        super(collection);
    }

    /**
     * オペレーター_取引ロール割当リストから作成します
     *
     * @param operator_BizTranRoleList オペレーター_取引ロール割当リスト
     * @return オペレーター_取引ロール割当群
     */
    public static Operator_BizTranRoles createFrom(Collection<Operator_BizTranRole> operator_BizTranRoleList) {
        return new Operator_BizTranRoles(operator_BizTranRoleList);
    }

    /**
     * オペレーター_取引ロール割当リストを取得します
     *
     * @return オペレーター_取引ロール割当リスト
     */
    public List<Operator_BizTranRole> getValues() {
        return super.value;
    }

    /**
     * オペレーター_取引ロール割当群の条件検索を行います
     *
     * @param aPredicate オペレーター_取引ロール割当群の検索条件
     * @return オペレーター_取引ロール割当群
     */
    public Operator_BizTranRoles select(Predicate<Operator_BizTranRole> aPredicate) {
        return Operator_BizTranRoles.createFrom(selectEntitiesBy(aPredicate));
    }

    /**
     * オペレーター_取引ロール割当群の並び替えを行います
     *
     * @param anyOrders ソートする要素名群
     * @return オペレーター_取引ロール割当群
     */
    public Operator_BizTranRoles sortBy(Orders anyOrders) {
        return Operator_BizTranRoles.createFrom(sortBy(anyOrders.toComparator()));
    }
}
