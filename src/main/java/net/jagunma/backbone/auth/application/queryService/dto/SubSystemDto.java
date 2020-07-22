package net.jagunma.backbone.auth.application.queryService.dto;

public class SubSystemDto {
	/**
	 * サブシステムコード
	 */
	private String subSystemCode;
	/**
	 * サブシステム名
	 */
	private String subSystemName;

	public String getSubSystemCode() { return subSystemCode; }
	public void setSubSystemCode(String subSystemCode) { this.subSystemCode = subSystemCode; }
	public String getSubSystemName() { return subSystemName; }
	public void setSubSystemName(String subSystemName) { this.subSystemName = subSystemName; }

	/**
	 * コンストラクタ
	 */
	public SubSystemDto() {}

	/**
	 * コンストラクタ
	 * @param subSystemCode サブシステムコード
	 * @param subSystemName サブシステム名
	 */
	public SubSystemDto(String subSystemCode, String subSystemName) {
		this.subSystemCode = subSystemCode;
		this.subSystemName = subSystemName;
	}
}
