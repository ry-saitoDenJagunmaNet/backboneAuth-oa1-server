package net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntity;
import net.jagunma.common.util.objects2.Objects2;

/**
 * 取引ロール
 */
public class BizTranRole {

	private final BizTranRoleEntity bizTranRoleEntity;

	/**
	 * コンストラクタ
	 */
	BizTranRole(BizTranRoleEntity bizTranRoleEntity) {
		this.bizTranRoleEntity = bizTranRoleEntity;
	}

	/*
	 * ファクトリメソッド
	 */
	public static BizTranRole of(BizTranRoleEntity bizTranRoleEntity) {
		return new BizTranRole(bizTranRoleEntity);
	}

	public static BizTranRole empty() {
		return new BizTranRole(new BizTranRoleEntity());
	}

	public boolean isEmpty() {
		return bizTranRoleEntity == null || bizTranRoleEntity.sameValueAs(new BizTranRoleEntity());
	}

	/**
	 * 取引ロールIDのＧｅｔ
	 * @return 取引ロールID
	 */
	public Long getBizTranRoleId() { return this.bizTranRoleEntity.getBizTranRoleId(); }
	/**
	 * 取引ロールコードのＧｅｔ
 	 * @return 取引ロールコード
	 */
	public String getBizTranRoleCode() { return this.bizTranRoleEntity.getBizTranRoleCode(); }
	/**
	 * 取引ロール名のＧｅｔ
	 * @return 取引ロール名
	 */
	public String getBizTranRoleName() { return this.bizTranRoleEntity.getBizTranRoleName(); }
	/**
	 * サブシステムコードのＧｅｔ
	 * @return サブシステムコード
	 */
	public String getSubSystemCode() { return this.bizTranRoleEntity.getSubSystemCode(); }
	/**
	 * 登録者IDのＧｅｔ
	 * @return 登録者ID
	 */
	public Long getCreatedBy() { return this.bizTranRoleEntity.getCreatedBy(); }
	/**
	 * 登録日時のＧｅｔ
	 * @return 登録日時
	 */
	public LocalDateTime getCreatedAt() { return this.bizTranRoleEntity.getCreatedAt(); }
	/**
	 * 登録元IPアドレスのＧｅｔ
	 * @return 登録元IPアドレス
	 */
	public String getCreatedIpAddress() { return this.bizTranRoleEntity.getCreatedIpAddress(); }
	/**
	 * 更新者IDのＧｅｔ
	 * @return 更新者ID
	 */
	public Long getUpdatedBy() { return this.bizTranRoleEntity.getUpdatedBy(); }
	/**
	 * 更更新日時のＧｅｔ
	 * @return 更新日時
	 */
	public LocalDateTime getUpdatedAt() { return this.bizTranRoleEntity.getUpdatedAt(); }
	/**
	 * 更新元IPアドレスのＧｅｔ
	 * @return 更新元IPアドレス
	 */
	public String getUpdatedIpAddress() { return this.bizTranRoleEntity.getUpdatedIpAddress(); }
	/**
	 * レコードバージョンのＧｅｔ
	 * @return レコードバージョン
	 */
	public Integer getRecordVersion() { return this.bizTranRoleEntity.getRecordVersion(); }

	/**
	 * リポジトリ用のEntityGetterです
	 *
	 * @return リポジトリ間で使用するDBEntity
	 * @implNote 項目を取得する目的では使用しないでください
	 */
	public BizTranRoleEntity getBizTranRoleEntityForRepository() {
		return bizTranRoleEntity;
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
			BizTranRole that = (BizTranRole) o;
			return Objects2.equal(this.bizTranRoleEntity.getBizTranRoleId(), that.getBizTranRoleId()) &&
				Objects2.equal(this.bizTranRoleEntity.getBizTranRoleCode(), that.getBizTranRoleCode()) &&
				Objects2.equal(this.bizTranRoleEntity.getBizTranRoleName(), that.getBizTranRoleName()) &&
				Objects2.equal(this.bizTranRoleEntity.getSubSystemCode(), that.getSubSystemCode()) &&
				Objects2.equal(this.bizTranRoleEntity.getCreatedBy(), that.getCreatedBy()) &&
				Objects2.equal(this.bizTranRoleEntity.getCreatedAt(), that.getCreatedAt()) &&
				Objects2.equal(this.bizTranRoleEntity.getCreatedIpAddress(), that.getCreatedIpAddress()) &&
				Objects2.equal(this.bizTranRoleEntity.getUpdatedBy(), that.getUpdatedBy()) &&
				Objects2.equal(this.bizTranRoleEntity.getUpdatedAt(), that.getUpdatedAt()) &&
				Objects2.equal(this.bizTranRoleEntity.getUpdatedIpAddress(), that.getUpdatedIpAddress()) &&
				Objects2.equal(this.bizTranRoleEntity.getRecordVersion(), that.getRecordVersion());
		} else {
			return false;
		}
	}
}
