package net.jagunma.backbone.auth.authmanager.application.model.domain.calendar;

/**
 * カレンダー検索
 */
public interface CalendarsRepository {
	/**
	 * カレンダーの条件検索を行います。
	 * @param calendarCriteria カレンダーの検索条件
	 * @return カレンダー群
	 */
	Calendars selectBy(CalendarCriteria calendarCriteria);
	/**
	 * カレンダーの全件検索を行います。
	 * @return カレンダー群
	 */
	Calendars selectAll();
}
