package net.jagunma.backbone.auth.authmanager.application.model.domain.operator.roleAssignment.operator_BizTranRole;

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

	/**
	 * コンストラクタ
	 */
	Operator_BizTranRole(Operator_BizTranRoleEntity operator_BizTranRoleEntity,
		BizTranRole bizTranRole) {

		this.operator_BizTranRoleEntity = operator_BizTranRoleEntity;
		this.bizTranRole = bizTranRole;
	}

	/*
	 * ファクトリメソッド
	 */
	public static Operator_BizTranRole createOf(Operator_BizTranRoleEntity operator_BizTranRoleEntity,
		BizTranRole bizTranRolel) {
		return new Operator_BizTranRole(operator_BizTranRoleEntity, bizTranRolel);
	}

	public static Operator_BizTranRole empty() {
		return new Operator_BizTranRole(new Operator_BizTranRoleEntity(), BizTranRole.empty());
	}

	/**
	 * オペレーター_取引ロール割当IDのＧｅｔ
	 * @return オペレーター_取引ロール割当ID
	 */
	public Long getOperator_BizTranRoleId() { return this.operator_BizTranRoleEntity.getOperator_BizTranRoleId(); }
	/**
	 * オペレーターIDのＧｅｔ
	 * @return オペレーターID
	 */
	public Long getOperatorId() { return this.operator_BizTranRoleEntity.getOperatorId(); }
	/**
	 * 取引ロールIDのＧｅｔ
	 * @return 取引ロールID
	 */
	public Long getBizTranRoleId() { return this.operator_BizTranRoleEntity.getBizTranRoleId(); }
	/**
	 * 有効期限開始日のＧｅｔ
	 * @return 有効期限開始日
	 */
	public LocalDate getExpirationStartDate() { return this.operator_BizTranRoleEntity.getExpirationStartDate(); }
	/**
	 * 有効期限終了日のＧｅｔ
	 * @return 有効期限終了日
	 */
	public LocalDate getExpirationEndDate() { return this.operator_BizTranRoleEntity.getExpirationEndDate(); }
	/**
	 * 登録者IDのＧｅｔ
	 * @return 登録者ID
	 */
	public Long getCreatedBy() { return this.operator_BizTranRoleEntity.getCreatedBy(); }
	/**
	 * 登録日時のＧｅｔ
	 * @return 登録日時
	 */
	public LocalDateTime getCreatedAt() { return this.operator_BizTranRoleEntity.getCreatedAt(); }
	/**
	 * 登録元IPアドレスのＧｅｔ
	 * @return 登録元IPアドレス
	 */
	public String getCreatedIpAddress() { return this.operator_BizTranRoleEntity.getCreatedIpAddress(); }
	/**
	 * 更新者IDのＧｅｔ
	 * @return 更新者ID
	 */
	public Long getUpdatedBy() { return this.operator_BizTranRoleEntity.getUpdatedBy(); }
	/**
	 * 更新日時のＧｅｔ
	 * @return 更新日時
	 */
	public LocalDateTime getUpdatedAt() { return this.operator_BizTranRoleEntity.getUpdatedAt(); }
	/**
	 * 更新元IPアドレスのＧｅｔ
	 * @return 更新元IPアドレス
	 */
	public String getUpdatedIpAddress() { return this.operator_BizTranRoleEntity.getUpdatedIpAddress(); }
	/**
	 * レコードバージョンのＧｅｔ
	 * @return レコードバージョン
	 */
	public Integer getRecordVersion() { return this.operator_BizTranRoleEntity.getRecordVersion(); }
	/**
	 * 取引ロールのＧｅｔ
	 * @return 取引ロール
	 */
	public BizTranRole getBizTranRole() { return this.bizTranRole; }

	/**
	 * リポジトリ用のEntityGetterです
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください
	 */
	public Operator_BizTranRoleEntity getOperator_BizTranRoleEntityForRepository() {
		return operator_BizTranRoleEntity;
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
