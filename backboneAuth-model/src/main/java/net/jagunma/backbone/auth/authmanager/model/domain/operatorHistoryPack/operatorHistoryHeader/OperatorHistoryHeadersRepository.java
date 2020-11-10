package net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * オペレーター履歴ヘッダー群検索
 */
public interface OperatorHistoryHeadersRepository {

    /**
     * オペレーター履歴ヘッダー群の条件検索を行います
     *
     * @param operatorHistoryHeaderCriteria オペレーター履歴ヘッダーの検索条件
     * @param orders                        オーダー指定
     * @return 取引群
     */
    OperatorHistoryHeaders selectBy(OperatorHistoryHeaderCriteria operatorHistoryHeaderCriteria, Orders orders);
}
