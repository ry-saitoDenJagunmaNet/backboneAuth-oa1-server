package net.jagunma.backbone.auth.authmanager.application.model.types;

/**
 * パスワード変更種別の列挙型です。
 */
public enum PasswordHistoryChangeType {
	初期((short) 0),
	ユーザーによる変更((short) 1),
	管理者によるリセット((short) 2),
	機器認証パスワード((short) 3);

	private final Short code;

	private PasswordHistoryChangeType(Short code) {
		this.code = code;
	}

	public Short getCode() {
		return code;
	}
}
