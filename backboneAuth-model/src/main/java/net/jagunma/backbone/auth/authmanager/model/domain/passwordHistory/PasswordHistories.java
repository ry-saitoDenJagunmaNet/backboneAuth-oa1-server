package net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;

/**
 * パスワード履歴群
 */
public class PasswordHistories {

    private final ArrayList<PasswordHistory> list = newArrayList();

    // コンストラクタ
    PasswordHistories(Collection<PasswordHistory> collection) {
        this.list.addAll(collection);
    }

    /**
     * パスワード履歴リストから作成します。
     *
     * @param passwordHistoryList パスワード履歴リスト
     * @return パスワード履歴群
     */
    public static PasswordHistories createFrom(Collection<PasswordHistory> passwordHistoryList) {
        return new PasswordHistories(passwordHistoryList);
    }

    /**
     * パスワード履歴リストを取得します。
     *
     * @return パスワード履歴リスト
     */
    public ArrayList<PasswordHistory> getList() {
        return list;
    }
}
