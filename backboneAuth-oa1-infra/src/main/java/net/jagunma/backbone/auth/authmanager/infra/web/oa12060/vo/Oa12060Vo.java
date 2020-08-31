package net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfResponseVo;

/**
 * OA12060 View Object
 */
public class Oa12060Vo extends BaseOfResponseVo {

    private static final long serialVersionUID = 1L;

    // コンストラクタ
    public Oa12060Vo() {
    }

    public void createFrom(
        String yearMonth,
        Short calendarTypeFilterCheck1,
        Short calendarTypeFilterCheck2,
        Short calendarTypeFilterCheck3,
        String workingdayOrHolidaySelect) {

        this.yearMonth = LocalDate
            .parse(yearMonth + "/01", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        this.calendarTypeFilterCheck1 = calendarTypeFilterCheck1;
        this.calendarTypeFilterCheck2 = calendarTypeFilterCheck2;
        this.calendarTypeFilterCheck3 = calendarTypeFilterCheck3;
        this.workingdayOrHolidaySelect = workingdayOrHolidaySelect;
    }

    /**
     * 年月
     */
    private LocalDate yearMonth;
    /**
     * 年月文字列
     */
    private String yearMonthToString;
    /**
     * 表示対象経済システム稼働
     */
    private Short calendarTypeFilterCheck1;
    /**
     * 表示対象信用
     */
    private Short calendarTypeFilterCheck2;
    /**
     * 表示対象広域物流
     */
    private Short calendarTypeFilterCheck3;
    /**
     * 稼働・休日選択
     */
    private String workingdayOrHolidaySelect;
    /**
     * カレンダー検索結果レスポンス（カレンダーテーブルHtml）
     */
    private Oa12060SearchResponseVo searchResponse = new Oa12060SearchResponseVo();
    /**
     * カレンダーリスト
     */
    private List<Oa12060CalendarVo> CalendarList;

    public LocalDate getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(LocalDate yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getYearMonthToString() {
        return yearMonthToString;
    }

    public void setYearMonthToString(String yearMonthToString) {
        this.yearMonthToString = yearMonthToString;
    }

    public Short getCalendarTypeFilterCheck1() {
        return calendarTypeFilterCheck1;
    }

    public void setCalendarTypeFilterCheck1(Short calendarTypeFilterCheck1) {
        this.calendarTypeFilterCheck1 = calendarTypeFilterCheck1;
    }

    public Short getCalendarTypeFilterCheck2() {
        return calendarTypeFilterCheck2;
    }

    public void setCalendarTypeFilterCheck2(Short calendarTypeFilterCheck2) {
        this.calendarTypeFilterCheck2 = calendarTypeFilterCheck2;
    }

    public Short getCalendarTypeFilterCheck3() {
        return calendarTypeFilterCheck3;
    }

    public void setCalendarTypeFilterCheck3(Short calendarTypeFilterCheck3) {
        this.calendarTypeFilterCheck3 = calendarTypeFilterCheck3;
    }

    public String getWorkingdayOrHolidaySelect() {
        return workingdayOrHolidaySelect;
    }

    public void setWorkingdayOrHolidaySelect(String workingdayOrHolidaySelect) {
        this.workingdayOrHolidaySelect = workingdayOrHolidaySelect;
    }

    public Oa12060SearchResponseVo getSearchResponse() {
        return searchResponse;
    }

    public void setSearchResponse(Oa12060SearchResponseVo searchResponse) {
        this.searchResponse = searchResponse;
    }

    public List<Oa12060CalendarVo> getCalendarList() {
        return CalendarList;
    }

    public void setCalendarList(List<Oa12060CalendarVo> CalendarList) {
        this.CalendarList = CalendarList;
    }
}
