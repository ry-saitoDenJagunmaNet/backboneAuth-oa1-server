package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.application.model.domain.calendar.CalendarRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand.CalendarEntryRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * カレンダー登録サービス
 */
@Service
@Transactional
public class CalendarCommandService {

	private final CalendarRepositoryForStore calendarRepositoryForStore;

	// コンストラクタ
	public CalendarCommandService(CalendarRepositoryForStore calendarRepositoryForStore) {
		this.calendarRepositoryForStore = calendarRepositoryForStore;
	}

	/**
	 * 稼働日または休日を登録します。
	 *
	 * @param request 登録するカレンダー情報
	 */
	public void entry(CalendarEntryRequest request) {
		for (Calendar calendar : request.getCalendars().getValues()) {
			calendarRepositoryForStore.store(calendar);
		}
	}
}
