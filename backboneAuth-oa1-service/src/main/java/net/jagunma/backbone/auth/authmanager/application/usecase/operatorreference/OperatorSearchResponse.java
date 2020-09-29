package net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference;

import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistories;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraces;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraces;

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
     * アカウントロック群のＳｅｔ
     *
     * @param accountLocks アカウントロック群
     */
    void setAccountLocks(AccountLocks accountLocks);
    /**
     * パスワード履歴群のＳｅｔ
     *
     * @param passwordHistories パスワード履歴群
     */
    void setPasswordHistories(PasswordHistories passwordHistories);
    /**
     * サインイン証跡群のＳｅｔ
     *
     * @param signInTraces サインイン証跡群
     */
    void setSignInTraces(SignInTraces signInTraces);
    /**
     * サインアウト証跡群のＳｅｔ
     *
     * @param signOutTraces サインアウト証跡群
     */
    void setSignOutTraces(SignOutTraces signOutTraces);
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
