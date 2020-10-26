package net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Collection;
import java.util.List;

/**
 * 取引グループ群
 */
public class BizTranGrps {

    private final List<BizTranGrp> list = newArrayList();

    // コンストラクタ
    BizTranGrps(Collection<BizTranGrp> collection) {
        this.list.addAll(collection);
    }

    /**
     * 取引グループリストから作成します
     *
     * @param bizTranGrpList 取引グループリスト
     * @return 取引グループ群
     */
    public static BizTranGrps createFrom(Collection<BizTranGrp> bizTranGrpList) {
        return new BizTranGrps(bizTranGrpList);
    }

    /**
     * 取引グループリストを取得します
     *
     * @return 取引グループリスト
     */
    public List<BizTranGrp> getValues() {
        return list;
    }
}
