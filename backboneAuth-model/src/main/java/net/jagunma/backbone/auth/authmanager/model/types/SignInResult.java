package net.jagunma.backbone.auth.authmanager.model.types;

/**
 * サインイン結果の列挙型
 */
public enum SignInResult {
	Success((short) 0, "成功"),
	FailedOperator((short) 1, "失敗（存在しないオペレーター）"),
	FailedPassword((short) 2, "失敗（パスワード誤り）"),
	BlockIp((short) 3, "遮断（IPアドレス範囲外）"),
	DeniedAccount((short) 4, "拒否（アカウントロック中）"),
	DeniedExpiration((short) 5, "拒否（有効期限範囲外）"),
	UnKnown((short) -1, "未定義");

	private final short code;
	private final String name;

	// コンストラクタ
	private SignInResult(short code, String name) {
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
	 * @return サインイン結果
	 */
	public static SignInResult codeOf(short code) {
		for (SignInResult enumItem : values()) {
			if (enumItem.code == code) {
				return enumItem;
			}
		}
		return SignInResult.UnKnown;
	}
}
