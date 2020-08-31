package net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTranRole_BizTranGrp;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * 取引ロール_取引グループ割当
 */
public class BizTranRole_BizTranGrp {

	private final BizTranRole_BizTranGrpEntity bizTranRole_BizTranGrpEntity;

	// コンストラクタ
	BizTranRole_BizTranGrp(BizTranRole_BizTranGrpEntity bizTranRole_BizTranGrpEntity) {
		this.bizTranRole_BizTranGrpEntity = bizTranRole_BizTranGrpEntity;
	}
	// ファクトリーメソッド
	public static BizTranRole_BizTranGrp of(BizTranRole_BizTranGrpEntity bizTranRole_BizTranGrpEntity) {
		return new BizTranRole_BizTranGrp(bizTranRole_BizTranGrpEntity);
	}
	// 空生成メソッド
	public static BizTranRole_BizTranGrp empty() {
		return new BizTranRole_BizTranGrp(new BizTranRole_BizTranGrpEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return bizTranRole_BizTranGrpEntity == null || bizTranRole_BizTranGrpEntity.sameValueAs(new BizTranRole_BizTranGrpEntity());
	}
	// Getter
	public Long getBizTranRole_BizTranGrpId() { return this.bizTranRole_BizTranGrpEntity.getBizTranRole_BizTranGrpId(); }
	public Long getBizTranRoleId() { return this.bizTranRole_BizTranGrpEntity.getBizTranRoleId(); }
	public Long getBizTranGrpId() { return this.bizTranRole_BizTranGrpEntity.getBizTranGrpId(); }
	public String getSubSystemCode() { return this.bizTranRole_BizTranGrpEntity.getSubSystemCode(); }
	public Long getCreatedBy() { return this.bizTranRole_BizTranGrpEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.bizTranRole_BizTranGrpEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.bizTranRole_BizTranGrpEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.bizTranRole_BizTranGrpEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.bizTranRole_BizTranGrpEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.bizTranRole_BizTranGrpEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.bizTranRole_BizTranGrpEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです。
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください
	 */
	public BizTranRole_BizTranGrpEntity getBizTranRole_BizTranGrpEntityForRepository() {
		return bizTranRole_BizTranGrpEntity;
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
			BizTranRole_BizTranGrp that = (BizTranRole_BizTranGrp) o;
			return Objects2.equal(this.bizTranRole_BizTranGrpEntity.getBizTranRole_BizTranGrpId(), that.getBizTranRole_BizTranGrpId()) &&
				Objects2.equal(this.bizTranRole_BizTranGrpEntity.getBizTranRoleId(), that.getBizTranRoleId()) &&
				Objects2.equal(this.bizTranRole_BizTranGrpEntity.getBizTranGrpId(), that.getBizTranGrpId()) &&
				Objects2.equal(this.bizTranRole_BizTranGrpEntity.getSubSystemCode(), that.getSubSystemCode()) &&
				Objects2.equal(this.bizTranRole_BizTranGrpEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.bizTranRole_BizTranGrpEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.bizTranRole_BizTranGrpEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.bizTranRole_BizTranGrpEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.bizTranRole_BizTranGrpEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.bizTranRole_BizTranGrpEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.bizTranRole_BizTranGrpEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
