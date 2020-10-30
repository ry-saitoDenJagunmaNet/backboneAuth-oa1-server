package net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operator_BizTranRoleHistory;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Collection;
import java.util.List;

/**
 * オペレーター_取引ロール割当履歴群
 */
public class Operator_BizTranRoleHistories {

    private final List<Operator_BizTranRoleHistory> list = newArrayList();

    // コンストラクタ
    Operator_BizTranRoleHistories(Collection<Operator_BizTranRoleHistory> collection) {
        this.list.addAll(collection);
    }

    /**
     * オペレーター_取引ロール割当履歴リストから作成します
     *
     * @param operator_BizTranRoleHistoryList オペレーター_取引ロール割当履歴リスト
     * @return オペレーター_取引ロール割当履歴群
     */
    public static Operator_BizTranRoleHistories createFrom(Collection<Operator_BizTranRoleHistory> operator_BizTranRoleHistoryList) {
        return new Operator_BizTranRoleHistories(operator_BizTranRoleHistoryList);
    }

    /**
     * オペレーター_取引ロール割当履歴リストを取得します
     *
     * @return オペレーター_取引ロール割当履歴リスト
     */
    public List<Operator_BizTranRoleHistory> getValues() {
        return list;
    }
}
