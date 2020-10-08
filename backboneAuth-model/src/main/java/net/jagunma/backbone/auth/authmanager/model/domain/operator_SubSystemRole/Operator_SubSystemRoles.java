package net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Collection;
import java.util.List;

/**
 * オペレーター_サブシステムロール割当群
 */
public class Operator_SubSystemRoles {

    private final List<Operator_SubSystemRole> list = newArrayList();

    // コンストラクタ
    Operator_SubSystemRoles(Collection<Operator_SubSystemRole> collection) {
        this.list.addAll(collection);
    }

    /**
     * オペレーター_サブシステムロール割当リストから作成します。
     *
     * @param operator_SubSystemRoleList オペレーター_サブシステムロール割当リスト
     * @return オペレーター_サブシステムロール割当群
     */
    public static Operator_SubSystemRoles createFrom(Collection<Operator_SubSystemRole> operator_SubSystemRoleList) {
        return new Operator_SubSystemRoles(operator_SubSystemRoleList);
    }

    /**
     * オペレーター_サブシステムロール割当リストを取得します。
     *
     * @return オペレーター_サブシステムロール割当リスト
     */
    public List<Operator_SubSystemRole> getValues() {
        return list;
    }
}
