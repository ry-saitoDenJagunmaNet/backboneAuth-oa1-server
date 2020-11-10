package net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operator_SubSystemRoleHistory;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Collection;
import java.util.List;

/**
 * オペレーター_サブシステムロール割当履歴群
 */
public class Operator_SubSystemRoleHistories {

    private final List<Operator_SubSystemRoleHistory> list = newArrayList();

    // コンストラクタ
    Operator_SubSystemRoleHistories(Collection<Operator_SubSystemRoleHistory> collection) {
        this.list.addAll(collection);
    }

    /**
     * オペレーター_サブシステムロール割当履歴リストから作成します
     *
     * @param operator_SubSystemRoleHistoryList オペレーター_サブシステムロール割当履歴リスト
     * @return オペレーター_サブシステムロール割当履歴群
     */
    public static Operator_SubSystemRoleHistories createFrom(Collection<Operator_SubSystemRoleHistory> operator_SubSystemRoleHistoryList) {
        return new Operator_SubSystemRoleHistories(operator_SubSystemRoleHistoryList);
    }

    /**
     * オペレーター_サブシステムロール割当履歴リストを取得します
     *
     * @return オペレーター_サブシステムロール割当履歴リスト
     */
    public List<Operator_SubSystemRoleHistory> getValues() {
        return list;
    }
}
