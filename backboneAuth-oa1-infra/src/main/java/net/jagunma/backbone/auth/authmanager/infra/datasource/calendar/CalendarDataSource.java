package net.jagunma.backbone.auth.authmanager.infra.datasource.calendar;

import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarType;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntity;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntityCriteria;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntityDao;
import org.springframework.stereotype.Component;

/**
 * カレンダー検索 DataSource
 */
@Component
public class CalendarDataSource implements CalendarRepository {

    private final CalendarEntityDao calendarEntityDao;

    // コンストラクタ
    public CalendarDataSource(CalendarEntityDao calendarEntityDao) {
        this.calendarEntityDao = calendarEntityDao;
    }

    /**
     * カレンダーの条件検索を行います
     *
     * @param calendarCriteria カレンダーの検索条件
     * @return カレンダー
     */
    @Override
    public Calendar findOneBy(CalendarCriteria calendarCriteria) {

        CalendarEntityCriteria entityCriteria = new CalendarEntityCriteria();
        entityCriteria.getCalendarIdCriteria().assignFrom(calendarCriteria.getCalendarIdCriteria());
        entityCriteria.getDateCriteria().assignFrom(calendarCriteria.getDateCriteria());

        CalendarEntity calendarEntity = calendarEntityDao.findOneBy(entityCriteria);
        return calendarEntity == null? null : Calendar.createFrom(
            calendarEntity.getCalendarId(),
            CalendarType.codeOf(calendarEntity.getCalendarType()),
            calendarEntity.getDate(),
            calendarEntity.getIsHoliday(),
            calendarEntity.getIsManualChange(),
            calendarEntity.getIsRelease(),
            calendarEntity.getRecordVersion());
    }
}
