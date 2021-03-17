package net.jagunma.backbone.auth.authmanager.model.types;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * パスワード変更種別の列挙型
 */
public enum PasswordChangeType {
    初期((short) 0, "初期"),
    ユーザーによる変更((short) 1, "ユーザーによる変更"),
    管理者によるリセット((short) 2, "管理者によるリセット"),
    機器認証パスワード((short) 3, "機器認証パスワード"),
    UnKnown((short) -1, "未定義");

    private final short code;
    private final String displayName;

    // コンストラクタ
    private PasswordChangeType(short code, String displayName) {
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
    public static List<PasswordChangeType> getValidList() {
        List<PasswordChangeType> list = Arrays.asList(values());
        return list.stream().filter(s->!s.name().equals("UnKnown")).collect(Collectors.toList());
    }

    /**
     * 有効なPasswordChangeTypeのＧｅｔ
     * （UnKnownを除いたPasswordChangeType）
     *
     * @return 有効なPasswordChangeType
     */
    public static PasswordChangeType[] getValidValues() {
        return getValidList().toArray(new PasswordChangeType[getValidList().size()]);
    }

    /**
     * コードで検索を行います
     *
     * @param code コード
     * @return パスワード変更種別
     */
    public static PasswordChangeType codeOf(short code) {
        for (PasswordChangeType enumItem : values()) {
            if (enumItem.code == code) {
                return enumItem;
            }
        }
        return PasswordChangeType.UnKnown;
    }

    /**
     * 初期かを判定します
     *
     * @return true:初期
     */
    public boolean is初期() {
        return this.equals(初期);
    }

    /**
     * ユーザーによる変更かを判定します
     *
     * @return true:ユーザーによる変更
     */
    public boolean isユーザーによる変更() {
        return this.equals(ユーザーによる変更);
    }

    /**
     * 管理者によるリセットかを判定します
     *
     * @return true:管理者によるリセット
     */
    public boolean is管理者によるリセット() {
        return this.equals(管理者によるリセット);
    }

    /**
     * 機器認証パスワードかを判定します
     *
     * @return true:機器認証パスワード
     */
    public boolean is機器認証パスワード() {
        return this.equals(機器認証パスワード);
    }
}
