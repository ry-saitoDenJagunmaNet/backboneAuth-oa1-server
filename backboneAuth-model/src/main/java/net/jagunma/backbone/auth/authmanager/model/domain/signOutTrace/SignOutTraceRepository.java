package net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * サインアウト証跡検索
 */
public interface SignOutTraceRepository {

    /**
     * サインアウト証跡群の条件検索を行います
     *
     * @param signOutTraceCriteria サインアウト証跡の検索条件
     * @param orders               オーダー指定
     * @return サインアウト証跡群
     */
    SignOutTraces selectBy(SignOutTraceCriteria signOutTraceCriteria, Orders orders);
    /**
     * 最新オペレーターのサインアウト証跡群の検索を行います
     *
     * @param signOutTraceCriteria サインアウト証跡の検索条件
     * @return サインアウト証跡群
     */
    SignOutTraces latestBy(SignOutTraceCriteria signOutTraceCriteria, Orders orders);
}
