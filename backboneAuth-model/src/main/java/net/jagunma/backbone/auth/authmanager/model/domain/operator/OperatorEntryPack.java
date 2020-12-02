package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import java.time.LocalDate;

/**
 * オペレーターエントリーパック
 */
public class OperatorEntryPack {

    private final String operatorCode;
    private final String operatorName;
    private final String mailAddress;
    private final LocalDate validThruStartDate;
    private final LocalDate validThruEndDate;
    private final Long jaId;
    private final String jaCode;
    private final Long branchId;
    private final String branchCode;
    private final String changeCause;
    private final String password;

    // コンストラクタ
    OperatorEntryPack(
        String operatorCode,
        String operatorName,
        String mailAddress,
        LocalDate validThruStartDate,
        LocalDate validThruEndDate,
        Long jaId,
        String jaCode,
        Long branchId,
        String branchCode,
        String changeCause,
        String password) {

        this.operatorCode = operatorCode;
        this.operatorName = operatorName;
        this.mailAddress = mailAddress;
        this.validThruStartDate = validThruStartDate;
        this.validThruEndDate = validThruEndDate;
        this.jaId = jaId;
        this.jaCode = jaCode;
        this.branchId = branchId;
        this.branchCode = branchCode;
        this.changeCause = changeCause;
        this.password = password;
    }
    // ファクトリーメソッド
    public static OperatorEntryPack createFrom(
        String operatorCode,
        String operatorName,
        String mailAddress,
        LocalDate validThruStartDate,
        LocalDate validThruEndDate,
        Long jaId,
        String jaCode,
        Long branchId,
        String branchCode,
        String changeCause,
        String password) {

        return new OperatorEntryPack(
            operatorCode,
            operatorName,
            mailAddress,
            validThruStartDate,
            validThruEndDate,
            jaId,
            jaCode,
            branchId,
            branchCode,
            changeCause,
            password);
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
    public LocalDate getValidThruStartDate() {
        return validThruStartDate;
    }
    public LocalDate getValidThruEndDate() {
        return validThruEndDate;
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
}
