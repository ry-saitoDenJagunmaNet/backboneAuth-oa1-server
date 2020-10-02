package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import java.time.LocalDate;

/**
 * オペレーターエントリーパック
 */
public class OperatorEntryPack {

    private final String operatorCode;
    private final String operatorName;
    private final String mailAddress;
    private final LocalDate expirationStartDate;
    private final LocalDate expirationEndDate;
    private final Long jaId;
    private final String jaCode;
    private final Long branchId;
    private final String branchCode;
    private final String changeCause;
    private final String password;
    private final String confirmPassword;

    // コンストラクタ
    OperatorEntryPack(
        String operatorCode,
        String operatorName,
        String mailAddress,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Long jaId,
        String jaCode,
        Long branchId,
        String branchCode,
        String changeCause,
        String password,
        String confirmPassword) {

        this.operatorCode = operatorCode;
        this.operatorName = operatorName;
        this.mailAddress = mailAddress;
        this.expirationStartDate = expirationStartDate;
        this.expirationEndDate = expirationEndDate;
        this.jaId = jaId;
        this.jaCode = jaCode;
        this.branchId = branchId;
        this.branchCode = branchCode;
        this.changeCause = changeCause;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
    // ファクトリーメソッド
    public static OperatorEntryPack createFrom(
        String operatorCode,
        String operatorName,
        String mailAddress,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Long jaId,
        String jaCode,
        Long branchId,
        String branchCode,
        String changeCause,
        String password,
        String confirmPassword) {

        return new OperatorEntryPack(
            operatorCode,
            operatorName,
            mailAddress,
            expirationStartDate,
            expirationEndDate,
            jaId,
            jaCode,
            branchId,
            branchCode,
            changeCause,
            password,
            confirmPassword);
    }

    // Getter
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
    public String getChangeCause() {
        return changeCause;
    }
    public String getPassword() {
        return password;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
}
