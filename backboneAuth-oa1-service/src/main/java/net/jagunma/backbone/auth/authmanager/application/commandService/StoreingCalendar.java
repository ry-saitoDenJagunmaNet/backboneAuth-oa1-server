package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand.CalendarEntryRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarRepositoryForStore;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * カレンダー登録サービス
 */
@Component
@Transactional
public class StoreingCalendar {

	private final CalendarRepositoryForStore calendarRepositoryForStore;
	private final CalendarRepository calendarRepository;

	// コンストラクタ
	public StoreingCalendar(
		CalendarRepositoryForStore calendarRepositoryForStore,
		CalendarRepository calendarRepository) {

		this.calendarRepositoryForStore = calendarRepositoryForStore;
		this.calendarRepository = calendarRepository;
	}

	/**
	 * 稼働日または休日を登録します。
	 *
	 * @param request 登録するカレンダー情報
	 */
	public int execute(CalendarEntryRequest request) {

		// パラメーターの検証
		StoreingCalendarValidator.with(request).validate();

		int reflected = 0;
		for (Calendar calendar : request.getCalendars().getValues()) {
			// 稼働/休日が変更になったデータを更新
			Calendar oldClendar = calendarRepository.findOneById(calendar.getCalendarId());
			if (!calendar.getIsHoliday().equals(oldClendar.getIsHoliday())) {
				calendarRepositoryForStore.update(calendar);
				reflected++;
			}
		}
		return reflected;
	}
}