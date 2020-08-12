package net.jagunma.backbone.auth.authmanager.application.model.domain.subSystemRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.subSystem.SubSystem;
import net.jagunma.common.util.objects2.Objects2;

/**
 * サブシステムロール
 */
public class SubSystemRole {
	private final String subSystemRoleCode;
	private final String subSystemRoleName;
	private final List<SubSystem> subSystemList;

	/**
	 * コンストラクタ
	 */
	SubSystemRole(String subSystemRoleCode, String subSystemRoleName, List<SubSystem> subSystemList) {
		this.subSystemRoleCode = subSystemRoleCode;
		this.subSystemRoleName = subSystemRoleName;
		this.subSystemList = subSystemList;
	}

	/*
	 * ファクトリメソッド
	 */
	public static SubSystemRole createOf(String subSystemRoleCode, String subSystemRoleName, List<SubSystem> subSystemList) {
		return new SubSystemRole(subSystemRoleCode, subSystemRoleName, subSystemList);
	}

	public static SubSystemRole empty() {
		return new SubSystemRole("", "", newArrayList());
	}
	/**
	 * サブシステムロールコードのＧｅｔ
	 * @return サブシステムロールコード
	 */
	public String getSubSystemRoleCode() { return subSystemRoleCode; }
	/**
	 * サブシステムロール名のＧｅｔ
	 * @return サブシステムロール名
	 */
	public String getSubSystemRoleName() { return subSystemRoleName; }
	/**
	 * サブシステムリストのＧｅｔ
	 * @return サブシステムリスト
	 */
	public List<SubSystem> getSubSystemList() { return subSystemList; }

	/**
	 * オブジェクトの比較を行います。
	 * @param o 比較するオブジェクト
	 * @return true：比較結果は同じ　false：比較結果は差異がある
	 */
	public boolean sameValueAs(Object o) {
		if (this == o) {
			return true;
		} else if (o != null && this.getClass() == o.getClass()) {
			SubSystemRole that = (SubSystemRole) o;
			return Objects2.equal(this.getSubSystemRoleCode(), that.getSubSystemRoleCode()) &&
				Objects2.equal(this.getSubSystemRoleName(), that.getSubSystemRoleName()) &&
				Objects2.equal(this.getSubSystemList(), that.getSubSystemList());
		} else {
			return false;
		}
	}

}
