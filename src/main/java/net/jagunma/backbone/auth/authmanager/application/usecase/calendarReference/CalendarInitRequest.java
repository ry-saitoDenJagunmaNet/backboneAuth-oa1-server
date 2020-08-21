package net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference;

import java.time.LocalDate;

/**
 * カレンダー参照サービス Request
 */
public interface CalendarInitRequest {
	/**
	 * 年月のＧｅｔ
	 * @return 年月
	 */
	LocalDate getYearMonth();
}
