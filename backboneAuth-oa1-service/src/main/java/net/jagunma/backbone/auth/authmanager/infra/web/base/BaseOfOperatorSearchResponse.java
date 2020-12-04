package net.jagunma.backbone.auth.authmanager.infra.web.base;

import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaders;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;

public class BaseOfOperatorSearchResponse {

    protected Operator operator;
    protected Operators operators;
    protected AccountLocks accountLocks;
    protected Operator_SubSystemRoles operator_SubSystemRoles;
    protected Operator_BizTranRoles operator_BizTranRoles;
    protected OperatorHistoryHeaders operatorHistoryHeaders;

    /**
     * オペレーターのＳｅｔ
     *
     * @param operator オペレーター
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
    /**
     * オペレーター群のＳｅｔ
     *
     * @param operators オペレーター群
     */
    public void setOperators(Operators operators) {
        this.operators = operators;
    }
    /**
     * アカウントロック群のＳｅｔ
     *
     * @param accountLocks アカウントロック群
     */
    public void setAccountLocks(AccountLocks accountLocks) {
        this.accountLocks = accountLocks;
    }
    /**
     * オペレーター_サブシステムロール割当群のＳｅｔ
     *
     * @param operator_SubSystemRoles オペレーター_サブシステムロール割当群
     */
    public void setOperator_SubSystemRoles(Operator_SubSystemRoles operator_SubSystemRoles) {
        this.operator_SubSystemRoles = operator_SubSystemRoles;
    }
    /**
     * オペレーター_取引ロール割当群のＳｅｔ
     *
     * @param operator_BizTranRoles オペレーター_取引ロール割当群
     */
    public void setOperator_BizTranRoles(Operator_BizTranRoles operator_BizTranRoles) {
        this.operator_BizTranRoles = operator_BizTranRoles;
    }
    /**
     * オペレーター履歴ヘッダーのＳｅｔ
     *
     * @param operatorHistoryHeaders オペレーター履歴ヘッダー
     */
    public void setOperatorHistoryHeaders(OperatorHistoryHeaders operatorHistoryHeaders) {
        this.operatorHistoryHeaders = operatorHistoryHeaders;
    }
}
