package net.jagunma.backbone.auth.application.queryService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Operator {
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
	private long CreatedBy;
	/**
	 * 登録日時
	 */
	private LocalDateTime CreatedAt;
	/**
	 * 登録元IPアドレス
	 */
	private String CreatedIpAddress;
	/**
	 * 更新者ID
	 */
	private Long UpdatedBy;
	/**
	 * 更新日時
	 */
	private LocalDateTime UpdatedAt;
	/**
	 * 更新元IPアドレス
	 */
	private String UpdatedIpAddress;
	/**
	 * レコードバージョン
	 */
	private int RecordVersion;

	/**
	 * 店舗名
	 */
	private String tempoName;
	/**
	 * ロック状態
	 */
	private int lockStatus;

	/**
	 * オペレーターサブシステムロール割当リスト
	 */
	private List<OperatorSubSystemRole> operatorSubSystemRoleList = new ArrayList<OperatorSubSystemRole>();
	/**
	 * オペレーター取引ロール割当リスト
	 */
	private List<OperatorBizTranRole> operatorBizTranRoleList = new ArrayList<OperatorBizTranRole>();

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
	public long getCreatedBy() { return CreatedBy; }
	public void setCreatedBy(long CreatedBy) { this.CreatedBy = CreatedBy; }
	public LocalDateTime getCreatedAt() { return CreatedAt; }
	public void setCreatedAt(LocalDateTime CreatedAt) { this.CreatedAt = CreatedAt; }
	public String getCreatedIpAddress() { return CreatedIpAddress; }
	public void setCreatedIpAddress(String CreatedIpAddress) { this.CreatedIpAddress = CreatedIpAddress; }
	public Long getUpdatedBy() { return UpdatedBy; }
	public void setUpdatedBy(Long UpdatedBy) { this.UpdatedBy = UpdatedBy; }
	public LocalDateTime getUpdatedAt() { return UpdatedAt; }
	public void setUpdatedAt(LocalDateTime UpdatedAt) { this.UpdatedAt = UpdatedAt; }
	public String getUpdatedIpAddress() { return UpdatedIpAddress; }
	public void setUpdatedIpAddress(String UpdatedIpAddress) { this.UpdatedIpAddress = UpdatedIpAddress; }
	public int getRecordVersion() { return RecordVersion; }
	public void setRecordVersion(int RecordVersion) { this.RecordVersion = RecordVersion; }

	public String getTempoName() { return tempoName; }
	public void setTempoName(String tempoName) { this.tempoName = tempoName; }
	public int getLockStatus() { return lockStatus; }
	public void setLockStatus(int lockStatus) { this.lockStatus = lockStatus; }

	public List<OperatorSubSystemRole> getOperatorSubSystemRoleList() { return operatorSubSystemRoleList; }
	public void setOperatorSubSystemRoleList(List<OperatorSubSystemRole> operatorSubSystemRoleList) { this.operatorSubSystemRoleList = operatorSubSystemRoleList; }
	public List<OperatorBizTranRole> getOperatorBizTranRoleList() { return operatorBizTranRoleList; }
	public void setOperatorBizTranRoleList(List<OperatorBizTranRole> operatorBizTranRoleList) { this.operatorBizTranRoleList = operatorBizTranRoleList; }
}
