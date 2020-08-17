package net.jagunma.backbone.auth.authmanager.application.model.domain.operator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistoryPackHeader.OperatorHistoryPackHeaderEntity;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * オペレーター
 */
public class Operator {

	private OperatorEntity operatorEntity;
	private PasswordHistoryEntity passwordHistoryEntity;
	private OperatorHistoryPackHeaderEntity operatorHistoryPackHeaderEntity;

	// コンストラクタ
	Operator(OperatorEntity operatorEntity) {
		this.operatorEntity = operatorEntity;
	}
	Operator(OperatorEntity operatorEntity, PasswordHistoryEntity passwordHistoryEntity) {
		this.operatorEntity = operatorEntity;
		this.passwordHistoryEntity = passwordHistoryEntity;
	}

	// ファクトリーメソッド
	public static Operator of(OperatorEntity operatorEntity) {
		return new Operator(operatorEntity);
	}
	public static Operator createOf(OperatorEntity operatorEntity, PasswordHistoryEntity passwordHistoryEntity) {
		return new Operator(operatorEntity);
	}
	public static Operator createOf(OperatorEntity operatorEntity, OperatorHistoryPackHeaderEntity operatorHistoryPackHeaderEntity) {
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

	public PasswordHistoryEntity getPasswordHistory() {return this.passwordHistoryEntity;}




//	public String getTempoName() { return tempoName; }
//	public long getAccountLockId() { return accountLockId; }
//	public LocalDateTime getOccurredDateTime() { return occurredDateTime; }
//	public Short getLockStatus() { return lockStatus; }
//	public Long getPasswordHistoryId() { return passwordHistoryId; }
//	public LocalDateTime getChangeDateTime() { return changeDateTime; }
//	public Short getChangeType() { return changeType; }
//	public Long getSignInTraceId() { return signInTraceId; }
//	public LocalDateTime getTryDateTime() { return tryDateTime; }
//	public String getTryIpAddress() { return tryIpAddress; }
//	public Short getSignInCause() { return signInCause; }
//	public Short getSignInResult() { return signInResult; }
//	public Long getSignOutTraceId() { return signOutTraceId; }
//	public LocalDateTime getSignOutDateTime() { return signOutDateTime; }
//	public String getSignOutIpAddress() { return signOutIpAddress; }




	/**
	 * リポジトリ用のEntityGetterです
	 * <strong>
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください <strong/>
	 */
	public OperatorEntity getOperatorEntityForRepository() {
		return operatorEntity;
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
			Operator that = (Operator) o;
			return  true;
//			return Objects2.equal(this.operatorEntity.getOperatorId(), that.getOperatorId()) &&
//				Objects2.equal(this.operatorEntity.getOperatorCode(), that.getOperatorCode()) &&
//				Objects2.equal(this.operatorEntity.getOperatorName(), that.getOperatorName()) &&
//				Objects2.equal(this.operatorEntity.getSubSystemCode(), that.getSubSystemCode()) &&
//				Objects2.equal(this.operatorEntity.getCreatedBy(), that.getCreatedBy()) &&
//				Objects2.equal(this.operatorEntity.getCreatedAt(), that.getCreatedAt()) &&
//				Objects2.equal(this.operatorEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
//				Objects2.equal(this.operatorEntity.getUpdatedBy(), that.getUpdatedBy()) &&
//				Objects2.equal(this.operatorEntity.getUpdatedAt(), that.getUpdatedAt()) &&
//				Objects2.equal(this.operatorEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
//				Objects2.equal(this.operatorEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
