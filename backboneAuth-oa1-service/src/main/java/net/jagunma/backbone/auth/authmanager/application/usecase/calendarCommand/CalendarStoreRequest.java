package net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.Oa12060StoreDetailsConverter;

/**
 * カレンダーメンテナンス反映サービス Request
 */
public interface CalendarStoreRequest {
	/**
	 * 年月のＧｅｔ
	 * @return 年月
	 */
	LocalDate getYearMonth();
	/**
	 * カレンダーリストを作成します。
	 * @return カレンダーリスト
	 */
	List<Oa12060StoreDetailsConverter> createCalendarList();
}
