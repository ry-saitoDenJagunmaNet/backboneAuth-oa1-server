package net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendars;

/**
 * カレンダー検索サービス Response
 */
public interface CalendarSearchResponse {
    /**
     * 年月のＳｅｔ
     * @param yearMonth 年月
     */
    void setYearMonth(LocalDate yearMonth);
    /**
     * カレンダー群のＳｅｔ
     * @param calendars カレンダー群
     */
    void setCalendars(Calendars calendars);
}
