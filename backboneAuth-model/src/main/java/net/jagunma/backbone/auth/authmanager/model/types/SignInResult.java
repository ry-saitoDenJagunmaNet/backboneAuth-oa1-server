package net.jagunma.backbone.auth.authmanager.model.types;

/**
 * サインイン結果の列挙型
 */
public enum SignInResult {
    成功((short) 0, "成功"),
    失敗_存在しないオペレーター((short) 1, "失敗（存在しないオペレーター）"),
    失敗_パスワード誤り((short) 2, "失敗（パスワード誤り）"),
    遮断_IPアドレス範囲外((short) 3, "遮断（IPアドレス範囲外）"),
    拒否_アカウントロック中((short) 4, "拒否（アカウントロック中）"),
    拒否_有効期限範囲外((short) 5, "拒否（有効期限範囲外）"),
    UnKnown((short) -1, "未定義");

    private final short code;
    private final String displayName;

    // コンストラクタ
    private SignInResult(short code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    /**
     * コードのＧｅｔ
     * @return コード
     */
    public short getCode() {
        return code;
    }
    /**
     * 表示名称のＧｅｔ
     * @return 表示名称
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * コードで検索を行います
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
