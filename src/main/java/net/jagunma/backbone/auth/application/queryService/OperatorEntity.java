package net.jagunma.backbone.auth.application.queryService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OperatorEntity {
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
	 * 店舗名
	 */
	private String tempoName;
	/**
	 * 利用可否状態
	 */
	private int availableStatus;
	/**
	 * ロック状態
	 */
	private int lockStatus;
	/**
	 * サブシステムロールコード
	 */
	private String subSystemRoleCode;
	/**
	 * サブシステムロール名
	 */
	private String subSystemRoleName;
	/**
	 * オペレーター_サブシステムロール割当有効期限開始日
	 */
	private LocalDate subSystemRoleExpirationStartDate;
	/**
	 * オペレーター_サブシステムロール割当有効期限終了日
	 */
	private LocalDate subSystemRoleExpirationEndDate;
	/**
	 * 取引ロールコード
	 */
	private String bizTranRoleCode;
	/**
	 * 取引ロール名
	 */
	private String bizTranRoleName;
	/**
	 * オペレーター_取引ロール割当有効期限開始日
	 */
	private LocalDate bizTranRoleExpirationStartDate;
	/**
	 * オペレーター_取引ロール割当有効期限終了日
	 */
	private LocalDate bizTranRoleExpirationEndDate;

	public long getOperatorId() { return operatorId; }
	public void setOperatorId(long operatorId) { this.operatorId = operatorId; }
	public String getOperatorCode() { return operatorCode; }
	public void setOperatorCode(String operatorCode) { this.operatorCode = operatorCode; }
	public String getOperatorName() {return operatorName;}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public LocalDate getExpirationStartDate() {
		return expirationStartDate;
	}
	public void setExpirationStartDate(LocalDate expirationStartDate) { this.expirationStartDate = expirationStartDate; }
	public LocalDate getExpirationEndDate() {
		return expirationEndDate;
	}
	public void setExpirationEndDate(LocalDate expirationEndDate) {this.expirationEndDate = expirationEndDate; }
	public boolean getIsDeviceAuth() {
		return isDeviceAuth;
	}
	public void setIsDeviceAuth(boolean isDeviceAuth) {
		this.isDeviceAuth = isDeviceAuth;
	}
	public long getJaId() {
		return jaId;
	}
	public void setJaId(long jaId) {
		this.jaId = jaId;
	}
	public String getJaCode() {
		return jaCode;
	}
	public void setJaCode(String jaCode) {
		this.jaCode = jaCode;
	}
	public long getTempoId() {
		return tempoId;
	}
	public void setTempoId(long tempoId) {
		this.tempoId = tempoId;
	}
	public String getTempoCode() {
		return tempoCode;
	}
	public void setTempoCode(String tempoCode) {
		this.tempoCode = tempoCode;
	}
	public String getTempoName() {
		return tempoName;
	}
	public void setTempoName(String tempoName) {
		this.tempoName = tempoName;
	}
	public int getAvailableStatus() {
		return availableStatus;
	}
	public void setAvailableStatus(int availableStatus) {
		this.availableStatus = availableStatus;
	}
	public int getLockStatus() {
		return lockStatus;
	}
	public void setLockStatus(int lockStatus) {
		this.lockStatus = lockStatus;
	}
	public String getSubSystemRoleCode() {return subSystemRoleCode;}
	public void setSubSystemRoleCode(String subSystemRoleCode) {this.subSystemRoleCode = subSystemRoleCode;}
	public String getSubSystemRoleName() {return subSystemRoleName;}
	public void setSubSystemRoleName(String subSystemRoleName) {this.subSystemRoleName = subSystemRoleName;}
	public LocalDate getSubSystemRoleExpirationStartDate() {return subSystemRoleExpirationStartDate;}
	public void setSubSystemRoleExpirationStartDate(LocalDate subSystemRoleExpirationStartDate) {this.subSystemRoleExpirationStartDate = subSystemRoleExpirationStartDate;}
	public LocalDate getSubSystemRoleExpirationEndDate() {return subSystemRoleExpirationEndDate;}
	public void setSubSystemRoleExpirationEndDate(LocalDate subSystemRoleExpirationEndDate) {this.subSystemRoleExpirationEndDate = subSystemRoleExpirationEndDate;}
	public String getBizTranRoleCode() {return bizTranRoleCode;}
	public void setBizTranRoleCode(String bizTranRoleCode) {this.bizTranRoleCode = bizTranRoleCode;}
	public String getBizTranRoleName() {return bizTranRoleName;}
	public void setBizTranRoleName(String bizTranRoleName) {this.bizTranRoleName = bizTranRoleName;}
	public LocalDate getBizTranRoleExpirationStartDate() {return bizTranRoleExpirationStartDate;}
	public void setBizTranRoleExpirationStartDate(LocalDate bizTranRoleExpirationStartDate) {this.bizTranRoleExpirationStartDate = bizTranRoleExpirationStartDate;}
	public LocalDate getBizTranRoleExpirationEndDate() {return bizTranRoleExpirationEndDate;}
	public void setBizTranRoleExpirationEndDate(LocalDate bizTranRoleExpirationEndDate) {this.bizTranRoleExpirationEndDate = bizTranRoleExpirationEndDate;}
}
