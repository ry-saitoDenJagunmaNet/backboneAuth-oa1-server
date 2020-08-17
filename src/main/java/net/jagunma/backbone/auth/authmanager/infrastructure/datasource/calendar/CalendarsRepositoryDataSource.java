package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.calendar;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.calendar.CalendarCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.calendar.Calendars;
import net.jagunma.backbone.auth.authmanager.application.model.domain.calendar.CalendarsRepository;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntity;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * カレンダー検索 DataSource
 */
@Component
public class CalendarsRepositoryDataSource implements CalendarsRepository {

	private final CalendarEntityDao calendarEntityDao;

	/**
	 * コンストラクタ
	 */
	CalendarsRepositoryDataSource(CalendarEntityDao calendarEntityDao) {
		this.calendarEntityDao = calendarEntityDao;
	}

	/**
	 * カレンダーの条件検索を行います。
	 * @param calendarCriteria カレンダーの検索条件
	 * @return カレンダー群
	 */
	@Override
	public Calendars selectBy(CalendarCriteria calendarCriteria) {
		Orders orders = Orders.empty().addOrder("CalendarType").addOrder("Date");
		List<CalendarEntity> list = calendarEntityDao.findBy(calendarCriteria, orders);
		return Calendars.createFrom(list);
	}

	/**
	 * カレンダーの全件検索を行います。
	 * @return カレンダー群
	 */
	@Override
	public Calendars selectAll() {
		Orders orders = Orders.empty().addOrder("CalendarType").addOrder("Date");
		return Calendars.createFrom(calendarEntityDao.findAll(orders));
	}
}
