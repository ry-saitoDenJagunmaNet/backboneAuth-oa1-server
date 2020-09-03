package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorEntryRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * オペレーター登録サービス Validator
 */
class EntryOperatorValidator {

	private final OperatorEntryRequest request;

	/**
	 * コンストラクタ
	 */
	EntryOperatorValidator(OperatorEntryRequest request) {
		this.request = request;
	}

	public static EntryOperatorValidator with(OperatorEntryRequest request) {
		return new EntryOperatorValidator(request);
	}

	/**
	 * リクエストの検証を行います。
	 */
	public void validate() {

		// 未入力チェック
		Preconditions.checkNotNull(request.getOperatorCode6(), () -> new GunmaRuntimeException("EOA10000", "オペレーター"));
		Preconditions.checkNotNull(request.getOperatorName(), () -> new GunmaRuntimeException("EOA10000", "オペレーター"));
		Preconditions.checkNotNull(request.getMailAddress(), () -> new GunmaRuntimeException("EOA10000", "オペレーター"));
		Preconditions.checkNotNull(request.getExpirationStartDate(), () -> new GunmaRuntimeException("EOA10000", "オペレーター"));
		Preconditions.checkNotNull(request.getExpirationEndDate(), () -> new GunmaRuntimeException("EOA10000", "オペレーター"));
		Preconditions.checkNotNull(request.getTempoId(), () -> new GunmaRuntimeException("EOA10000", "オペレーター"));

		// 有効期限 大小チェック
		if (request.getExpirationStartDate().compareTo(request.getExpirationEndDate()) > 0) {
			throw new GunmaRuntimeException("EOA10000", "オペレーター");
		}
	}
}
