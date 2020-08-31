package net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTranGrp;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * 取引グループ
 */
public class BizTranGrp {

	private final BizTranGrpEntity bizTranGrpEntity;

	// コンストラクタ
	BizTranGrp(BizTranGrpEntity bizTranGrpEntity) {
		this.bizTranGrpEntity = bizTranGrpEntity;
	}
	// ファクトリーメソッド
	public static BizTranGrp of(BizTranGrpEntity bizTranGrpEntity) {
		return new BizTranGrp(bizTranGrpEntity);
	}
	// 空生成メソッド
	public static BizTranGrp empty() {
		return new BizTranGrp(new BizTranGrpEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return bizTranGrpEntity == null || bizTranGrpEntity.sameValueAs(new BizTranGrpEntity());
	}
	// Getter
	public Long getBizTranGrpId() { return this.bizTranGrpEntity.getBizTranGrpId(); }
	public String getBizTranGrpCode() { return this.bizTranGrpEntity.getBizTranGrpCode(); }
	public String getBizTranGrpName() { return this.bizTranGrpEntity.getBizTranGrpName(); }
	public String getSubSystemCode() { return this.bizTranGrpEntity.getSubSystemCode(); }
	public Long getCreatedBy() { return this.bizTranGrpEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.bizTranGrpEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.bizTranGrpEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.bizTranGrpEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.bizTranGrpEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.bizTranGrpEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.bizTranGrpEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです。
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください
	 */
	public BizTranGrpEntity getBizTranGrpEntityForRepository() {
		return bizTranGrpEntity;
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
			BizTranGrp that = (BizTranGrp) o;
			return Objects2.equal(this.bizTranGrpEntity.getBizTranGrpId(), that.getBizTranGrpId()) &&
				Objects2.equal(this.bizTranGrpEntity.getBizTranGrpCode(), that.getBizTranGrpCode()) &&
				Objects2.equal(this.bizTranGrpEntity.getBizTranGrpName(), that.getBizTranGrpName()) &&
				Objects2.equal(this.bizTranGrpEntity.getSubSystemCode(), that.getSubSystemCode()) &&
				Objects2.equal(this.bizTranGrpEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.bizTranGrpEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.bizTranGrpEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.bizTranGrpEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.bizTranGrpEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.bizTranGrpEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.bizTranGrpEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
