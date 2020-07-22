package net.jagunma.backbone.auth.authmanager.application.queryService.dto;

import java.util.List;

public class SubSystemRoleDto {
	/**
	 * サブシステムロールコード
	 */
	private String subSystemRoleCode;
	/**
	 * サブシステムロール名
	 */
	private String subSystemRoleName;
	/**
	 * サブシステムコードリスト
	 */
	private List<SubSystemDto> subSystemCodeList;

	public String getSubSystemRoleCode() { return subSystemRoleCode; }
	public void setSubSystemRoleCode(String subSystemRoleCode) { this.subSystemRoleCode = subSystemRoleCode; }
	public String getSubSystemRoleName() { return subSystemRoleName; }
	public void setSubSystemRoleName(String subSystemRoleName) { this.subSystemRoleName = subSystemRoleName; }
	public List<SubSystemDto> getSubSystemCodeList() { return subSystemCodeList; }
	public void setSubSystemCodeList(List<SubSystemDto> subSystemCodeList) { this.subSystemCodeList = subSystemCodeList; }
}
