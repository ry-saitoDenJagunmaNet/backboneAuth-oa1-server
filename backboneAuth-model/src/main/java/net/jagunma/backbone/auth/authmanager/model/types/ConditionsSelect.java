package net.jagunma.backbone.auth.authmanager.model.types;

/**
 * 検索条件の選択列挙型
 */
public enum ConditionsSelect {
	指定なし(0),
	ＡＮＤ(1),
	ＯＲ(2);

	private final Integer code;

	private ConditionsSelect(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
}
