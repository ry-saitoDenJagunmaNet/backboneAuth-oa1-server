package net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.model.domain.calendar.CalendarCriteria;

/**
 * カレンダー参照サービス Request
 */
public interface CalendarSearchRequest {
	/**
	 * 年月のＧｅｔ
	 * @return 年月
	 */
	LocalDate getYearMonth();

	/**
	 * カレンダーの検索条件を生成します。
	 */
	CalendarCriteria genCalendarCriteria();
}
