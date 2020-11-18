package net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.util.CheckboxSmoother;
import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfResponseVo;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemSource;

/**
 * OA11010 View Object
 */
public class Oa11010Vo extends BaseOfResponseVo {

    private static final long serialVersionUID = 1L;

    /**
     * ＪＡ
     */
    private String ja;
    /**
     * ＪＡID
     */
    private Long jaId;
    /**
     * 店舗ID
     */
    private Long branchId;
    /**
     * 店舗コンボボックスItemsSource
     */
    private List<SelectOptionItemSource> branchItemsSource;
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
     * 利用可否状態 利用可能
     */
    private CheckboxSmoother availableStatus0;
//    private Boolean availableStatus0;
    /**
     * 利用可否状態 利用不可
     */
    private CheckboxSmoother availableStatus1;
//    private Boolean availableStatus1;
    /**
     * 有効期限選択
     */
    private Integer expirationSelect;
    /**
     * 状態指定日
     */
    private LocalDate expirationStatusDate;
    /**
     * 条件指定 有効期限開始（開始日）
     */
    private LocalDate expirationStartDateFrom;
    /**
     * 条件指定 有効期限開始（終了日）
     */
    private LocalDate expirationStartDateTo;
    /**
     * 条件指定 有効期限終了（開始日）
     */
    private LocalDate expirationEndDateFrom;
    /**
     * 条件指定 有効期限終了（終了日）
     */
    private LocalDate expirationEndDateTo;

    /**
     * サブシステムロール条件選択
     */
    private Integer subSystemRoleConditionsSelect;
    /**
     * サブシステムロール一覧
     */
    private List<Oa11010SubSystemRoleVo> subSystemRoleList;

    /**
     * 取引ロール条件選択
     */
    private Integer bizTranRoleConditionsSelect;
    /**
     * 取引ロール一覧フィルター用サブシステムコード
     */
    private String bizTranRoleSubSystemCode;
    /**
     * 取引ロール一覧フィルター用サブシステムコンボボックスリスト
     */
    private List<SelectOptionItemSource> bizTranRoleSubSystemList;
    /**
     * 取引ロール一覧
     */
    private List<Oa11010BizTranRoleVo> bizTranRoleList;

    /**
     * 機器認証使用
     */
    private Short deviceAuthUse;
    /**
     * 機器認証未使用
     */
    private Short deviceAuthUnuse;
    /**
     * 最終ロック・アンロック発生日（開始日）
     */
    private LocalDate accountLockOccurredDateFrom;
    /**
     * 最終ロック・アンロック発生日（終了日）
     */
    private LocalDate accountLockOccurredDateTo;
    /**
     * 現在ロック状態ロック
     */
    private Short accountLockStatusLock;
    /**
     * 現在ロック状態アンロック
     */
    private Short accountLockStatusUnlock;
    /**
     * 最終パスワード変更日チェック
     */
    private Short passwordHistoryCheck;
    /**
     * 最終パスワード変更日数
     */
    private Integer passwordHistoryLastChangeDate;
    /**
     * 最終パスワード変更日状態
     */
    private String passwordHistoryLastChangeDateStatus;
    /**
     * 最終パスワード変更種別初期
     */
    private Short passwordHistoryChangeType0;
    /**
     * 最終パスワード変更種別ユーザによる変更
     */
    private Short passwordHistoryChangeType1;
    /**
     * 最終パスワード変更種別管理者によるリセット
     */
    private Short passwordHistoryChangeType2;
    /**
     * 最終パスワード変更種別機器認証パスワード
     */
    private Short passwordHistoryChangeType3;
    /**
     * 最終サインオペレーション試行日（開始日）
     */
    private LocalDate signintraceTrydateFrom;
    /**
     * 最終サインオペレーション試行日（終了日）
     */
    private LocalDate signintraceTrydateTo;
    /**
     * 最終サインオペレーション元IPアドレス
     */
    private String signintraceTryIpAddress;
    /**
     * 最終サインオペレーションサインイン
     */
    private Short signintraceSignIn;
    /**
     * 最終サインオペレーションサインアウト
     */
    private Short signintraceSignOut;
    /**
     * 最終サインオペレーションサインイン結果
     */
    private Short[] signintraceSignInResult;

    /**
     * オペレーター一覧表示ページ
     */
    private int pageNo;

