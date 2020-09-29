package net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;

/**
 * オペレーター_取引ロール割当群
 */
public class Operator_BizTranRoles {

    private final ArrayList<Operator_BizTranRole> list = newArrayList();

    // コンストラクタ
    Operator_BizTranRoles(Collection<Operator_BizTranRole> collection) {
        this.list.addAll(collection);
    }

    /**
     * オペレーター_取引ロール割当リストから作成します。
     *
     * @param operator_BizTranRoleList オペレーター_取引ロール割当リスト
     * @return オペレーター_取引ロール割当群
     */
    public static Operator_BizTranRoles createFrom(Collection<Operator_BizTranRole> operator_BizTranRoleList) {
        return new Operator_BizTranRoles(operator_BizTranRoleList);
    }

    /**
     * オペレーター_取引ロール割当リストを取得します。
     *
     * @return オペレーター_取引ロール割当リスト
     */
    public ArrayList<Operator_BizTranRole> getValues() {
        return list;
    }
}
