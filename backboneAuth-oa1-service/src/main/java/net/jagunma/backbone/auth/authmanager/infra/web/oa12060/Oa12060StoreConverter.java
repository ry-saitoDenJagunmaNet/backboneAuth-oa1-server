package net.jagunma.backbone.auth.authmanager.infra.web.oa12060;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand.CalendarStoreRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo.Oa12060Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendars;
import net.jagunma.backbone.auth.authmanager.model.types.CalendarType;

/**
 * OA12060 カレンダーメンテナンス 登録 Converter
 */
class Oa12060StoreConverter implements CalendarStoreRequest {

	/**
	 * OA12060 View Object
	 */
	private final Oa12060Vo arg;

	// コンストラクタ
	Oa12060StoreConverter(Oa12060Vo oa12060Vo) {
		arg = oa12060Vo;
	}
	// ファクトリーメソッド
	public static Oa12060StoreConverter with(Oa12060Vo oa12060Vo) {
		return new Oa12060StoreConverter(oa12060Vo);
	}

	/**
	 * 年月のＧｅｔ
	 *
	 * @return 年月
	 */
	public LocalDate getYearMonth() {
		return arg.getYearMonth();
	}

	/**
	 * カレンダー群のＧｅｔ
	 *
	 * @return カレンダー群
	 */
	public Calendars getCalendars() {

		arg.setYearMonth(LocalDate.parse( arg.getYearMonthToString() + "/01", DateTimeFormatter
			.ofPattern("yyyy/MM/dd")));

		List<Calendar> calendars = newArrayList();
		arg.getCalendarList().forEach(c -> {
			if (c.getCalendarId1() != null) {
				Calendar calendar = Calendar.createFrom(
					c.getCalendarId1(),
					CalendarType.Economy,
					null,
					!Oa12060Vo.CHECKBOX_TRUE.equals(c.getIsWorkingDay1()),
					true,
					null,
					c.getRecordVersion1());
				calendars.add(calendar);
			}
			if (c.getCalendarId2() != null) {
				Calendar calendar = Calendar.createFrom(
					c.getCalendarId2(),
					CalendarType.Credit,
					null,
					!Oa12060Vo.CHECKBOX_TRUE.equals(c.getIsWorkingDay2()),
					true,
					null,
					c.getRecordVersion2());
				calendars.add(calendar);
			}
			if (c.getCalendarId3() != null) {
				Calendar calendar = Calendar.createFrom(
					c.getCalendarId3(),
					CalendarType.WideAreaLogistics,
					null,
					!Oa12060Vo.CHECKBOX_TRUE.equals(c.getIsWorkingDay3()),
					true,
					null,
					c.getRecordVersion3());
				calendars.add(calendar);
			}
		});
		return Calendars.createFrom(calendars);
	}
}
