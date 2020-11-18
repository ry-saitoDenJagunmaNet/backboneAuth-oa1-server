package net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Collection;
import java.util.List;

/**
 * 一時取引抑止群
 */
public class SuspendBizTrans {

    private final List<SuspendBizTran> list = newArrayList();

    // コンストラクタ
    SuspendBizTrans(Collection<SuspendBizTran> collection) {
        this.list.addAll(collection);
    }

    /**
     * 一時取引抑止リストから作成します
     *
     * @param suspendBizTranList 一時取引抑止リスト
     * @return 一時取引抑止群
     */
    public static SuspendBizTrans createFrom(Collection<SuspendBizTran> suspendBizTranList) {
        return new SuspendBizTrans(suspendBizTranList);
    }

    /**
     * 一時取引抑止リストを取得します
     *
     * @return 一時取引抑止リスト
     */
    public List<SuspendBizTran> getValues() {
        return list;
    }
}
