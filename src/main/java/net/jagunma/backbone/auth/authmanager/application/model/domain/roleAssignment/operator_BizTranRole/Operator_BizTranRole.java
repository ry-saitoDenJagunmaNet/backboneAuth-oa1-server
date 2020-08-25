package net.jagunma.backbone.auth.authmanager.application.model.domain.roleAssignment.operator_BizTranRole;

import java.time.LocalDate;
import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * オペレーター_取引ロール割当
 */
public class Operator_BizTranRole {

	private final Operator_BizTranRoleEntity operator_BizTranRoleEntity;
	private final BizTranRole bizTranRole;

	// コンストラクタ
	Operator_BizTranRole(Operator_BizTranRoleEntity operator_BizTranRoleEntity,
		BizTranRole bizTranRole) {
		this.operator_BizTranRoleEntity = operator_BizTranRoleEntity;
		this.bizTranRole = bizTranRole;
	}
	// ファクトリーメソッド
	public static Operator_BizTranRole createOf(Operator_BizTranRoleEntity operator_BizTranRoleEntity,
		BizTranRole bizTranRole) {
		return new Operator_BizTranRole(operator_BizTranRoleEntity, bizTranRole);
	}
	// 空生成メソッド
	public static Operator_BizTranRole empty() {
		return new Operator_BizTranRole(new Operator_BizTranRoleEntity(), BizTranRole.empty());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return operator_BizTranRoleEntity == null || operator_BizTranRoleEntity.sameValueAs(new Operator_BizTranRoleEntity());
	}
	// Getter
	public Long getOperator_BizTranRoleId() { return this.operator_BizTranRoleEntity.getOperator_BizTranRoleId(); }
	public Long getOperatorId() { return this.operator_BizTranRoleEntity.getOperatorId(); }
	public Long getBizTranRoleId() { return this.operator_BizTranRoleEntity.getBizTranRoleId(); }
	public LocalDate getExpirationStartDate() { return this.operator_BizTranRoleEntity.getExpirationStartDate(); }
	public LocalDate getExpirationEndDate() { return this.operator_BizTranRoleEntity.getExpirationEndDate(); }
	public Long getCreatedBy() { return this.operator_BizTranRoleEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.operator_BizTranRoleEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.operator_BizTranRoleEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.operator_BizTranRoleEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.operator_BizTranRoleEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.operator_BizTranRoleEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.operator_BizTranRoleEntity.getRecordVersion(); }
	public BizTranRole getBizTranRole() { return this.bizTranRole; }

	/**
	 * リポジトリ用のEntityGetterです。
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください
	 */
	public Operator_BizTranRoleEntity getOperator_BizTranRoleEntityForRepository() {
		return operator_BizTranRoleEntity;
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
			Operator_BizTranRole that = (Operator_BizTranRole) o;
			return Objects2.equal(this.operator_BizTranRoleEntity.getOperator_BizTranRoleId(), that.getOperator_BizTranRoleId()) &&
				Objects2.equal(this.operator_BizTranRoleEntity.getOperatorId(), that.getOperatorId()) &&
				Objects2.equal(this.operator_BizTranRoleEntity.getBizTranRoleId(), that.getBizTranRoleId()) &&
				Objects2.equal(this.operator_BizTranRoleEntity.getExpirationStartDate(), that.getExpirationStartDate()) &&
				Objects2.equal(this.operator_BizTranRoleEntity.getExpirationEndDate(), that.getExpirationEndDate()) &&
				Objects2.equal(this.operator_BizTranRoleEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.operator_BizTranRoleEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.operator_BizTranRoleEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.operator_BizTranRoleEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.operator_BizTranRoleEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.operator_BizTranRoleEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.operator_BizTranRoleEntity.getRecordVersion(), that.getRecordVersion()) &&
				this.bizTranRole.sameValueAs(that.bizTranRole);
		} else {
			return false;
		}
	}
}
