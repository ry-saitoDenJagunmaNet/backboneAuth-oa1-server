package net.jagunma.backbone.auth.authmanager.model.types;

/**
 * 利用可否状態の列挙型
 */
public enum AvailableStatus {
	Available((short) 0, "利用可能"),
	UnAvailable((short) 1, "利用不可"),
	UnKnown((short) -1, "未定義");

	private final short code;
	private final String name;

	// コンストラクタ
	private AvailableStatus(short code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * コードのＧｅｔ
	 * @return コード
	 */
	public short getCode() {
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
	 * @return 利用可否状態
	 */
	public static AvailableStatus codeOf(short code) {
		for (AvailableStatus enumItem : values()) {
			if (enumItem.code == code) {
				return enumItem;
			}
		}
		return AvailableStatus.UnKnown;
	}
}
