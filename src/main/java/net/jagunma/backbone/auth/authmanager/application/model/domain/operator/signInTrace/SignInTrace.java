package net.jagunma.backbone.auth.authmanager.application.model.domain.operator.signInTrace;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * サインイン証跡
 */
public class SignInTrace {

	private SignInTraceEntity signInTraceEntity;

	// コンストラクタ
	SignInTrace(SignInTraceEntity signInTraceEntity) {
		this.signInTraceEntity = signInTraceEntity;
	}
	// ファクトリーメソッド
	public static SignInTrace of(SignInTraceEntity signInTraceEntity) {
		return new SignInTrace(signInTraceEntity);
	}
	// 空生成メソッド
	public static SignInTrace empty() {
		return new SignInTrace(new SignInTraceEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return signInTraceEntity == null || signInTraceEntity.sameValueAs(new SignInTraceEntity());
	}
	// Getter
	public Long getSignInTraceId() { return this.signInTraceEntity.getSignInTraceId(); }
	public LocalDateTime getTryDateTime() { return this.signInTraceEntity.getTryDateTime(); }
	public String getTryIpAddress() { return this.signInTraceEntity.getTryIpAddress(); }
	public String getOperatorCode() { return this.signInTraceEntity.getOperatorCode(); }
	public Short getSignInCause() { return this.signInTraceEntity.getSignInCause(); }
	public Short getSignInResult() { return this.signInTraceEntity.getSignInResult(); }
	public Long getCreatedBy() { return this.signInTraceEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.signInTraceEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.signInTraceEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.signInTraceEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.signInTraceEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.signInTraceEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.signInTraceEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです
	 * <strong>
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください <strong/>
	 */
	public SignInTraceEntity getOperatorHistoryEntityForRepository() {
		return signInTraceEntity;
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
			SignInTrace that = (SignInTrace) o;
			return Objects2.equal(this.signInTraceEntity.getSignInTraceId(), that.getSignInTraceId()) &&
				Objects2.equal(this.signInTraceEntity.getTryDateTime(), that.getTryDateTime()) &&
				Objects2.equal(this.signInTraceEntity.getTryIpAddress(), that.getTryIpAddress()) &&
				Objects2.equal(this.signInTraceEntity.getOperatorCode(), that.getOperatorCode()) &&
				Objects2.equal(this.signInTraceEntity.getSignInCause(), that.getSignInCause()) &&
				Objects2.equal(this.signInTraceEntity.getSignInResult(), that.getSignInResult()) &&
				Objects2.equal(this.signInTraceEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.signInTraceEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.signInTraceEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.signInTraceEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.signInTraceEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.signInTraceEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.signInTraceEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
