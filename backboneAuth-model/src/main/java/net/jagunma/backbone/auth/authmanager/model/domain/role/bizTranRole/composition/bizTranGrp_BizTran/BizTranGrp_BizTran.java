package net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTranGrp_BizTran;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * 取引グループ_取引割当
 */
public class BizTranGrp_BizTran {

	private final BizTranGrp_BizTranEntity bizTranGrp_BizTranEntity;

	// コンストラクタ
	BizTranGrp_BizTran(BizTranGrp_BizTranEntity bizTranGrp_BizTranEntity) {
		this.bizTranGrp_BizTranEntity = bizTranGrp_BizTranEntity;
	}
	// ファクトリーメソッド
	public static BizTranGrp_BizTran of(BizTranGrp_BizTranEntity bizTranGrp_BizTranEntity) {
		return new BizTranGrp_BizTran(bizTranGrp_BizTranEntity);
	}
	// 空生成メソッド
	public static BizTranGrp_BizTran empty() {
		return new BizTranGrp_BizTran(new BizTranGrp_BizTranEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return bizTranGrp_BizTranEntity == null || bizTranGrp_BizTranEntity.sameValueAs(new BizTranGrp_BizTranEntity());
	}
	// Getter
	public Long getBizTranGrp_BizTranId() { return this.bizTranGrp_BizTranEntity.getBizTranGrp_BizTranId(); }
	public Long getBizTranGrpId() { return this.bizTranGrp_BizTranEntity.getBizTranGrpId(); }
	public Long getBizTranId() { return this.bizTranGrp_BizTranEntity.getBizTranId(); }
	public String getSubSystemCode() { return this.bizTranGrp_BizTranEntity.getSubSystemCode(); }
	public Long getCreatedBy() { return this.bizTranGrp_BizTranEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.bizTranGrp_BizTranEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.bizTranGrp_BizTranEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.bizTranGrp_BizTranEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.bizTranGrp_BizTranEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.bizTranGrp_BizTranEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.bizTranGrp_BizTranEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです。
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください
	 */
	public BizTranGrp_BizTranEntity getBizTranGrp_BizTranEntityForRepository() {
		return bizTranGrp_BizTranEntity;
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
			BizTranGrp_BizTran that = (BizTranGrp_BizTran) o;
			return Objects2.equal(this.bizTranGrp_BizTranEntity.getBizTranGrp_BizTranId(), that.getBizTranGrp_BizTranId()) &&
				Objects2.equal(this.bizTranGrp_BizTranEntity.getBizTranGrpId(), that.getBizTranGrpId()) &&
				Objects2.equal(this.bizTranGrp_BizTranEntity.getBizTranId(), that.getBizTranId()) &&
				Objects2.equal(this.bizTranGrp_BizTranEntity.getSubSystemCode(), that.getSubSystemCode()) &&
				Objects2.equal(this.bizTranGrp_BizTranEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.bizTranGrp_BizTranEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.bizTranGrp_BizTranEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.bizTranGrp_BizTranEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.bizTranGrp_BizTranEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.bizTranGrp_BizTranEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.bizTranGrp_BizTranEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
