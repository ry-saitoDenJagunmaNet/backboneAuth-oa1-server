package net.jagunma.backbone.auth.authmanager.application.queryService.dto;

import java.util.List;

public class SubSystemRoleReferenceDto {
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
	private List<SubSystemReferenceDto> subSystemReferenceDtoList;

	public String getSubSystemRoleCode() { return subSystemRoleCode; }
	public void setSubSystemRoleCode(String subSystemRoleCode) { this.subSystemRoleCode = subSystemRoleCode; }
	public String getSubSystemRoleName() { return subSystemRoleName; }
	public void setSubSystemRoleName(String subSystemRoleName) { this.subSystemRoleName = subSystemRoleName; }
	public List<SubSystemReferenceDto> getSubSystemReferenceDtoList() { return subSystemReferenceDtoList; }
	public void setSubSystemReferenceDtoList(List<SubSystemReferenceDto> subSystemReferenceDtoList) { this.subSystemReferenceDtoList = subSystemReferenceDtoList; }
}
