package net.jagunma.backbone.auth.authmanager.application.model.types;

/**
 * 有効期限検索条件の選択列挙型です。
 */
public enum ConditionsExpirationSelect {
	指定なし(0),
	状態指定日(1),
	条件指定(2);

	private final Integer code;

	private ConditionsExpirationSelect(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
}

