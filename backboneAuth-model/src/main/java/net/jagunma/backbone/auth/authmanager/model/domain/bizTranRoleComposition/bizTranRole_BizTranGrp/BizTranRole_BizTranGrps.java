package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import net.jagunma.common.ddd.model.entity2.AbstractEntities2;
import net.jagunma.common.ddd.model.orders.Orders;

/**
 * 取引ロール_取引グループ割当群
 */
public class BizTranRole_BizTranGrps extends AbstractEntities2<BizTranRole_BizTranGrp, BizTranRole_BizTranGrps> {

    // コンストラクタ
    BizTranRole_BizTranGrps(Collection<BizTranRole_BizTranGrp> collection) {
        super(collection);
    }

    /**
     * 取引ロール_取引グループ割当リストから作成します
     *
     * @param bizTranRole_BizTranGrpList 取引ロール_取引グループ割当リスト
     * @return 取引ロール_取引グループ割当群
     */
    public static BizTranRole_BizTranGrps createFrom(Collection<BizTranRole_BizTranGrp> bizTranRole_BizTranGrpList) {
        return new BizTranRole_BizTranGrps(bizTranRole_BizTranGrpList);
    }

    /**
     * 取引ロール_取引グループ割当リストを取得します
     *
     * @return 取引ロール_取引グループリスト
     */
    public List<BizTranRole_BizTranGrp> getValues() {
        return super.value;
    }

    /**
     * 取引ロール_取引グループ割当群の条件検索を行います
     *
     * @param aPredicate 取引ロール_取引グループ割当群の検索条件
     * @return 取引ロール_取引グループ割当群
     */
    public BizTranRole_BizTranGrps select(Predicate<BizTranRole_BizTranGrp> aPredicate) {
        return BizTranRole_BizTranGrps.createFrom(selectEntitiesBy(aPredicate));
    }

    /**
     * 取引ロール_取引グループ割当群の並び替えを行います
     *
     * @param anyOrders ソートする要素名群
     * @return 取引ロール_取引グループ割当群
     */
    public BizTranRole_BizTranGrps sortBy(Orders anyOrders) {
        return BizTranRole_BizTranGrps.createFrom(sortBy(anyOrders.toComparator()));
    }
}
