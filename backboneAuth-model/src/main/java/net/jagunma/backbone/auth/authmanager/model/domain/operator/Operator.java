package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * オペレーター
 */
public class Operator {

	private final OperatorEntity operatorEntity;

	// コンストラクタ
	Operator(OperatorEntity operatorEntity) {
		this.operatorEntity = operatorEntity;
	}
	// ファクトリーメソッド
	public static Operator of(OperatorEntity operatorEntity) {
		return new Operator(operatorEntity);
	}
	// 空生成メソッド
	public static Operator empty() {
		return new Operator(new OperatorEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return operatorEntity == null || operatorEntity.sameValueAs(new OperatorEntity());
	}
	// Getter
	public Long getOperatorId() { return this.operatorEntity.getOperatorId(); }
	public String getOperatorCode() { return this.operatorEntity.getOperatorCode(); }
	public String getOperatorName() { return this.operatorEntity.getOperatorName(); }
	public String getMailAddress() { return this.operatorEntity.getMailAddress(); }
	public LocalDate getExpirationStartDate() { return this.operatorEntity.getExpirationStartDate(); }
	public LocalDate getExpirationEndDate() { return this.operatorEntity.getExpirationEndDate(); }
	public Boolean getIsDeviceAuth() { return this.operatorEntity.getIsDeviceAuth(); }
	public Long getJaId() { return this.operatorEntity.getJaId(); }
	public String getJaCode() { return this.operatorEntity.getJaCode(); }
	public Long getTempoId() { return this.operatorEntity.getTempoId(); }
	public String getTempoCode() { return this.operatorEntity.getTempoCode(); }
	public Short getAvailableStatus() { return this.operatorEntity.getAvailableStatus(); }
	public Long getCreatedBy() { return this.operatorEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.operatorEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.operatorEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.operatorEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.operatorEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.operatorEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.operatorEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです。
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください
	 */
	public OperatorEntity getOperatorEntityForRepository() {
		return operatorEntity;
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
			Operator that = (Operator) o;
			return Objects2.equal(this.operatorEntity.getOperatorId(), that.getOperatorId()) &&
				Objects2.equal(this.operatorEntity.getOperatorCode(), that.getOperatorCode()) &&
				Objects2.equal(this.operatorEntity.getOperatorName(), that.getOperatorName()) &&
				Objects2.equal(this.operatorEntity.getMailAddress(), that.getMailAddress()) &&
				Objects2.equal(this.operatorEntity.getExpirationStartDate(), that.getExpirationStartDate()) &&
				Objects2.equal(this.operatorEntity.getExpirationEndDate(), that.getExpirationEndDate()) &&
				Objects2.equal(this.operatorEntity.getIsDeviceAuth(), that.getIsDeviceAuth()) &&
				Objects2.equal(this.operatorEntity.getJaId(), that.getJaId()) &&
				Objects2.equal(this.operatorEntity.getJaCode(), that.getJaCode()) &&
				Objects2.equal(this.operatorEntity.getTempoId(), that.getTempoId()) &&
				Objects2.equal(this.operatorEntity.getTempoCode(), that.getTempoCode()) &&
				Objects2.equal(this.operatorEntity.getAvailableStatus(), that.getAvailableStatus()) &&
				Objects2.equal(this.operatorEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.operatorEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.operatorEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.operatorEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.operatorEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.operatorEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.operatorEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
