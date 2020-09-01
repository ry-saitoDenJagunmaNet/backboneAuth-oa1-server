package net.jagunma.backbone.auth.authmanager.infra.datasource.calendar;

import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarRepositoryForStore;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntity;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntityDao;
import org.springframework.stereotype.Component;

/**
 * カレンダー登録
 */
@Component
public class CalendarRepositoryForStoreDataSource implements CalendarRepositoryForStore {

    private final CalendarEntityDao calendarEntityDao;

    // コンストラクタ
    public CalendarRepositoryForStoreDataSource(CalendarEntityDao calendarEntityDao) {
        this.calendarEntityDao = calendarEntityDao;
    }

    /**
     * カレンダー更新を行います。
     *
     * @param calendar カレンダー
     */
    public Calendar update(Calendar calendar) {
        calendarEntityDao.updateExcludeNull(createEntity(calendar));
        return calendar;
    }

    /**
     * カレンダー情報を作成します。
     *
     * @param calendar カレンダー
     * @return カレンダー情報
     */
    private CalendarEntity createEntity(Calendar calendar) {
        CalendarEntity entity = new CalendarEntity();
        entity.setCalendarId(calendar.getCalendarId());
        entity.setCalendarType(calendar.getCalendarType().getCode());
        entity.setDate(calendar.getDate());
        entity.setIsHoliday(calendar.getIsHoliday());
        entity.setIsManualChange(calendar.getIsManualChange());
        entity.setIsRelease(calendar.getIsRelease());
        entity.setRecordVersion(calendar.getRecordVersion());
        return entity;
    }
}
