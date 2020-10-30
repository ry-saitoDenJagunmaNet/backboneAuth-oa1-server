package net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack;

import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistory.OperatorHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operator_BizTranRoleHistory.Operator_BizTranRoleHistories;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistories;

/**
 * オペレーター履歴パック
 */
public class OperatorHistoryPack {

    private final OperatorHistoryHeader operatorHistoryHeader;
    private final OperatorHistory operatorHistory;
    private final Operator_SubSystemRoleHistories operator_SubSystemRoleHistories;
    private final Operator_BizTranRoleHistories operator_BizTranRoleHistories;

    // コンストラクタ
    OperatorHistoryPack(
        OperatorHistoryHeader operatorHistoryHeader,
        OperatorHistory operatorHistory,
        Operator_SubSystemRoleHistories operator_SubSystemRoleHistories,
        Operator_BizTranRoleHistories operator_BizTranRoleHistories) {

        this.operatorHistoryHeader = operatorHistoryHeader;
        this.operatorHistory = operatorHistory;
        this.operator_SubSystemRoleHistories = operator_SubSystemRoleHistories;
        this.operator_BizTranRoleHistories = operator_BizTranRoleHistories;
    }

    // ファクトリーメソッド
    public static OperatorHistoryPack createFrom(
        OperatorHistoryHeader operatorHistoryHeader,
        OperatorHistory operatorHistory,
        Operator_SubSystemRoleHistories operator_SubSystemRoleHistories,
        Operator_BizTranRoleHistories operator_BizTranRoleHistories) {

        return new OperatorHistoryPack(
            operatorHistoryHeader,
            operatorHistory,
            operator_SubSystemRoleHistories,
            operator_BizTranRoleHistories);
    }

    // Getter
    public OperatorHistoryHeader getOperatorHistoryHeader() {
        return operatorHistoryHeader;
    }
    public OperatorHistory getOperatorHistory() {
        return operatorHistory;
    }
    public Operator_SubSystemRoleHistories getOperator_SubSystemRoleHistories() {
        return operator_SubSystemRoleHistories;
    }
    public Operator_BizTranRoleHistories getOperator_BizTranRoleHistories() {
        return operator_BizTranRoleHistories;
    }
}
