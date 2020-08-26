package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.application.model.domain.calendar.CalendarCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.calendar.CalendarsRepository;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * カレンダー参照サービス
 */
@Service
@Transactional
public class CalendarQueryService {

	private final CalendarsRepository calendarsRepository;

	// コンストラクタ
	public CalendarQueryService(CalendarsRepository calendarsRepository) {
		this.calendarsRepository = calendarsRepository;
	}

	/**
	 * カレンダー群を取得します。
	 *
	 * @param request 条件
	 * @param response 検索結果
	 */
	public void getCalendars(CalendarSearchRequest request, CalendarSearchResponse response) {

		// パラメーターの検証
		CalendarQueryValidator.with(request).validate();

		// カレンダー検索
		response.setCalendars(calendarsRepository.selectBy(genCalendarCriteria(request)));
	}

	/**
	 * カレンダーの検索条件を生成します。
	 *
	 * @return カレンダー検索条件
	 */
	private CalendarCriteria genCalendarCriteria(CalendarSearchRequest request) {

		CalendarCriteria criteria = new CalendarCriteria();

		criteria.getDateCriteria().setMoreOrEqual(request.getYearMonth().withDayOfMonth(1));
		criteria.getDateCriteria().setLessThan(request.getYearMonth().withDayOfMonth(1).plusMonths(1));

		return criteria;
	}

}
