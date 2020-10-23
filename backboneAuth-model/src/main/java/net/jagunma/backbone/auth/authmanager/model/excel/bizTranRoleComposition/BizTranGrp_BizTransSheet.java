package net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Collection;
import java.util.List;

/**
 * 取引グループ－取引編成群
 */
public class BizTranGrp_BizTransSheet {
    private final List<BizTranGrp_BizTranSheet> list = newArrayList();

    // コンストラクタ
    BizTranGrp_BizTransSheet(Collection<BizTranGrp_BizTranSheet> collection) {
        this.list.addAll(collection);
    }

    /**
     * 取引グループ－取引編成リストから作成します。
     *
     * @param bizTranGrp_BizTranSheetList 取引グループ－取引編成リスト
     * @return 取引グループ－取引編成群
     */
    public static BizTranGrp_BizTransSheet createFrom(Collection<BizTranGrp_BizTranSheet> bizTranGrp_BizTranSheetList) {
        return new BizTranGrp_BizTransSheet(bizTranGrp_BizTranSheetList);
    }

    /**
     * 取引グループ－取引編成リストを取得します。
     *
     * @return 取引グループ－取引編成リスト
     */
    public List<BizTranGrp_BizTranSheet> getValues() {
        return list;
    }
}
