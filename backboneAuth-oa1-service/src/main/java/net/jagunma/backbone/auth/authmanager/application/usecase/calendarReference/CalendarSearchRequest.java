package net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference;

import java.time.LocalDate;

/**
 * カレンダー検索サービス Request
 */
public interface CalendarSearchRequest {
    /**
     * 年月のＧｅｔ
     * @return 年月
     */
    LocalDate getYearMonth();
}
