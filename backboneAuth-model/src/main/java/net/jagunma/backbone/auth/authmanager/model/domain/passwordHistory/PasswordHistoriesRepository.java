package net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * パスワード履歴群検索
 */
public interface PasswordHistoriesRepository {

    /**
     * パスワード履歴群の条件検索を行います
     *
     * @param passwordHistoryCriteria パスワード履歴の検索条件
     * @param orders                  オーダー指定
     * @return パスワード履歴群
     */
    PasswordHistories selectBy(PasswordHistoryCriteria passwordHistoryCriteria, Orders orders);
}
