package net.jagunma.backbone.auth.authmanager.model.domain.jaIpAddressRange;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * JA割当IPアドレス範囲検索
 */
public interface JaIpAddressRangeRepository {

    /**
     * JA割当IPアドレス範囲群の検索を行います
     *
     * @param jaIpAddressRangeCriteria JA割当IPアドレス範囲の検索条件
     * @param orders                   オーダー指定
     * @return JA割当IPアドレス範囲群
     */
    JaIpAddressRanges selectBy(JaIpAddressRangeCriteria jaIpAddressRangeCriteria, Orders orders);
}
