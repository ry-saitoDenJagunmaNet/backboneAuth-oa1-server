package net.jagunma.backbone.auth.application.queryService;

import java.util.Map;

public class SubSystemRole {
	/**
	 * サブシステムロールコード
	 */
	private String SubSystemRoleCode;
	/**
	 * サブシステムロール名
	 */
	private String SubSystemRoleName;
	/**
	 * サブシステムコードリスト
	 */
	private Map<String, String> SubSystemCodeList;

	public String getSubSystemRoleCode() { return SubSystemRoleCode; }
	public void setSubSystemRoleCode(String SubSystemRoleCode) { this.SubSystemRoleCode = SubSystemRoleCode; }
	public String getSubSystemRoleName() { return SubSystemRoleName; }
	public void setSubSystemRoleName(String SubSystemRoleName) { this.SubSystemRoleName = SubSystemRoleName; }
	public Map<String, String> getSubSystemCodeList() { return SubSystemCodeList; }
	public void setSubSystemCodeList(Map<String, String> SubSystemCodeList) { this.SubSystemCodeList = SubSystemCodeList; }
}
