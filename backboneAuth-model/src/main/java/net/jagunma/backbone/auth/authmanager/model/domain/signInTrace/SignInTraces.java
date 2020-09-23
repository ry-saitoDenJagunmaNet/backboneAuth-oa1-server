package net.jagunma.backbone.auth.authmanager.model.domain.signInTrace;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;

/**
 * サインイン証跡群
 */
public class SignInTraces {

    private final ArrayList<SignInTrace> list = newArrayList();

    // コンストラクタ
    SignInTraces(Collection<SignInTrace> collection) {
        this.list.addAll(collection);
    }

    /**
     * サインイン証跡リストから作成します。
     *
     * @param signInTraceList サインイン証跡リスト
     * @return サインイン証跡群
     */
    public static SignInTraces createFrom(Collection<SignInTrace> signInTraceList) {
        return new SignInTraces(signInTraceList);
    }

    /**
     * アサインイン証跡リストを取得します。
     *
     * @return サインイン証跡リスト
     */
    public ArrayList<SignInTrace> getList() {
        return list;
    }
}
