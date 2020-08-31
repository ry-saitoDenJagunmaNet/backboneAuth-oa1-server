package net.jagunma.backbone.auth.authmanager.application.service.oa12060;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendars;
import net.jagunma.backbone.auth.authmanager.model.types.CalendarType;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchResponse;
import net.jagunma.backbone.auth.authmanager.infrastructure.web.oa12060.vo.Oa12060SearchResponseVo;
import net.jagunma.backbone.auth.authmanager.infrastructure.web.oa12060.vo.Oa12060Vo;

class Oa12060SearchPresenter implements CalendarSearchResponse {

	private LocalDate yearMonth;
	private Calendars calendars;
	private boolean calendarTypeFilterCheck1disabled = false;
	private boolean calendarTypeFilterCheck2disabled = false;
	private boolean calendarTypeFilterCheck3disabled = false;

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

		calendarTypeFilterCheck1disabled = isCalendarTypeFilterCheckDisabled("Economy");
		calendarTypeFilterCheck2disabled = isCalendarTypeFilterCheckDisabled("Credit");
		calendarTypeFilterCheck3disabled = isCalendarTypeFilterCheckDisabled("WideAreaLogistics");

		// 表示対象経済選択無効
		vo.setCalendarTypeFilterCheck1disabled(calendarTypeFilterCheck1disabled);
		// 表示対象信用選択無効
		vo.setCalendarTypeFilterCheck2disabled(calendarTypeFilterCheck2disabled);
		// 表示対象広域物流選択無効
		vo.setCalendarTypeFilterCheck3disabled(calendarTypeFilterCheck3disabled);
		if (calendarTypeFilterCheck1disabled && calendarTypeFilterCheck2disabled && calendarTypeFilterCheck3disabled) {
			vo.setMessage("対象のカレンダーが登録されていません。");
		}

		// カレンターテーブルHtmlを生成
		vo.setCalendarTable(genCalendarTableHtml());
	}

	/**
	 * responseに変換
	 *
	 * @param vo カレンダーテーブル検索結果
	 * @param oa12060Vo カレンダーメンテナンスView Object
	 */
	public void bindTo(Oa12060SearchResponseVo vo, Oa12060Vo oa12060Vo) {
		oa12060Vo.setMessage(vo.getMessage());
		oa12060Vo.setSearchResponse(vo);
		oa12060Vo.setCalendarTypeFilterCheck1(calendarTypeFilterCheck1disabled? (short)0 : (short)1);
		oa12060Vo.setCalendarTypeFilterCheck2(calendarTypeFilterCheck2disabled? (short)0 : (short)1);
		oa12060Vo.setCalendarTypeFilterCheck3(calendarTypeFilterCheck3disabled? (short)0 : (short)1);
	}

	private boolean isCalendarTypeFilterCheckDisabled(String calendarTypeString) {
		if (calendars.getValues().stream().filter(c->CalendarType.valueOf(calendarTypeString).equals(c.getCalendarType())).count()
			== yearMonth.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth()) {
			return false;
		}
		return true;
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
		int index = day - 1;
		String checked[] = {" checked=\"checked\"", " checked=\"checked\"", " checked=\"checked\""};
		String calendarId[] = {"", "", ""};
		int recordVersion[] = {0, 0, 0};
		for (Calendar calendar : calendarList) {
			// 経済
			if (calendar.getCalendarType().isEconomy()) {
				if (calendar.getIsHoliday()) {checked[0] = "";}
				calendarId[0] = calendar.getCalendarId().toString();
				recordVersion[0] = calendar.getRecordVersion();
				continue;
			}
			// 信用
			if (calendar.getCalendarType().isCredit()) {
				if (calendar.getIsHoliday()) {checked[1] = "";}
				calendarId[1] = calendar.getCalendarId().toString();
				recordVersion[1] = calendar.getRecordVersion();
				continue;
			}
			// 広域物流
			if (calendar.getCalendarType().isWideAreaLogistics()) {
				if (calendar.getIsHoliday()) {checked[2] = "";}
				calendarId[2] = calendar.getCalendarId().toString();
				recordVersion[2] = calendar.getRecordVersion();
			}

//			// 経済
//			if (CalendarType.Economy.equals(calendar.getCalendarType())) {
//				if (calendar.getIsHoliday()) {checked[0] = "";}
//				calendarId[0] = calendar.getCalendarId().toString();
//				recordVersion[0] = calendar.getRecordVersion();
//				continue;
//			}
//			// 信用
//			if (CalendarType.Credit.equals(calendar.getCalendarType())) {
//				if (calendar.getIsHoliday()) {checked[1] = "";}
//				calendarId[1] = calendar.getCalendarId().toString();
//				recordVersion[1] = calendar.getRecordVersion();
//				continue;
//			}
//			// 広域物流
//			if (CalendarType.WideAreaLogistics.equals(calendar.getCalendarType())) {
//				if (calendar.getIsHoliday()) {checked[2] = "";}
//				calendarId[2] = calendar.getCalendarId().toString();
//				recordVersion[2] = calendar.getRecordVersion();
//			}
		}

		StringBuilder html = new StringBuilder();

		// 左セル　日付
		html.append("   <td class=\"").append(classids).append("\">").append(day).append("</td>");
		// 右セル　チェックボックス３つ
		html.append("   <td class=\"oaex_calendarcell_right\">");
		html.append("    <div><label class=\"oaex_calendarcell_check1\">");
		html.append(String.format("     <input type=\"checkbox\" id=\"calendar_type_check1[%d]\" name=\"CalendarList[%d].isWorkingDay1\" value=\"1\"%s></input>", index, index, checked[0]));
		html.append("     <span>経済</span>");
		html.append(String.format("     <input type=\"hidden\" name=\"CalendarList[%d].calendarId1\" value=\"%s\"></input>", index, calendarId[0]));
		html.append(String.format("     <input type=\"hidden\" name=\"CalendarList[%d].recordVersion1\" value=\"%d\"></input>", index, recordVersion[0]));
		html.append("    </label></div>");

		html.append("    <div><label class=\"oaex_calendarcell_check2\">");
		html.append(String.format("     <input type=\"checkbox\" id=\"calendar_type_check2[%d]\" name=\"CalendarList[%d].isWorkingDay2\" value=\"1\"%s></input>", index, index, checked[1]));
		html.append("     <span>信用</span>");
		html.append(String.format("     <input type=\"hidden\" name=\"CalendarList[%d].calendarId2\" value=\"%s\"></input>", index, calendarId[1]));
		html.append(String.format("     <input type=\"hidden\" name=\"CalendarList[%d].recordVersion2\" value=\"%d\"></input>", index, recordVersion[1]));
		html.append("    </label></div>");

		html.append("    <div><label class=\"oaex_calendarcell_check3\">");
		html.append(String.format("     <input type=\"checkbox\" id=\"calendar_type_check3[%d]\" name=\"CalendarList[%d].isWorkingDay3\" value=\"1\"%s></input>", index, index, checked[2]));
		html.append("     <span>広域物流</span>");
		html.append(String.format("     <input type=\"hidden\" name=\"CalendarList[%d].calendarId3\" value=\"%s\"></input>", index, calendarId[2]));
		html.append(String.format("     <input type=\"hidden\" name=\"CalendarList[%d].recordVersion3\" value=\"%d\"></input>", index, recordVersion[2]));
		html.append("    </label></div>");
		html.append("   </td>");

		return html.toString();
	}
}
