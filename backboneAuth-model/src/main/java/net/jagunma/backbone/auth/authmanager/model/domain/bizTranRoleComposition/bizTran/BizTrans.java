package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Collection;
import java.util.List;

/**
 * 取引群
 */
public class BizTrans {

    private final List<BizTran> list = newArrayList();

    // コンストラクタ
    BizTrans(Collection<BizTran> collection) {
        this.list.addAll(collection);
    }

    /**
     * 取引リストから作成します
     *
     * @param bizTranList 取引リスト
     * @return 取引群
     */
    public static BizTrans createFrom(Collection<BizTran> bizTranList) {
        return new BizTrans(bizTranList);
    }

    /**
     * 取引リストを取得します
     *
     * @return 取引リスト
     */
    public List<BizTran> getValues() {
        return list;
    }
}
