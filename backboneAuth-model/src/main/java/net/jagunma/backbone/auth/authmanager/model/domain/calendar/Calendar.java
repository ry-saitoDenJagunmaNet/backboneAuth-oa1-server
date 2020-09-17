package net.jagunma.backbone.auth.authmanager.model.domain.calendar;

import java.time.LocalDate;

/**
 * カレンダー
 */
public class Calendar {

    private Long calendarId;
    private CalendarType calendarType;
    private LocalDate date;
    private Boolean isHoliday;
    private Boolean isManualChange;
    private Boolean isRelease;
    private Integer recordVersion;

    // コンストラクタ
    Calendar(
        Long calendarId,
        CalendarType calendarType,
        LocalDate date,
        Boolean isHoliday,
        Boolean isManualChange,
        Boolean isRelease,
        Integer recordVersion) {

        this.calendarId = calendarId;
        this.calendarType = calendarType;
        this.date = date;
        this.isHoliday = isHoliday;
        this.isManualChange = isManualChange;
        this.isRelease = isRelease;
        this.recordVersion = recordVersion;
    }

    // ファクトリーメソッド
    public static Calendar createFrom(
        Long calendarId,
        CalendarType calendarType,
        LocalDate date,
        Boolean isHoliday,
        Boolean isManualChange,
        Boolean isRelease,
        Integer recordVersion) {

        return new Calendar(
            calendarId,
            calendarType,
            date,
            isHoliday,
            isManualChange,
            isRelease,
            recordVersion);
    }

    // Getter
    public Long getCalendarId() { return calendarId; }
    public CalendarType getCalendarType() { return calendarType; }
    public LocalDate getDate() { return date; }
    public Boolean getIsHoliday() { return isHoliday; }
    public Boolean getIsManualChange() { return isManualChange; }
    public Boolean getIsRelease() { return isRelease; }
    public Integer getRecordVersion() { return recordVersion; }
}
