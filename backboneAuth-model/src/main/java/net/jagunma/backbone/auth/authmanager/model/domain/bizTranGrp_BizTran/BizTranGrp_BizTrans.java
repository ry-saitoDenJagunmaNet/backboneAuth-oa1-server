package net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp_BizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Collection;
import java.util.List;

/**
 * 取引グループ_取引割当群
 */
public class BizTranGrp_BizTrans {

    private final List<BizTranGrp_BizTran> list = newArrayList();

    // コンストラクタ
    BizTranGrp_BizTrans(Collection<BizTranGrp_BizTran> collection) {
        this.list.addAll(collection);
    }

    /**
     * 取引グループ_取引割当リストから作成します。
     *
     * @param bizTranGrp_BizTransList 取引グループ_取引割当リスト
     * @return 取引グループ_取引割当群
     */
    public static BizTranGrp_BizTrans createFrom(Collection<BizTranGrp_BizTran> bizTranGrp_BizTransList) {
        return new BizTranGrp_BizTrans(bizTranGrp_BizTransList);
    }

    /**
     * 取引グループ_取引割当リストを取得します。
     *
     * @return 取引グループ_取引割当リスト
     */
    public List<BizTranGrp_BizTran> getValues() {
        return list;
    }
}
