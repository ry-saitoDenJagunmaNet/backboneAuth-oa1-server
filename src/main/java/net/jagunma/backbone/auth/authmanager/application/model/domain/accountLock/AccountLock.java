package net.jagunma.backbone.auth.authmanager.application.model.domain.accountLock;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * アカウントロック
 */
public class AccountLock {

	private final AccountLockEntity accountLockEntity;

	// コンストラクタ
	AccountLock(AccountLockEntity accountLockEntity) {
		this.accountLockEntity = accountLockEntity;
	}
	// ファクトリーメソッド
	public static AccountLock of(AccountLockEntity accountLockEntity) {
		return new AccountLock(accountLockEntity);
	}
	// 空生成メソッド
	public static AccountLock empty() {
		return new AccountLock(new AccountLockEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return accountLockEntity == null || accountLockEntity.sameValueAs(new AccountLockEntity());
	}
	// Getter
	public Long getAccountLockId() { return this.accountLockEntity.getAccountLockId(); }
	public Long getOperatorId() { return this.accountLockEntity.getOperatorId(); }
	public LocalDateTime getOccurredDateTime() { return this.accountLockEntity.getOccurredDateTime(); }
	public Short getLockStatus() { return this.accountLockEntity.getLockStatus(); }
	public Long getCreatedBy() { return this.accountLockEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.accountLockEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.accountLockEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.accountLockEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.accountLockEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.accountLockEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.accountLockEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです。
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください
	 */
	public AccountLockEntity getAccountLockEntityForRepository() {
		return accountLockEntity;
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
			AccountLock that = (AccountLock) o;
			return Objects2.equal(this.accountLockEntity.getAccountLockId(), that.getAccountLockId()) &&
				Objects2.equal(this.accountLockEntity.getOperatorId(), that.getOperatorId()) &&
				Objects2.equal(this.accountLockEntity.getOccurredDateTime(), that.getOccurredDateTime()) &&
				Objects2.equal(this.accountLockEntity.getLockStatus(), that.getLockStatus()) &&
				Objects2.equal(this.accountLockEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.accountLockEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.accountLockEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.accountLockEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.accountLockEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.accountLockEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.accountLockEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
