package net.jagunma.backbone.auth.authmanager.model.types;

/**
 * アカウントロック状態の列挙型
 */
public enum AccountLockStatus {
    アンロック((short) 0, "アンロック"),
    ロック((short) 1, "ロック"),
    UnKnown((short) -1, "未定義");

    private final short code;
    private final String name;

    // コンストラクタ
    private AccountLockStatus(short code, String name) {
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
     * @return アカウントロック状態
     */
    public static AccountLockStatus codeOf(short code) {
        for (AccountLockStatus enumItem : values()) {
            if (enumItem.code == code) {
                return enumItem;
            }
        }
        return AccountLockStatus.UnKnown;
    }
}
