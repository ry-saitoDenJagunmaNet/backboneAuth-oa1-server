package net.jagunma.backbone.auth.authmanager.model.domain.roleAssignment.operator_SubSystemRole;

import java.time.LocalDate;
import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * オペレーター_サブシステムロール割当
 */
public class Operator_SubSystemRole {

//	private final Operator_SubSystemRoleEntity operator_SubSystemRoleEntity;
//	private final SubSystemRole subSystemRole;
//
//	// コンストラクタ
//	Operator_SubSystemRole(Operator_SubSystemRoleEntity operator_SubSystemRoleEntity,
//		SubSystemRole subSystemRole) {
//		this.operator_SubSystemRoleEntity = operator_SubSystemRoleEntity;
//		this.subSystemRole = subSystemRole;
//	}
//	// ファクトリーメソッド
//	public static Operator_SubSystemRole createOf(Operator_SubSystemRoleEntity operator_SubSystemRoleEntity,
//		SubSystemRole subSystemRole) {
//		return new Operator_SubSystemRole(operator_SubSystemRoleEntity, subSystemRole);
//	}
//	// 空生成メソッド
//	public static Operator_SubSystemRole empty() {
//		return new Operator_SubSystemRole(new Operator_SubSystemRoleEntity(), SubSystemRole.empty());
//	}
//	// 空判定メソッド
//	public boolean isEmpty() {
//		return operator_SubSystemRoleEntity == null || operator_SubSystemRoleEntity.sameValueAs(new Operator_SubSystemRoleEntity());
//	}
//	// Getter
//	public Long getOperator_SubSystemRoleId() { return this.operator_SubSystemRoleEntity.getOperator_SubSystemRoleId(); }
//	public Long getOperatorId() { return this.operator_SubSystemRoleEntity.getOperatorId(); }
//	public String getSubSystemRoleCode() { return this.operator_SubSystemRoleEntity.getSubSystemRoleCode(); }
//	public LocalDate getExpirationStartDate() { return this.operator_SubSystemRoleEntity.getExpirationStartDate(); }
//	public LocalDate getExpirationEndDate() { return this.operator_SubSystemRoleEntity.getExpirationEndDate(); }
//	public Long getCreatedBy() { return this.operator_SubSystemRoleEntity.getCreatedBy(); }
//	public LocalDateTime getCreatedAt() { return this.operator_SubSystemRoleEntity.getCreatedAt(); }
//	public String getCreatedIpAddress() { return this.operator_SubSystemRoleEntity.getCreatedIpAddress(); }
//	public Long getUpdatedBy() { return this.operator_SubSystemRoleEntity.getUpdatedBy(); }
//	public LocalDateTime getUpdatedAt() { return this.operator_SubSystemRoleEntity.getUpdatedAt(); }
//	public String getUpdatedIpAddress() { return this.operator_SubSystemRoleEntity.getUpdatedIpAddress(); }
//	public Integer getRecordVersion() { return this.operator_SubSystemRoleEntity.getRecordVersion(); }
//	public SubSystemRole getSubSystemRole() { return this.subSystemRole; }
//
//	/**
//	 * リポジトリ用のEntityGetterです。
//	 *
//	 * @return リポジトリ間で使用するDBEntity
//	 * @implNote 項目を取得する目的では使用しないでください
//	 */
//	public Operator_SubSystemRoleEntity getOperator_SubSystemRoleEntityForRepository() {
//		return operator_SubSystemRoleEntity;
//	}
//
//	/**
//	 * オブジェクトの比較を行います。
//	 *
//	 * @param o 比較するオブジェクト
//	 * @return true：比較結果は同じ　false：比較結果は差異がある
//	 */
//	public boolean sameValueAs(Object o) {
//		if (this == o) {
//			return true;
//		} else if (o != null && this.getClass() == o.getClass()) {
//			Operator_SubSystemRole that = (Operator_SubSystemRole) o;
//			return Objects2.equal(this.operator_SubSystemRoleEntity.getOperator_SubSystemRoleId(), that.getOperator_SubSystemRoleId()) &&
//				Objects2.equal(this.operator_SubSystemRoleEntity.getOperatorId(), that.getOperatorId()) &&
//				Objects2.equal(this.operator_SubSystemRoleEntity.getSubSystemRoleCode(), that.getSubSystemRoleCode()) &&
//				Objects2.equal(this.operator_SubSystemRoleEntity.getExpirationStartDate(), that.getExpirationStartDate()) &&
//				Objects2.equal(this.operator_SubSystemRoleEntity.getExpirationEndDate(), that.getExpirationEndDate()) &&
//				Objects2.equal(this.operator_SubSystemRoleEntity.getCreatedBy(), that.getCreatedBy()) &&
//				Objects2.equal(this.operator_SubSystemRoleEntity.getCreatedAt(), that.getCreatedAt()) &&
//				Objects2.equal(this.operator_SubSystemRoleEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
//				Objects2.equal(this.operator_SubSystemRoleEntity.getUpdatedBy(), that.getUpdatedBy()) &&
//				Objects2.equal(this.operator_SubSystemRoleEntity.getUpdatedAt(), that.getUpdatedAt()) &&
//				Objects2.equal(this.operator_SubSystemRoleEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
//				Objects2.equal(this.operator_SubSystemRoleEntity.getRecordVersion(), that.getRecordVersion()) &&
//				this.subSystemRole.sameValueAs(that.subSystemRole);
//		} else {
//			return false;
//		}
//	}
}
