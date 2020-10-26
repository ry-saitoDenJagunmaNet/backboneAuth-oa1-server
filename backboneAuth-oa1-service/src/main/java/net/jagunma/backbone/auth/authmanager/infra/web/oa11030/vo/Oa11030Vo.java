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
     * オペレーターID
     */
    private Long operatorId;
    /**
     * レコードバージョン
     */
    private Integer recordVersion;
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
     * 変更事由プレースホルダー
     */
    private String changeCausePlaceholder;
    /**
     * ロック状態
     */
    private Short accountLockStatus;
    /**
     * サブシステムロールテーブル
     */
    private List<Oa11030SubsystemRoleTableVo> Oa11030SubsystemRoleTableVoList;
    /**
     * 取引ロールテーブル
     */
    private List<Oa11030BizTranRoleTableVo> Oa11030BizTranRoleTableVoList;

    /**
     * 店舗コンボボックスItemsSource
     */
    private List<SelectOptionItemSource> branchItemsSource;

    // Getter
    public Long getOperatorId() {
        return operatorId;
    }
    public Integer getRecordVersion() {
        return recordVersion;
    }
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
    public Boolean getIsDeviceAuth() {
        return isDeviceAuth;
    }
    public Short getAvailableStatus() {
        return availableStatus;
    }
    public String getChangeCause() {
        return changeCause;
    }
    public String getChangeCausePlaceholder() {
        return changeCausePlaceholder;
    }
    public Short getAccountLockStatus() {
        return accountLockStatus;
    }
    public List<Oa11030SubsystemRoleTableVo> getOa11030SubsystemRoleTableVoList() {
        return Oa11030SubsystemRoleTableVoList;
    }
    public List<Oa11030BizTranRoleTableVo> getOa11030BizTranRoleTableVoList() {
        return Oa11030BizTranRoleTableVoList;
    }
    public List<SelectOptionItemSource> getBranchItemsSource() {
        return branchItemsSource;
    }

    // Setter
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
    public void setRecordVersion(Integer recordVersion) {
        this.recordVersion = recordVersion;
    }
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
    public void setIsDeviceAuth(Boolean isDeviceAuth) {
        this.isDeviceAuth = isDeviceAuth;
    }
    public void setAvailableStatus(Short availableStatus) {
        this.availableStatus = availableStatus;
    }
    public void setChangeCause(String changeCause) {
        this.changeCause = changeCause;
    }
    public void setChangeCausePlaceholder(String changeCausePlaceholder) {
        this.changeCausePlaceholder = changeCausePlaceholder;
    }
    public void setAccountLockStatus(Short accountLockStatus) {
        this.accountLockStatus = accountLockStatus;
    }
    public void setOa11030SubsystemRoleTableVoList(List<Oa11030SubsystemRoleTableVo> oa11030SubsystemRoleTableVoList) {
        Oa11030SubsystemRoleTableVoList = oa11030SubsystemRoleTableVoList;
    }
    public void setOa11030BizTranRoleTableVoList(List<Oa11030BizTranRoleTableVo> oa11030BizTranRoleTableVoList) {
        Oa11030BizTranRoleTableVoList = oa11030BizTranRoleTableVoList;
    }
    public void setBranchItemsSource(List<SelectOptionItemSource> branchItemsSource) {
        this.branchItemsSource = branchItemsSource;
    }
}
