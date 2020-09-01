package net.jagunma.backbone.auth.authmanager.model.domain.calendar;

/**
 * カレンダー検索
 */
public interface CalendarRepository {
	/**
	 * カレンダーのカレンダーIDによる検索を行います。
	 *
	 * @param calendarId カレンダーID
	 * @return カレンダー
	 */
	Calendar findOneById(Long  calendarId);
	/**
	 * カレンダーの条件検索を行います。
	 *
	 * @param calendarCriteria カレンダーの検索条件
	 * @return カレンダー
	 */
	Calendar findOneBy(CalendarCriteria calendarCriteria);
}
