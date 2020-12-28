package net.jagunma.backbone.auth.authmanager.model.types;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * アカウントロック状態の列挙型
 */
public enum AccountLockStatus {
    アンロック((short) 0, "アンロック"),
    ロック((short) 1, "ロック"),
    UnKnown((short) -1, "未定義");

    private final short code;
    private final String displayName;

    // コンストラクタ
    private AccountLockStatus(short code, String displayName) {
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
     * 有効なリストのＧｅｔ
     * （UnKnownを除いたリスト）
     *
     * @return 有効なリスト
     */
    public static List<AccountLockStatus> getValidList() {
        List<AccountLockStatus> list = Arrays.asList(values());
        return list.stream().filter(s->!s.name().equals("UnKnown")).collect(Collectors.toList());
    }

    /**
     * コードで検索を行います
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
