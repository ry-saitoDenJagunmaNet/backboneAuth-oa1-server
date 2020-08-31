package net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * 取引ロール
 */
public class BizTranRole {

	private final BizTranRoleEntity bizTranRoleEntity;

	// コンストラクタ
	BizTranRole(BizTranRoleEntity bizTranRoleEntity) {
		this.bizTranRoleEntity = bizTranRoleEntity;
	}
	// ファクトリーメソッド
	public static BizTranRole of(BizTranRoleEntity bizTranRoleEntity) {
		return new BizTranRole(bizTranRoleEntity);
	}
	// 空生成メソッド
	public static BizTranRole empty() {
		return new BizTranRole(new BizTranRoleEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return bizTranRoleEntity == null || bizTranRoleEntity.sameValueAs(new BizTranRoleEntity());
	}
	// Getter
	public Long getBizTranRoleId() { return this.bizTranRoleEntity.getBizTranRoleId(); }
	public String getBizTranRoleCode() { return this.bizTranRoleEntity.getBizTranRoleCode(); }
	public String getBizTranRoleName() { return this.bizTranRoleEntity.getBizTranRoleName(); }
	public String getSubSystemCode() { return this.bizTranRoleEntity.getSubSystemCode(); }
	public Long getCreatedBy() { return this.bizTranRoleEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.bizTranRoleEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.bizTranRoleEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.bizTranRoleEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.bizTranRoleEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.bizTranRoleEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.bizTranRoleEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです。
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください
	 */
	public BizTranRoleEntity getBizTranRoleEntityForRepository() {
		return bizTranRoleEntity;
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
			BizTranRole that = (BizTranRole) o;
			return Objects2.equal(this.bizTranRoleEntity.getBizTranRoleId(), that.getBizTranRoleId()) &&
				Objects2.equal(this.bizTranRoleEntity.getBizTranRoleCode(), that.getBizTranRoleCode()) &&
				Objects2.equal(this.bizTranRoleEntity.getBizTranRoleName(), that.getBizTranRoleName()) &&
				Objects2.equal(this.bizTranRoleEntity.getSubSystemCode(), that.getSubSystemCode()) &&
				Objects2.equal(this.bizTranRoleEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.bizTranRoleEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.bizTranRoleEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.bizTranRoleEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.bizTranRoleEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.bizTranRoleEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.bizTranRoleEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
