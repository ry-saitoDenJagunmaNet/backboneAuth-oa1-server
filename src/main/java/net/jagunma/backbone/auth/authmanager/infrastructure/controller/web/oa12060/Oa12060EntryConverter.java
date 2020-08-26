package net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa12060;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.application.model.domain.calendar.Calendars;
import net.jagunma.backbone.auth.authmanager.application.model.types.CalendarType;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand.CalendarEntryRequest;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa12060.vo.Oa12060Vo;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntity;

/**
 * OA12060 カレンダーメンテナンス 登録 Converter
 */
class Oa12060EntryConverter implements CalendarEntryRequest {

	/**
	 * OA12060 View Object
	 */
	private final Oa12060Vo vo;

	// コンストラクタ
	Oa12060EntryConverter(Oa12060Vo oa12060Vo) {
		vo = oa12060Vo;
	}

	// ファクトリーメソッド
	public static Oa12060EntryConverter with(Oa12060Vo oa12060Vo) {
		return new Oa12060EntryConverter(oa12060Vo);
	}

	/**
	 * 年月のＧｅｔ
	 *
	 * @return 年月
	 */
	public LocalDate getYearMonth() {
		return vo.getYearMonth();
	}

	/**
	 * カレンダー群のＧｅｔ
	 *
	 * @return カレンダー群
	 */
	public Calendars getCalendars() {

//        Calenders calenders = carelderReposity.selectBy(YearMonth);
//        Calenders newClenders = Carenders.createFrom(arg.getCalendarList());
//        carenderRerpository.store(newClenders.getDeleteRecs);

		List<Calendar> calendars = newArrayList();
		vo.getCalendarList().forEach(c -> {
			Calendar calendar = Calendar.createFrom(
				c.getCalendarId1(),
				CalendarType.Economy,
				null,
				!Oa12060Vo.CHECKBOX_TRUE.equals(c.getIsWorkingDay1()),
				true,
				null,
				c.getRecordVersion1());
			calendars.add(calendar);
			calendar = Calendar.createFrom(
				c.getCalendarId2(),
				CalendarType.Credit,
				null,
				!Oa12060Vo.CHECKBOX_TRUE.equals(c.getIsWorkingDay2()),
				true,
				null,
				c.getRecordVersion2());
			calendars.add(calendar);
			calendar = Calendar.createFrom(
				c.getCalendarId3(),
				CalendarType.WideAreaLogistics,
				null,
				!Oa12060Vo.CHECKBOX_TRUE.equals(c.getIsWorkingDay3()),
				true,
				null,
				c.getRecordVersion3());
			calendars.add(calendar);
		});
		return Calendars.createFrom(calendars);
	}
}
