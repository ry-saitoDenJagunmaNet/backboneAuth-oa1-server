package net.jagunma.backbone.auth.authmanager.application.model.domain.operator.packHistory.operatorHistory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * オペレーター履歴
 */
public class OperatorHistory {

	private OperatorHistoryEntity operatorHistoryEntity;

	// コンストラクタ
	OperatorHistory(OperatorHistoryEntity operatorHistoryEntity) {
		this.operatorHistoryEntity = operatorHistoryEntity;
	}
	// ファクトリーメソッド
	public static OperatorHistory of(OperatorHistoryEntity operatorHistoryEntity) {
		return new OperatorHistory(operatorHistoryEntity);
	}
	// 空生成メソッド
	public static OperatorHistory empty() {
		return new OperatorHistory(new OperatorHistoryEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return operatorHistoryEntity == null || operatorHistoryEntity.sameValueAs(new OperatorHistoryEntity());
	}
	// Getter
	public Long getOperatorHistoryId() { return this.operatorHistoryEntity.getOperatorHistoryId(); }
	public Long getOperatorId() { return this.operatorHistoryEntity.getOperatorId(); }
	public String getOperatorCode() { return this.operatorHistoryEntity.getOperatorCode(); }
	public String getOperatorName() { return this.operatorHistoryEntity.getOperatorName(); }
	public String getMailAddress() { return this.operatorHistoryEntity.getMailAddress(); }
	public LocalDate getExpirationStartDate() { return this.operatorHistoryEntity.getExpirationStartDate(); }
	public LocalDate getExpirationEndDate() { return this.operatorHistoryEntity.getExpirationEndDate(); }
	public Boolean getIsDeviceAuth() { return this.operatorHistoryEntity.getIsDeviceAuth(); }
	public Long getJaId() { return this.operatorHistoryEntity.getJaId(); }
	public String getJaCode() { return this.operatorHistoryEntity.getJaCode(); }
	public Long getTempoId() { return this.operatorHistoryEntity.getTempoId(); }
	public String getTempoCode() { return this.operatorHistoryEntity.getTempoCode(); }
	public Short getAvailableStatus() { return this.operatorHistoryEntity.getAvailableStatus(); }
	public Long getCreatedBy() { return this.operatorHistoryEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.operatorHistoryEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.operatorHistoryEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.operatorHistoryEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.operatorHistoryEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.operatorHistoryEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.operatorHistoryEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです
	 * <strong>
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください <strong/>
	 */
	public OperatorHistoryEntity getOperatorHistoryEntityForRepository() {
		return operatorHistoryEntity;
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
			OperatorHistory that = (OperatorHistory) o;
			return Objects2.equal(this.operatorHistoryEntity.getOperatorHistoryId(), that.getOperatorHistoryId()) &&
				Objects2.equal(this.operatorHistoryEntity.getOperatorId(), that.getOperatorId()) &&
				Objects2.equal(this.operatorHistoryEntity.getOperatorCode(), that.getOperatorCode()) &&
				Objects2.equal(this.operatorHistoryEntity.getOperatorName(), that.getOperatorName()) &&
				Objects2.equal(this.operatorHistoryEntity.getMailAddress(), that.getMailAddress()) &&
				Objects2.equal(this.operatorHistoryEntity.getExpirationStartDate(), that.getExpirationStartDate()) &&
				Objects2.equal(this.operatorHistoryEntity.getExpirationEndDate(), that.getExpirationEndDate()) &&
				Objects2.equal(this.operatorHistoryEntity.getIsDeviceAuth(), that.getIsDeviceAuth()) &&
				Objects2.equal(this.operatorHistoryEntity.getJaId(), that.getJaId()) &&
				Objects2.equal(this.operatorHistoryEntity.getJaCode(), that.getJaCode()) &&
				Objects2.equal(this.operatorHistoryEntity.getTempoId(), that.getTempoId()) &&
				Objects2.equal(this.operatorHistoryEntity.getTempoCode(), that.getTempoCode()) &&
				Objects2.equal(this.operatorHistoryEntity.getAvailableStatus(), that.getAvailableStatus()) &&
				Objects2.equal(this.operatorHistoryEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.operatorHistoryEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.operatorHistoryEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.operatorHistoryEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.operatorHistoryEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.operatorHistoryEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.operatorHistoryEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
