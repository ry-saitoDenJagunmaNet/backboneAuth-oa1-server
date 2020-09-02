package net.jagunma.backbone.auth.authmanager.model.domain.calendar;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * カレンダー検索
 */
public interface CalendarsRepository {
	/**
	 * カレンダーの条件検索を行います。
	 *
	 * @param calendarCriteria カレンダーの検索条件
	 * @param orders オーダー指定
	 * @return カレンダー群
	 */
	Calendars selectBy(CalendarCriteria calendarCriteria, Orders orders);
	/**
	 * カレンダーの条件検索を行います。
	 *
	 * @param calendarCriteria カレンダーの検索条件
	 * @return カレンダー群
	 */
	Calendars selectBy(CalendarCriteria calendarCriteria);

	/**
	 * カレンダーの全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return カレンダー群
	 */
	Calendars selectAll(Orders orders);
	/**
	 * カレンダーの全件検索を行います。
	 *
	 * @return カレンダー群
	 */
	Calendars selectAll();
}
