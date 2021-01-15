package net.jagunma.backbone.auth.authmanager.infra.datasource.calendar;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarType;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendars;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntity;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntityCriteria;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * カレンダー検索 DataSource
 */
@Component
public class CalendarDataSource implements CalendarRepository {

    private final Orders defaultOrders = Orders.empty().addOrder("calendarType").addOrder("date");
    private final CalendarEntityDao calendarEntityDao;

    // コンストラクタ
    public CalendarDataSource(CalendarEntityDao calendarEntityDao) {
        this.calendarEntityDao = calendarEntityDao;
    }

    /**
     * カレンダーの検索を行います
     *
     * @param calendarId カレンダーID
     * @return カレンダー
     */
    public Calendar findOneById(Long calendarId) {

        CalendarEntityCriteria entityCriteria = new CalendarEntityCriteria();
        entityCriteria.getCalendarIdCriteria().setEqualTo(calendarId);

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

    /**
     * カレンダー群の検索を行います
     *
     * @param calendarCriteria カレンダーの検索条件
     * @param orders           オーダー指定
     * @return カレンダー群
     */
    public Calendars selectBy(CalendarCriteria calendarCriteria, Orders orders) {

        CalendarEntityCriteria entityCriteria = new CalendarEntityCriteria();
        entityCriteria.getCalendarIdCriteria().assignFrom(calendarCriteria.getCalendarIdCriteria());
        entityCriteria.getDateCriteria().assignFrom(calendarCriteria.getDateCriteria());

        List<CalendarEntity> list = calendarEntityDao.findBy(entityCriteria, orders);
        return createCalendars(list);
    }

    /**
     * カレンダー群の検索を行います
     *
     * @param calendarCriteria カレンダーの検索条件
     * @return カレンダー群
     */
    public Calendars selectBy(CalendarCriteria calendarCriteria) {
        return selectBy(calendarCriteria, defaultOrders);
    }

    /**
     * カレンダー群の全件検索を行います
     *
     * @param orders オーダー指定
     * @return カレンダー群
     */
    public Calendars selectAll(Orders orders) {
        List<CalendarEntity> list = calendarEntityDao.findAll(orders);
        return createCalendars(list);
    }

    /**
     * カレンダー群の全件検索を行います
     *
     * @return カレンダー群
     */
    public Calendars selectAll() {
        return selectAll(defaultOrders);
    }

    /**
     *  カレンダー群を作成します
     *
     * @param entityList カレンダーリスト
     * @return カレンダー群
     */
    private Calendars createCalendars(List<CalendarEntity> entityList) {
        List<Calendar> list = newArrayList();
        for (CalendarEntity entity : entityList) {
            list.add(Calendar.createFrom(
                entity.getCalendarId(),
                CalendarType.codeOf(entity.getCalendarType()),
                entity.getDate(),
                entity.getIsHoliday(),
                entity.getIsManualChange(),
                entity.getIsRelease(),
                entity.getRecordVersion()
            ));
        }
        return Calendars.createFrom(list);
    }

}
