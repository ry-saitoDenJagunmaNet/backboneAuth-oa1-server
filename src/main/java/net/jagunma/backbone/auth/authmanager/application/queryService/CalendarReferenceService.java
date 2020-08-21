package net.jagunma.backbone.auth.authmanager.application.queryService;

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
public class CalendarReferenceService {

	private final CalendarsRepository calendarsRepository;

	// コンストラクタ
	public CalendarReferenceService(CalendarsRepository calendarsRepository) {
		this.calendarsRepository = calendarsRepository;
	}

	/**
	 * カレンダー群を取得します。
	 *
	 * @param request 条件
	 * @param response 検索結果
	 */
	public void getCalendars(CalendarSearchRequest request, CalendarSearchResponse response) {

		// カレンダー検索
		response.setCalendars(calendarsRepository.selectBy(request.genCalendarCriteria()));
	}
}
