package net.jagunma.backbone.auth.authmanager.application.queryService.dto;

public class TempoDto {
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

	/**
	 * コンストラクタ
	 */
	public TempoDto() {}

	/**
	 * コンストラクタ
	 * @param tempoCode 店舗コード
	 * @param tempoName 店舗名
	 */
	public TempoDto(String tempoCode, String tempoName) {
		this.tempoCode = tempoCode;
		this.tempoName = tempoName;
	}

}
