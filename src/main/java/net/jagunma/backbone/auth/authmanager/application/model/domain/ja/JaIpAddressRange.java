package net.jagunma.backbone.auth.authmanager.application.model.domain.ja;

import java.time.LocalDate;
import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.jaIpAddressRange.JaIpAddressRangeEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * JA割当IPアドレス範囲
 */
public class JaIpAddressRange {

	private JaIpAddressRangeEntity jaIpAddressRangeEntity;

	// コンストラクタ
	JaIpAddressRange(JaIpAddressRangeEntity jaIpAddressRangeEntity) {
		this.jaIpAddressRangeEntity = jaIpAddressRangeEntity;
	}
	// ファクトリーメソッド
	public static JaIpAddressRange of(JaIpAddressRangeEntity jaIpAddressRangeEntity) {
		return new JaIpAddressRange(jaIpAddressRangeEntity);
	}
	// 空生成メソッド
	public static JaIpAddressRange empty() {
		return new JaIpAddressRange(new JaIpAddressRangeEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return jaIpAddressRangeEntity == null || jaIpAddressRangeEntity.sameValueAs(new JaIpAddressRangeEntity());
	}
	// Getter
	public Long getJaIpAddressRangeId() { return this.jaIpAddressRangeEntity.getJaIpAddressRangeId(); }
	public String getJaCode() { return this.jaIpAddressRangeEntity.getJaCode(); }
	public String getIpAddressRange() { return this.jaIpAddressRangeEntity.getIpAddressRange(); }
	public LocalDate getExpirationStartDate() { return this.jaIpAddressRangeEntity.getExpirationStartDate(); }
	public LocalDate getExpirationEndDate() { return this.jaIpAddressRangeEntity.getExpirationEndDate(); }
	public Long getCreatedBy() { return this.jaIpAddressRangeEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.jaIpAddressRangeEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.jaIpAddressRangeEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.jaIpAddressRangeEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.jaIpAddressRangeEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.jaIpAddressRangeEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.jaIpAddressRangeEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです
	 * <strong>
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください <strong/>
	 */
	public JaIpAddressRangeEntity getJaIpAddressRangeEntityForRepository() {
		return jaIpAddressRangeEntity;
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
			JaIpAddressRange that = (JaIpAddressRange) o;
			return Objects2.equal(this.jaIpAddressRangeEntity.getJaIpAddressRangeId(), that.getJaIpAddressRangeId()) &&
				Objects2.equal(this.jaIpAddressRangeEntity.getJaCode(), that.getJaCode()) &&
				Objects2.equal(this.jaIpAddressRangeEntity.getIpAddressRange(), that.getIpAddressRange()) &&
				Objects2.equal(this.jaIpAddressRangeEntity.getExpirationStartDate(), that.getExpirationStartDate()) &&
				Objects2.equal(this.jaIpAddressRangeEntity.getExpirationEndDate(), that.getExpirationEndDate()) &&
				Objects2.equal(this.jaIpAddressRangeEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.jaIpAddressRangeEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.jaIpAddressRangeEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.jaIpAddressRangeEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.jaIpAddressRangeEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.jaIpAddressRangeEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.jaIpAddressRangeEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
