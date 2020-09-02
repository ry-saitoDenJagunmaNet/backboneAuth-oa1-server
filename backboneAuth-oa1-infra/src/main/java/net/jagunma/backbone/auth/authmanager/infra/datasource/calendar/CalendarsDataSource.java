package net.jagunma.backbone.auth.authmanager.infra.datasource.calendar;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendars;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarsRepository;
import net.jagunma.backbone.auth.authmanager.model.types.CalendarType;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntity;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * カレンダー検索 DataSource
 */
@Component
public class CalendarsDataSource implements CalendarsRepository {

	private final CalendarEntityDao calendarEntityDao;
	private final Orders defaultOrders = Orders.empty().addOrder("calendarType").addOrder("date");

	// コンストラクタ
	CalendarsDataSource(CalendarEntityDao calendarEntityDao) {
		this.calendarEntityDao = calendarEntityDao;
	}

	/**
	 * カレンダーの条件検索を行います。
	 *
	 * @param calendarCriteria カレンダーの検索条件
	 * @param orders           オーダー指定
	 * @return カレンダー群
	 */
	@Override
	public Calendars selectBy(CalendarCriteria calendarCriteria, Orders orders) {
		List<CalendarEntity> list = calendarEntityDao.findBy(calendarCriteria, orders);
		return createCalendars(list);
	}

	/**
	 * カレンダーの条件検索を行います。
	 *
	 * @param calendarCriteria カレンダーの検索条件
	 * @return カレンダー群
	 */
	@Override
	public Calendars selectBy(CalendarCriteria calendarCriteria) {
		return selectBy(calendarCriteria, defaultOrders);
	}

	/**
	 * カレンダーの全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return カレンダー群
	 */
	@Override
	public Calendars selectAll(Orders orders) {
		List<CalendarEntity> list = calendarEntityDao.findAll(orders);
		return createCalendars(list);
	}

	/**
	 * カレンダーの全件検索を行います。
	 *
	 * @return カレンダー群
	 */
	@Override
	public Calendars selectAll() {
		return selectAll(defaultOrders);
	}

	/**
	 *  カレンダー群を作成します。
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
