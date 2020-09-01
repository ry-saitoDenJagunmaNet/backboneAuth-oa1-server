package net.jagunma.backbone.auth.authmanager.model.types;

/**
 * サブシステムの列挙型
 */
public enum SubSystem {
	KB("KB", "購買"),
	YS("YS", "販売・青果"),
	YF("YF", "販売・花卉"),
	HK("HK", "販売・米"),
	HM("HM", "販売・麦"),
	AN("AN", "販売・畜産"),
	UnKnown("", "未定義");

	private final String code;
	private final String name;

	private SubSystem(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * コードのＧｅｔ
	 * @return コード
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 名称のＧｅｔ
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * コードで検索を行います。
	 *
	 * @param code コード
	 * @return サブシステム
	 */
	public static SubSystem codeOf(String code) {
		for (SubSystem enumItem : values()) {
			if (enumItem.code.equals(code)) {
				return enumItem;
			}
		}
		return SubSystem.UnKnown;
	}
}
