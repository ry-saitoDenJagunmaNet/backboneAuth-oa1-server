package net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.calendar.Calendars;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa12060.vo.Oa12060CalendarVo;

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
	public Calendars getCalendars();
}
