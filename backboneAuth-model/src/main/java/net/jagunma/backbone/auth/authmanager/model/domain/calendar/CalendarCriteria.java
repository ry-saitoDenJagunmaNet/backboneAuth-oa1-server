package net.jagunma.backbone.auth.authmanager.model.domain.calendar;

import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;

/**
 * カレンダーの検索条件
 */
public class CalendarCriteria {

    /**
     * カレンダーIDの検索条件
     */
    private LongCriteria calendarIdCriteria = new LongCriteria();
    /**
     * 日付の検索条件
     */
    private LocalDateCriteria dateCriteria = new LocalDateCriteria();

    // Getter
    public LongCriteria getCalendarIdCriteria() {
        return calendarIdCriteria;
    }
    public LocalDateCriteria getDateCriteria() {
        return dateCriteria;
    }
}
