package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Collection;
import java.util.List;

/**
 * 取引ロール_取引グループ割当群
 */
public class BizTranRole_BizTranGrps {

    private final List<BizTranRole_BizTranGrp> list = newArrayList();

    // コンストラクタ
    BizTranRole_BizTranGrps(Collection<BizTranRole_BizTranGrp> collection) {
        this.list.addAll(collection);
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
        return list;
    }
}
