package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarsRepository;
import org.springframework.stereotype.Service;

/**
 * カレンダー検索サービス
 */
@Service
public class SearchCalendar {

	private final CalendarsRepository calendarsRepository;

	// コンストラクタ
	public SearchCalendar(CalendarsRepository calendarsRepository) {
		this.calendarsRepository = calendarsRepository;
	}

	/**
	 * カレンダー群を検索します。
	 *
	 * @param request 条件
	 * @param response 検索結果
	 */
	public void execute(CalendarSearchRequest request, CalendarSearchResponse response) {

		// パラメーターの検証
		SearchCalendarValidator.with(request).validate();

		// カレンダー検索
		response.setCalendars(calendarsRepository.selectBy(genCriteria(request)));
		response.setYearMonth(request.getYearMonth());
	}

	/**
	 * カレンダーの検索条件を生成します。
	 *
	 * @return カレンダー検索条件
	 */
	private CalendarCriteria genCriteria(CalendarSearchRequest request) {

		CalendarCriteria criteria = new CalendarCriteria();

		criteria.getDateCriteria().setFrom(request.getYearMonth().withDayOfMonth(1));
		criteria.getDateCriteria().setTo(request.getYearMonth().withDayOfMonth(1).plusMonths(1).minusDays(1));

		return criteria;
	}

}
