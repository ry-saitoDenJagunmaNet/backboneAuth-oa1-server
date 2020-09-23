package net.jagunma.backbone.auth.authmanager.model.types;

/**
 * 有効期限検索条件選択の列挙型
 */
public enum ConditionsExpirationSelect {
    指定なし(0, "指定なし"),
    状態指定日(1, "状態指定日"),
    条件指定(2, "条件指定"),
    UnKnown(-1, "未定義");

    private final int code;
    private final String name;

    // コンストラクタ
    private ConditionsExpirationSelect(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * コードのＧｅｔ
     * @return コード
     */
    public int getCode() {
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
     * @return 有効期限検索条件選択
     */
    public static ConditionsExpirationSelect codeOf(int code) {
        for (ConditionsExpirationSelect enumItem : values()) {
            if (enumItem.code == code) {
                return enumItem;
            }
        }
        return ConditionsExpirationSelect.UnKnown;
    }
}
