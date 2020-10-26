package net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryHeader;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;

/**
 * オペレーター履歴ヘッダー
 */
public class OperatorHistoryHeader {

    private final Long operatorHistoryId;
    private final Long operatorId;
    private final LocalDateTime changeDateTime;
    private final String changeCause;
    private final Integer recordVersion;
    private final Operator operator;

    // コンストラクタ
    OperatorHistoryHeader(
        Long operatorHistoryId,
        Long operatorId,
        LocalDateTime changeDateTime,
        String changeCause,
        Integer recordVersion,
        Operator operator) {

        this.operatorHistoryId = operatorHistoryId;
        this.operatorId = operatorId;
        this.changeDateTime = changeDateTime;
        this.changeCause = changeCause;
        this.recordVersion = recordVersion;
        this.operator = operator;
    }

    // ファクトリーメソッド
    public static OperatorHistoryHeader createFrom(
        Long operatorHistoryId,
        Long operatorId,
        LocalDateTime changeDateTime,
        String changeCause,
        Integer recordVersion,
        Operator operator) {

        return new OperatorHistoryHeader(
            operatorHistoryId,
            operatorId,
            changeDateTime,
            changeCause,
            recordVersion,
            operator);
    }

    // Getter
    public Long getOperatorHistoryId() {
        return operatorHistoryId;
    }
    public Long getOperatorId() {
        return operatorId;
    }
    public LocalDateTime getChangeDateTime() {
        return changeDateTime;
    }
    public String getChangeCause() {
        return changeCause;
    }
    public Integer getRecordVersion() {
        return recordVersion;
    }
    public Operator getOperator() {
        return operator;
    }
}