    // Getter／Setter
    public String getJa() {
        return ja;
    }
    public void setJa(String ja) {
        this.ja = ja;
    }
    public Long getJaId() {
        return jaId;
    }
    public void setJaId(Long jaId) {
        this.jaId = jaId;
    }
    public Long getBranchId() {
        return branchId;
    }
    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
    public List<SelectOptionItemSource> getBranchItemsSource() {
        return branchItemsSource;
    }
    public void setBranchItemsSource(List<SelectOptionItemSource> branchItemsSource) {
        this.branchItemsSource = branchItemsSource;
    }
    public String getOperatorCode() {
        return operatorCode;
    }
    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }
    public String getOperatorName() {
        return operatorName;
    }
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
    public String getMailAddress() {
        return mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
//    public Boolean getAvailableStatus0() {
//        return availableStatus0;
//    }
//    public void setAvailableStatus0(Boolean availableStatus0) {
//        this.availableStatus0 = availableStatus0;
//    }
//    public Boolean getAvailableStatus1() {
//        return availableStatus1;
//    }
//    public void setAvailableStatus1(Boolean availableStatus1) {
//        this.availableStatus1 = availableStatus1;
//    }

    public CheckboxSmoother getAvailableStatus0() {
        return availableStatus0;
    }
    public void setAvailableStatus0(CheckboxSmoother availableStatus0) {
        this.availableStatus0 = availableStatus0;
    }
    public CheckboxSmoother getAvailableStatus1() {
        return availableStatus1;
    }
    public void setAvailableStatus1(CheckboxSmoother availableStatus1) {
        this.availableStatus1 = availableStatus1;
    }


    public Integer getExpirationSelect() {
        return expirationSelect;
    }
    public void setExpirationSelect(Integer expirationSelect) {
        this.expirationSelect = expirationSelect;
    }
    public LocalDate getExpirationStatusDate() {
        return expirationStatusDate;
    }
    public void setExpirationStatusDate(LocalDate expirationStatusDate) {
        this.expirationStatusDate = expirationStatusDate;
    }
    public LocalDate getExpirationStartDateFrom() {
        return expirationStartDateFrom;
    }
    public void setExpirationStartDateFrom(LocalDate expirationStartDateFrom) {
        this.expirationStartDateFrom = expirationStartDateFrom;
    }
    public LocalDate getExpirationStartDateTo() {
        return expirationStartDateTo;
    }
    public void setExpirationStartDateTo(LocalDate expirationStartDateTo) {
        this.expirationStartDateTo = expirationStartDateTo;
    }
    public LocalDate getExpirationEndDateFrom() {
        return expirationEndDateFrom;
    }
    public void setExpirationEndDateFrom(LocalDate expirationEndDateFrom) {
        this.expirationEndDateFrom = expirationEndDateFrom;
    }
    public LocalDate getExpirationEndDateTo() {
        return expirationEndDateTo;
    }
    public void setExpirationEndDateTo(LocalDate expirationEndDateTo) {
        this.expirationEndDateTo = expirationEndDateTo;
    }
    public Integer getSubSystemRoleConditionsSelect() {
        return subSystemRoleConditionsSelect;
    }
    public void setSubSystemRoleConditionsSelect(Integer subSystemRoleConditionsSelect) {
        this.subSystemRoleConditionsSelect = subSystemRoleConditionsSelect;
    }
    public List<Oa11010SubSystemRoleVo> getSubSystemRoleList() {
        return subSystemRoleList;
    }
    public void setSubSystemRoleList(List<Oa11010SubSystemRoleVo> subSystemRoleList) {
        this.subSystemRoleList = subSystemRoleList;
    }
    public Integer getBizTranRoleConditionsSelect() {
        return bizTranRoleConditionsSelect;
    }
    public void setBizTranRoleConditionsSelect(Integer bizTranRoleConditionsSelect) {
        this.bizTranRoleConditionsSelect = bizTranRoleConditionsSelect;
    }
    public String getBizTranRoleSubSystemCode() {
        return bizTranRoleSubSystemCode;
    }
    public void setBizTranRoleSubSystemCode(String bizTranRoleSubSystemCode) {
        this.bizTranRoleSubSystemCode = bizTranRoleSubSystemCode;
    }
    public List<SelectOptionItemSource> getBizTranRoleSubSystemList() {
        return bizTranRoleSubSystemList;
    }
    public void setBizTranRoleSubSystemList(List<SelectOptionItemSource> bizTranRoleSubSystemList) {
        this.bizTranRoleSubSystemList = bizTranRoleSubSystemList;
    }
    public List<Oa11010BizTranRoleVo> getBizTranRoleList() {
        return bizTranRoleList;
    }
    public void setBizTranRoleList(List<Oa11010BizTranRoleVo> bizTranRoleList) {
        this.bizTranRoleList = bizTranRoleList;
    }
    public Short getDeviceAuthUse() {
        return deviceAuthUse;
    }
    public void setDeviceAuthUse(Short deviceAuthUse) {
        this.deviceAuthUse = deviceAuthUse;
    }
    public Short getDeviceAuthUnuse() {
        return deviceAuthUnuse;
    }
    public void setDeviceAuthUnuse(Short deviceAuthUnuse) {
        this.deviceAuthUnuse = deviceAuthUnuse;
    }
    public LocalDate getAccountLockOccurredDateFrom() {
        return accountLockOccurredDateFrom;
    }
    public void setAccountLockOccurredDateFrom(LocalDate accountLockOccurredDateFrom) {
        this.accountLockOccurredDateFrom = accountLockOccurredDateFrom;
    }
    public LocalDate getAccountLockOccurredDateTo() {
        return accountLockOccurredDateTo;
    }
    public void setAccountLockOccurredDateTo(LocalDate accountLockOccurredDateTo) {
        this.accountLockOccurredDateTo = accountLockOccurredDateTo;
    }
    public Short getAccountLockStatusLock() {
        return accountLockStatusLock;
    }
    public void setAccountLockStatusLock(Short accountLockStatusLock) {
        this.accountLockStatusLock = accountLockStatusLock;
    }
    public Short getAccountLockStatusUnlock() {
        return accountLockStatusUnlock;
    }
    public void setAccountLockStatusUnlock(Short accountLockStatusUnlock) {
        this.accountLockStatusUnlock = accountLockStatusUnlock;
    }
    public Short getPasswordHistoryCheck() {
        return passwordHistoryCheck;
    }
    public void setPasswordHistoryCheck(Short passwordHistoryCheck) {
        this.passwordHistoryCheck = passwordHistoryCheck;
    }
    public Integer getPasswordHistoryLastChangeDate() {
        return passwordHistoryLastChangeDate;
    }
    public void setPasswordHistoryLastChangeDate(Integer passwordHistoryLastChangeDate) {
        this.passwordHistoryLastChangeDate = passwordHistoryLastChangeDate;
    }
    public String getPasswordHistoryLastChangeDateStatus() {
        return passwordHistoryLastChangeDateStatus;
    }
    public void setPasswordHistoryLastChangeDateStatus(String passwordHistoryLastChangeDateStatus) {
        this.passwordHistoryLastChangeDateStatus = passwordHistoryLastChangeDateStatus;
    }
    public Short getPasswordHistoryChangeType0() {
        return passwordHistoryChangeType0;
    }
    public void setPasswordHistoryChangeType0(Short passwordHistoryChangeType0) {
        this.passwordHistoryChangeType0 = passwordHistoryChangeType0;
    }
    public Short getPasswordHistoryChangeType1() {
        return passwordHistoryChangeType1;
    }
    public void setPasswordHistoryChangeType1(Short passwordHistoryChangeType1) {
        this.passwordHistoryChangeType1 = passwordHistoryChangeType1;
    }
    public Short getPasswordHistoryChangeType2() {
        return passwordHistoryChangeType2;
    }
    public void setPasswordHistoryChangeType2(Short passwordHistoryChangeType2) {
        this.passwordHistoryChangeType2 = passwordHistoryChangeType2;
    }
    public Short getPasswordHistoryChangeType3() {
        return passwordHistoryChangeType3;
    }
    public void setPasswordHistoryChangeType3(Short passwordHistoryChangeType3) {
        this.passwordHistoryChangeType3 = passwordHistoryChangeType3;
    }
    public LocalDate getSignintraceTrydateFrom() {
        return signintraceTrydateFrom;
    }
    public void setSignintraceTrydateFrom(LocalDate signintraceTrydateFrom) {
        this.signintraceTrydateFrom = signintraceTrydateFrom;
    }
    public LocalDate getSignintraceTrydateTo() {
        return signintraceTrydateTo;
    }
    public void setSignintraceTrydateTo(LocalDate signintraceTrydateTo) {
        this.signintraceTrydateTo = signintraceTrydateTo;
    }
    public String getSignintraceTryIpAddress() {
        return signintraceTryIpAddress;
    }
    public void setSignintraceTryIpAddress(String signintraceTryIpAddress) {
        this.signintraceTryIpAddress = signintraceTryIpAddress;
    }
    public Short getSignintraceSignIn() {
        return signintraceSignIn;
    }
    public void setSignintraceSignIn(Short signintraceSignIn) {
        this.signintraceSignIn = signintraceSignIn;
    }
    public Short getSignintraceSignOut() {
        return signintraceSignOut;
    }
    public void setSignintraceSignOut(Short signintraceSignOut) {
        this.signintraceSignOut = signintraceSignOut;
    }
    public Short[] getSignintraceSignInResult() {
        return signintraceSignInResult;
    }
    public void setSignintraceSignInResult(Short[] signintraceSignInResult) {
        this.signintraceSignInResult = signintraceSignInResult;
    }
    public int getPageNo() {
        return pageNo;
    }
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
