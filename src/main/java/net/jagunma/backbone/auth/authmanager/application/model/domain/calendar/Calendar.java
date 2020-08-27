package net.jagunma.backbone.auth.authmanager.application.model.domain.calendar;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.model.types.CalendarType;
import net.jagunma.common.util.objects2.Objects2;

/**
 * カレンダー
 */
public class Calendar {

	private Long calendarId = null;
	private CalendarType calendarType = null;
	private LocalDate date = null;
	private Boolean isHoliday = null;
	private Boolean isManualChange = null;
	private Boolean isRelease = null;
	private Integer recordVersion = null;

	// コンストラクタ
	Calendar() {}
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
	// 空生成メソッド
	public static Calendar empty() { return new Calendar(); }
	// 空判定メソッド
	public boolean isEmpty() {
		return calendarId == null &&
			calendarType == null &&
			date == null &&
			isHoliday == null &&
			isManualChange == null &&
			isRelease == null &&
			recordVersion == null;
	}
	// Getter
	public Long getCalendarId() {
		return this.calendarId;
	}
	public CalendarType getCalendarType() {
		return this.calendarType;
	}
	public LocalDate getDate() {
		return this.date;
	}
	public Boolean getIsHoliday() {
		return this.isHoliday;
	}
	public Boolean getIsManualChange() {
		return this.isManualChange;
	}
	public Boolean getIsRelease() {
		return this.isRelease;
	}
	public Integer getRecordVersion() {
		return this.recordVersion;
	}

	/**
	 * オブジェクトの比較を行います。
	 *
	 * @param o 比較するオブジェクト
	 * @return true：比較結果は同じ　false：比較結果は差異がある
	 */
	public boolean sameValueAs(Object o) {
		if (this == o) {
			return true;
		} else if (o != null && this.getClass() == o.getClass()) {
			Calendar that = (Calendar) o;
			return Objects2.equal(this.getCalendarId(), that.getCalendarId()) &&
				Objects2.equal(this.getCalendarType(), that.getCalendarType()) &&
				Objects2.equal(this.getDate(), that.getDate()) &&
				Objects2.equal(this.getIsHoliday(), that.getIsHoliday()) &&
				Objects2.equal(this.getIsManualChange(), that.getIsManualChange()) &&
				Objects2.equal(this.getIsRelease(), that.getIsRelease()) &&
				Objects2.equal(this.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
