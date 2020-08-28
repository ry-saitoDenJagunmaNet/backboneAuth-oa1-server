package net.jagunma.backbone.auth.authmanager.application.model.domain.operator.operatorHistory.operatorHistoryHeader;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * オペレーター履歴ヘッダー
 */
public class OperatorHistoryHeader {

	private final OperatorHistoryHeaderEntity operatorHistoryHeaderEntity;

	// コンストラクタ
	OperatorHistoryHeader(OperatorHistoryHeaderEntity operatorHistoryHeaderEntity) {
		this.operatorHistoryHeaderEntity = operatorHistoryHeaderEntity;
	}
	// ファクトリーメソッド
	public static OperatorHistoryHeader of(OperatorHistoryHeaderEntity operatorHistoryHeaderEntity) {
		return new OperatorHistoryHeader(operatorHistoryHeaderEntity);
	}
	// 空生成メソッド
	public static OperatorHistoryHeader empty() {
		return new OperatorHistoryHeader(new OperatorHistoryHeaderEntity());
	}
	// 空判定メソッド
	public boolean isEmpty() {
		return operatorHistoryHeaderEntity == null || operatorHistoryHeaderEntity.sameValueAs(new OperatorHistoryHeaderEntity());
	}
	// Getter
	public Long getOperatorHistoryId() { return this.operatorHistoryHeaderEntity.getOperatorHistoryId(); }
	public Long getOperatorId() { return this.operatorHistoryHeaderEntity.getOperatorId(); }
	public LocalDateTime getChangeDateTime() { return this.operatorHistoryHeaderEntity.getChangeDateTime(); }
	public String getChangeCause() { return this.operatorHistoryHeaderEntity.getChangeCause(); }
	public Long getCreatedBy() { return this.operatorHistoryHeaderEntity.getCreatedBy(); }
	public LocalDateTime getCreatedAt() { return this.operatorHistoryHeaderEntity.getCreatedAt(); }
	public String getCreatedIpAddress() { return this.operatorHistoryHeaderEntity.getCreatedIpAddress(); }
	public Long getUpdatedBy() { return this.operatorHistoryHeaderEntity.getUpdatedBy(); }
	public LocalDateTime getUpdatedAt() { return this.operatorHistoryHeaderEntity.getUpdatedAt(); }
	public String getUpdatedIpAddress() { return this.operatorHistoryHeaderEntity.getUpdatedIpAddress(); }
	public Integer getRecordVersion() { return this.operatorHistoryHeaderEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです。
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください
	 */
	public OperatorHistoryHeaderEntity getOperatorHistoryHeaderEntityForRepository() {
		return operatorHistoryHeaderEntity;
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
			OperatorHistoryHeader that = (OperatorHistoryHeader) o;
			return Objects2.equal(this.operatorHistoryHeaderEntity.getOperatorHistoryId(), that.getOperatorHistoryId()) &&
				Objects2.equal(this.operatorHistoryHeaderEntity.getOperatorId(), that.getOperatorId()) &&
				Objects2.equal(this.operatorHistoryHeaderEntity.getChangeDateTime(), that.getChangeDateTime()) &&
				Objects2.equal(this.operatorHistoryHeaderEntity.getChangeCause(), that.getChangeCause()) &&
				Objects2.equal(this.operatorHistoryHeaderEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.operatorHistoryHeaderEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.operatorHistoryHeaderEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.operatorHistoryHeaderEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.operatorHistoryHeaderEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.operatorHistoryHeaderEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.operatorHistoryHeaderEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
