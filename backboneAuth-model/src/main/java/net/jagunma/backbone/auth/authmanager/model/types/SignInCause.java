package net.jagunma.backbone.auth.authmanager.model.types;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
     * 有効なリストのＧｅｔ
     * （UnKnownを除いたリスト）
     *
     * @return 有効なリスト
     */
    public static List<SignInCause> getValidList() {
        List<SignInCause> list = Arrays.asList(values());
        return list.stream().filter(s->!s.name().equals("UnKnown")).collect(Collectors.toList());
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
