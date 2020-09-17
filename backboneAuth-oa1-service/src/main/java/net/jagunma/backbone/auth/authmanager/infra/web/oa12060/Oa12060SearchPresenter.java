package net.jagunma.backbone.auth.authmanager.infra.web.oa12060;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo.Oa12060Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendars;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarType;

/**
 * OA12060 検索 Presenter
 */
class Oa12060SearchPresenter implements CalendarSearchResponse {

    private LocalDate yearMonth;
    private Calendars calendars;

    /**
     * 年月のＳｅｔ
     *
     * @param yearMonth 年月
     */
    public void setYearMonth(LocalDate yearMonth) { this.yearMonth = yearMonth; }
    /**
     * カレンダー群のＳｅｔ
     *
     * @param calendars カレンダー群
     */
    public void setCalendars(Calendars calendars) { this.calendars = calendars; }

    /**
     * responseに変換
     *
     * @param vo ViewObject
     */
    public void bindTo(Oa12060Vo vo) {

        // 表示対象フィルターチェックdisabledの判定
        boolean calendarTypeFilterCheck1disabled = isCalendarTypeFilterCheckDisabled(CalendarType.経済システム稼働カレンダー);
        boolean calendarTypeFilterCheck2disabled = isCalendarTypeFilterCheckDisabled(CalendarType.信用カレンダー);
        boolean calendarTypeFilterCheck3disabled = isCalendarTypeFilterCheckDisabled(CalendarType.広域物流カレンダー);

        // 表示対象経済選択無効
        vo.setCalendarTypeFilterCheck1disabled(calendarTypeFilterCheck1disabled);
        // 表示対象信用選択無効
        vo.setCalendarTypeFilterCheck2disabled(calendarTypeFilterCheck2disabled);
        // 表示対象広域物流選択無効
        vo.setCalendarTypeFilterCheck3disabled(calendarTypeFilterCheck3disabled);
        if (calendarTypeFilterCheck1disabled && calendarTypeFilterCheck2disabled && calendarTypeFilterCheck3disabled) {
            //表示対象フィルターチェックが全て無効の場合（カレンダーデータが全日付分揃っていない）
            vo.setMessage("対象のカレンダーが登録されていません。");
        }

        // カレンターテーブルHtmlを生成
        vo.setCalendarTable(genCalendarTableHtml());

        // 表示対象フィルターチェックの設定
        vo.setCalendarTypeFilterCheck1(calendarTypeFilterCheck1disabled? (short)0 : (short)1);
        vo.setCalendarTypeFilterCheck2(calendarTypeFilterCheck2disabled? (short)0 : (short)1);
        vo.setCalendarTypeFilterCheck3(calendarTypeFilterCheck3disabled? (short)0 : (short)1);
    }

    /**
     * 表示対象フィルターチェックが無効か判定します。
     *
     * @param calendarType カレンダータイプ
     * @return true:表示対象フィルターチェックが無効
     */
    private boolean isCalendarTypeFilterCheckDisabled(CalendarType calendarType) {

        // カレンダータイプのデータがその月分全て登録済かチェック（日数分の件数でチェック）
        return calendars.getValues().stream().filter(c -> calendarType.equals(c.getCalendarType()))
            .count() != yearMonth.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
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
        if (currentWeek == 6) {classids = "blue-text";}
        classids += " oaex_calendarcell_left";

        // 対象日が稼働日の場合。チェックボックスをチェック済にする（データがない場合は、稼働日）
        int index = day - 1;
        String[] checked = {" checked=\"checked\"", " checked=\"checked\"", " checked=\"checked\""};
        String[] calendarId = {"", "", ""};
        int[] recordVersion = {0, 0, 0};
        for (Calendar calendar : calendarList) {
            // 経済
            if (calendar.getCalendarType().is経済システム稼働カレンダー()) {
                if (calendar.getIsHoliday()) {checked[0] = "";}
                calendarId[0] = calendar.getCalendarId().toString();
                recordVersion[0] = calendar.getRecordVersion();
            }
            // 信用
            if (calendar.getCalendarType().is信用カレンダー()) {
                if (calendar.getIsHoliday()) {checked[1] = "";}
                calendarId[1] = calendar.getCalendarId().toString();
                recordVersion[1] = calendar.getRecordVersion();
            }
            // 広域物流
            if (calendar.getCalendarType().is広域物流カレンダー()) {
                if (calendar.getIsHoliday()) {checked[2] = "";}
                calendarId[2] = calendar.getCalendarId().toString();
                recordVersion[2] = calendar.getRecordVersion();
            }
        }

        StringBuilder html = new StringBuilder();

        // 左セル 日付
        html.append("   <td class=\"").append(classids).append("\">").append(day).append("</td>");
        // 右セル チェックボックス３つ
        html.append("   <td class=\"oaex_calendarcell_right\">");
        html.append("    <div>");
        html.append("     <label class=\"oaex_calendarcell_check1\">");
        html.append(String.format("      <input type=\"checkbox\" id=\"calendar_type_check1[%d]\" name=\"CalendarList[%d].isWorkingDay1\" value=\"1\"%s/>", index, index, checked[0]));
        html.append("      <span>経済</span>");
        html.append("     </label>");
        html.append(String.format("     <input type=\"hidden\" name=\"CalendarList[%d].calendarId1\" value=\"%s\"/>", index, calendarId[0]));
        html.append(String.format("     <input type=\"hidden\" name=\"CalendarList[%d].recordVersion1\" value=\"%d\"/>", index, recordVersion[0]));
        html.append("    </div>");

        html.append("    <div>");
        html.append("     <label class=\"oaex_calendarcell_check2\">");
        html.append(String.format("      <input type=\"checkbox\" id=\"calendar_type_check2[%d]\" name=\"CalendarList[%d].isWorkingDay2\" value=\"1\"%s/>", index, index, checked[1]));
        html.append("      <span>信用</span>");
        html.append("     </label>");
        html.append(String.format("     <input type=\"hidden\" name=\"CalendarList[%d].calendarId2\" value=\"%s\"/>", index, calendarId[1]));
        html.append(String.format("     <input type=\"hidden\" name=\"CalendarList[%d].recordVersion2\" value=\"%d\"/>", index, recordVersion[1]));
        html.append("    </div>");

        html.append("    <div>");
        html.append("     <label class=\"oaex_calendarcell_check3\">");
        html.append(String.format("      <input type=\"checkbox\" id=\"calendar_type_check3[%d]\" name=\"CalendarList[%d].isWorkingDay3\" value=\"1\"%s/>", index, index, checked[2]));
        html.append("      <span>広域物流</span>");
        html.append("     </label>");
        html.append(String.format("     <input type=\"hidden\" name=\"CalendarList[%d].calendarId3\" value=\"%s\"/>", index, calendarId[2]));
        html.append(String.format("     <input type=\"hidden\" name=\"CalendarList[%d].recordVersion3\" value=\"%d\"/>", index, recordVersion[2]));
        html.append("    </div>");
        html.append("   </td>");

        return html.toString();
    }
}
