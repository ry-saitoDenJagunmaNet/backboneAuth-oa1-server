package net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistory;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;

/**
 * オペレーター履歴
 */
public class OperatorHistory {

    private final Long operatorHistoryId;
    private final Long operatorId;
    private final String operatorCode;
    private final String operatorName;
    private final String mailAddress;
    private final LocalDate expirationStartDate;
    private final LocalDate expirationEndDate;
    private final Boolean isDeviceAuth;
    private final Long jaId;
    private final String jaCode;
    private final Long branchId;
    private final String branchCode;
    private final AvailableStatus availableStatus;
    private final Integer recordVersion;

    // コンストラクタ
    OperatorHistory(
        Long operatorHistoryId,
        Long operatorId,
        String operatorCode,
        String operatorName,
        String mailAddress,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Boolean isDeviceAuth,
        Long jaId,
        String jaCode,
        Long branchId,
        String branchCode,
        AvailableStatus availableStatus,
        Integer recordVersion) {

        this.operatorHistoryId = operatorHistoryId;
        this.operatorId = operatorId;
        this.operatorCode = operatorCode;
        this.operatorName = operatorName;
        this.mailAddress = mailAddress;
        this.expirationStartDate = expirationStartDate;
        this.expirationEndDate = expirationEndDate;
        this.isDeviceAuth = isDeviceAuth;
        this.jaId = jaId;
        this.jaCode = jaCode;
        this.branchId = branchId;
        this.branchCode = branchCode;
        this.availableStatus = availableStatus;
        this.recordVersion = recordVersion;
    }

    // ファクトリーメソッド
    public static OperatorHistory createFrom(
        Long operatorHistoryId,
        Long operatorId,
        String operatorCode,
        String operatorName,
        String mailAddress,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Boolean isDeviceAuth,
        Long jaId,
        String jaCode,
        Long branchId,
        String branchCode,
        AvailableStatus availableStatus,
        Integer recordVersion) {

        return new OperatorHistory(
            operatorHistoryId,
            operatorId,
            operatorCode,
            operatorName,
            mailAddress,
            expirationStartDate,
            expirationEndDate,
            isDeviceAuth,
            jaId,
            jaCode,
            branchId,
            branchCode,
            availableStatus,
            recordVersion);
    }

    // Getter
    public Long getOperatorHistoryId() {
        return operatorHistoryId;
    }
    public Long getOperatorId() {
        return operatorId;
    }
    public String getOperatorCode() {
        return operatorCode;
    }
    public String getOperatorName() {
        return operatorName;
    }
    public String getMailAddress() {
        return mailAddress;
    }
    public LocalDate getExpirationStartDate() {
        return expirationStartDate;
    }
    public LocalDate getExpirationEndDate() {
        return expirationEndDate;
    }
    public Boolean getIsDeviceAuth() {
        return isDeviceAuth;
    }
    public Long getJaId() {
        return jaId;
    }
    public String getJaCode() {
        return jaCode;
    }
    public Long getBranchId() {
        return branchId;
    }
    public String getBranchCode() {
        return branchCode;
    }
    public AvailableStatus getAvailableStatus() {
        return availableStatus;
    }
    public Integer getRecordVersion() {
        return recordVersion;
    }
}
