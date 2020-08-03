package net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemReferenceDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoReferenceDto;

/**
 * OA11010 View Object
 */
public class Oa11010Vo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * チェックボックスのチェックtrue状態値
	 */
	public static final Integer CHECKBOX_TRUE  = 1;

	/**
	 * ＪＡ
	 */
	private String ja;
	/**
	 * ＪＡID
	 */
	private long jaId;
	/**
	 * 店舗
	 */
	private String tempoCode;
	/**
	 * 店舗コンボボックスリスト
	 */
	private List<TempoReferenceDto> tempoReferenceDtoList;
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
	private Integer availableStatus0;
	/**
	 * 利用可否状態 利用不可
	 */
	private Integer availableStatus1;
	/**
	 * 有効期限選択
	 * */
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
	private List<SubSystemReferenceDto> bizTranRoleSubSystemList;
	/**
	 * 取引ロール一覧
	 */
	private List<Oa11010BizTranRoleVo> bizTranRoleList;

	/**
	 * 機器認証使用
	 */
	private Integer deviceAuthUse;
	/**
	 * 機器認証未使用
	 */
	private Integer deviceAuthUnuse;
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
	private Integer accountLockStatusLock;
	/**
	 * 現在ロック状態アンロック
	 */
	private Integer accountLockStatusUnlock;
	/**
	 * 最終パスワード変更日チェック
	 */
	private Integer passwordHistoryCheck;
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
	private Integer passwordHistoryChangeType0;
	/**
	 * 最終パスワード変更種別ユーザによる変更
	 */
	private Integer passwordHistoryChangeType1;
	/**
	 * 最終パスワード変更種別管理者によるリセット
	 */
	private Integer passwordHistoryChangeType2;
	/**
	 * 最終パスワード変更種別機器認証パスワード
	 */
	private Integer passwordHistoryChangeType3;
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
	private Integer signintraceSignIn;
	/**
	 * 最終サインオペレーションサインアウト
	 */
	private Integer signintraceSignOut;
	/**
	 * 最終サインオペレーションサインイン結果
	 */
	private Short[] signintraceSignInResult;

	/**
	 * オペレーター一覧表示ページ
	 */
	private int pageNo;

	public String getJa() { return ja; }
	public void setJa(String ja) { this.ja = ja; }
	public long getJaId() { return jaId; }
	public void setJaId(long jaId) { this.jaId = jaId; }
	public String getTempoCode() { return tempoCode; }
	public void setTempoCode(String tempoCode) { this.tempoCode = tempoCode; }
	public List<TempoReferenceDto> getTempoReferenceDtoList() { return tempoReferenceDtoList; }
	public void setTempoReferenceDtoList(List<TempoReferenceDto> tempoReferenceDtoList) { this.tempoReferenceDtoList = tempoReferenceDtoList; }
	public String getOperatorCode() { return operatorCode; }
	public void setOperatorCode(String operatorCode) { this.operatorCode = operatorCode; }
	public String getOperatorName() { return operatorName; }
	public void setOperatorName(String operatorName) { this.operatorName = operatorName; }
	public String getMailAddress() { return mailAddress; }
	public void setMailAddress(String mailAddress) { this.mailAddress = mailAddress; }
	public Integer getAvailableStatus0() { return availableStatus0; }
	public void setAvailableStatus0(Integer availableStatus0) { this.availableStatus0 = availableStatus0; }
	public Integer getAvailableStatus1() { return availableStatus1; }
	public void setAvailableStatus1(Integer availableStatus1) { this.availableStatus1 = availableStatus1; }
	public Integer getExpirationSelect() { return expirationSelect; }
	public void setExpirationSelect(Integer expirationSelect) { this.expirationSelect = expirationSelect; }
	public LocalDate getExpirationStatusDate() { return expirationStatusDate; }
	public void setExpirationStatusDate(LocalDate expirationStatusDate) { this.expirationStatusDate = expirationStatusDate; }
	public LocalDate getExpirationStartDateFrom() { return expirationStartDateFrom; }
	public void setExpirationStartDateFrom(LocalDate expirationStartDateFrom) { this.expirationStartDateFrom = expirationStartDateFrom; }
	public LocalDate getExpirationStartDateTo() { return expirationStartDateTo; }
	public void setExpirationStartDateTo(LocalDate expirationStartDateTo) { this.expirationStartDateTo = expirationStartDateTo; }
	public LocalDate getExpirationEndDateFrom() { return expirationEndDateFrom; }
	public void setExpirationEndDateFrom(LocalDate expirationEndDateFrom) { this.expirationEndDateFrom = expirationEndDateFrom; }
	public LocalDate getExpirationEndDateTo() { return expirationEndDateTo; }
	public void setExpirationEndDateTo(LocalDate expirationEndDateTo) { this.expirationEndDateTo = expirationEndDateTo; }

	public Integer getSubSystemRoleConditionsSelect() { return subSystemRoleConditionsSelect; }
	public void setSubSystemRoleConditionsSelect(Integer subSystemRoleConditionsSelect) { this.subSystemRoleConditionsSelect = subSystemRoleConditionsSelect; }
	public List<Oa11010SubSystemRoleVo> getSubSystemRoleList() { return subSystemRoleList; }
	public void setSubSystemRoleList(List<Oa11010SubSystemRoleVo> subSystemRoleList) { this.subSystemRoleList = subSystemRoleList; }

	public Integer getBizTranRoleConditionsSelect() { return bizTranRoleConditionsSelect; }
	public void setBizTranRoleConditionsSelect(Integer bizTranRoleConditionsSelect) { this.bizTranRoleConditionsSelect = bizTranRoleConditionsSelect; }
	public String getBizTranRoleSubSystemCode() { return bizTranRoleSubSystemCode; }
	public void setBizTranRoleSubSystemCode(String bizTranRoleSubSystemCode) { this.bizTranRoleSubSystemCode = bizTranRoleSubSystemCode; }
	public List<SubSystemReferenceDto> getBizTranRoleSubSystemList() { return bizTranRoleSubSystemList; }
	public void setBizTranRoleSubSystemList(List<SubSystemReferenceDto> bizTranRoleSubSystemList) { this.bizTranRoleSubSystemList = bizTranRoleSubSystemList; }
	public List<Oa11010BizTranRoleVo> getBizTranRoleList() { return bizTranRoleList; }
	public void setBizTranRoleList(List<Oa11010BizTranRoleVo> bizTranRoleList) { this.bizTranRoleList = bizTranRoleList; }

	public Integer getDeviceAuthUse() { return deviceAuthUse; }
	public void setDeviceAuthUse(Integer deviceAuthUse) { this.deviceAuthUse = deviceAuthUse; }
	public Integer getDeviceAuthUnuse() { return deviceAuthUnuse; }
	public void setDeviceAuthUnuse(Integer deviceAuthUnuse) { this.deviceAuthUnuse = deviceAuthUnuse; }
	public LocalDate getAccountLockOccurredDateFrom() { return accountLockOccurredDateFrom; }
	public void setAccountLockOccurredDateFrom(LocalDate accountLockOccurredDateFrom) { this.accountLockOccurredDateFrom = accountLockOccurredDateFrom; }
	public LocalDate getAccountLockOccurredDateTo() { return accountLockOccurredDateTo; }
	public void setAccountLockOccurredDateTo(LocalDate accountLockOccurredDateTo) { this.accountLockOccurredDateTo = accountLockOccurredDateTo; }
	public Integer getAccountLockStatusLock() { return accountLockStatusLock; }
	public void setAccountLockStatusLock(Integer accountLockStatusLock) { this.accountLockStatusLock = accountLockStatusLock; }
	public Integer getAccountLockStatusUnlock() { return accountLockStatusUnlock; }
	public void setAccountLockStatusUnlock(Integer accountLockStatusUnlock) { this.accountLockStatusUnlock = accountLockStatusUnlock; }
	public Integer getPasswordHistoryCheck() { return passwordHistoryCheck; }
	public void setPasswordHistoryCheck(Integer passwordHistoryCheck) { this.passwordHistoryCheck = passwordHistoryCheck; }
	public Integer getPasswordHistoryLastChangeDate() { return passwordHistoryLastChangeDate; }
	public void setPasswordHistoryLastChangeDate(Integer passwordHistoryLastChangeDate) { this.passwordHistoryLastChangeDate = passwordHistoryLastChangeDate; }
	public String getPasswordHistoryLastChangeDateStatus() { return passwordHistoryLastChangeDateStatus; }
	public void setPasswordHistoryLastChangeDateStatus(String passwordHistoryLastChangeDateStatus) { this.passwordHistoryLastChangeDateStatus = passwordHistoryLastChangeDateStatus; }
	public Integer getPasswordHistoryChangeType0() { return passwordHistoryChangeType0; }
	public void setPasswordHistoryChangeType0(Integer passwordHistoryChangeType0) { this.passwordHistoryChangeType0 = passwordHistoryChangeType0; }
	public Integer getPasswordHistoryChangeType1() { return passwordHistoryChangeType1; }
	public void setPasswordHistoryChangeType1(Integer passwordHistoryChangeType1) { this.passwordHistoryChangeType1 = passwordHistoryChangeType1; }
	public Integer getPasswordHistoryChangeType2() { return passwordHistoryChangeType2; }
	public void setPasswordHistoryChangeType2(Integer passwordHistoryChangeType2) { this.passwordHistoryChangeType2 = passwordHistoryChangeType2; }
	public Integer getPasswordHistoryChangeType3() { return passwordHistoryChangeType3; }
	public void setPasswordHistoryChangeType3(Integer passwordHistoryChangeType3) { this.passwordHistoryChangeType3 = passwordHistoryChangeType3; }
	public LocalDate getSignintraceTrydateFrom() { return signintraceTrydateFrom; }
	public void setSignintraceTrydateFrom(LocalDate signintraceTrydateFrom) { this.signintraceTrydateFrom = signintraceTrydateFrom; }
	public LocalDate getSignintraceTrydateTo() { return signintraceTrydateTo; }
	public void setSignintraceTrydateTo(LocalDate signintraceTrydateTo) { this.signintraceTrydateTo = signintraceTrydateTo; }
	public String getSignintraceTryIpAddress() { return signintraceTryIpAddress; }
	public void setSignintraceTryIpAddress(String signintraceTryIpAddress) { this.signintraceTryIpAddress = signintraceTryIpAddress; }
	public Integer getSignintraceSignIn() { return signintraceSignIn; }
	public void setSignintraceSignIn(Integer signintraceSignIn) { this.signintraceSignIn = signintraceSignIn; }
	public Integer getSignintraceSignOut() { return signintraceSignOut; }
	public void setSignintraceSignOut(Integer signintraceSignOut) { this.signintraceSignOut = signintraceSignOut; }
	public Short[] getSignintraceSignInResult() { return signintraceSignInResult; }
	public void setSignintraceSignInResult(Short[] signintraceSignInResult) { this.signintraceSignInResult = signintraceSignInResult; }

	public int getPageNo() { return pageNo; }
	public void setPageNo(int pageNo) { this.pageNo = pageNo; }

	/**
	 * 利用可否状態IncludesList取得
	 * @return 利用可否状態IncludesList
	 */
	public List<Short> getAvailableStatusIncludesList() {
		List<Short> result = newArrayList();
		if (CHECKBOX_TRUE.equals(availableStatus0)) {result.add((short) 0);}
		if (CHECKBOX_TRUE.equals(availableStatus1)) {result.add((short) 1);}
		return result;
	}

	/**
	 * アカウントロック状態IncludesList取得
	 * @return アカウントロック状態IncludesList
	 */
	public List<Integer> getAccountLockStatusIncludesList() {
		List<Integer> result = newArrayList();
		if (CHECKBOX_TRUE.equals(accountLockStatusLock)) {result.add(0);}
		if (CHECKBOX_TRUE.equals(accountLockStatusUnlock)) {result.add(1);}
		return result;
	}
}
