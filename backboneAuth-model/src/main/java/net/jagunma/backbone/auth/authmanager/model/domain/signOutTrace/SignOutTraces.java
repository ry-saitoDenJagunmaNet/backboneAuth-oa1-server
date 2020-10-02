package net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;

/**
 * サインアウト証跡群
 */
public class SignOutTraces {

    private final ArrayList<SignOutTrace> list = newArrayList();

    // コンストラクタ
    SignOutTraces(Collection<SignOutTrace> collection) {
        this.list.addAll(collection);
    }

    /**
     * サインアウト証跡リストから作成します。
     *
     * @param signOutTraceList サインアウト証跡リスト
     * @return サインアウト証跡群
     */
    public static SignOutTraces createFrom(Collection<SignOutTrace> signOutTraceList) {
        return new SignOutTraces(signOutTraceList);
    }

    /**
     * サインアウト証跡リストを取得します。
     *
     * @return サインアウト証跡リスト
     */
    public ArrayList<SignOutTrace> getValues() {
        return list;
    }
}
