package net.jagunma.backbone.auth.authmanager.model.types;

/**
 * カレンダー種類の列挙型
 */
public enum CalendarType {
	Observatory((short) 0, "天文台カレンダー"),
	Economy((short) 1,"経済システム稼働カレンダー"),
	Credit((short) 2,"信用カレンダー"),
	WideAreaLogistics((short) 3,"広域物流カレンダー"),
	UnKnown((short) -1,"未定義");

	private final short code;
	private final String name;

	// コンストラクタ
	private CalendarType(short code, String name) {
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
	 * @return カレンダー種類
	 */
	public static CalendarType codeOf(short code) {
		for (CalendarType enumItem : values()) {
			if (enumItem.code == code) {
				return enumItem;
			}
		}
		return CalendarType.UnKnown;
	}

	/**
	 * 天文台カレンダーかを判定します。
	 * @return true:天文台カレンダー
	 */
	public boolean isObservatory() {
		return this.equals(Observatory);
	}
	/**
	 * 経済システム稼働カレンダーかを判定します。
	 * @return true:経済システム稼働カレンダー
	 */
	public boolean isEconomy() {
		return this.equals(Economy);
	}
	/**
	 * 信用カレンダーかを判定します。
	 * @return true:信用カレンダー
	 */
	public boolean isCredit() {
		return this.equals(Credit);
	}
	/**
	 * 広域物流カレンダーかを判定します。
	 * @return true:広域物流カレンダー
	 */
	public boolean isWideAreaLogistics() {
		return this.equals(WideAreaLogistics);
	}
}
