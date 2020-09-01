package net.jagunma.backbone.auth.authmanager.model.domain.calendar;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.types.CalendarType;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * カレンダー
 */
public class Calendar {

	private final CalendarEntity calendarEntity;

	// コンストラクタ
	Calendar(CalendarEntity calendarEntity) {
		this.calendarEntity = calendarEntity;
	}
	// ファクトリーメソッド
	public static Calendar of(CalendarEntity calendarEntity) {
		return new Calendar(calendarEntity);
	}
	public static Calendar createFrom(
		Long calendarId,
		CalendarType calendarType,
		LocalDate date,
		Boolean isHoliday,
		Boolean isManualChange,
		Boolean isRelease,
		Integer recordVersion) {

		CalendarEntity entity = new CalendarEntity();
		entity.setCalendarId(calendarId);
		entity.setCalendarType(calendarType.getCode());
		entity.setDate(date);
		entity.setIsHoliday(isHoliday);
		entity.setIsManualChange(isManualChange);
		entity.setIsRelease(isRelease);
		entity.setRecordVersion(recordVersion);

		return new Calendar(entity);
	}
	// 空生成メソッド
	public static Calendar empty() { return new Calendar(new CalendarEntity()); }
	// 空判定メソッド
	public boolean isEmpty() {
		return calendarEntity == null || calendarEntity.sameValueAs(new CalendarEntity());
	}
	// Getter
	public Long getCalendarId()	{ return calendarEntity.getCalendarId(); }
	public CalendarType getCalendarType() { return CalendarType.codeOf(calendarEntity.getCalendarType()); }
	public LocalDate getDate() { return calendarEntity.getDate(); }
	public Boolean getIsHoliday() { return calendarEntity.getIsHoliday(); }
	public Boolean getIsManualChange() { return calendarEntity.getIsManualChange(); }
	public Boolean getIsRelease() { return calendarEntity.getIsRelease(); }
	public Integer getRecordVersion() { return calendarEntity.getRecordVersion(); }

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
