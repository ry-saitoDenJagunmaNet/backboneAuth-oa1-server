package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import net.jagunma.common.ddd.model.entity2.AbstractEntities2;
import net.jagunma.common.ddd.model.orders.Orders;

/**
 * 取引グループ_取引割当群
 */
public class BizTranGrp_BizTrans extends AbstractEntities2<BizTranGrp_BizTran, BizTranGrp_BizTrans> {

    // コンストラクタ
    BizTranGrp_BizTrans(Collection<BizTranGrp_BizTran> collection) {
        super(collection);
    }

    /**
     * 取引グループ_取引割当リストから作成します
     *
     * @param bizTranGrp_BizTransList 取引グループ_取引割当リスト
     * @return 取引グループ_取引割当群
     */
    public static BizTranGrp_BizTrans createFrom(Collection<BizTranGrp_BizTran> bizTranGrp_BizTransList) {
        return new BizTranGrp_BizTrans(bizTranGrp_BizTransList);
    }

    /**
     * 取引グループ_取引割当リストを取得します
     *
     * @return 取引グループ_取引割当リスト
     */
    public List<BizTranGrp_BizTran> getValues() {
        return super.value;
    }

    /**
     * 取引グループ_取引割当群の条件検索を行います
     *
     * @param aPredicate 取引グループ_取引割当群の検索条件
     * @return 取引グループ_取引割当群
     */
    public BizTranGrp_BizTrans select(Predicate<BizTranGrp_BizTran> aPredicate) {
        return BizTranGrp_BizTrans.createFrom(selectEntitiesBy(aPredicate));
    }

    /**
     * 取引グループ_取引割当群の並び替えを行います
     *
     * @param anyOrders ソートする要素名群
     * @return 取引グループ_取引割当群
     */
    public BizTranGrp_BizTrans sortBy(Orders anyOrders) {
        return BizTranGrp_BizTrans.createFrom(sortBy(anyOrders.toComparator()));
    }
}
