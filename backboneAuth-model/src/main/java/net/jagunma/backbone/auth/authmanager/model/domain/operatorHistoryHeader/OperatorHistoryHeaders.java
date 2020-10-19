package net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryHeader;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Collection;
import java.util.List;

/**
 * オペレーター履歴ヘッダー群
 */
public class OperatorHistoryHeaders {

    private final List<OperatorHistoryHeader> list = newArrayList();

    // コンストラクタ
    OperatorHistoryHeaders(Collection<OperatorHistoryHeader> collection) {
        this.list.addAll(collection);
    }

    /**
     * オペレーター履歴ヘッダーリストから作成します。
     *
     * @param operatorHistoryHeaderList オペレーター履歴ヘッダーリスト
     * @return オペレーター履歴ヘッダー群
     */
    public static OperatorHistoryHeaders createFrom(Collection<OperatorHistoryHeader> operatorHistoryHeaderList) {
        return new OperatorHistoryHeaders(operatorHistoryHeaderList);
    }

    /**
     * オペレーター履歴ヘッダーリストを取得します。
     *
     * @return オペレーター履歴ヘッダーリスト
     */
    public List<OperatorHistoryHeader> getValues() {
        return list;
    }
}
