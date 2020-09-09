package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import java.time.LocalDate;

/**
 * オペレーターエントリーパック
 */
public class OperatorEntryPack {

    private String operatorCode;
    private String operatorName;
    private String mailAddress;
    private LocalDate expirationStartDate;
    private LocalDate expirationEndDate;
    private Long jaId;
    private String jaCode;
    private Long tempoId;
    private String tempoCode;
    private String changeCause;
    private String password;
    private String confirmPassword;

    // コンストラクタ
    OperatorEntryPack() {
    }
    OperatorEntryPack(
        String operatorCode,
        String operatorName,
        String mailAddress,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Long jaId,
        String jaCode,
        Long tempoId,
        String tempoCode,
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
        this.tempoId = tempoId;
        this.tempoCode = tempoCode;
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
        Long tempoId,
        String tempoCode,
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
            tempoId,
            tempoCode,
            changeCause,
            password,
            confirmPassword);
    }
    // 空生成メソッド
    public static OperatorEntryPack empty() {
        return new OperatorEntryPack();
    }
    // 空判定メソッド
    public boolean isEmpty() {
        return operatorCode == null &&
            operatorName == null &&
            mailAddress == null &&
            expirationStartDate == null &&
            expirationEndDate == null &&
            jaId == null &&
            jaCode == null &&
            tempoId == null &&
            tempoCode == null &&
            changeCause == null &&
            password == null &&
            confirmPassword == null;
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
    public Long getTempoId() {
        return tempoId;
    }
    public String getTempoCode() {
        return tempoCode;
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
