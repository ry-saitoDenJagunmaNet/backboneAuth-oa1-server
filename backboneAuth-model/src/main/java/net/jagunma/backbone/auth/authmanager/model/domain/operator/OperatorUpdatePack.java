package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;

/**
 * オペレーターアップデートパック
 */
public class OperatorUpdatePack {

    private final Long operatorId;
    private final String operatorName;
    private final String mailAddress;
    private final LocalDate expirationStartDate;
    private final LocalDate expirationEndDate;
    private final Boolean isDeviceAuth;
    private final Long branchId;
    private final String branchCode;
    private final AvailableStatus availableStatus;
    private final Integer recordVersion;
    private final String changeCause;

    // コンストラクタ
    OperatorUpdatePack(
        Long operatorId,
        String operatorName,
        String mailAddress,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Boolean isDeviceAuth,
        Long branchId,
        String branchCode,
        AvailableStatus availableStatus,
        Integer recordVersion,
        String changeCause) {

        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.mailAddress = mailAddress;
        this.expirationStartDate = expirationStartDate;
        this.expirationEndDate = expirationEndDate;
        this.isDeviceAuth = isDeviceAuth;
        this.branchId = branchId;
        this.branchCode = branchCode;
        this.availableStatus = availableStatus;
        this.recordVersion = recordVersion;
        this.changeCause = changeCause;
    }
    // ファクトリーメソッド
    public static OperatorUpdatePack createFrom(
        Long operatorId,
        String operatorName,
        String mailAddress,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Boolean isDeviceAuth,
        Long branchId,
        String branchCode,
        AvailableStatus availableStatus,
        Integer recordVersion,
        String changeCause) {

        return new OperatorUpdatePack(
            operatorId,
            operatorName,
            mailAddress,
            expirationStartDate,
            expirationEndDate,
            isDeviceAuth,
            branchId,
            branchCode,
            availableStatus,
            recordVersion,
            changeCause);
    }

    // Getter
    public Long getOperatorId() {
        return operatorId;
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
    public String getChangeCause() {
        return changeCause;
    }
}
