package net.jagunma.backbone.auth.authmanager.application.service.oa12060;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import net.jagunma.backbone.auth.authmanager.infrastructure.web.oa12060.vo.Oa12060Vo;

/**
 * OA12060 カレンダーメンテナンス 画面初期表示 Presenter
 */
class Oa12060InitPresenter {

	private LocalDate yearMonth;
	private Short calendarTypeCheck1;
	private Short calendarTypeCheck2;
	private Short calendarTypeCheck3;
	private String workingdayOrHolidaySelect;

	/**
	 * 年月のＳｅｔ
	 * @param yearMonth 年月
	 */
	public void setYearMonth(LocalDate yearMonth) { this.yearMonth = yearMonth; }
	/**
	 * 表示対象経済システム稼働のＳｅｔ
	 * @param calendarTypeCheck1 表示対象経済システム稼働
	 */
	public void setCalendarTypeCheck1(Short calendarTypeCheck1) { this.calendarTypeCheck1 = calendarTypeCheck1; }
	/**
	 * 表示対象信用のＳｅｔ
	 * @param calendarTypeCheck2 表示対象信用
	 */
	public void setCalendarTypeCheck2(Short calendarTypeCheck2) { this.calendarTypeCheck2 = calendarTypeCheck2; }
	/**
	 * 表示対象広域物流のＳｅｔ
	 * @param calendarTypeCheck3 表示対象広域物流
	 */
	public void setCalendarTypeCheck3(Short calendarTypeCheck3) { this.calendarTypeCheck3 = calendarTypeCheck3; }
	/**
	 * 稼働・休日選択のＳｅｔ
	 * @param workingdayOrHolidaySelect 稼働・休日選択
	 */
	public void setWorkingdayOrHolidaySelect(String workingdayOrHolidaySelect) { this.workingdayOrHolidaySelect = workingdayOrHolidaySelect; }

	/**
	 * responseに変換
	 *
	 * @param vo カレンダーメンテナンスView Object
	 */
	public void bindTo(Oa12060Vo vo) {
		vo.setYearMonth(yearMonth);
		vo.setYearMonthToString(yearMonth.format(DateTimeFormatter.ofPattern("yyyy/MM")));
		vo.setCalendarTypeFilterCheck1(calendarTypeCheck1);
		vo.setCalendarTypeFilterCheck2(calendarTypeCheck2);
		vo.setCalendarTypeFilterCheck3(calendarTypeCheck3);
		vo.setWorkingdayOrHolidaySelect(workingdayOrHolidaySelect);
		vo.setSearchResponse(vo.getSearchResponse());
	}
}
