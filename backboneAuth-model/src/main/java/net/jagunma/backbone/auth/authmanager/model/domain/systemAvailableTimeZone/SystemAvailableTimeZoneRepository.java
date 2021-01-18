package net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * システム利用可能時間帯検索
 */
public interface SystemAvailableTimeZoneRepository {

    /**
     * システム利用可能時間帯群の条件検索を行います
     *
     * @param systemAvailableTimeZoneCriteria システム利用可能時間帯の検索条件
     * @param orders               オーダー指定
     * @return ステム利用可能時間帯群
     */
    SystemAvailableTimeZones selectBy(SystemAvailableTimeZoneCriteria systemAvailableTimeZoneCriteria, Orders orders);
}
