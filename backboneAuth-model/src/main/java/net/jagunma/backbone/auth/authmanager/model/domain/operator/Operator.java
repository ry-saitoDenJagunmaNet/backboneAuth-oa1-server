package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import java.time.LocalDate;

/**
 * オペレーター
 */
public class Operator {

    private final Long operatorId;
    private final String operatorCode;
    private final String operatorName;
    private final String mailAddress;
    private final LocalDate expirationStartDate;
    private final LocalDate expirationEndDate;
    private final Boolean isDeviceAuth;
    private final Long jaId;
    private final String jaCode;
    private final Long tempoId;
    private final String tempoCode;
    private final Short availableStatus;
    private final Integer recordVersion;

    // コンストラクタ
    Operator(
        Long operatorId,
        String operatorCode,
        String operatorName,
        String mailAddress,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Boolean isDeviceAuth,
        Long jaId,
        String jaCode,
        Long tempoId,
        String tempoCode,
        Short availableStatus,
        Integer recordVersion) {

        this.operatorId = operatorId;
        this.operatorCode = operatorCode;
        this.operatorName = operatorName;
        this.mailAddress = mailAddress;
        this.expirationStartDate = expirationStartDate;
        this.expirationEndDate = expirationEndDate;
        this.isDeviceAuth = isDeviceAuth;
        this.jaId = jaId;
        this.jaCode = jaCode;
        this.tempoId = tempoId;
        this.tempoCode = tempoCode;
        this.availableStatus = availableStatus;
        this.recordVersion = recordVersion;
    }

    // ファクトリーメソッド
    public static Operator createFrom(
        Long operatorId,
        String operatorCode,
        String operatorName,
        String mailAddress,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Boolean isDeviceAuth,
        Long jaId,
        String jaCode,
        Long tempoId,
        String tempoCode,
        Short availableStatus,
        Integer recordVersion) {

        return new Operator(
            operatorId,
            operatorCode,
            operatorName,
            mailAddress,
            expirationStartDate,
            expirationEndDate,
            isDeviceAuth,
            jaId,
            jaCode,
            tempoId,
            tempoCode,
            availableStatus,
            recordVersion);
    }

    // Getter
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
    public Long getTempoId() {
        return tempoId;
    }
    public String getTempoCode() {
        return tempoCode;
    }
    public Short getAvailableStatus() {
        return availableStatus;
    }
    public Integer getRecordVersion() {
        return recordVersion;
    }
}
