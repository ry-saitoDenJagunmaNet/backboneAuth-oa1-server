package net.jagunma.backbone.auth.authmanager.model.domain.calendar;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * カレンダー検索
 */
public interface CalendarRepository {

    /**
     * カレンダーの検索を行います
     *
     * @param calendarId カレンダーID
     * @return カレンダー
     */
    Calendar findOneById(Long calendarId);
    /**
     * カレンダー群の検索を行います
     *
     * @param calendarCriteria カレンダーの検索条件
     * @param orders オーダー指定
     * @return カレンダー群
     */
    Calendars selectBy(CalendarCriteria calendarCriteria, Orders orders);
    /**
     * カレンダー群の検索を行います
     *
     * @param calendarCriteria カレンダーの検索条件
     * @return カレンダー群
     */
    Calendars selectBy(CalendarCriteria calendarCriteria);
    /**
     * カレンダー群の全件検索を行います
     *
     * @param orders オーダー指定
     * @return カレンダー群
     */
    Calendars selectAll(Orders orders);
    /**
     * カレンダー群の全件検索を行います
     *
     * @return カレンダー群
     */
    Calendars selectAll();
}
