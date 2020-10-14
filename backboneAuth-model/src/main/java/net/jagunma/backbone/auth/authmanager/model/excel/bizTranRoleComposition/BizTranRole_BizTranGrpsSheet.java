package net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Collection;
import java.util.List;

/**
 * 取引ロール＋取引グループ群
 */
public class BizTranRole_BizTranGrpsSheet {

    private final List<BizTranRole_BizTranGrpSheet> list = newArrayList();

    // コンストラクタ
    BizTranRole_BizTranGrpsSheet(Collection<BizTranRole_BizTranGrpSheet> collection) {
        this.list.addAll(collection);
    }

    /**
     * 取引ロール編成リストから作成します。
     *
     * @param bizTranRoleOrganizationList 取引ロール編成リスト
     * @return 取引ロール編成群
     */
    public static BizTranRole_BizTranGrpsSheet createFrom(Collection<BizTranRole_BizTranGrpSheet> bizTranRoleOrganizationList) {
        return new BizTranRole_BizTranGrpsSheet(bizTranRoleOrganizationList);
    }

    /**
     * 取引ロール編成リストを取得します。
     *
     * @return 取引ロール編成リスト
     */
    public List<BizTranRole_BizTranGrpSheet> getValues() {
        return list;
    }
}
