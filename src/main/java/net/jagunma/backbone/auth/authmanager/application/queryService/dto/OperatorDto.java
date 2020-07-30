package net.jagunma.backbone.auth.authmanager.application.queryService.dto;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OperatorDto {
	/**
	 * オペレーターID
	 */
	private long operatorId;
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
	 * 有効期限開始日
	 */
	private LocalDate expirationStartDate;
	/**
	 * 有効期限終了日
	 */
	private LocalDate expirationEndDate;
	/**
	 * 機器認証
	 */
	private boolean isDeviceAuth;
	/**
	 * JAID
	 */
	private long jaId;
	/**
	 * JAコード
	 */
	private String jaCode;
	/**
	 * 店舗ID
	 */
	private long tempoId;
	/**
	 * 店舗コード
	 */
	private String tempoCode;
	/**
	 * 利用可否状態
	 */
	private int availableStatus;
	/**
	 * 登録者ID
	 */
	private long createdBy;
	/**
	 * 登録日時
	 */
	private LocalDateTime createdAt;
	/**
	 * 登録元IPアドレス
	 */
	private String createdIpAddress;
	/**
	 * 更新者ID
	 */
	private Long updatedBy;
	/**
	 * 更新日時
	 */
	private LocalDateTime updatedAt;
	/**
	 * 更新元IPアドレス
	 */
	private String updatedIpAddress;
	/**
	 * レコードバージョン
	 */
	private int recordVersion;
	/**
	 * 店舗名
	 */
	private String tempoName;
		/**
	 * アカウントロック情報ID
	 */
	private long accountLockId;
	/**
	 * 発生日時
	 */
	private LocalDateTime occurredDateTime;
	/**
	 * ロック状態
	 */
	private Short lockStatus;
	/**
	 * パスワード履歴ID
	 */
	private Long passwordHistoryId;
	/**
	 * パスワード履歴変更日時
	 */
	private LocalDateTime changeDateTime;
	/**
	 * 変更種別
	 */
	private Short changeType;
	/**
	 * サインイン証跡ID
	 */
	private Long signInTraceId;
	/**
	 * サインイン試行日時
	 */
	private LocalDateTime tryDateTime;
	/**
	 * サインイン試行IPアドレス
	 */
	private String tryIpAddress;
	/**
	 * サインイン起因
	 */
	private Short signInCause;
	/**
	 * サインイン結果
	 */
	private Short signInResult;
	/**
	 * サインアウト証跡ID
	 */
	private Long signOutTraceId;
	/**
	 * サインアウト日時
	 */
	private LocalDateTime signOutDateTime;
	/**
	 * サインアウトIPアドレス
	 */
	private String signOutIpAddress;

	/**
	 * オペレーターサブシステムロール割当リスト
	 */
	private List<OperatorSubSystemRoleDto> operatorSubSystemRoleList = newArrayList();
	/**
	 * オペレーター取引ロール割当リスト
	 */
	private List<OperatorBizTranRoleDto> operatorBizTranRoleList = newArrayList();

	public long getOperatorId() { return operatorId; }
	public void setOperatorId(long operatorId) { this.operatorId = operatorId; }
	public String getOperatorCode() { return operatorCode; }
	public void setOperatorCode(String operatorCode) { this.operatorCode = operatorCode; }
	public String getOperatorName() { return operatorName; }
	public void setOperatorName(String operatorName) { this.operatorName = operatorName; }
	public String getMailAddress() { return mailAddress; }
	public void setMailAddress(String mailAddress) { this.mailAddress = mailAddress; }
	public LocalDate getExpirationStartDate() { return expirationStartDate; }
	public void setExpirationStartDate(LocalDate expirationStartDate) { this.expirationStartDate = expirationStartDate; }
	public LocalDate getExpirationEndDate() { return expirationEndDate; }
	public void setExpirationEndDate(LocalDate expirationEndDate) { this.expirationEndDate = expirationEndDate; }
	public boolean getIsDeviceAuth() { return isDeviceAuth; }
	public void setIsDeviceAuth(boolean isDeviceAuth) { this.isDeviceAuth = isDeviceAuth; }
	public long getJaId() { return jaId; }
	public void setJaId(long jaId) { this.jaId = jaId; }
	public String getJaCode() { return jaCode; }
	public void setJaCode(String jaCode) { this.jaCode = jaCode; }
	public long getTempoId() { return tempoId; }
	public void setTempoId(long tempoId) { this.tempoId = tempoId; }
	public String getTempoCode() { return tempoCode; }
	public void setTempoCode(String tempoCode) { this.tempoCode = tempoCode; }
	public int getAvailableStatus() { return availableStatus; }
	public void setAvailableStatus(int availableStatus) { this.availableStatus = availableStatus; }
	public long getCreatedBy() { return createdBy; }
	public void setCreatedBy(long createdBy) { this.createdBy = createdBy; }
	public LocalDateTime getCreatedAt() { return createdAt; }
	public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
	public String getCreatedIpAddress() { return createdIpAddress; }
	public void setCreatedIpAddress(String createdIpAddress) { this.createdIpAddress = createdIpAddress; }
	public Long getUpdatedBy() { return updatedBy; }
	public void setUpdatedBy(Long updatedBy) { this.updatedBy = updatedBy; }
	public LocalDateTime getUpdatedAt() { return updatedAt; }
	public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
	public String getUpdatedIpAddress() { return updatedIpAddress; }
	public void setUpdatedIpAddress(String updatedIpAddress) { this.updatedIpAddress = updatedIpAddress; }
	public int getRecordVersion() { return recordVersion; }
	public void setRecordVersion(int recordVersion) { this.recordVersion = recordVersion; }
	public String getTempoName() { return tempoName; }
	public void setTempoName(String tempoName) { this.tempoName = tempoName; }
	public long getAccountLockId() { return accountLockId; }
	public void setAccountLockId(long accountLockId) { this.accountLockId = accountLockId; }
	public LocalDateTime getOccurredDateTime() { return occurredDateTime; }
	public void setOccurredDateTime(LocalDateTime occurredDateTime) { this.occurredDateTime = occurredDateTime; }
	public Short getLockStatus() { return lockStatus; }
	public void setLockStatus(Short lockStatus) { this.lockStatus = lockStatus; }
	public Long getPasswordHistoryId() { return passwordHistoryId; }
	public void setPasswordHistoryId(Long passwordHistoryId) { this.passwordHistoryId = passwordHistoryId; }
	public LocalDateTime getChangeDateTime() { return changeDateTime; }
	public void setChangeDateTime(LocalDateTime changeDateTime) { this.changeDateTime = changeDateTime; }
	public Short getChangeType() { return changeType; }
	public void setChangeType(Short changeType) { this.changeType = changeType; }
	public Long getSignInTraceId() { return signInTraceId; }
	public void setSignInTraceId(Long signInTraceId) { this.signInTraceId = signInTraceId; }
	public LocalDateTime getTryDateTime() { return tryDateTime; }
	public void setTryDateTime(LocalDateTime tryDateTime) { this.tryDateTime = tryDateTime; }
	public String getTryIpAddress() { return tryIpAddress; }
	public void setTryIpAddress(String tryIpAddress) { this.tryIpAddress = tryIpAddress; }
	public Short getSignInCause() { return signInCause; }
	public void setSignInCause(Short signInCause) { this.signInCause = signInCause; }
	public Short getSignInResult() { return signInResult; }
	public void setSignInResult(Short signInResult) { this.signInResult = signInResult; }
	public Long getSignOutTraceId() { return signOutTraceId; }
	public void setSignOutTraceId(Long signOutTraceId) { this.signOutTraceId = signOutTraceId; }
	public LocalDateTime getSignOutDateTime() { return signOutDateTime; }
	public void setSignOutDateTime(LocalDateTime signOutDateTime) { this.signOutDateTime = signOutDateTime; }
	public String getSignOutIpAddress() { return signOutIpAddress; }
	public void setSignOutIpAddress(String signOutIpAddress) { this.signOutIpAddress = signOutIpAddress; }

	public List<OperatorSubSystemRoleDto> getOperatorSubSystemRoleList() { return operatorSubSystemRoleList; }
	public void setOperatorSubSystemRoleList(List<OperatorSubSystemRoleDto> operatorSubSystemRoleList) { this.operatorSubSystemRoleList = operatorSubSystemRoleList; }
	public List<OperatorBizTranRoleDto> getOperatorBizTranRoleList() { return operatorBizTranRoleList; }
	public void setOperatorBizTranRoleList(List<OperatorBizTranRoleDto> operatorBizTranRoleList) { this.operatorBizTranRoleList = operatorBizTranRoleList; }

	public OperatorDto() {
		this.operatorId = 0;
		this.operatorCode = "";
		this.operatorName = "";
		this.mailAddress = "";
		this.expirationStartDate = null;
		this.expirationEndDate = null;
		this.isDeviceAuth = false;
		this.jaId = 0;
		this.jaCode = "";
		this.tempoId = 0;
		this.tempoCode = "";
		this.availableStatus = 0;
		this.createdBy = 0;
		this.createdAt = null;
		this.createdIpAddress = "";
		this.updatedBy = null;
		this.updatedAt = null;
		this.updatedIpAddress = "";
		this.recordVersion = 0;
		this.tempoName = "";
		this.accountLockId = 0;
		this.occurredDateTime = null;
		this.lockStatus = 0;
		this.passwordHistoryId = null;
		this.changeDateTime = null;
		this.changeType = null;
		this.signInTraceId = null;
		this.tryDateTime = null;
		this.tryIpAddress = "";
		this.signInCause = null;
		this.signInResult = null;
		this.signOutTraceId = null;
		this.operatorSubSystemRoleList = newArrayList();
		this.operatorBizTranRoleList = newArrayList();
	}
}
