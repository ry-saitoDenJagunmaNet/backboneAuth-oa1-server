package net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference;

import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistories;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraces;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraces;
import net.jagunma.common.values.model.branch.BranchesAtMoment;

/**
 * オペレーターリスト参照サービス Response
 */
public interface OperatorSearchResponse {
    /**
     * オペレーター一覧表示ページのＳｅｔ
     *
     * @param pageNo オペレーター一覧表示ページ
     */
    void setPageNo(int pageNo);
    /**
     * オペレーター群のＳｅｔ
     *
     * @param operators オペレーター群
     */
    void setOperators(Operators operators);
    /**
     * ある時点Branch群のＳｅｔ
     *
     * @param branchesAtMoment ある時点Branch群
     */
    void setBranchesAtMoment(BranchesAtMoment branchesAtMoment);
    /**
     * アカウントロック群のＳｅｔ
     *
     * @param accountLocks アカウントロック群
     */
    void setAccountLocks(AccountLocks accountLocks);
    /**
     * オペレーター_サブシステムロール割当群のＳｅｔ
     *
     * @param operator_SubSystemRoles オペレーター_サブシステムロール割当群
     */
    void setOperator_SubSystemRoles(Operator_SubSystemRoles operator_SubSystemRoles);
    /**
     * オペレーター_取引ロール割当群のＳｅｔ
     *
     * @param operator_BizTranRoles オペレーター_取引ロール割当群
     */
    void setOperator_BizTranRoles(Operator_BizTranRoles operator_BizTranRoles);
}
