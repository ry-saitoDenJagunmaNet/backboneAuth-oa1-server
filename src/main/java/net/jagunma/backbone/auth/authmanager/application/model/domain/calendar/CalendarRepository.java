package net.jagunma.backbone.auth.authmanager.application.model.domain.calendar;

/**
 * カレンダー検索
 */
public interface CalendarRepository {
	/**
	 * カレンダーの条件検索を行います。
	 * @param calendarCriteria カレンダーの検索条件
	 * @return カレンダー
	 */
	Calendar findOneBy(CalendarCriteria calendarCriteria);
}
