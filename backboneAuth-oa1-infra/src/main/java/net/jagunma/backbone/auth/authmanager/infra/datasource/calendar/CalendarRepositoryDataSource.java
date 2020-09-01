package net.jagunma.backbone.auth.authmanager.infra.datasource.calendar;

import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarRepository;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntity;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntityDao;
import org.springframework.stereotype.Component;

/**
 * カレンダー検索 DataSource
 */
@Component
public class CalendarRepositoryDataSource implements CalendarRepository {

    private final CalendarEntityDao calendarEntityDao;

    // コンストラクタ
    public CalendarRepositoryDataSource(CalendarEntityDao calendarEntityDao) {
        this.calendarEntityDao = calendarEntityDao;
    }

    /**
     * カレンダーのカレンダーIDによる検索を行います。
     *
     * @param calendarId カレンダーID
     * @return カレンダー
     */
    public Calendar findOneById(Long  calendarId) {
        CalendarCriteria calendarCriteria = new CalendarCriteria();
        calendarCriteria.getCalendarIdCriteria().setEqualTo(calendarId);
        CalendarEntity calendarEntity = calendarEntityDao.findOneBy(calendarCriteria);
        return Calendar.of(calendarEntity);
    }

    /**
     * カレンダーの条件検索を行います。
     *
     * @param calendarCriteria カレンダーの検索条件
     * @return カレンダー
     */
    @Override
    public Calendar findOneBy(CalendarCriteria calendarCriteria) {
        CalendarEntity calendarEntity = calendarEntityDao.findOneBy(calendarCriteria);
        return Calendar.of(calendarEntity);
    }
}
