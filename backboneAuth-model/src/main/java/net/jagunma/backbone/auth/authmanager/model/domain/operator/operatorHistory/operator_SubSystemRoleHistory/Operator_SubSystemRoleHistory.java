package net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operator_SubSystemRoleHistory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistoryEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * オペレーター_サブシステムロール割当履歴
 */
public class Operator_SubSystemRoleHistory {

	private Operator_SubSystemRoleHistoryEntity operator_SubSystemRoleHistoryEntity;

	// コンストラクタ
	Operator_SubSystemRoleHistory(Operator_SubSystemRoleHistoryEntity operator_SubSystemRoleHistoryEntity) {
		this.operator_SubSystemRoleHistoryEntity = operator_SubSystemRoleHistoryEntity;
	}
	// ファクトリーメソッド
	public static Operator_SubSystemRoleHistory of(Operator_SubSystemRoleHistoryEntity operator_SubSystemRoleHistoryEntity) {
		return new Operator_SubSystemRoleHistory(operator_SubSystemRoleHistoryEntity);
	}
	// 空生成メソッド
	public static Operator_SubSystemRoleHistory empty() {
		return new Operator_SubSystemRoleHistory(new Operator_SubSystemRoleHistoryEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return operator_SubSystemRoleHistoryEntity == null || operator_SubSystemRoleHistoryEntity.sameValueAs(new Operator_SubSystemRoleHistoryEntity());
	}
	// Getter
	public Long getOperatorHistoryId() { return this.operator_SubSystemRoleHistoryEntity.getOperatorHistoryId(); }
	public Long getOperatorSubSystemRoleId() { return this.operator_SubSystemRoleHistoryEntity.getOperator_SubSystemRoleId(); }
	public Long getOperatorId() { return this.operator_SubSystemRoleHistoryEntity.getOperatorId(); }
	public String getSubSystemRoleCode() { return this.operator_SubSystemRoleHistoryEntity.getSubSystemRoleCode(); }
	public LocalDate getExpirationStartDate() { return this.operator_SubSystemRoleHistoryEntity.getExpirationStartDate(); }
	public LocalDate getExpirationEndDate() { return this.operator_SubSystemRoleHistoryEntity.getExpirationEndDate(); }
	public Long getCreatedBy() { return this.operator_SubSystemRoleHistoryEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.operator_SubSystemRoleHistoryEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.operator_SubSystemRoleHistoryEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.operator_SubSystemRoleHistoryEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.operator_SubSystemRoleHistoryEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.operator_SubSystemRoleHistoryEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.operator_SubSystemRoleHistoryEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです。
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください
	 */
	public Operator_SubSystemRoleHistoryEntity getOperator_SubSystemRoleHistoryEntityForRepository() {
		return operator_SubSystemRoleHistoryEntity;
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
			Operator_SubSystemRoleHistory that = (Operator_SubSystemRoleHistory) o;
			return Objects2.equal(this.operator_SubSystemRoleHistoryEntity.getOperatorHistoryId(), that.getOperatorHistoryId()) &&
				Objects2.equal(this.operator_SubSystemRoleHistoryEntity.getOperator_SubSystemRoleId(), that.getOperatorSubSystemRoleId()) &&
				Objects2.equal(this.operator_SubSystemRoleHistoryEntity.getOperatorId(), that.getOperatorId()) &&
				Objects2.equal(this.operator_SubSystemRoleHistoryEntity.getSubSystemRoleCode(), that.getSubSystemRoleCode()) &&
				Objects2.equal(this.operator_SubSystemRoleHistoryEntity.getExpirationStartDate(), that.getExpirationStartDate()) &&
				Objects2.equal(this.operator_SubSystemRoleHistoryEntity.getExpirationEndDate(), that.getExpirationEndDate()) &&
				Objects2.equal(this.operator_SubSystemRoleHistoryEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.operator_SubSystemRoleHistoryEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.operator_SubSystemRoleHistoryEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.operator_SubSystemRoleHistoryEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.operator_SubSystemRoleHistoryEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.operator_SubSystemRoleHistoryEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.operator_SubSystemRoleHistoryEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
