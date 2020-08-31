package net.jagunma.backbone.auth.authmanager.model.domain.history.operatorHistoryPack.operator_BizTranRoleHistory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRoleHistory.Operator_BizTranRoleHistoryEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * オペレーター_取引ロール割当履歴
 */
public class Operator_BizTranRoleHistory {

	private Operator_BizTranRoleHistoryEntity operator_BizTranRoleHistoryEntity;

	// コンストラクタ
	Operator_BizTranRoleHistory(Operator_BizTranRoleHistoryEntity operator_BizTranRoleHistoryEntity) {
		this.operator_BizTranRoleHistoryEntity = operator_BizTranRoleHistoryEntity;
	}
	// ファクトリーメソッド
	public static Operator_BizTranRoleHistory of(Operator_BizTranRoleHistoryEntity operator_BizTranRoleHistoryEntity) {
		return new Operator_BizTranRoleHistory(operator_BizTranRoleHistoryEntity);
	}
	// 空生成メソッド
	public static Operator_BizTranRoleHistory empty() {
		return new Operator_BizTranRoleHistory(new Operator_BizTranRoleHistoryEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return operator_BizTranRoleHistoryEntity == null || operator_BizTranRoleHistoryEntity.sameValueAs(new Operator_BizTranRoleHistoryEntity());
	}
	// Getter
	public Long getOperatorHistoryId() { return this.operator_BizTranRoleHistoryEntity.getOperatorHistoryId(); }
	public Long getOperatorBizTranRoleId() { return this.operator_BizTranRoleHistoryEntity.getOperator_BizTranRoleId(); }
	public Long getOperatorId() { return this.operator_BizTranRoleHistoryEntity.getOperatorId(); }
	public Long getBizTranRoleId() { return this.operator_BizTranRoleHistoryEntity.getBizTranRoleId(); }
	public LocalDate getExpirationStartDate() { return this.operator_BizTranRoleHistoryEntity.getExpirationStartDate(); }
	public LocalDate getExpirationEndDate() { return this.operator_BizTranRoleHistoryEntity.getExpirationEndDate(); }
	public Long getCreatedBy() { return this.operator_BizTranRoleHistoryEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.operator_BizTranRoleHistoryEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.operator_BizTranRoleHistoryEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.operator_BizTranRoleHistoryEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.operator_BizTranRoleHistoryEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.operator_BizTranRoleHistoryEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.operator_BizTranRoleHistoryEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです。
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください
	 */
	public Operator_BizTranRoleHistoryEntity getOperator_BizTranRoleHistoryEntityForRepository() {
		return operator_BizTranRoleHistoryEntity;
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
			Operator_BizTranRoleHistory that = (Operator_BizTranRoleHistory) o;
			return Objects2.equal(this.operator_BizTranRoleHistoryEntity.getOperatorHistoryId(), that.getOperatorHistoryId()) &&
				Objects2.equal(this.operator_BizTranRoleHistoryEntity.getOperator_BizTranRoleId(), that.getOperatorBizTranRoleId()) &&
				Objects2.equal(this.operator_BizTranRoleHistoryEntity.getOperatorId(), that.getOperatorId()) &&
				Objects2.equal(this.operator_BizTranRoleHistoryEntity.getBizTranRoleId(), that.getBizTranRoleId()) &&
				Objects2.equal(this.operator_BizTranRoleHistoryEntity.getExpirationStartDate(), that.getExpirationStartDate()) &&
				Objects2.equal(this.operator_BizTranRoleHistoryEntity.getExpirationEndDate(), that.getExpirationEndDate()) &&
				Objects2.equal(this.operator_BizTranRoleHistoryEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.operator_BizTranRoleHistoryEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.operator_BizTranRoleHistoryEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.operator_BizTranRoleHistoryEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.operator_BizTranRoleHistoryEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.operator_BizTranRoleHistoryEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.operator_BizTranRoleHistoryEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
