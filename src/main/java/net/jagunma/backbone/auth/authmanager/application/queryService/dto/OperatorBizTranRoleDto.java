package net.jagunma.backbone.auth.authmanager.application.queryService.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OperatorBizTranRoleDto {

	/**
	 * オペレーター_取引ロール割当ID
	 */
	private long operator_BizTranRoleId;
	/**
	 * オペレーターID
	 */
	private long operatorId;
	/**
	 * 取引ロールID
	 */
	private long bizTranRoleId;
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
	 * 取引ロールコード
	 */
	private String bizTranRoleCode;
	/**
	 * 取引ロール名
	 */
	private String bizTranRoleName;
	/**
	 * サブシステムコード
	 */
	private String subSystemCode;

	public long getOperator_BizTranRoleId() { return operator_BizTranRoleId; }
	public void setOperator_BizTranRoleId(long operator_BizTranRoleId) { this.operator_BizTranRoleId = operator_BizTranRoleId; }
	public long getOperatorId() { return operatorId; }
	public void setOperatorId(long operatorId) { this.operatorId = operatorId; }
	public long getBizTranRoleId() { return bizTranRoleId; }
	public void setBizTranRoleId(long bizTranRoleId) { this.bizTranRoleId = bizTranRoleId; }
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
	public String getBizTranRoleCode() { return bizTranRoleCode; }
	public void setBizTranRoleCode(String bizTranRoleCode) { this.bizTranRoleCode = bizTranRoleCode; }
	public String getBizTranRoleName() { return bizTranRoleName; }
	public void setBizTranRoleName(String bizTranRoleName) { this.bizTranRoleName = bizTranRoleName; }
	public String getSubSystemCode() { return subSystemCode; }
	public void setSubSystemCode(String subSystemCode) { this.subSystemCode = subSystemCode; }

	public OperatorBizTranRoleDto() {
		this.operator_BizTranRoleId = 0;
		this.operatorId = 0;
		this.bizTranRoleId = 0;
		this.expirationStartDate = null;
		this.expirationEndDate = null;
		this.createdBy = 0;
		this.createdAt = null;
		this.createdIpAddress = "";
		this.updatedBy = null;
		this.updatedAt = null;
		this.updatedIpAddress = "";
		this.recordVersion = 0;
		this.bizTranRoleCode = "";
		this.bizTranRoleName = "";
		this.subSystemCode = "";
	}
}
