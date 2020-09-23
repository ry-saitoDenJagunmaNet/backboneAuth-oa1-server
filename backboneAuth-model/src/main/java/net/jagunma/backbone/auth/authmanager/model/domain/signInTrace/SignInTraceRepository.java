package net.jagunma.backbone.auth.authmanager.model.domain.signInTrace;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * サインイン証跡群検索
 */
public interface SignInTraceRepository {

    /**
     * サインイン証跡群の条件検索を行います。
     *
     * @param signInTraceCriteria サインイン証跡の検索条件
     * @param orders              オーダー指定
     * @return サインイン証跡群
     */
    SignInTraces selectBy(SignInTraceCriteria signInTraceCriteria, Orders orders);
}
