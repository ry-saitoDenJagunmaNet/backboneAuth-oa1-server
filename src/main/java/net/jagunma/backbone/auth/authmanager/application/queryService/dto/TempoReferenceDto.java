package net.jagunma.backbone.auth.authmanager.application.queryService.dto;

/**
 * 店舗参照Ｄｔｏ
 */
public class TempoReferenceDto {

	// コンストラクタ
	public TempoReferenceDto() {}
	public TempoReferenceDto(String tempoCode, String tempoName) {
		this.tempoCode = tempoCode;
		this.tempoName = tempoName;
	}

	/**
	 * 店舗コード
	 */
	private String tempoCode;
	/**
	 * 店舗名
	 */
	private String tempoName;

	public String getTempoCode() { return tempoCode; }
	public void setTempoCode(String tempoCode) { this.tempoCode = tempoCode; }
	public String getTempoName() { return tempoName; }
	public void setTempoName(String tempoName) { this.tempoName = tempoName; }
}
