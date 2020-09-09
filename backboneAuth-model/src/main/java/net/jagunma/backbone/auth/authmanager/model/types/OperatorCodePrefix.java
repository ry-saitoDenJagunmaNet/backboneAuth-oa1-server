package net.jagunma.backbone.auth.authmanager.model.types;

/**
 * オペレータコードプレフィックスの列挙型
 */
public enum OperatorCodePrefix {
    赤城たちばな("002", "zv"),
    前橋市("006", "yu"),
    たかさき("070", "vr"),
    はぐくみ("074", "uq"),
    北群渋川("160", "qm"),
    たのふじ("210", "tp"),
    甘楽富岡("280", "so"),
    碓氷安中("350", "rn"),
    あがつま("415", "ok"),
    利根沼田("470", "mi"),
    佐波伊勢崎("550", "ws"),
    にったみどり("617", "ie"),
    太田市("618", "hd"),
    邑楽館林("744", "gc"),
    電算センター("990", "fb"),
    UnKnown("", "未定義");

    private final String code;
    private final String prefix;

    // コンストラクタ
    private OperatorCodePrefix(String code, String prefix) {
        this.code = code;
        this.prefix = prefix;
    }

    /**
     * コードのＧｅｔ
     * @return コード
     */
    public String getCode() {
        return code;
    }
    /**
     * プレフィックスのＧｅｔ
     * @return プレフィックス
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * コードで検索を行います。
     *
     * @param code コード
     * @return オペレータコードプレフィックス
     */
    public static OperatorCodePrefix codeOf(String code) {
        for (OperatorCodePrefix enumItem : values()) {
            if (enumItem.code == code) {
                return enumItem;
            }
        }
        return OperatorCodePrefix.UnKnown;
    }
}
