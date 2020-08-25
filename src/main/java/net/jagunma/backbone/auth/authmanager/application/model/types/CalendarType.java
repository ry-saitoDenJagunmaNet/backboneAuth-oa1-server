package net.jagunma.backbone.auth.authmanager.application.model.types;

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

	public boolean isObservatory() {
		if (this.equals(Observatory)) {return true;}
		return false;
	}
	public boolean isEconomy() {
		if (this.equals(Economy)) {return true;}
		return false;
	}
	public boolean isCredit() {
		if (this.equals(Credit)) {return true;}
		return false;
	}
	public boolean isWideAreaLogistics() {
		if (this.equals(WideAreaLogistics)) {return true;}
		return false;
	}
}
