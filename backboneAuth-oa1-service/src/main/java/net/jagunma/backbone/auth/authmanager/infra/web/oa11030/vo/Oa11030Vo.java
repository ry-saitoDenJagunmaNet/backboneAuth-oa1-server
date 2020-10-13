package net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfResponseVo;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemSource;

/**
 * OA11030 ViewObject
 */
public class Oa11030Vo extends BaseOfResponseVo {

    private static final long serialVersionUID = 1L;

    /**
     * ＪＡ
     */
    private String ja;
    /**
     * 店舗ID
     */
    private Long branchId;
    /**
     * オペレーターコード
     */
    private String operatorCode;
    /**
     * オペレーター名
     */
    private String operatorName;
    /**
     * メールアドレス
     */
    private String mailAddress;
    /**
     * 有効期限（開始日）
     */
    private LocalDate expirationStartDate;
    /**
     * 有効期限（終了日）
     */
    private LocalDate expirationEndDate;
    /**
     * 機器認証
     */
    private Boolean isDeviceAuth;
    /**
     * 利用可否状態
     */
    private Short availableStatus;
    /**
     * 変更事由
     */
    private String changeCause;
    /**
     * ロック状態
     */
    private Short lockStatus;

    /**
     * 店舗コンボボックスItemsSource
     */
    private List<SelectOptionItemSource> branchItemsSource;

    /**
     * サブシステムロールテーブル
     */
    private List<Oa11030SubsystemRoleTableVo> subsystemRoleTable;

    /**
     * 取引ロールテーブル
     */
    private List<Oa11030BizTranRoleTableVo> bizTranRoleTable;

    // Getter
    public String getJa() {
        return ja;
    }
    public Long getBranchId() {
        return branchId;
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
    public Boolean getDeviceAuth() {
        return isDeviceAuth;
    }
    public Short getAvailableStatus() {
        return availableStatus;
    }
    public String getChangeCause() {
        return changeCause;
    }
    public Short getLockStatus() {
        return lockStatus;
    }
    public List<SelectOptionItemSource> getBranchItemsSource() {
        return branchItemsSource;
    }
    public List<Oa11030SubsystemRoleTableVo> getSubsystemRoleTable() {
        return subsystemRoleTable;
    }
    public List<Oa11030BizTranRoleTableVo> getBizTranRoleTable() {
        return bizTranRoleTable;
    }

    // Setter
    public void setJa(String ja) {
        this.ja = ja;
    }
    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
    public void setExpirationStartDate(LocalDate expirationStartDate) {
        this.expirationStartDate = expirationStartDate;
    }
    public void setExpirationEndDate(LocalDate expirationEndDate) {
        this.expirationEndDate = expirationEndDate;
    }
    public void setDeviceAuth(Boolean deviceAuth) {
        isDeviceAuth = deviceAuth;
    }
    public void setAvailableStatus(Short availableStatus) {
        this.availableStatus = availableStatus;
    }
    public void setChangeCause(String changeCause) {
        this.changeCause = changeCause;
    }
    public void setLockStatus(Short lockStatus) {
        this.lockStatus = lockStatus;
    }
    public void setBranchItemsSource(List<SelectOptionItemSource> branchItemsSource) {
        this.branchItemsSource = branchItemsSource;
    }
    public void setSubsystemRoleTable(List<Oa11030SubsystemRoleTableVo> subsystemRoleTable) {
        this.subsystemRoleTable = subsystemRoleTable;
    }
    public void setBizTranRoleTable(List<Oa11030BizTranRoleTableVo> bizTranRoleTable) {
        this.bizTranRoleTable = bizTranRoleTable;
    }
}

/**
 * サブシステムロールテーブル
 */
class Oa11030SubsystemRoleTableVo {
    private String roleName;
    private String expirationDate;
}

/**
 * 取引ロールテーブル
 */
class Oa11030BizTranRoleTableVo {
    private String roleCode;
    private String roleName;
    private String expirationDate;
}
