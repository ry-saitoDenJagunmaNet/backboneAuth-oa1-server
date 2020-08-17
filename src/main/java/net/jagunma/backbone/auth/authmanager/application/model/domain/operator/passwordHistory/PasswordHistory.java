package net.jagunma.backbone.auth.authmanager.application.model.domain.operator.passwordHistory;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * パスワード履歴
 */
public class PasswordHistory {

	private PasswordHistoryEntity passwordHistoryEntity;

	// コンストラクタ
	PasswordHistory(PasswordHistoryEntity passwordHistoryEntity) {
		this.passwordHistoryEntity = passwordHistoryEntity;
	}
	// ファクトリーメソッド
	public static PasswordHistory of(PasswordHistoryEntity passwordHistoryEntity) {
		return new PasswordHistory(passwordHistoryEntity);
	}
	// 空生成メソッド
	public static PasswordHistory empty() {
		return new PasswordHistory(new PasswordHistoryEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return passwordHistoryEntity == null || passwordHistoryEntity.sameValueAs(new PasswordHistoryEntity());
	}
	// Getter
	public Long getPasswordHistoryId() { return this.passwordHistoryEntity.getPasswordHistoryId(); }
	public Long getOperatorId() { return this.passwordHistoryEntity.getOperatorId(); }
	public LocalDateTime getChangeDateTime() { return this.passwordHistoryEntity.getChangeDateTime(); }
	public String getPassword() { return this.passwordHistoryEntity.getPassword(); }
	public Short getChangeType() { return this.passwordHistoryEntity.getChangeType(); }
	public Long getCreatedBy() { return this.passwordHistoryEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.passwordHistoryEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.passwordHistoryEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.passwordHistoryEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.passwordHistoryEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.passwordHistoryEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.passwordHistoryEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです
	 * <strong>
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください <strong/>
	 */
	public PasswordHistoryEntity getOperatorHistoryEntityForRepository() {
		return passwordHistoryEntity;
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
			PasswordHistory that = (PasswordHistory) o;
			return Objects2.equal(this.passwordHistoryEntity.getPasswordHistoryId(), that.getPasswordHistoryId()) &&
				Objects2.equal(this.passwordHistoryEntity.getOperatorId(), that.getOperatorId()) &&
				Objects2.equal(this.passwordHistoryEntity.getChangeDateTime(), that.getChangeDateTime()) &&
				Objects2.equal(this.passwordHistoryEntity.getPassword(), that.getPassword()) &&
				Objects2.equal(this.passwordHistoryEntity.getChangeType(), that.getChangeType()) &&
				Objects2.equal(this.passwordHistoryEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.passwordHistoryEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.passwordHistoryEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.passwordHistoryEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.passwordHistoryEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.passwordHistoryEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.passwordHistoryEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
