package net.jagunma.backbone.auth.authmanager.application.queryService.dto;

/**
 * サブシステム参照Ｄｔｏ
 */
public class SubSystemReferenceDto {

	/**
	 * コンストラクタ
	 */
	public SubSystemReferenceDto() {}

	/**
	 * コンストラクタ
	 * @param subSystemCode サブシステムコード
	 * @param subSystemName サブシステム名
	 */
	public SubSystemReferenceDto(String subSystemCode, String subSystemName) {
		this.subSystemCode = subSystemCode;
		this.subSystemName = subSystemName;
	}

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
}
