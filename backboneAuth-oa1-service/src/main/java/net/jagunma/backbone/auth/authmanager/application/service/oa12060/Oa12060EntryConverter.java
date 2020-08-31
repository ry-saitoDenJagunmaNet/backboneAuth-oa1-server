package net.jagunma.backbone.auth.authmanager.application.service.oa12060;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendars;
import net.jagunma.backbone.auth.authmanager.model.types.CalendarType;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand.CalendarEntryRequest;
import net.jagunma.backbone.auth.authmanager.infrastructure.web.oa12060.vo.Oa12060Vo;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntity;

/**
 * OA12060 カレンダーメンテナンス 登録 Converter
 */
class Oa12060EntryConverter implements CalendarEntryRequest {

    /**
     * OA12060 View Object
     */
    private final Oa12060Vo arg;

    // コンストラクタ
    Oa12060EntryConverter(Oa12060Vo oa12060Vo) {
        arg = oa12060Vo;
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
        return arg.getYearMonth();
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

        List<CalendarEntity> calendarEntityList = newArrayList();
        arg.getCalendarList().forEach(c -> {
            CalendarEntity entity = new CalendarEntity();
            entity.setCalendarId(c.getCalendarId1());
            entity.setCalendarType(CalendarType.Economy.getCode());
            entity.setIsHoliday(!Oa12060Vo.CHECKBOX_TRUE.equals(c.getIsWorkingDay1()));
            entity.setIsManualChange(true);
            entity.setRecordVersion(c.getRecordVersion1());
            calendarEntityList.add(entity);

            entity = new CalendarEntity();
            entity.setCalendarId(c.getCalendarId2());
            entity.setCalendarType(CalendarType.Credit.getCode());
            entity.setIsHoliday(!Oa12060Vo.CHECKBOX_TRUE.equals(c.getIsWorkingDay2()));
            entity.setIsManualChange(true);
            entity.setRecordVersion(c.getRecordVersion2());
            calendarEntityList.add(entity);

            entity = new CalendarEntity();
            entity.setCalendarId(c.getCalendarId3());
            entity.setCalendarType(CalendarType.WideAreaLogistics.getCode());
            entity.setIsHoliday(!Oa12060Vo.CHECKBOX_TRUE.equals(c.getIsWorkingDay3()));
            entity.setIsManualChange(true);
            entity.setRecordVersion(c.getRecordVersion3());
            calendarEntityList.add(entity);
        });
        return Calendars.createFrom(calendarEntityList);
    }

//	public CalendarEntityCriteria genCalendarEntityCriteria() {
//		CalendarEntityCriteria calendarEntityCriteria = new CalendarEntityCriteria();
//		return calendarEntityCriteria;
//	}

}
