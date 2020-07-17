package net.jagunma.backbone.auth.application.queryService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OperatorSubSystemRole {
	/**
	 * オペレーター_サブシステムロール割当ID
	 */
	private long operator_SubSystemRoleId;
	/**
	 * オペレーターID
	 */
	private long operatorId;
	/**
	 * サブシステムロールコード
	 */
	private String subSystemRoleCode;
	/**
	 * 有効期限開始日
	 */
	private LocalDate expirationStartDate;
	/**
	 * 有効期限終了日
	 */
	private LocalDate expirationEndDate;
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
	 * オペレーターコード
	 */
	private String operatorCode;
	/**
	 * オペレーター名
	 */
	private String operatorName;
	/**
	 * サブシステムロール名
	 */
	private String SubSystemRoleName;
	/**
	 * サブシステムコードリスト
	 */
	private List<String> SubSystemCodeList;

	public long getOperator_SubSystemRoleId() { return operator_SubSystemRoleId; }
	public void setOperator_SubSystemRoleId(long operator_SubSystemRoleId) { this.operator_SubSystemRoleId = operator_SubSystemRoleId; }
	public long getOperatorId() { return operatorId; }
	public void setOperatorId(long operatorId) { this.operatorId = operatorId; }
	public String getSubSystemRoleCode() { return subSystemRoleCode; }
	public void setSubSystemRoleCode(String subSystemRoleCode) { this.subSystemRoleCode = subSystemRoleCode; }
	public LocalDate getExpirationStartDate() { return expirationStartDate; }
	public void setExpirationStartDate(LocalDate expirationStartDate) { this.expirationStartDate = expirationStartDate; }
	public LocalDate getExpirationEndDate() { return expirationEndDate; }
	public void setExpirationEndDate(LocalDate expirationEndDate) { this.expirationEndDate = expirationEndDate; }
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
	public String getOperatorCode() { return operatorCode; }
	public void setOperatorCode(String operatorCode) { this.operatorCode = operatorCode; }
	public String getOperatorName() { return operatorName; }
	public void setOperatorName(String operatorName) { this.operatorName = operatorName; }
	public String getSubSystemRoleName() { return SubSystemRoleName; }
	public void setSubSystemRoleName(String SubSystemRoleName) { this.SubSystemRoleName = SubSystemRoleName; }
	public List<String> getSubSystemCodeList() { return SubSystemCodeList; }
	public void setSubSystemCodeList(List<String> SubSystemCodeList) { this.SubSystemCodeList = SubSystemCodeList; }
}
