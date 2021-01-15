package net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * オペレーター履歴ヘッダー検索
 */
public interface OperatorHistoryHeaderRepository {

    /**
     * 対象オペレーターで最新のオペレーター履歴ヘッダーの検索を行います
     *
     * @param operatorId オペレーターID
     * @return 対象オペレーターの中で最新のオペレーター履歴ヘッダー
     */
    OperatorHistoryHeader latestOneByOperatorId(Long operatorId);
    /**
     * オペレーター履歴ヘッダー群の検索を行います
     *
     * @param operatorHistoryHeaderCriteria オペレーター履歴ヘッダーの検索条件
     * @param orders                        オーダー指定
     * @return オペレーター履歴ヘッダー群
     */
    OperatorHistoryHeaders selectBy(OperatorHistoryHeaderCriteria operatorHistoryHeaderCriteria, Orders orders);
}
