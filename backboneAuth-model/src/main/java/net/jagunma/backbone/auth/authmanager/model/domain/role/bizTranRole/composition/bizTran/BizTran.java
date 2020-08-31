package net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTran;

import java.time.LocalDate;
import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * 取引
 */
public class BizTran {

	private final BizTranEntity bizTranEntity;

	// コンストラクタ
	BizTran(BizTranEntity bizTranEntity) {
		this.bizTranEntity = bizTranEntity;
	}
	// ファクトリーメソッド
	public static BizTran of(BizTranEntity bizTranEntity) {
		return new BizTran(bizTranEntity);
	}
	// 空生成メソッド
	public static BizTran empty() {
		return new BizTran(new BizTranEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return bizTranEntity == null || bizTranEntity.sameValueAs(new BizTranEntity());
	}
	// Getter
	public Long getBizTranId() { return this.bizTranEntity.getBizTranId(); }
	public String getBizTranCode() { return this.bizTranEntity.getBizTranCode(); }
	public String getBizTranName() { return this.bizTranEntity.getBizTranName(); }
	public Boolean getIsCenterBizTran() { return this.bizTranEntity.getIsCenterBizTran(); }
	public LocalDate getExpirationStartDate() { return this.bizTranEntity.getExpirationStartDate(); }
	public LocalDate getExpirationEndDate() { return this.bizTranEntity.getExpirationEndDate(); }
	public String getSubSystemCode() { return this.bizTranEntity.getSubSystemCode(); }
	public Long getCreatedBy() { return this.bizTranEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.bizTranEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.bizTranEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.bizTranEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.bizTranEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.bizTranEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.bizTranEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです。
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください
	 */
	public BizTranEntity getBizTranEntityForRepository() {
		return bizTranEntity;
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
			BizTran that = (BizTran) o;
			return Objects2.equal(this.bizTranEntity.getBizTranId(), that.getBizTranId()) &&
				Objects2.equal(this.bizTranEntity.getBizTranCode(), that.getBizTranCode()) &&
				Objects2.equal(this.bizTranEntity.getBizTranName(), that.getBizTranName()) &&
				Objects2.equal(this.bizTranEntity.getIsCenterBizTran(), that.getIsCenterBizTran()) &&
				Objects2.equal(this.bizTranEntity.getExpirationStartDate(), that.getExpirationStartDate()) &&
				Objects2.equal(this.bizTranEntity.getExpirationEndDate(), that.getExpirationEndDate()) &&
				Objects2.equal(this.bizTranEntity.getSubSystemCode(), that.getSubSystemCode()) &&
				Objects2.equal(this.bizTranEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.bizTranEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.bizTranEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.bizTranEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.bizTranEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.bizTranEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.bizTranEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
