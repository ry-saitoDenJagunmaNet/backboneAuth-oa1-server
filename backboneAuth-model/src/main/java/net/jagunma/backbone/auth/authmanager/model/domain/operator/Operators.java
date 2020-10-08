package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Collection;
import java.util.List;

/**
 * オペレーター群
 */
public class Operators {

    private final List<Operator> list = newArrayList();

    // コンストラクタ
    Operators(Collection<Operator> collection) {
        this.list.addAll(collection);
    }

    /**
     * オペレーターリストから作成します。
     *
     * @param operatorList オペレーターリスト
     * @return オペレーター群
     */
    public static Operators createFrom(Collection<Operator> operatorList) {
        return new Operators(operatorList);
    }

    /**
     * オペレーターリストを取得します。
     *
     * @return オペレーターリスト
     */
    public List<Operator> getValues() {
        return list;
    }
}
