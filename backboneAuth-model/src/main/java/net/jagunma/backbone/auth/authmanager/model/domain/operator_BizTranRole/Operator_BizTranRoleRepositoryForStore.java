package net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole;

/**
 * オペレーター_取引ロール割当格納
 */
public interface Operator_BizTranRoleRepositoryForStore {

    /**
     * オペレーター_取引ロール割当群の格納を行います
     *
     * @param operatorId オペレーターID
     * @param operator_BizTranRoles オペレーター_取引ロール割当群
     * @param changeCause 変更事由
     */
    void store(Long operatorId, Operator_BizTranRoles operator_BizTranRoles, String changeCause);
}