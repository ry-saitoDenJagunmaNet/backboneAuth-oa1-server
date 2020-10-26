package net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * サインアウト証跡群検索
 */
public interface SignOutTracesRepository {

    /**
     * サインアウト証跡群の条件検索を行います
     *
     * @param signOutTraceCriteria サインアウト証跡の検索条件
     * @param orders               オーダー指定
     * @return サインアウト証跡群
     */
    SignOutTraces selectBy(SignOutTraceCriteria signOutTraceCriteria, Orders orders);
}
