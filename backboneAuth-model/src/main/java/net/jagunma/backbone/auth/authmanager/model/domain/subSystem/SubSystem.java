package net.jagunma.backbone.auth.authmanager.model.domain.subSystem;

import net.jagunma.common.util.objects2.Objects2;

/**
 * サブシステム
 */
public class SubSystem {

	private final String subSystemCode;
	private final String subSystemName;

	// コンストラクタ
	SubSystem(String subSystemCode, String subSystemName) {
		this.subSystemCode = subSystemCode;
		this.subSystemName = subSystemName;
	}
	// ファクトリーメソッド
	public static SubSystem createOf(String subSystemCode, String subSystemName) {
		return new SubSystem(subSystemCode, subSystemName);
	}
	// 空生成メソッド
	public static SubSystem empty() {
		return new SubSystem("", "");
	}
	// Getter
	public String getSubSystemCode() { return this.subSystemCode; }
	public String getSubSystemName() { return this.subSystemName; }

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
			SubSystem that = (SubSystem) o;
			return Objects2.equal(this.getSubSystemCode(), that.getSubSystemCode()) &&
				Objects2.equal(this.getSubSystemName(), that.getSubSystemName());
		} else {
			return false;
		}
	}
}
