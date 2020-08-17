package net.jagunma.backbone.auth.authmanager.application.model.domain.calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
	// 空生成メソッド
	public static Calendar empty() {
		return new Calendar(new CalendarEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return calendarEntity == null || calendarEntity.sameValueAs(new CalendarEntity());
	}

	// Getter
	public Long getCalendarId() { return this.calendarEntity.getCalendarId(); }
	public Short getCalendarType() { return this.calendarEntity.getCalendarType(); }
	public LocalDate getDate() { return this.calendarEntity.getDate(); }
	public Boolean getIsHoliday() { return this.calendarEntity.getIsHoliday(); }
	public Boolean getIsManualChange() { return this.calendarEntity.getIsManualChange(); }
	public Boolean getIsRelease() { return this.calendarEntity.getIsRelease(); }
	public Long getCreatedBy() { return this.calendarEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.calendarEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.calendarEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.calendarEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.calendarEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.calendarEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.calendarEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください
	 */
	public CalendarEntity getCalendarEntityForRepository() {
		return calendarEntity;
	}

	/**
	 * オブジェクトの比較を行います。
	 * @param o 比較するオブジェクト
	 * @return true：比較結果は同じ　false：比較結果は差異がある
	 */
	public boolean sameValueAs(Object o) {
		if (this == o) {
			return true;
		} else if (o != null && this.getClass() == o.getClass()) {
			Calendar that = (Calendar) o;
			return Objects2.equal(this.calendarEntity.getCalendarId(), that.getCalendarId()) &&
				Objects2.equal(this.calendarEntity.getCalendarType(), that.getCalendarType()) &&
				Objects2.equal(this.calendarEntity.getDate(), that.getDate()) &&
				Objects2.equal(this.calendarEntity.getIsHoliday(), that.getIsHoliday()) &&
				Objects2.equal(this.calendarEntity.getIsManualChange(), that.getIsManualChange()) &&
				Objects2.equal(this.calendarEntity.getIsRelease(), that.getIsRelease()) &&
				Objects2.equal(this.calendarEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.calendarEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.calendarEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.calendarEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.calendarEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.calendarEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.calendarEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}

}
