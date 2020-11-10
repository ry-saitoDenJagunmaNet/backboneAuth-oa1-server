package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Collection;
import java.util.List;

/**
 * 取引ロール群
 */
public class BizTranRoles {

    private final List<BizTranRole> list = newArrayList();

    // コンストラクタ
    BizTranRoles(Collection<BizTranRole> collection) {
        this.list.addAll(collection);
    }

    /**
     * 取引ロールリストから作成します
     *
     * @param bizTranRoleList 取引ロールリスト
     * @return 取引ロール群
     */
    public static BizTranRoles createFrom(Collection<BizTranRole> bizTranRoleList) {
        return new BizTranRoles(bizTranRoleList);
    }

    /**
     * 取引ロールリストを取得します
     *
     * @return 取引ロールリスト
     */
    public List<BizTranRole> getValues() {
        return list;
    }
}
