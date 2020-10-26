package net.jagunma.backbone.auth.authmanager.model.types;

/**
 * サブシステムの列挙型
 */
public enum SubSystem {
    購買("KB", "購買"),
    販売_青果("YS", "販売・青果"),
    販売_花卉("YF", "販売・花卉"),
    販売_米("HK", "販売・米"),
    販売_麦("HM", "販売・麦"),
    販売_畜産("AN", "販売・畜産"),
    UnKnown("", "未定義");

    private final String code;
    private final String name;

    private SubSystem(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * コードのＧｅｔ
     * @return コード
     */
    public String getCode() {
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
     * コードで検索を行います
     *
     * @param code コード
     * @return サブシステム
     */
    public static SubSystem codeOf(String code) {
        for (SubSystem enumItem : values()) {
            if (enumItem.code.equals(code)) {
                return enumItem;
            }
        }
        return SubSystem.UnKnown;
    }

    /**
     * 名称で検索を行います
     *
     * @param name 名称
     * @return サブシステム
     */
    public static SubSystem nameOf(String name) {
        for (SubSystem enumItem : values()) {
            if (enumItem.name.equals(name)) {
                return enumItem;
            }
        }
        return SubSystem.UnKnown;
    }
}
