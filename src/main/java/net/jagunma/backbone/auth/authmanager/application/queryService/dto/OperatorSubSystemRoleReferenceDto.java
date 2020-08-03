package net.jagunma.backbone.auth.authmanager.application.queryService.dto;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OperatorSubSystemRoleReferenceDto {
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
	private Integer recordVersion;
//	/**
//	 * オペレーターコード
//	 */
//	private String operatorCode;
//	/**
//	 * オペレーター名
//	 */
//	private String operatorName;
	/**
	 * サブシステムロール名
	 */
	private String subSystemRoleName;
	/**
	 * サブシステムコードリスト
	 */
	private List<SubSystemReferenceDto> subSystemReferenceDtoList;

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
	public Integer getRecordVersion() { return recordVersion; }
	public void setRecordVersion(Integer recordVersion) { this.recordVersion = recordVersion; }
//	public String getOperatorCode() { return operatorCode; }
//	public void setOperatorCode(String operatorCode) { this.operatorCode = operatorCode; }
//	public String getOperatorName() { return operatorName; }
//	public void setOperatorName(String operatorName) { this.operatorName = operatorName; }
	public String getSubSystemRoleName() { return subSystemRoleName; }
	public void setSubSystemRoleName(String subSystemRoleName) { this.subSystemRoleName = subSystemRoleName; }
	public List<SubSystemReferenceDto> getSubSystemReferenceDtoList() { return subSystemReferenceDtoList; }
	public void setSubSystemReferenceDtoList(List<SubSystemReferenceDto> subSystemReferenceDtoList) { this.subSystemReferenceDtoList = subSystemReferenceDtoList; }

	/**
	 * 有効期限開始日をフォーマットして取得します。
	 * @return フォーマットした有効期限開始日
	 */
	public String getExpirationStartDateToStringFormat() { return formatLocalDate(expirationStartDate); }
	/**
	 * 有効期限終了日をフォーマットして取得します。
	 * @return フォーマットした有効期限終了日
	 */
	public String getExpirationEndDateToStringFormat() { return formatLocalDate(expirationEndDate); }

	public OperatorSubSystemRoleReferenceDto() {
		this.operator_SubSystemRoleId = 0;
		this.operatorId = 0;
		this.subSystemRoleCode = "";
		this.expirationStartDate = null;
		this.expirationEndDate = null;
		this.createdBy = 0;
		this.createdAt = null;
		this.createdIpAddress = "";
		this.updatedBy = null;
		this.updatedAt = null;
		this.updatedIpAddress = "";
		this.recordVersion = 0;
//		this.operatorCode = "";
//		this.operatorName = "";
		this.subSystemRoleName = "";
		this.subSystemReferenceDtoList = newArrayList();
	}

	/**
	 * 日付を”yyyy/MM/dd”の書式でフォ－マットします。
	 * @param localDt フォーマット対象の日付
	 * @return フォ－マットした日付
	 */
	private String formatLocalDate(LocalDate localDt) {
		if (localDt == null) {
			return "";
		}
		return localDt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
	}
}
