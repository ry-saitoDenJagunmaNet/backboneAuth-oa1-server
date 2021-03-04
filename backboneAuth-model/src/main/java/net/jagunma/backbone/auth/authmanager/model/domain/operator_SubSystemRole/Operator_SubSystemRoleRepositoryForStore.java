package net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole;

/**
 * オペレーター_サブシステムロール割当格納
 */
public interface Operator_SubSystemRoleRepositoryForStore {

    /**
     * オペレーター_サブシステムロール割当群の格納を行います
     *
     * @param operatorId オペレーターID
     * @param operator_SubSystemRoles オペレーター_サブシステムロール割当群
     * @param changeCause 変更事由
     */
    void store(Long operatorId, Operator_SubSystemRoles operator_SubSystemRoles, String changeCause);
}