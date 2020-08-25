package net.jagunma.backbone.auth.authmanager.application.model.domain.suspendBizTran;

import java.time.LocalDate;
import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * 一時取引抑止
 */
public class SuspendBizTran {

	private final SuspendBizTranEntity suspendBizTranEntity;

	// コンストラクタ
	SuspendBizTran(SuspendBizTranEntity suspendBizTranEntity) {
		this.suspendBizTranEntity = suspendBizTranEntity;
	}
	// ファクトリーメソッド
	public static SuspendBizTran of(SuspendBizTranEntity suspendBizTranEntity) {
		return new SuspendBizTran(suspendBizTranEntity);
	}
	// 空生成メソッド
	public static SuspendBizTran empty() {
		return new SuspendBizTran(new SuspendBizTranEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return suspendBizTranEntity == null || suspendBizTranEntity.sameValueAs(new SuspendBizTranEntity());
	}
	// Getter
	public Long getSuspendBizTranId() { return this.suspendBizTranEntity.getSuspendBizTranId(); }
	public String getJaCode() { return this.suspendBizTranEntity.getJaCode(); }
	public String getTempoCode() { return this.suspendBizTranEntity.getTempoCode(); }
	public String getSubSystemCode() { return this.suspendBizTranEntity.getSubSystemCode(); }
	public String getBizTranGrpCode() { return this.suspendBizTranEntity.getBizTranGrpCode(); }
	public String getBizTranCode() { return this.suspendBizTranEntity.getBizTranCode(); }
	public LocalDate getSuspendStartDate() { return this.suspendBizTranEntity.getSuspendStartDate(); }
	public LocalDate getSuspendEndDate() { return this.suspendBizTranEntity.getSuspendEndDate(); }
	public String getSuspendReason() { return this.suspendBizTranEntity.getSuspendReason(); }
	public Long getCreatedBy() { return this.suspendBizTranEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.suspendBizTranEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.suspendBizTranEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.suspendBizTranEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.suspendBizTranEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.suspendBizTranEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.suspendBizTranEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです。
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください
	 */
	public SuspendBizTranEntity getSuspendBizTranEntityForRepository() {
		return suspendBizTranEntity;
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
			SuspendBizTran that = (SuspendBizTran) o;
			return Objects2.equal(this.suspendBizTranEntity.getSuspendBizTranId(), that.getSuspendBizTranId()) &&
				Objects2.equal(this.suspendBizTranEntity.getJaCode(), that.getJaCode()) &&
				Objects2.equal(this.suspendBizTranEntity.getTempoCode(), that.getTempoCode()) &&
				Objects2.equal(this.suspendBizTranEntity.getSubSystemCode(), that.getSubSystemCode()) &&
				Objects2.equal(this.suspendBizTranEntity.getBizTranGrpCode(), that.getBizTranGrpCode()) &&
				Objects2.equal(this.suspendBizTranEntity.getBizTranCode(), that.getBizTranCode()) &&
				Objects2.equal(this.suspendBizTranEntity.getSuspendStartDate(), that.getSuspendStartDate()) &&
				Objects2.equal(this.suspendBizTranEntity.getSuspendEndDate(), that.getSuspendEndDate()) &&
				Objects2.equal(this.suspendBizTranEntity.getSuspendReason(), that.getSuspendReason()) &&
				Objects2.equal(this.suspendBizTranEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.suspendBizTranEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.suspendBizTranEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.suspendBizTranEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.suspendBizTranEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.suspendBizTranEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.suspendBizTranEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
