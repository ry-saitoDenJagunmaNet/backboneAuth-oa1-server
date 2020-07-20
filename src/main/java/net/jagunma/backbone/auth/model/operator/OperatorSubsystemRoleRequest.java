package net.jagunma.backbone.auth.model.operator;

import java.time.LocalDate;

public class OperatorSubsystemRoleRequest {
	/**
	 * サブシステムロール選択
	 */
	private Integer subsystemRoleSelected;
	/**
	 * サブシステムロールID
	 */
	private long subsystemRoleId;
	/**
	 * サブシステムロールコード
	 */
	private String subsystemRoleCode;
	/**
	 * サブシステムロール名
	 */
	private String subsystemRoleName;
	/**
	 * 有効期限選択
	 */
	private Integer expirationSelect;
	/**
	 * 状態指定日
	 */
	private LocalDate expirationStatusDate;
	/**
	 * 条件指定日開始（開始日）
	 */
	private LocalDate expirationStartDateFrom;
	/**
	 * 条件指定日開始（終了日）
	 */
	private LocalDate expirationStartDateTo;
	/**
	 * 条件指定日終了（開始日）
	 */
	private LocalDate expirationEndDateFrom;
	/**
	 * 条件指定日終了（終了日）
	 */
	private String expirationEndDateTo;

	public Integer getSubsystemRoleSelected() { return subsystemRoleSelected; }
	public void setSubsystemRoleSelected(Integer subsystemRoleSelected) { this.subsystemRoleSelected = subsystemRoleSelected; }
	public long getSubsystemRoleId() { return subsystemRoleId; }
	public void setSubsystemRoleId(long subsystemRoleId) { this.subsystemRoleId = subsystemRoleId; }
	public String getSubsystemRoleCode() { return subsystemRoleCode; }
	public void setSubsystemRoleCode(String subsystemRoleCode) { this.subsystemRoleCode = subsystemRoleCode; }
	public String getSubsystemRoleName() { return subsystemRoleName; }
	public void setSubsystemRoleName(String subsystemRoleName) { this.subsystemRoleName = subsystemRoleName; }
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
	public String getExpirationEndDateTo() { return expirationEndDateTo; }
	public void setExpirationEndDateTo(String expirationEndDateTo) { this.expirationEndDateTo = expirationEndDateTo; }
}
