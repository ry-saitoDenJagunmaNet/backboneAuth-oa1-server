package net.jagunma.backbone.auth.authmanager.application.model.types;

/**
 * アカウントロックロック状態の列挙型です。
 */
public enum AccountLockLockStatus {
	UnLock((short) 0),
	Lock((short) 1);

	private final Short code;

	private AccountLockLockStatus(Short code) {
		this.code = code;
	}

	public Short getCode() {
		return code;
	}
}
