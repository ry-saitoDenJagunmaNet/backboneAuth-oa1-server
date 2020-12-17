package net.jagunma.backbone.auth.authmanager.model.types;

/**
 * 利用可否状態の列挙型
 */
public enum AvailableStatus {
    利用可能((short) 0, "利用可能"),
    利用不可((short) 1, "利用不可"),
    UnKnown((short) -1, "未定義");

    private final short code;
    private final String displayName;

    // コンストラクタ
    private AvailableStatus(short code, String displayName) {
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
