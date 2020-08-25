package net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.model.domain.calendar.Calendars;

/**
 * カレンダー 登録サービス Request
 */
public interface CalendarEntryRequest {
	/**
	 * 年月のＧｅｔ
	 * @return 年月
	 */
	LocalDate getYearMonth();

	/**
	 * カレンダー群のＧｅｔ
	 * @return カレンダー群
	 */
	Calendars getCalendars();
}
