package net.jagunma.backbone.auth.authmanager.model.types;

/**
 * パスワード変更種別の列挙型
 */
public enum PasswordChangeType {
	初期((short) 0, "初期"),
	ユーザーによる変更((short) 1, "ユーザーによる変更"),
	管理者によるリセット((short) 2, "管理者によるリセット"),
	機器認証パスワード((short) 3, "機器認証パスワード"),
	UnKnown((short) -1, "未定義");

	private final short code;
	private final String name;

	// コンストラクタ
	private PasswordChangeType(short code, String name) {
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
	 * @return パスワード変更種別
	 */
	public static PasswordChangeType codeOf(short code) {
		for (PasswordChangeType enumItem : values()) {
			if (enumItem.code == code) {
				return enumItem;
			}
		}
		return PasswordChangeType.UnKnown;
	}
}
