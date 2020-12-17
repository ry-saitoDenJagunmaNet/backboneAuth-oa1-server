package net.jagunma.backbone.auth.authmanager.model.types;

/**
 * サインイン起因の列挙型
 */
public enum SignInCause {
    サインイン((short) 1, "サインイン"),
    継続サインイン((short) 2, "継続サインイン"),
    UnKnown((short) -1, "未定義");

    private final short code;
    private final String displayName;

    // コンストラクタ
    private SignInCause(short code, String displayName) {
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
     * @return サインイン起因
     */
    public static SignInCause codeOf(short code) {
        for (SignInCause enumItem : values()) {
            if (enumItem.code == code) {
                return enumItem;
            }
        }
        return SignInCause.UnKnown;
    }
}
