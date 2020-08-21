package net.jagunma.backbone.auth.authmanager.application.service.oa12060;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.application.model.domain.calendar.Calendars;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchResponse;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa12060.vo.Oa12060SearchResponseVo;

class Oa12060SearchPresenter implements CalendarSearchResponse {

	private LocalDate yearMonth;
	private Calendars calendars;

	/**
	 * 年月のＳｅｔ
	 * @param yearMonth 年月
	 */
	public void setYearMonth(LocalDate yearMonth) { this.yearMonth = yearMonth; }
	/**
	 * カレンダー群のＳｅｔ
	 * @param calendars カレンダー群
	 */
	public void setCalendars(Calendars calendars) { this.calendars = calendars; }

	/**
	 * responseに変換
	 *
	 * @param vo カレンダーテーブル検索結果
	 */
	public void bindTo(Oa12060SearchResponseVo vo) {
		vo.setCalendarTable(genCalendarTableHtml());
	}

	/**
	 * カレンターテーブルHtmlを生成します。
	 *
	 * @return カレンターテーブルHtml
	 */
	private String genCalendarTableHtml() {

		// 月初日
		LocalDate startDate = yearMonth.withDayOfMonth(1);
		// 月末日
		int lastDay = startDate.plusMonths(1).minusDays(1).getDayOfMonth();
		// 月初日の曜日
		int startWeek = startDate.getDayOfWeek().getValue();
		if (startWeek == 7) {startWeek = 0; }
		System.out.println("### startDate="+startDate+",lastDay="+lastDay+",startWeek="+startWeek);

		StringBuilder html = new StringBuilder();

		html.append("<table id=\"calendar_table\">");
		// 見出し
		html.append(" <thead>");
		html.append("  <tr>");
		html.append("   <th colspan=\"2\" class=\"center-align red-text\">日</th>");
		html.append("   <th colspan=\"2\" class=\"center-align\">月</th>");
		html.append("   <th colspan=\"2\" class=\"center-align\">火</th>");
		html.append("   <th colspan=\"2\" class=\"center-align\">水</th>");
		html.append("   <th colspan=\"2\" class=\"center-align\">木</th>");
		html.append("   <th colspan=\"2\" class=\"center-align\">金</th>");
		html.append("   <th colspan=\"2\" class=\"center-align blue-text\">土</th>");
		html.append("  </tr>");
		html.append("</thead>");

		// 明細
		boolean firstRow = true;
		int currentWeek = startWeek;
		List<Calendar> list = calendars.getValues();
		System.out.println("### list.couny="+list.size());

		html.append(" <tbody>");
		html.append("  <tr>");
		for (int day = 1; day <= lastDay; day++) {
			if (firstRow) {
				// 第１週（１日まで空セル設定）
				html.append("   <td colspan=\"2\"></td>".repeat(Math.max(0, startWeek)));
				firstRow = false;
			}
			// カレンダーセル（一日分）のhtml生成
			LocalDate targetDate = yearMonth.withDayOfMonth(day);
			html.append(genCalendarCellHtml(day, currentWeek, list.stream().filter(c->c.getDate().equals(targetDate)).collect(
				Collectors.toList())));
			currentWeek++;
			if (currentWeek >= 7) {
				html.append("  </tr>");
				if (day < lastDay) {
					html.append("  <tr>");
				}
				currentWeek = 0;
			}
		}
		html.append("  </tr>");

		html.append(" </tbody>");
		html.append("</table>");

		return html.toString();
	}

	/**
	 * カレンダーセル（一日分）のhtmlを生成します。
	 *
	 * @param day 対象日
	 * @param currentWeek 対象曜日
	 * @param calendarList カレンダーリスト
	 * @return カレンダーセル（一日分）のhtml
	 */
	private String genCalendarCellHtml(int day, int currentWeek, List<Calendar> calendarList) {
		String classids = "";
		// 日曜日の日付の色設定
		if (currentWeek == 0) {classids = "red-text";}
		// 土曜日の日付の色設定
		if (currentWeek == 7) {classids = "blue-text";}
		classids += " oaex_calendarcell_left";

		// 対象日が稼働日の場合。チェックボックスをチェック済にする（データがない場合は、稼働日）
		String checked1 = " checked=\"checked\"";
		String checked2 = " checked=\"checked\"";
		String checked3 = " checked=\"checked\"";
		String calendarId1 = "";
		String calendarId2 = "";
		String calendarId3 = "";
		for (Calendar calendar : calendarList) {
			// 経済
			if (calendar.getCalendarType() == 1) {
				if (calendar.getIsHoliday()) {checked1 = "";}
				calendarId1 = calendar.getCalendarId().toString();
				continue;
			}
			// 信用
			if (calendar.getCalendarType() == 2) {
				if (calendar.getIsHoliday()) {checked2 = "";}
				calendarId2= calendar.getCalendarId().toString();
				continue;
			}
			// 広域物流
			if (calendar.getCalendarType() == 3) {
				if (calendar.getIsHoliday()) {checked3 = "";}
				calendarId3= calendar.getCalendarId().toString();
			}
		}

		StringBuilder html = new StringBuilder();

		// 左セル　日付
		html.append("   <td class=\"").append(classids).append("\">").append(day).append("</td>");
		// 右セル　チェックボックス３つ
		html.append("   <td class=\"oaex_calendarcell_right\">");
		html.append("    <div><label class=\"oaex_calendarcell_check1\">");
		html.append(String.format("     <input type=\"checkbox\" id=\"calendar_type_check1[%d]\" name=\"CalendarList[%d].isWorkingDay1\" value=\"1\"%s></input>", day, day, checked1));
		html.append("     <span>経済</span>");
		html.append(String.format("     <input type=\"hidden\" name=\"CalendarList[%d].calendarId1\" value=\"%s\"></input>", day, calendarId1));
		html.append("    </label></div>");

		html.append("    <div><label class=\"oaex_calendarcell_check2\">");
		html.append(String.format("     <input type=\"checkbox\" id=\"calendar_type_check2[%d]\" name=\"CalendarList[%d].isWorkingDay2\" value=\"1\"%s></input>", day, day, checked2));
		html.append("     <span>信用</span>");
		html.append(String.format("     <input type=\"hidden\" name=\"CalendarList[%d].calendarId2\" value=\"%s\"></input>", day, calendarId2));
		html.append("    </label></div>");

		html.append("    <div><label class=\"oaex_calendarcell_check3\">");
		html.append(String.format("     <input type=\"checkbox\" id=\"calendar_type_check3[%d]\" name=\"CalendarList[%d].isWorkingDay3\" value=\"1\"%s></input>", day, day, checked3));
		html.append("     <span>広域物流</span>");
		html.append(String.format("     <input type=\"hidden\" name=\"CalendarList[%d].calendarId3\" value=\"%s\"></input>", day, calendarId3));
		html.append("    </label></div>");
		html.append("   </td>");

		return html.toString();
	}
}
