package net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference;

import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaders;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;

/**
 * オペレーター群参照サービス Response
 */
public interface OperatorsSearchResponse {
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
    /**
     * オペレーター履歴ヘッダーのＳｅｔ
     *
     * @param operatorHistoryHeaders オペレーター履歴ヘッダー
     */
    void setOperatorHistoryHeaders(OperatorHistoryHeaders operatorHistoryHeaders);
}
