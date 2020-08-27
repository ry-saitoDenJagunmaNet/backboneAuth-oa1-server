package net.jagunma.backbone.auth.authmanager.application.model.domain.operator.operatorHistoryPack.operatorHistoryPackHeader;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.operatorHistoryPackHeader.OperatorHistoryPackHeaderEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * オペレーター履歴パックヘッダー
 */
public class OperatorHistoryPackHeader {

	private final OperatorHistoryPackHeaderEntity operatorHistoryPackHeaderEntity;

	// コンストラクタ
	OperatorHistoryPackHeader(OperatorHistoryPackHeaderEntity operatorHistoryPackHeaderEntity) {
		this.operatorHistoryPackHeaderEntity = operatorHistoryPackHeaderEntity;
	}
	// ファクトリーメソッド
	public static OperatorHistoryPackHeader of(OperatorHistoryPackHeaderEntity operatorHistoryPackHeaderEntity) {
		return new OperatorHistoryPackHeader(operatorHistoryPackHeaderEntity);
	}
	// 空生成メソッド
	public static OperatorHistoryPackHeader empty() {
		return new OperatorHistoryPackHeader(new OperatorHistoryPackHeaderEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return operatorHistoryPackHeaderEntity == null || operatorHistoryPackHeaderEntity.sameValueAs(new OperatorHistoryPackHeaderEntity());
	}
	// Getter
	public Long getOperatorHistoryId() { return this.operatorHistoryPackHeaderEntity.getOperatorHistoryId(); }
	public Long getOperatorId() { return this.operatorHistoryPackHeaderEntity.getOperatorId(); }
	public LocalDateTime getChangeDateTime() { return this.operatorHistoryPackHeaderEntity.getChangeDateTime(); }
	public String getChangeCause() { return this.operatorHistoryPackHeaderEntity.getChangeCause(); }
	public Long getCreatedBy() { return this.operatorHistoryPackHeaderEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.operatorHistoryPackHeaderEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.operatorHistoryPackHeaderEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.operatorHistoryPackHeaderEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.operatorHistoryPackHeaderEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.operatorHistoryPackHeaderEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.operatorHistoryPackHeaderEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです。
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください
	 */
	public OperatorHistoryPackHeaderEntity getOperatorHistoryPackHeaderEntityForRepository() {
		return operatorHistoryPackHeaderEntity;
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
			OperatorHistoryPackHeader that = (OperatorHistoryPackHeader) o;
			return Objects2.equal(this.operatorHistoryPackHeaderEntity.getOperatorHistoryId(), that.getOperatorHistoryId()) &&
				Objects2.equal(this.operatorHistoryPackHeaderEntity.getOperatorId(), that.getOperatorId()) &&
				Objects2.equal(this.operatorHistoryPackHeaderEntity.getChangeDateTime(), that.getChangeDateTime()) &&
				Objects2.equal(this.operatorHistoryPackHeaderEntity.getChangeCause(), that.getChangeCause()) &&
				Objects2.equal(this.operatorHistoryPackHeaderEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.operatorHistoryPackHeaderEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.operatorHistoryPackHeaderEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.operatorHistoryPackHeaderEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.operatorHistoryPackHeaderEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.operatorHistoryPackHeaderEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.operatorHistoryPackHeaderEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
