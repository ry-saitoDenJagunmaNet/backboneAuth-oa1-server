package net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010.Dto;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import net.jagunma.common.server.annotation.FeatureGroupInfo;
import net.jagunma.common.server.annotation.FeatureInfo;
import net.jagunma.common.server.annotation.ServiceInfo;
import net.jagunma.common.server.annotation.SubSystemInfo;
import net.jagunma.common.server.annotation.SystemInfo;
import org.springframework.stereotype.Controller;

/**
 * OA11010検索リクエスト
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：OA11010
 * 機能名：オペレーター＜一覧＞
 * サービスID：OA11010
 * サービス名：OA11010サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "O", name = "業務共通システム")
@SubSystemInfo(id = "OA", name = "基幹系認証管理サブシステム")
@FeatureGroupInfo(id = "OA1", name = "管理WEB")
@FeatureInfo(id = "OA11010", name = "オペレーター＜一覧＞")
@ServiceInfo(id = "OA11010", name = "OA11010サービス")
@Controller
public class Oa11010SearchRequestDto implements Serializable {
	private static final long serialVersionUID = 1L;

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
	private Map<String, String> tempoList;
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
	 * 状態指定日 状態指定日
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
	private Integer subsystemRoleConditionsSelect;
	/**
	 * サブシステムロール一覧
	 */
	private List<Oa11010SubsystemRoleDto> subsystemRoleList;

	/**
	 * 取引ロール条件選択
	 */
	private Integer biztranRoleConditionsSelect;
	/**
	 * サブシステムロールサブシステムコード
	 */
	private String subsystemRoleSubsystemCode;
	/**
	 * サブシステムロールサブシステムコンボボックスリスト
	 */
	private Map<String, String> subsystemRoleSubsystemList;
	/**
	 * 取引ロール一覧
	 */
	private List<Oa11010BiztranRoleDto> biztranRoleList;

	/**
	 * 機器認証使用
	 */
	private Integer deviceAuthUse;
	/**
	 * 機器認証未使用
	 */
	private Integer deviceAuthUnused;
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
	private Integer[] signintraceSignInResult;

	/**
	 * オペレータ一覧表示ページ
	 */
	private int pageNo;

	public String getJa() { return ja; }
	public void setJa(String ja) { this.ja = ja; }
	public long getJaId() { return jaId; }
	public void setJaId(long jaId) { this.jaId = jaId; }
	public String getTempoCode() { return tempoCode; }
	public void setTempoCode(String tempoCode) { this.tempoCode = tempoCode; }
	public Map<String, String> getTempoList() { return tempoList; }
	public void setTempoList(Map<String, String> tempoList) { this.tempoList = tempoList; }
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

	public Integer getSubsystemRoleConditionsSelect() { return subsystemRoleConditionsSelect; }
	public void setSubsystemRoleConditionsSelect(Integer subsystemRoleConditionsSelect) { this.subsystemRoleConditionsSelect = subsystemRoleConditionsSelect; }
	public List<Oa11010SubsystemRoleDto> getSubsystemRoleList() { return subsystemRoleList; }
	public void setSubsystemRoleList(List<Oa11010SubsystemRoleDto> subsystemRoleList) { this.subsystemRoleList = subsystemRoleList; }

	public Integer getBiztranRoleConditionsSelect() { return biztranRoleConditionsSelect; }
	public void setBiztranRoleConditionsSelect(Integer biztranRoleConditionsSelect) { this.biztranRoleConditionsSelect = biztranRoleConditionsSelect; }
	public String getSubsystemRoleSubsystemCode() { return subsystemRoleSubsystemCode; }
	public void setSubsystemRoleSubsystemCode(String subsystemRoleSubsystemCode) { this.subsystemRoleSubsystemCode = subsystemRoleSubsystemCode; }
	public Map<String, String> getSubsystemRoleSubsystemList() { return subsystemRoleSubsystemList; }
	public void setSubsystemRoleSubsystemList(Map<String, String> subsystemRoleSubsystemList) { this.subsystemRoleSubsystemList = subsystemRoleSubsystemList; }
	public List<Oa11010BiztranRoleDto> getBiztranRoleList() { return biztranRoleList; }
	public void setBiztranRoleList(List<Oa11010BiztranRoleDto> biztranRoleList) { this.biztranRoleList = biztranRoleList; }

	public Integer getDeviceAuthUse() { return deviceAuthUse; }
	public void setDeviceAuthUse(Integer deviceAuthUse) { this.deviceAuthUse = deviceAuthUse; }
	public Integer getDeviceAuthUnused() { return deviceAuthUnused; }
	public void setDeviceAuthUnused(Integer deviceAuthUnused) { this.deviceAuthUnused = deviceAuthUnused; }
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
	public Integer[] getSignintraceSignInResult() { return signintraceSignInResult; }
	public void setSignintraceSignInResult(Integer[] signintraceSignInResult) { this.signintraceSignInResult = signintraceSignInResult; }

	public int getPageNo() { return pageNo; }
	public void setPageNo(int pageNo) { this.pageNo = pageNo; }

	/**
	 * 利用可否状態IncludesList取得
	 * @return 利用可否状態IncludesList
	 */
	public List<Short> getAvailableStatusIncludesList() {
		List<Short> result = newArrayList();
		if (availableStatus0 != null && availableStatus0 == 1) {result.add((short) 0);}
		if (availableStatus1 != null && availableStatus1 == 1) {result.add((short) 1);}
		return result;
	}
}
