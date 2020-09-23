package net.jagunma.backbone.auth.authmanager.model.types;

/**
 * 検索条件選択の列挙型
 */
public enum ConditionsSelect {
    指定なし(0, "指定なし"),
    AND(1, "AND"),
    OR(2, "OR"),
    UnKnown(-1, "未定義");

    private final int code;
    private final String name;

    // コンストラクタ
    private ConditionsSelect(int code, String name) {
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
     * @return 検索条件選択
     */
    public static ConditionsSelect codeOf(int code) {
        for (ConditionsSelect enumItem : values()) {
            if (enumItem.code == code) {
                return enumItem;
            }
        }
        return ConditionsSelect.UnKnown;
    }
}
