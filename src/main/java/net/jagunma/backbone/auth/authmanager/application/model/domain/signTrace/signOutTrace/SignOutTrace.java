package net.jagunma.backbone.auth.authmanager.application.model.domain.signTrace.signOutTrace;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.signOutTrace.SignOutTraceEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * サインアウト証跡
 */
public class SignOutTrace {

	private final SignOutTraceEntity signOutTraceEntity;

	// コンストラクタ
	SignOutTrace(SignOutTraceEntity signOutTraceEntity) {
		this.signOutTraceEntity = signOutTraceEntity;
	}
	// ファクトリーメソッド
	public static SignOutTrace of(SignOutTraceEntity signOutTraceEntity) {
		return new SignOutTrace(signOutTraceEntity);
	}
	// 空生成メソッド
	public static SignOutTrace empty() {
		return new SignOutTrace(new SignOutTraceEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return signOutTraceEntity == null || signOutTraceEntity.sameValueAs(new SignOutTraceEntity());
	}
	// Getter
	public Long getSignOutTraceId() { return this.signOutTraceEntity.getSignOutTraceId(); }
	public LocalDateTime getSignOutDateTime() { return this.signOutTraceEntity.getSignOutDateTime(); }
	public String getSignOutIpAddress() { return this.signOutTraceEntity.getSignOutIpAddress(); }
	public Long getOperatorId() { return this.signOutTraceEntity.getOperatorId(); }
	public Long getCreatedBy() { return this.signOutTraceEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.signOutTraceEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.signOutTraceEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.signOutTraceEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.signOutTraceEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.signOutTraceEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.signOutTraceEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです。
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください
	 */
	public SignOutTraceEntity getSignOutTraceEntityForRepository() {
		return signOutTraceEntity;
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
			SignOutTrace that = (SignOutTrace) o;
			return Objects2.equal(this.signOutTraceEntity.getSignOutTraceId(), that.getSignOutTraceId()) &&
				Objects2.equal(this.signOutTraceEntity.getSignOutDateTime(), that.getSignOutDateTime()) &&
				Objects2.equal(this.signOutTraceEntity.getSignOutIpAddress(), that.getSignOutIpAddress()) &&
				Objects2.equal(this.signOutTraceEntity.getOperatorId(), that.getOperatorId()) &&
				Objects2.equal(this.signOutTraceEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.signOutTraceEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.signOutTraceEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.signOutTraceEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.signOutTraceEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.signOutTraceEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.signOutTraceEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
