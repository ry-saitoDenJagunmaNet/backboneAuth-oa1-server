package net.jagunma.backbone.auth.authmanager.model.types;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * サブシステムの列挙型
 */
public enum SubSystem {
    購買("KB", "購買", 1),
    販売_青果("YS", "販売・青果", 2),
    販売_花卉("YF", "販売・花卉", 3),
    販売_米("HK", "販売・米", 4),
    販売_麦("HM", "販売・麦", 5),
    販売_畜産("AN", "販売・畜産", 6),
    UnKnown("", "未定義", 7);

    private final String code;
    private final String displayName;
    private final Integer displaySortOrder;

    private SubSystem(String code, String displayName, Integer displaySortOrder) {
        this.code = code;
        this.displayName = displayName;
        this.displaySortOrder = displaySortOrder;
    }

    /**
     * コードのＧｅｔ
     * @return コード
     */
    public String getCode() {
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
     * 表示順のＧｅｔ
     * @return 表示順
     */
    public Integer getDisplaySortOrder() {
        return displaySortOrder;
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
            if (enumItem.displayName.equals(name)) {
                return enumItem;
            }
        }
        return SubSystem.UnKnown;
    }
}
