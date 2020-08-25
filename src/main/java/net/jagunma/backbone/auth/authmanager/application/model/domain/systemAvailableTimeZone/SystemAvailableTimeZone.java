package net.jagunma.backbone.auth.authmanager.application.model.domain.systemAvailableTimeZone;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.systemAvailableTimeZone.SystemAvailableTimeZoneEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * システム利用可能時間帯
 */
public class SystemAvailableTimeZone {

	private final SystemAvailableTimeZoneEntity systemAvailableTimeZoneEntity;

	// コンストラクタ
	SystemAvailableTimeZone(SystemAvailableTimeZoneEntity systemAvailableTimeZoneEntity) {
		this.systemAvailableTimeZoneEntity = systemAvailableTimeZoneEntity;
	}
	// ファクトリーメソッド
	public static SystemAvailableTimeZone of(SystemAvailableTimeZoneEntity systemAvailableTimeZoneEntity) {
		return new SystemAvailableTimeZone(systemAvailableTimeZoneEntity);
	}
	// 空生成メソッド
	public static SystemAvailableTimeZone empty() {
		return new SystemAvailableTimeZone(new SystemAvailableTimeZoneEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return systemAvailableTimeZoneEntity == null || systemAvailableTimeZoneEntity.sameValueAs(new SystemAvailableTimeZoneEntity());
	}
	// Getter
	public Long getSystemAvailableTimeZoneId() { return this.systemAvailableTimeZoneEntity.getSystemAvailableTimeZoneId(); }
	public String getSubSystemCode() { return this.systemAvailableTimeZoneEntity.getSubSystemCode(); }
	public Short getDayOfWeek() { return this.systemAvailableTimeZoneEntity.getDayOfWeek(); }
	public String getStartTime() { return this.systemAvailableTimeZoneEntity.getStartTime(); }
	public String getEndTime() { return this.systemAvailableTimeZoneEntity.getEndTime(); }
	public Long getCreatedBy() { return this.systemAvailableTimeZoneEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.systemAvailableTimeZoneEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.systemAvailableTimeZoneEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.systemAvailableTimeZoneEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.systemAvailableTimeZoneEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.systemAvailableTimeZoneEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.systemAvailableTimeZoneEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです。
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください
	 */
	public SystemAvailableTimeZoneEntity getSystemAvailableTimeZoneEntityForRepository() {
		return systemAvailableTimeZoneEntity;
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
			SystemAvailableTimeZone that = (SystemAvailableTimeZone) o;
			return Objects2.equal(this.systemAvailableTimeZoneEntity.getSystemAvailableTimeZoneId(), that.getSystemAvailableTimeZoneId()) &&
				Objects2.equal(this.systemAvailableTimeZoneEntity.getSubSystemCode(), that.getSubSystemCode()) &&
				Objects2.equal(this.systemAvailableTimeZoneEntity.getDayOfWeek(), that.getDayOfWeek()) &&
				Objects2.equal(this.systemAvailableTimeZoneEntity.getStartTime(), that.getStartTime()) &&
				Objects2.equal(this.systemAvailableTimeZoneEntity.getEndTime(), that.getEndTime()) &&
				Objects2.equal(this.systemAvailableTimeZoneEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.systemAvailableTimeZoneEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.systemAvailableTimeZoneEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.systemAvailableTimeZoneEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.systemAvailableTimeZoneEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.systemAvailableTimeZoneEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.systemAvailableTimeZoneEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
